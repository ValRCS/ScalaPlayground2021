object ParsingPersonCSV extends App {
  val relativePath = "src/resources/us-500.csv"
  val lines = Utilities.getLinesFromFile(relativePath)

  println(s"Got ${lines.length} lines")
//  lines.slice(0,10).foreach(println)

  //  val cols = lines.map(line => line.split(","))
  //we split each line into tokens by , separator an then trim whitespace from each token
  val cols = lines.map(line => line.split(",").map(_.trim))

  //TODO well get minimum number or entries and maximum number of entries in those cols
  //WE want to test if parsing went correctly meaning min and max should be equal == 12
  // a little sanity check before we proceed any further
}
