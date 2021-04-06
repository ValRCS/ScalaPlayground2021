import com.github.valrcs.Utilities

object RandomTest extends App {
  //full testing has their own folder and helper library
  
  val myNumbers = Utilities.getRandomIntSeq()
  println(myNumbers.max)
  println(myNumbers.min)
  println(myNumbers.length)

  val mySmallNumbers= Utilities.getRandomIntSeq(100_000, 0,1_000_000)
  println(mySmallNumbers.max)
  println(mySmallNumbers.min)
  println(mySmallNumbers.length)
  println(s"Regular signed integer max is: ${Int.MaxValue}")
  println(s"Regular LOOOONG integer max is: ${Long.MaxValue}")
  println(mySmallNumbers.map(_.toLong).sum.toDouble / mySmallNumbers.length)

//  Utilities.saveIntSeq(myNumbers, "./src/resources/myInts.txt")
  Utilities.saveIntSeq(mySmallNumbers, "./src/resources/my100k.txt")
}
