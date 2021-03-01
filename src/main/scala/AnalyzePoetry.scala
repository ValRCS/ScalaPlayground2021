import scala.io.Source

object AnalyzePoetry extends App {

  //with extends App we get access to command line arguments
  println("Command line arguments")
  args.foreach(println)

  val default_path = "src/resources/two_roads.txt"

  val real_path = if (args.size > 0 ) args(0) else default_path

  println(s"Will open files from $real_path")

  def getLinesFromFile(srcPath: String) = {
    val bufferedSource = Source.fromFile(srcPath)
    val lines = bufferedSource.getLines.toArray
    bufferedSource.close
    lines
  }

  val lines = getLinesFromFile(real_path)
  println(s"We have ${lines.size} lines in our $real_path file")

  lines.slice(340,360).foreach(println)

  def getAuthors(lines: Array[String], startLine: Int, endLine: Int): Array[String] = {
    val inLines = lines.slice(startLine, endLine)

    //idea line which contains only Upper case characters will be the author line
    //we could do this with regular expressions
    //today we will do it with just our regular tools

//    val authors = inLines.filter(line => !line.endsWith("_") or line.)
    val authors = inLines.filter(line => line.toUpperCase == line && (line.length > 0))
    authors
  }

  val authors = getAuthors(lines, 166, 334)
  authors.foreach(println)

  val linesToProcess = lines.slice(166,334)

  //TODO save a list of poems for each author with page numbers preferably
  def getPoemTitles(lines: Array[String], authors: Array[String]): Map[String, Array[String]] = {
    //so lines will be incoming text lines which we want to process in this
    //FIXME
    //TODO actually get the real poem titles :)
    //so endswiths, split, some other string functions might help here
    Map("Valdis" -> Array("3 little pigs", "it was a rainy and dark day"), "Liga" -> Array("Sunny day", "Cloudy day"))
  }


  val poemTitles = getPoemTitles(linesToProcess, authors)
  for ((key, value) <- poemTitles) {
    println(s"Author $key ")
    println("Poems")
    value.foreach(println)
  }

}
