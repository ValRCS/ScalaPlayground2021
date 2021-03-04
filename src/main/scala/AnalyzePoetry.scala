import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object AnalyzePoetry extends App {

  //with extends App we get access to command line arguments
  println("Command line arguments")
  args.foreach(println)

  val default_path = "src/resources/two_roads.txt"

  val real_path = if (args.length > 0 ) args(0) else default_path

  println(s"Will open files from $real_path")



  val lines = Utilities.getLinesFromFile(real_path)
  println(s"We have ${lines.length} lines in our $real_path file")

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


  val startLine = Utilities.findNeedle(lines, needle)
  val endLine = Utilities.findNeedle(lines, "bibliography")
  println(s"We will start at line: $startLine and end at $endLine")

  val noEmptyLines = removeEmptyLines(lines.slice(startLine,endLine))
  noEmptyLines.foreach(println)
  val noSubTitles = noEmptyLines.filter(line => !line.trim.startsWith("_"))
  println("*"*40)
  noSubTitles.foreach(println)


  def extractUnderscoreText(s: String):String = s match {
    case s"${head}_${target}_$tail" => s"HEAD: $head, TARGET: $target, TAIL: $tail" //we  need curly bracers to indicate that _ is not part of variable
    case _ => "no match" //default one
  }

  val underscoreLines = lines.filter(line => extractUnderscoreText(line) != "no match")
  underscoreLines.foreach(println)

  //how about transforming data first? and filter later?

  val transformedText = lines.map(extractUnderscoreText) //shortest
//  val transformedText = lines.map(extractUnderscoreText(_))
//  val transformedText = lines.map(line => extractUnderscoreText(line)) //full syntax

//  transformedText.foreach(println)
  val transformedFilteredText = transformedText.filter(_ != "no match")
  transformedFilteredText.foreach(println)

  def getAuthorsTitles(lines: Array[String]):Unit = {
    var emptyCount = 0
    val authorCount = 3
    val titleCount = 4
    //idea we count empty lines and reset counter upon first non empty
    //if this non empty line has uppercase we can decide on whether that is author or title
    for (line <- lines) {
      if (line.trim.length == 0) emptyCount += 1
      else if (line.toUpperCase == line) {
        if (emptyCount == authorCount) println(s"AUTHOR: $line") //if we wanted to save the results we could use ArrayBuffer or such
        else if (emptyCount == titleCount) println(s"TITLE: $line")
        emptyCount = 0
      } else emptyCount = 0 //could rewrite if logic a bit to get rid of double emptyCount = 0

    }
  }

  getAuthorsTitles(lines)

  //TODO get only the poem text only use starting line 340
  //end at line 4488
  val analyzeThis = lines.slice(340,4488) //nice to see those values used as vals
  val filteredLines = analyzeThis.filter(line => line.length > 1 && line.startsWith("  ") && !line.trim.startsWith("_"))

  //run THIS line and get only poem lines with nothing else
  //filters out names, titles, roman numbers and * * *
  val noCaps_1 = filteredLines.filter(line => line.toUpperCase != line)
//  noCaps_1.foreach(println)

  //method for filtering out roman numbers and that one * * * place
  //this keeps author names and poem names in place
  val pattern = "IVX* "
  val noCaps = filteredLines.filter(line => !line.toSet.subsetOf(pattern.toSet)) //so this means that "VIX* " also would be filtered out
//  noCaps.foreach(line => println(line.trim))

  def getPoemText(lines: Array[String]): Array[String] = {
//    lines.filter(line => !line.toUpperCase == line && line.length != 0))
    lines.filter(line => !(line.toUpperCase == line || line.length == 0))
  }

  val poemTextOnly = getPoemText(lines.slice(340,4488))
  poemTextOnly.foreach(println)

  val poems = lines.slice(340,4488).filter(line => line.startsWith("  ")).map(_.trim) //so we trim each line

  val savePoems = poems.mkString("\n") //joining our array of strings back into one big string
  val relative_save_path = "src/resources/poetry_only_poems.txt"


  import java.io.{PrintWriter, File}
  val pw = new PrintWriter(new File(relative_save_path ))
  pw.write(savePoems)
  pw.close()

  val onlyPageNumbers = lines.filter(line => line.matches(""".+_\d+_""")) //using triple quotes because we do not want to escape special characters
  onlyPageNumbers.foreach(println)

  //now lets extract title and the page number exactly
  //https://alvinalexander.com/scala/how-to-extract-parts-strings-match-regular-expression-regex-scala/
//using regex101.com or similar page to experiment with matches
  val regEx = """(.+)_(\d+)_""".r //notice the .r !!
//  val poemPagesProcessed = onlyPageNumbers.map(line => regEx(title,page) = line)
  val poemPagesProcessed = for (line <- onlyPageNumbers) yield {
    val regEx(title, page) = line
    (title, page)
  }
  poemPagesProcessed.foreach(println)

  //cleanup, sadly we had to use whole myTuple instead of unpacking tuple with (title, pageNum)
  val poemPagesTrimmed = poemPagesProcessed.map(myTuple => (myTuple._1.trim, myTuple._2.trim))
  poemPagesTrimmed.foreach(println)

  val dedications = lines.filter(line => line.matches(".*\\(.+\\).*")) //so intelleji did the double escaping for us here if we do not use """
  dedications.foreach(println)

  //we could get a more universal regex for phone extraction here
  //https://stackoverflow.com/questions/16699007/regular-expression-to-match-standard-10-digit-phone-number
  val phoneNumbers = lines.filter(line => line.matches(".*\\d{3}.{0,2}\\d{3}.{0,2}\\d{4}.*"))
  println(s"Got ${phoneNumbers.length} lines with phone numbers")
  phoneNumbers.foreach(println)
  val justPhoneRegEx = """.*(.\d{3}.{0,2}\d{3}.{0,2}\d{4}).*""".r
  val phones = phoneNumbers.map(line => {
    val justPhoneRegEx(phone) = line //a bit of strange syntax but it creates the results in phone from line given your regex
    phone
  })
  phones.foreach(println)

  //good reading https://www.baeldung.com/scala/find-index-element-in-list
  //we will get line number and the actual line which interest us
  //pair is just a name to indicate a tuple with 2 values
  val frostLines = lines.zipWithIndex.filter(pair => pair._1.matches(".*FROST.*"))
  frostLines.foreach(println)
  //I could have chained the following after matches but for clarity storing the intermediate results
  val cleanFrost = frostLines.map(pair => (pair._1.trim, pair._2+1)) //so removing whitespace to text and increasing index to match page number
  cleanFrost.foreach(println)

  //this only picks up emails in form of email00@domain.tld
  val simpleEmailRegEx = """.*\b(\w+@\w+.\w+).*""" //for matching it does not need the regex type just a string


  val emailLines = lines.filter(line => line.matches(simpleEmailRegEx))
  emailLines.foreach(println)

  val emailPattern = """\w+@\w+.\w+""".r //you'd use a more complex pattern for more complex emails

  //so we can go through lines one by one her we know line 3(index 2) has the 3 emails
  val emailMatches = emailPattern.findAllMatchIn(emailLines(2)).toArray //without toArray we get Iterator(lazy on demand)
  emailMatches.foreach(println)
  //how to get out all emails
  println("**** All emails **** ")
  //simplest would be to sticky all the filters lines back before getting the emails out

  val allEmails = emailPattern.findAllIn(emailLines.mkString("\n")).toArray
  allEmails.foreach(println)

  //we can use saveLines  utility method/function from our sibling Object in the same project
  Utilities.saveLines(allEmails, "./src/resources/emails.txt")

}
