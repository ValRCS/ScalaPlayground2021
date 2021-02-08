import scala.io.StdIn.readLine
//from https://github.com/LauSla/Scala-stuff/blob/main/BonusCalculator.scala
object BonusCalculator extends App {
  //TODO Calculate yearly Xmas Bonus
  //Ask for Employee Name
  val employee = readLine("What is your name? ")
  println(s"Nice to talk to you $employee!")

  //Ask for how long they have worked
  //ask for monthly wage
  //Calculate Xmas bonus if they have worked at least 2  years
  //Bonus is years worked over 2 years * 15% of monthly wage
  //so 5 years worked, 1000 Euros wage would give 450 Euro bonus
  //Ask for how long they have worked
  val tenure = readLine("How many years have you worked here for? ").toFloat //convert to number
  //ask for monthly wage
  val salary = readLine(s"And how much do we pay you, $employee? ").toFloat //convert to number
  //Calculate Xmas bonus if they have worked at least 2  years
  //Bonus is years worked over 2 years * 15% of monthly wage
  //so 5 years worked, 1000 Euros wage would give 450 Euro bonus
  //technically this would not be needed for tenure under 2
  //this could be important if the calculation was over the network, required some slow database
  val minYears = 2 //potentially this value could come from some external source/file, database, network,

  if (tenure < minYears) {
    println("Sorry, no bonus for you!")
  }
  else {
    val bonus = salary * (tenure.ceil - minYears) * 0.15 //.floor rounds it down, count FULL years only
    println(s"$employee, your bonus is $bonus!")
  }
  //there is no bonus here //it only lives in the else block
//  println(bonus)
}
