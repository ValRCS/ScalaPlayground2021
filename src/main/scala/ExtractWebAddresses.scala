import java.io.FileWriter

object ExtractWebAddresses extends App {

  val poetry_path = "src/resources/poetry_1922.txt"

  val lines = Utilities.getLinesFromFile(poetry_path)

  println(s"We got ${lines.length} to filter through")
  //TODO Extract all lines with web addresses
  //TODO only get the addresses without the extra text
  //TODO save them in a file

  //TODO bonus find also the line index where the web address was found
//  val webAddresses = lines.filter(line => (line.contains("www") || line.contains("http")))
//  //webAddresses.foreach(println)
//
//  //TODO only get the addresses without the extra text
//  val justSites = for (line <- webAddresses)
//    yield line.split(" ").filter(word => word.contains(".")).mkString(" ")
//  //so we split filter and then join the survivors back together, because we could have multiple matches in our filter
//
//  justSites.foreach(println)
//  //TODO save them in a file
//
//  //add new line character to each string for printing purposes
//
////  val outputfile = "./src/websites.txt"
////  val output = new FileWriter(outputfile ,true)
////  webAddresses.map(_.concat("\n")).foreach(output.write)
////  output.close()
//
//  //TODO bonus find also the line index where the web address was found
//  //webAddresses.foreach(println(s"${Utilities.findNeedle(lines, _)} _"))
//  for (address <- webAddresses) {
//    println(s"${Utilities.findNeedle(lines, address)} --> $address")
//  }

  val patternRegex = """\S*(http|www).*\.\S+"""  //does not remove () around website... could not figure that out
  val websiteLines = lines.filter(line => line.matches(".*(http|www).*")) //saying some text which has http or www in middle
  websiteLines.foreach(println)
  val websitePattern = patternRegex.r
//  val websiteMatches = websitePattern.findAllMatchIn(websiteLines(2)).toArray
  val allWebsites = websitePattern.findAllIn(websiteLines.mkString("\n")).toArray
  allWebsites.foreach(println)

//  val webAddressRegex = """.*(www.+\w+.\w+).*"""
//  val addressLines = lines.filter(line => line.matches(webAddressRegex))
//  addressLines.foreach(println)
//  val addressPattern = """(www.\w+.\w+)""".r
//  println("** All web addresses** ")
//  val allURLsMatch = addressPattern.findAllMatchIn(addressLines.mkString("\n")).toArray
//  val allURLs = for (myMatch <- allURLsMatch) yield myMatch.toString()

  val webSiteLengths = allWebsites.map(_.length) //so we map each string to its length
  println(s"Found ${webSiteLengths.length} websites, together they take up ${webSiteLengths.sum} characters")

}
