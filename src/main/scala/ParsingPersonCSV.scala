object ParsingPersonCSV extends App {
  val relativePath = "src/resources/us-500.csv"
  val lines = Utilities.getLinesFromFile(relativePath)

  println(s"Got ${lines.length} lines")
//  lines.slice(0,10).foreach(println)

  //  val cols = lines.map(line => line.split(","))
  //we split each line into tokens by , separator an then trim whitespace from each token
//  val cols = lines.map(line => line.split("\",\"").map(_.trim))
//  val cols = lines.map(line => line.split("\\,[^ ]").map(_.trim))
  val cols = lines.map(line => line.split("\"*,*\"").map(_.trim))
  //FIXME find regex to split we actually need , for sure but " are optional!


  //TODO well get minimum number or entries and maximum number of entries in those cols
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

  cols.slice(0,10).foreach(line => println(line.mkString(" | ")))

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


}
