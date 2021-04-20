import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn.readLine
// import package
import scala.util.control.Breaks._


object FindingPrimes extends App {
//  val howMany = 10
  val howMany = readLine("How many primes to find?").toInt
  val hardNumberLimit = 1_000_000
//  val primes = for (n <- 1 to howMany) yield {
//    while
//  }
  val primes = ArrayBuffer(2)

  var curNumber = 3
  while (primes.length < howMany && curNumber < hardNumberLimit) {
    breakable {
      val lastCheck = Math.ceil(Math.sqrt(curNumber)).toInt
      val divisors = Seq(2) ++ (3 to lastCheck by 2)
      for (n <- divisors) { //we know the number divides by itself and also by 1
        if (curNumber % n == 0) {
          println(s"curNumber $curNumber divides evenly with $n that's not a prime")
          break()
        }//so if the current candidate divides evenly by any number under itself (not counting 1) it is not a prime
      }
      println(s"$curNumber is a prime!")
      primes += curNumber //we add it to our prime collection
    }
    curNumber += 1
  }

  println(primes.mkString(","))
//  primes.foreach(println)
}
