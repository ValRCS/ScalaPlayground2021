import scala.io.Source

object Utilities {
  //not going to run it as such just use it for storing Utility functions/methods used in other objects / classes
  //I am using Object to store these because I do not need multiple copies
  def saveUrlToFile(url:String, folder:String = "./src/resources/"):Unit = {
    //TODO make a function which loads resource from url and
    // gets the last part of url and uses that to save into folder
    //you can use example from DownloadFiles
    //only challenge is to exract last part from the url and add it to folder
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
}
