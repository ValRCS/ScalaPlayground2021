package com.github.valrcs

import scala.io.Source

//so a singleton (single instance) object of all my Utilities functions
object Utilities {
  //not going to run it as such just use it for storing Utility functions/methods used in other objects / classes
  //I am using Object to store these because I do not need multiple copies
  def getFilePathFromUrl(url:String, folder:String = "./src/resources/"):String = {
    val fName = url.split("/").last.split('.') //if 25880.txt is assumed, final split and next line not needed
    val fileName = fName(0) + "." + fName(1) //.org/ebooks/25880.txt.utf-8 third one is encoding
    //only challenge is to extract last part from the url and add it to folder
    val relative_save_path = folder + fileName
    relative_save_path
  }

  //so we only do one thing save the url contents
  def saveUrlToFile(url:String, filePath:String, encoding:String="utf-8"):Unit = {
    //TODO make a function which loads resource from url and
    // gets the last part of url and uses that to save into folder
    //you can use example from DownloadFiles
    //only challenge is to extract last part from the url and add it to folder
    println(s"Will open $url")
    //FIXME change iso to encoding
    val txtBuffer = Source.fromURL(url, encoding)
    val lines = txtBuffer.getLines.toArray //so we will exhaust our buffer here
//    println(s"Dry run for lines: ${lines.length}") //notice this was 0 after we called getLines
    Utilities.saveLines(lines,filePath)
  }

  def getLinesFromFile(srcPath: String): Array[String] = {
    val bufferedSource = Source.fromFile(srcPath)
    val lines = bufferedSource.getLines.toArray
    bufferedSource.close
    lines
  }

  def saveString(text: String, destPath: String): Unit = {
    import java.io.{PrintWriter, File} //explicit import
    //import java.io._ //this was wildcard import meaning we got all of java.io library which we might not need
    val pw = new PrintWriter(new File(destPath))
    pw.write(text)
    pw.close()
  }

  def saveLines(lines: Array[String], destPath: String, sep: String = "\n"): Unit = {
    val txt = lines.mkString(sep)

    import java.io.{PrintWriter, File} //explicit import
    //import java.io._ //this was wildcard import meaning we got all of java.io library which we might not need
    val pw = new PrintWriter(new File(destPath ))
    pw.write(txt)
    pw.close()
  }

  def findNeedle(lines: Array[String], needle:String) :Int = {
    //    var lineNumber = -1 //our line indexes start with 0
    //    for ((line, index) <- lines.zipWithIndex) {
    //      if (lineNumber == -1 && line.toLowerCase.contains(needle.toLowerCase)) //so this way we only find first occurence
    //        lineNumber = index //optimization would be to return immediately when work is done
    //      //we could have save all the matches for particular line number
    //    }
    //    lineNumber
    //much shorter way of writing the above is using indexWhere
    lines.indexWhere(line => line.toLowerCase.contains(needle.toLowerCase))
  }

  def getRandomIntSeq(count: Int = 1000, min: Int = 0, max: Int = 100_000) :Seq[Int]= {
    val r = scala.util.Random
    //TODO check if we need to add + 1
    for (_ <- 1 to count ) yield min + r.nextInt(max-min+1) //we are using _ since we do not need index here
  }

  def saveIntSeq(ints: Seq[Int], destPath: String): Unit = {
//    val lines = ints.map(_.asInstanceOf[String]).toArray //so we had to cast to Scala string each member
//    //and cast lines to array
//    saveLines(lines, destPath)
    //Above would utilize existing function we made
    val txt = ints.mkString("\n")
    import java.io.{PrintWriter, File} //explicit import
    val pw = new PrintWriter(new File(destPath ))
    pw.write(txt)
    pw.close()
  }

  //and clamp
  def clamp(value: Int, min: Int, max: Int): Int = {
    if (value < min) min
    else if (value > max) max
    else value
  }
}
