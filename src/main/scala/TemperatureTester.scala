import scala.io.StdIn.readLine
// from https://github.com/LauSla/Scala-stuff/blob/main/TemperatureTester.scala

object TemperatureTester extends App {
  //TODO write a program which asks for user's temperature
  //if Temperature is over 37C print a warming about high temperature
  //if temperature is under 35 print a warning about being cold
  //otherwise print that everything is great :0
  val feverTemp = 37
  val coldTemp = 35
  val patient = readLine("What is your name?")
  val myTemp = readLine(s"What is your temperature $patient?").toFloat //convert to number
  if (myTemp >= feverTemp) {
    println("You've got a fever!")
  }
  else if (myTemp <= coldTemp) {
    println("You've got hypothermia!")
  }
  else {
    println("You're doing great!")
  }

}
