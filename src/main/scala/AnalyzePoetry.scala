import scala.collection.mutable.ArrayBuffer
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
//    val authorMap = authors.map( author => (author, ArrayBuffer(""))).toMap //in order to use toMap we need a sequence of pairs
    //so for each authoer we map this author to pair of author and an empty ArrayBuffer with Strings inside
    //then we use toMap to convert this array of above pairs to Map
    val authorMap = authors.map( author => (author, ArrayBuffer[String]())).toMap //in order to use toMap we need a sequence of pairs

    var curAuthor = ""
    for (line <- lines) {
      //go through lines
      //if we encounter author we set the curAuthor to this author
      //if we encounter title we add the title to curauthors ArrayBuffer
//      if (authors.contains(line.stripMargin)) { //this might help clean up empty spaces
      if (authors.contains(line)) {
//        println(s"Found author in $line")
        curAuthor = line //just keep in mind that we have an exact match if we did not have exact we'd need to find author
//      } else if (line.length > 2 && line(line.length-2).isDigit && line.endsWith("_") ) { //important that we check line.length first!!!
      } else if (line.length > 2 && line(line.length-2).isDigit && curAuthor != "") { //important that we check line.length first!!!
        //we got our title for
//        println(s"Author $curAuthor and title $line")
        println(s"Trying to add to author $curAuthor")
        val titles = authorMap(curAuthor) //basically shortcut to ArrayBuffer, notice it is val but we can add to its contents
        titles += line
//        authorMap(curAuthor) += line //would also work
      }
    }

    //then we need to convert from  Map[String, ArrayBuffer[String]] to  Map[String, Array[String]]
    val results = for ((author, titles) <- authorMap) yield (author, titles.toArray)

//    Map("Valdis" -> Array("3 little pigs", "it was a rainy and dark day"), "Liga" -> Array("Sunny day", "Cloudy day"))
    results
  }


  val poemTitles = getPoemTitles(linesToProcess, authors)
//  for ((key, value) <- poemTitles) {
//    println(s"Author $key ")
//    println("Poems")
//    value.foreach(println)
////    value.foreach(title => title.split(" ").foreach(println))
//    //so we split each line on whitespace of any type
//    //so we split by one space + at least some other whitespace
//    //otherwise we would split by each word
//    value.foreach(title => title.split(" \\s+").foreach(println))
//    //so now we split too much
//  }

  def removeEmptyLines (lines: Array[String]): Array[String] = {
//    val subResult = lines.filter(_.length > 1) //for single comparison check
    val subResult = lines.filter(line => line.length > 1) //for more complicated checks
    subResult
  }

  println("*"*40)

  //ideally i'd like to get rid of these hardcoded values

  val needle = "Amy Lowell" //also we do not know the case, but we know thats where we want to start

  def findNeedle(lines: Array[String], needle:String) :Int = {
    var lineNumber = -1 //our line indexes start with 0
    for ((line, index) <- lines.zipWithIndex) {
      if (lineNumber == -1 && line.toLowerCase.contains(needle.toLowerCase)) //so this way we only find first occurence
        lineNumber = index //optimization would be to return immediately when work is done
      //we could have save all the matches for particular line number
    }
    lineNumber
  }
  val startLine = findNeedle(lines, needle)
  val endLine = findNeedle(lines, "bibliography")
  println(s"We will start at line: $startLine and end at $endLine")

  val noEmptyLines = removeEmptyLines(lines.slice(startLine,endLine))
  noEmptyLines.foreach(println)
  val noSubTitles = noEmptyLines.filter(line => !line.trim.startsWith("_"))
  println("*"*40)
  noSubTitles.foreach(println)

}
