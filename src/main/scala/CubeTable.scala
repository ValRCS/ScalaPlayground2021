import scala.io.StdIn.readLine

object CubeTable extends App {
  //TODO write a program that takes user input for starting and end values
  //then saves cubes of those values in a Seq data type and then prints the cubes out
  val begVal = readLine("What is the starting value?").toInt
  val endVal = readLine("And what is the ending value?").toInt
  println(s"Will print cubes for $begVal to $endVal ")
//  val intSeq = (begVal to endVal).toVector //if you need the intermediate results
  val intSeq = begVal to endVal //in this case we could just get by with  range which would more efficient because it is on demand
//  val cubeSeq = intSeq.map(el => el*el*el)
//  val cubeSeq = intSeq.map(el => s"$el cube = ${math.pow(el,3)}")
//  val cubeSeq = for (n <- begVal to endVal) yield s"$n cube = ${math.pow(n,3)}"
  val cubeSeq = for (n <- begVal to endVal) yield (n, n*n*n)
  println(cubeSeq)
  cubeSeq.foreach(myTuple => println(s"${myTuple._1} cubed is ${myTuple._2}")) //so Tuple indexing is 1 based
  val cubeSeqSeq = for (n <- begVal to endVal) yield Seq(n, n*n*n) //so i will get a Sequence of Sequence (2d Matrix)
  cubeSeqSeq.foreach(myInnerSeq => println(s"${myInnerSeq(0)} cubed is ${myInnerSeq(1)}"))
  cubeSeqSeq.foreach(myInnerSeq => println(s"${myInnerSeq.head} cubed is ${myInnerSeq.last}"))
//  val cubeVal = allVal.filter(el => (math.pow(el,(1.0/3.0)) % 1) == 0)
//  val cubeVal = allVal.filter(el => (math.pow(el,(1/3.0)) == math.pow(el,(1/3.0)).round)) //we lose precision so check fails
  //so you would need to check for difference and if it is less than 0.000000001 then it is indeed a cube
//  println(cubeVal)

}
