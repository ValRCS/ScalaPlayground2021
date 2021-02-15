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

  //Scala is not big on mutating but we can do it
  //generally we want to do it before finalizing this data into some immutable
  //https://alvinalexander.com/scala/how-to-append-prepend-items-vector-seq-in-scala/
  println(myMutableSeq :+ 100)
  val newMutable = myMutableSeq :+ 100
  println(newMutable)
  val newImmutable = mySeq :+ 2000 //so again I have to save it in new value
  println(newImmutable)
  newMutable.drop(6)
  println(newMutable)
  val anotherMutable = newMutable.drop(2) //so drops from beginning
  println(anotherMutable)

  val bigSeq = (1 to 10).toVector //so Range is something half ready given on Demand, Vector is something already made
  println(bigSeq)
  println(bigSeq.slice(3,6)) //so it should start with 4 (index 3 is 4th element)
  val newSlice = bigSeq.slice(2,5)
  println(newSlice)
  println(newSlice.length)
  val by1000 = newSlice.map(_ * 1000)
  println(by1000)

  //so Array comes from Java and does not have as nice the printing
  val a2 = Array("one", "two", "three", "four")
  val a3 = Array(100, "two", "three", "four") //so try to avoid Any because well it is just too generic, keep one type per sequence
  println(by1000(2))
  println(by1000.last)
  println(a2.slice(1,3).toSeq.toString)

  println(bigSeq.sum)
  println(bigSeq.length)
  println(bigSeq.sum/bigSeq.length.toDouble) //so aritmetic mean we want one of the values be Double Long so we get Double Long as result




}
