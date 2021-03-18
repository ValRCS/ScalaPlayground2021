import java.io.FileWriter

import scala.sys.process.BasicIO.close

object CookBook extends App {
  //  val srcName = "13177-8.txt"
  //  val dstName = "13177-8-results.txt"

  val url = "https://www.gutenberg.org/files/13177/13177-8.txt"
  val destination = Utilities.getFilePathFromUrl(url)
  println(destination)

  import java.nio.file.{Paths, Files}
  if (Files.exists(Paths.get(destination))) println(s"$destination already exists")
  else Utilities.saveUrlToFile(url, destination,"iso-8859-1")

  val lines = Utilities.getLinesFromFile(destination)
  println(s"Got ${lines.length} from $destination")

  //goal is to avoid the magic numbers
  val start = 1024 //better get it by find
  val end = 3045
  val filtered = lines.slice(start,end).filter(line => line.toUpperCase == line || line.startsWith("    "))
//  val noEmptyLines = filtered.filter(line => line.trim != line) //this should clean all extra whitespace line
  val noEmptyLines = filtered.filter(line => line.length > 1) //this should clean all extra whitespace line
  Utilities.saveLines(noEmptyLines, "./src/resources/recipes-test.txt")

//  Utilities.saveUrlToFile(url)
////  val srcName = "c:/temp/13177-8.txt"
////  val dstName = "c:/temp/13177-8-cleaned.txt"
//  def openSource(fName:String) = {
//    //actually get a real sequence of strings
//    val filePointer = scala.io.Source.fromFile(srcName)
//    val myLines = filePointer.getLines.toSeq
//
//    //    filePointer.close()
//    myLines
//  }
//
//  def processSeq(mySeq:Seq[String])= {
//    //TODO actually do the assignment
//    mySeq.slice(0,8) //just some lines
//    //iterative solution
//    //TODO filter by multiple lines meaning CAPITAL then possible Illustration then 4 spaces
//    val ingredients = mySeq.filter(line => line.startsWith("    ")) //TODO add titles possibly with regex
//    //TODO more filtering for Illustrations and of course false titles meaning titles which do not have ingredients following
//    ingredients
//  }
//
//  def saveSeq(destName:String, mySeq:Seq[String]) = {
//    println(s"Saving my Sequence to file $destName")
//    //    mySeq.foreach(println) //we are good up to here
//    val fw = new FileWriter(dstName)
//    mySeq.map(_ + "\n").foreach(fw.write) // adding new line to each line before writing
//    fw.close()
//  }
//
//  //the actual program runs here, little tiny pipeline like a factory
//  val mySeq = openSource(srcName)
//  val filteredSeq = processSeq(mySeq)
//  saveSeq(dstName,filteredSeq)
}