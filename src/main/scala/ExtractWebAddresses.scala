object ExtractWebAddresses extends App {

  val poetry_path = "src/resources/poetry_1922.txt"

  val lines = Utilities.getLinesFromFile(poetry_path)

  println(s"We got ${lines.length} to filter through")
  //TODO Extract all lines with web addresses
  //TODO only get the addresses without the extra text
  //TODO save them in a file

  //TODO bonus find also the line index where the web address was found

}
