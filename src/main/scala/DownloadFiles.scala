import ReadingFiles.and_text

object DownloadFiles extends App {
  val url = "https://www.gutenberg.org/ebooks/25880.txt.utf-8"

  import scala.io.Source
  val txtBuffer = Source.fromURL(url) //so network resources is used here, could take a while for request for big file

  //we could have goten just a string without splitting lines by
  //val s = txtBuffer.mkString

  val lines = txtBuffer.getLines.toArray //so we will exhaust our buffer here
  println(txtBuffer.size) //notice this was 0 after we called getLines


  //lets see what the early lines show
  lines.slice(0,20).foreach(println)
  val txt = lines.mkString("\n")

  val relative_save_path = "src/resources/poetry_1922.txt"


  def saveLines(lines: Array[String], destPath: String, sep: String = "\n"): Unit = {
    val txt = lines.mkString(sep)

    import java.io.{PrintWriter, File} //explicit import
    //import java.io._ //this was wildcard import meaning we got all of java.io library which we might not need
    val pw = new PrintWriter(new File(destPath ))
    pw.write(txt)
    pw.close()
  }

  saveLines(lines, relative_save_path) //let's try with default "\n" separator
}
