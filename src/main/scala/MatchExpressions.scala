import scala.annotation.switch
import scala.io.StdIn.readLine

object MatchExpressions extends App {
  //https://alvinalexander.com/scala/how-to-use-scala-match-expression-like-switch-case-statement/
  // i is an integer
  val i = readLine("What numeric month is now? ").toInt

  //so we do not have to write a long chain of if if else if else else statements
  i match {
    case 1  => println("January")
    case 2  => println("February")
    case 3  => println("March")
    case 4  => println("April")
    case 5  => println("May")
    case 6  => println("June")
    case 7  => println("July")
    case 8  => println("August")
    case 9  => println("September")
    case 10 => println("October")
    case 11 => println("November")
    case 12 => println("December")
    case 13 => println("13th Month")
    // catch the default with a variable so you can print it
    case whoa  => println("Unexpected case: " + whoa.toString)
  }

  //we can save match results into a value(variable)
//  val month = i match {
  //@switch annotation is for perfomance reason will give a warning
  //if match expression can not be compiled into a fast lookup table
  val month = (i: @switch) match {
    case 1  => "January"
    case 2  => "February"
    case 3  => "March"
    case 4  => "April"
    case 5  => "May"
    case 6  => "June"
    case 7  => "July"
    case 8  => "August"
    case 9  => "September"
    case 10 => "October"
    case 11 => "November"
    case 12 => "December"
    // catch the default with a variable so you can print it
    case whoa  => "Unexpected case:"  + whoa.toString
  }
  println(s"Got month: $month")
}
