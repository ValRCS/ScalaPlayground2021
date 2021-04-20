import java.io.{FileNotFoundException, FileWriter}

object TheLottery extends App {
  val fileName = "C:\\Users\\val-wd\\Downloads\\Application_Development.csv" //TODO make relative path absolute path generally to be avoid
  val destName = "c:/temp/teamsScala2021.txt"

  var myText = Seq[String]()
  try {
    val filePointer = scala.io.Source.fromFile(fileName)
    val myLines = filePointer.getLines()
    myText = myLines.toSeq
    filePointer.close()
  } catch {
    //you catch specific errors for your needs
    case ex: FileNotFoundException =>
      println(s"file not found $ex")
  }
  myText.foreach(println)
  val shuffled = scala.util.Random.shuffle(myText)
  shuffled.foreach(println)
  val teams = shuffled zip shuffled.reverse
  teams.foreach(println)

  def saveSeq(destName:String, mySeq:Seq[Any]): Unit= {
    println(s"Saving my Sequence to file $destName")
    mySeq.foreach(println) //we are good up to here
    val fw = new FileWriter(destName)
    mySeq.map(_.toString + "\n").foreach(fw.write) // adding new line to each line before writing
    fw.close()
  }

  saveSeq(destName, teams)
}