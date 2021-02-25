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

}
