import scala.io.Source

object ReadingFiles extends App {
  println("Reading files")
  val absolute_path = "c:/Temp/sample-text-file.txt" //choose your own text file here
//  for (line <- Source.fromFile(absolute_path).getLines) {
//    println(line)
//  }
//  val lines = Source.fromFile(absolute_path).getLines.toArray
//  lines.foreach(println)
  //better get a reference to our file buffer(stream) and then close it properly when done
  val bufferedSource = Source.fromFile(absolute_path)
  val lines = bufferedSource.getLines.toArray
  bufferedSource.close //now file is properly closed (assuming no exceptions)

  //now we can work with file contents
  lines.foreach(println)
  //we can split each line into individual words, in this case by whitespace
  val lines_tokenized = lines.map(line => line.split(" "))
  lines_tokenized.foreach(line => {line.foreach(println); println("*"*20)})


  //let's check our current working directory because we need to know to have correct relative path

  println(System.getProperty("user.dir"))

  //so relative is relative to where our current working directory is
  val relative_path = "src/resources/two_roads.txt"

  def getLinesFromFile(srcPath: String) = {
    val bufferedSource = Source.fromFile(srcPath)
    val lines = bufferedSource.getLines.toArray
    bufferedSource.close
    lines
  }
  val poem_lines = getLinesFromFile(relative_path)
  poem_lines.foreach(println)

  val and_lines = poem_lines.filter(_.startsWith("And"))
//  val and_lines = poem_lines.filter(line => line.startsWith("And")) //longer syntax
  and_lines.foreach(println)
  val and_text = and_lines.mkString("\n") //one big string with newlines in between

  val relative_save_path = "src/resources/two_roads_cleaned.txt"
  // PrintWriter
  import java.io.{PrintWriter, File} //explicit import
  //import java.io._ //this was wildcard import meaning we got all of java.io library which we might not need
  val pw = new PrintWriter(new File(relative_save_path ))
  pw.write(and_text)
  pw.close() //when writing it is especially important to close as early as possible

}
