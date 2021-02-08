import scala.io.StdIn.readLine

object Conditional extends App {
  val myInput = readLine("Enter a number ").toInt
  //just an if
  if (myInput > 20) {
    println("Oh your number is over 20! ", myInput)
  }
  //I could use if else statements
  if (myInput > 10) {
    println("Oh your number is over 10! ", myInput)
  } else {
    println("I am positive your number is 10 or less")
  }

  if (myInput > 5) {
    println("Oh your number is over 5! ", myInput)
    //we can have if inside another if (but things can get complicated)
    if (myInput > 100) {
      println("Wow over 100!")
    } else {
      println("over 5 but less or equal to 100")
    }
  } else if (myInput < 5) {
    println("Oh your number is under 5! ", myInput)
  } else {
    println("Aha! your number must be 5!", myInput)
  }

  println(s"This always prints: Your number was $myInput")

  //we can use if s
  val myResult = if (myInput > 5) "Over 5" else "5 or less" //in Scala if statements return values

  println(myResult)
}
