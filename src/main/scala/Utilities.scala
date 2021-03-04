import scala.io.Source

object Utilities {
  //not going to run it as such just use it for storing Utility functions/methods used in other objects / classes
  //I am using Object to store these because I do not need multiple copies
  def getLinesFromFile(srcPath: String): Array[String] = {
    val bufferedSource = Source.fromFile(srcPath)
    val lines = bufferedSource.getLines.toArray
    bufferedSource.close
    lines
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
}
