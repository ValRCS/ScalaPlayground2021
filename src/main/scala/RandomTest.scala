object RandomTest extends App {
  //full testing has their own folder and helper library
  
  val myNumbers = Utilities.getRandomIntSeq()
  println(myNumbers.max)
  println(myNumbers.min)
  println(myNumbers.length)

  val mySmallNumbers= Utilities.getRandomIntSeq(1_000_000, 15,20)
  println(mySmallNumbers.max)
  println(mySmallNumbers.min)
  println(mySmallNumbers.length)
  println(mySmallNumbers.sum.toDouble / mySmallNumbers.length)

  Utilities.saveIntSeq(myNumbers, "./src/resources/myInts.txt")
}
