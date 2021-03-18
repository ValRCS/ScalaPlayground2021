import scala.collection.mutable.ArrayBuffer

object Fibonacci extends App {
    //1,1,2,3,5,8,13,21
  //golden ration between two values

  //neat looking recursive solution but very slow after certain numbers just grow the recursion tree
  //in recursion we need the base case/s and then some way of reducing the problem size
  def recFib(n: Int):Int = if (n < 2) 1 else recFib(n-1)+recFib(n-2)

  println(recFib(5))
  for (n <- 0 to 12) println(recFib(n))

//  println(recFib(35)) //in Python you would slow down already here
//  println(recFib(38))
//  println(recFib(45)) //slowing down finally

  //small exercise for you, write fibonacci iteratively (with a loop) for Thursday

  def getNthFib(n:Int):Long = {
    val allFib: ArrayBuffer[Long] = ArrayBuffer(0, 1)
    //here select which Fibonacci number you want (assuming you kind of know 1st and 2nd)
    for (i <- 1 to n-2) {
      allFib += allFib(i-1) + allFib(i)
    }
//    allFib.foreach(println)
//    println(s"Fibonacci number #${numOfFib} is ${allFib.last}")
    //this would be better suited to return everything
    allFib.last
  }
  println(Long.MaxValue)
  println(getNthFib(90))
  //to work with bigger numbers we will need BigNum libraries those are slower than regular Int and Long

  def iterativeFib(n: Int):Long= {
    var fib = 0L
    var last2 = 0L
    var last1 = 1L

    if (n == 0) fib = last2
    else if (n == 1) fib = last1

    else
      for (_ <- 2 to n) { //since we are not using index we might as well rename it to _
        fib = last2 + last1

        last2 = last1
        last1 = fib
      }

    fib
  }

  println(iterativeFib(5))
  var t0 = System.nanoTime()
  val f90 = iterativeFib(90)
  var t1 = System.nanoTime()
  println("Elapsed time: " + (t1 - t0) + "ns")

  t0 = System.nanoTime()
  val f91 = getNthFib(91)
  t1 = System.nanoTime()
  println("Elapsed time: " + (t1 - t0) + "ns")

  def fibonacci(n:Int):Long= {
    def fibNew(n:Int,n1:Long=0,n2:Long=1):Long= n match {
      case 0 => n1
      case 1 => n2
      case _ => fibNew(n-1,n2,(n2+n1))

    }

    fibNew(n)
  }

  t0 = System.nanoTime()
  val f35= fibonacci(91)
  t1 = System.nanoTime()
  println(s"Elapsed time to find $f35: " + (t1 - t0) + "ns")
}
