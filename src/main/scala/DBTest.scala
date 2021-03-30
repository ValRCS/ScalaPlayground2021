object DBTest extends App {
  println("Testing DB Connection!")
  val url = "jdbc:sqlite:C:/sqlite/db/chinook.db" //you need to adjust your absolute url here
//  val url = "jdbc:sqlite:C:/sqlite/db/NOTchinook.db" //so this created blank NOTchinnok.db if we did not have it

  import java.sql.DriverManager

  val conn = DriverManager.getConnection(url)
  //for SQL dbs with users and permission and ports and external servers you will also need to add some extra parameters

  val statement = conn.createStatement()
  val sql =
    """
      |SELECT * FROM genres
      |""".stripMargin

  val resultSet = statement.executeQuery(sql)
  val meta = resultSet.getMetaData
  println(s"We have ${meta.getColumnCount} columns")



  //the connection will be closed here after our app finishes running
  //sometimes you might want to close our connection earlier
}