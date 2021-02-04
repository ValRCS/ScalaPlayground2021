import java.util.Calendar

import scala.io.StdIn.readLine

object AgeCalculator extends App {
  println("This program will calculate the year when you will be 100")
  val targetYear = 100 //now we can change the targetYear as needed , maybe this year would come from outside somewhere
  val now = Calendar.getInstance()
  val currentYear = now.get(Calendar.YEAR)
  println(s"Current year is  $currentYear")
  val personName = readLine("What is your name?")
  val personAge = readLine(s"Nice to meet you $personName. Can you tell your age as well?").toInt
  val yearsToTarget = targetYear - personAge
  println(s"$personName, you have $yearsToTarget years left until you will be 100")
  println(s"$personName you will be $targetYear old in year ${currentYear+yearsToTarget}")

}
