object ParsingPersonCSV extends App {
  val relativePath = "src/resources/us-500.csv"
  val lines = Utilities.getLinesFromFile(relativePath)

  println(s"Got ${lines.length} lines")
//  lines.slice(0,10).foreach(println)

  //  val cols = lines.map(line => line.split(","))
  //we split each line into tokens by , separator an then trim whitespace from each token
  val cols = lines.map(line => line.split(",").map(_.trim))
}
