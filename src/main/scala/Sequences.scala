import scala.collection.mutable

object Sequences extends App {
  val mySeq = Seq(1,2,4,5) //generally you want this
  val myMutableSeq = mutable.Seq(1,2,6,4) //or use this when you need to adjust contents
  //so the below two really should not be needed
  var mySeqThatCanChangedToSomethingElse = Seq(1,2,6,7)
  var mutableAndChangableToSomethingElse = mutable.Seq(1,4,6,7,9)
  mySeqThatCanChangedToSomethingElse = Seq(5,5,5,2,5) //so var lets us reassing to new values

  println(mySeq)
  println(myMutableSeq) //so ArrayBuffer generally is meant for building up some Sequence for converting to immutable sequence later
  println(mySeq.reverse)
  println(mySeq.mkString("*_*"))
  println(mySeq.tail) //everything without first element
  println(mySeq.head)
  println(mySeq.contains(9000))
  println(mySeq.contains(4))
  println(mySeq.indexOf(5))
  println(mySeq.indexOf(555)) //so -1 on not found in Sequence
  println(mySeq.map(el => el+10)) //so we write a function on how to transform each element in our sequence
  val mappedSeq = mySeq.map(_ * 100) //so shorthand for map(el => el* 100)
  println(mappedSeq)
  //so map is one of the ways we work with immutable data, we just transform to another data
  //one of the advantages of this approach is that it works also on parallel machines(meaning clusters of machines)
  val filteredSeq = mySeq.filter(el => el > 3)
  println(filteredSeq)
  mySeq.foreach(el => println(el+20)) //so this will just pring each element with 20 added



}
