import scala.collection.mutable.ListBuffer

object DBTest extends App {
  println("Testing DB Connection!")
//  val url = "jdbc:sqlite:C:/sqlite/db/chinook.db" //you need to adjust your absolute url here
//  val url = "jdbc:sqlite:C:/sqlite/db/NOTchinook.db" //so this created blank NOTchinnok.db if we did not have it
  //better would be to use a relative path (relative here to our main project folder)
  val url = "jdbc:sqlite:./src/resources/db/chinook.db" //notice the . in ./src



  import java.sql.DriverManager

  val conn = DriverManager.getConnection(url)
  //for SQL dbs with users and permission and ports and external servers you will also need to add some extra parameters
  val statement = conn.createStatement() //Creates a Statement object for sending SQL statements to the database. S

  val tableName = "genres"
  val sql =
    s"""
      |SELECT * FROM $tableName
      |""".stripMargin
//FIXME very careful with adding strings directly to our sql queries
  //if you do not control tableName you run a risk of SQL injection

  val resultSet = statement.executeQuery(sql)
  val meta = resultSet.getMetaData
  println(s"We have ${meta.getColumnCount} columns")

  var colSeq = ListBuffer[String]() //buffer type collections are ideal when we are adding to some results
  for (i <- 1 to
    meta.getColumnCount) {
    println(meta.getColumnName(i)) //notice we are using 1 based well columns are 1 based index
    colSeq += meta.getColumnName(i)
  }
  println(colSeq)

  //alternative using yield
  val colNames = for (i <- 1 to
    meta.getColumnCount) yield {
//    println(meta.getColumnName(i)) //notice we are using 1 based well columns are 1 based index
    meta.getColumnName(i)
  }
  colNames.foreach(println)

//  val resultsBuffer = scala.collection.mutable.ListBuffer.empty[Seq[(String,String)]]
  val genreBuffer = scala.collection.mutable.ListBuffer.empty[Genres]
  while ( resultSet.next() ) {

//    println(resultSet.getString("Name")) //we do not want to depend on hardcoded column names, lets use our metadata
//    colSeq.foreach(col => print(col, resultSet.getString(col)))
//    println()
//    val row = colSeq.map(col => (col, resultSet.getString(col))) //TODO think of other structures to hold data
//    resultsBuffer.append(row.toSeq)
//    val genre = Genres(resultSet.getString(1).toInt, resultSet.getString(2))
    //we can also give the names of our columns
    val genre = Genres(resultSet.getString("GenreId").toInt, resultSet.getString("Name"))
    genreBuffer.append(genre)
//    println(row.size)
  }
//  val results = resultsBuffer.toSeq
//  println(results.size) //how many entries we have in our database
//
//  val filteredResults = results.filter(row => row(1)._2.startsWith("R")) //so 2nd columnd 2nd value of that columns tuple
//  filteredResults.foreach(println)

  val genreResults = genreBuffer.toSeq
  val filteredGenres = genreResults.filter(row => row.Name.startsWith("R"))
  filteredGenres.foreach(println)

  //the connection will be closed here after our app finishes running
  //sometimes you might want to close our connection earlier

  //create new sql query etc
  val tableName2 = "artists"

  val sql2 =
    s"""
       |SELECT * FROM $tableName2
       |""".stripMargin
  //FIXME very careful with adding strings directly to our sql queries
  //if you do not control tableName you run a risk of SQL injection

  val resultSet2 = statement.executeQuery(sql2)

  val artistBuffer = scala.collection.mutable.ListBuffer.empty[Artists]
  while ( resultSet.next() ) {
    val artist = Artists(resultSet2.getString("ArtistId").toInt, resultSet2.getString("Name"))
    artistBuffer.append(artist)
    //    println(row.size)
  }
  val artistResults = artistBuffer.toSeq
  val filteredArtists = artistResults.filter(row => row.Name.startsWith("A")) //we can use more powerful regex here than SQL
  filteredArtists.sortBy(artist => artist.Name).slice(0,10).foreach(println) // so this was Scala way
  //of achieving WHERE name LIKE "A%" //but Scala is more precise it only asks for capital A
  //also we are using ORDER BY name ASC(default), and LIMIT 10
  //create new sql query etc

  //Bonus find Artists starting with letter "A"
  // you can do it two ways you could also use SQL here which is fine

  //
  val sql3 =
    """
      |SELECT * from Artists
      |WHERE name LIKE "A%"
      |ORDER BY name
      |LIMIT 10
      |""".stripMargin

  val resultSet3 = statement.executeQuery(sql3)
  val artistFilteredBuffer = scala.collection.mutable.ListBuffer.empty[Artists]

  while ( resultSet.next() ) {
    val artist = Artists(resultSet2.getString("ArtistId").toInt, resultSet2.getString("Name"))
    artistFilteredBuffer.append(artist)
    //    println(row.size)
  }
  println("*"*40)
  val artistSQLFilterResults = artistFilteredBuffer.toSeq
  artistSQLFilterResults.foreach(println)
}