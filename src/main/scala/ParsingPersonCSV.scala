import java.sql.{Connection, PreparedStatement}

object ParsingPersonCSV extends App {
  val relativePath = "src/resources/us-500.csv"
  val lines = Utilities.getLinesFromFile(relativePath)

  println(s"Got ${lines.length} lines")
//  lines.slice(0,10).foreach(println)

  //early attempts which did no work
  //because we have some , inside our quotes also our zips are inconsistently marked with " or not
  //  val cols = lines.map(line => line.split(","))
  //we split each line into tokens by , separator an then trim whitespace from each token
//  val cols = lines.map(line => line.split("\",\"").map(_.trim))
//  val cols = lines.map(line => line.split("\\,[^ ]").map(_.trim))
//  val cols = lines.map(line => line.split("\"*,*\"").map(_.trim))

  //next approaches work
  val cols = lines.map(line => line.split("\",\"|\",|,\"").map(_.trim)) //so regex is eager meaning alternate order matter here
//val temp = lines.map(_.replace(", ","Ž")).map(_.split(","))
//  val cols = temp.map(tokens => tokens.map(_.replace("Ž", ", "))) //this Lauras approach also works
  val cleanCols = cols.map(line => line.map(_.stripPrefix("\"").stripSuffix("\"")))




  //WE want to test if parsing went correctly meaning min and max should be equal == 12
  // a little sanity check before we proceed any further
//  val tokenCounts = cols.map(tokens => tokens.length)
//  val tokenCounts = cols.map(_.length) //shorter syntax but same as above
  val tokenCounts = for {line <- cols} yield line.length //same thing but with yield which is much more flexible
  println(s"We have minimum of ${tokenCounts.min} items in each line and max of ${tokenCounts.max} items")

  val strangeLength = tokenCounts.max
  //TODO let's find those offending lines with 13 items!
  //time to do some filtering or yielding
  val longs = cols.filter(_.length > 12)
//  longs.foreach(line => println(line.mkString(" , ")))
  println(s"Misparsed long lines by hand ${longs.length}")
  val shorts = cols.filter(_.length < 12)
//  shorts.foreach(line => println(line.mkString(" | ")))
  println(s"Misparsed short lines by hand ${shorts.length}")

  cleanCols.slice(0,10).foreach(line => println(line.mkString(" | ")))

  //type alias so we do not have to type everything at once
  type PersonType = (String, String, String, String, String, String,String, Int, String, String, String, String)

  //  val rawData: java.net.URL = getClass.getResource("/wikipedia.csv")
  //http://nrinaudo.github.io/kantan.csv/rows_as_tuples.html
//  val rawData: java.net.URL = getClass.getResource(relativePath)
//
//  import kantan.csv._
//  import kantan.csv.ops._
//
////  val reader = rawData.asCsvReader[PersonType](rfc.withHeader) //rfc comes from kantan.csv._
//  val reader = rawData.asUnsafeCsvReader[PersonType](rfc.withHeader) //rfc comes from kantan.csv._
//
////  val klines = for (line <- reader) yield line
//  val tupleLines = reader.toSeq
//  tupleLines.slice(1,10).foreach(println)


  def convertToUsPerson(tokens: Seq[String]):USPerson = {
    USPerson(
      tokens(0),
      tokens(1),
      tokens(2),
      tokens(3),
      tokens(4),
      tokens(5),
      tokens(6),
      tokens(7).toInt,
      tokens(8),
      tokens(9),
      tokens(10),
      tokens(11)
    )

  }

  //we do not need header since we already coded it
  val usPersons = cleanCols.tail.map(convertToUsPerson(_))

  usPersons.slice(0,5).foreach(println)

//DB section TODO move to separate section or file
  val url = "jdbc:sqlite:./src/resources/db/us.db" //notice the . in ./src



  import java.sql.DriverManager

  val conn = DriverManager.getConnection(url)
  println(conn.getClientInfo()) //not too useful on SQLite but useful for something like SQL Server
  //for SQL dbs with users and permission and ports and external servers you will also need to add some extra parameters
  val statement = conn.createStatement() //Creates a Statement object for sending SQL statements to the database. S

  def migratePersonsTable(conn:Connection) = {
    println("Migrating table for Persons")
    val statement = conn.createStatement() //Creates a Statement object for sending SQL statements to the database. S

//"first_name","last_name","company_name","address","city","county","state","zip","phone1","phone2","email","web"
    val sql =
      """
        |DROP TABLE IF EXISTS persons;
        |CREATE TABLE IF NOT EXISTS persons (
        | first_name TEXT NOT NULL,
        | last_name TEXT NOT NULL,
        | company_name TEXT NOT NULL,
        | address TEXT NOT NULL,
        | city TEXT NOT NULL,
        | county TEXT NOT NULL,
        | state TEXT NOT NULL,
        | zip INTEGER NOT NULL,
        | phone1 TEXT NOT NULL,
        | phone2 TEXT NOT NULL,
        | email TEXT NOT NULL,
        | web TEXT NOT NULL
        |);
        |""".stripMargin

    statement.executeUpdate(sql)
  }

  migratePersonsTable(conn)

  //we need to use prepared statements to avoid BOBBY DROP TABLES problem
  //"first_name","last_name","company_name","address","city","county","state","zip","phone1","phone2","email","web"
  def insertPerson(conn: Connection, person: USPerson):Unit = {
    println(s"Inserting Person $person")
    val insertSql = """
                      |INSERT INTO persons(
                      |    first_name,
                      |    last_name,
                      |    company_name,
                      |    address,
                      |    city,
                      |    county,
                      |    state,
                      |    zip,
                      |    phone1,
                      |    phone2,
                      |    email,
                      |    web)
                      |VALUES (?,?,?,?,?,?,
                      |        ?,?,?,?,?,?);
""".stripMargin

    val preparedStmt: PreparedStatement = conn.prepareStatement(insertSql)
//sql 1 indexed here
    preparedStmt.setString (1, person.Name)
    preparedStmt.setString (2, person.LastName)
    preparedStmt.setString (3, person.Company)
    preparedStmt.setString (4, person.Address)
    preparedStmt.setString (5, person.City)
    preparedStmt.setString (6, person.Country)
    preparedStmt.setString (7, person.State)
    preparedStmt.setInt    (8, person.Zip)
    preparedStmt.setString (9, person.Phone1)
    preparedStmt.setString (10, person.Phone2)
    preparedStmt.setString (11, person.Email)
    preparedStmt.setString (12, person.Web)
    preparedStmt.execute
    //FIXME better performance to batch insert
    preparedStmt.close()
  }

  usPersons.foreach(insertPerson(conn, _))
}
