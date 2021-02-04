import scala.io.StdIn.readLine //so intelliJ will suggest what library needs importing

object MyApp extends App{
  println("My first Scala program!")
//  print("So print has no new line")
//  print("As can be seen here")
//  println() //just an empty line //is a comment meaning for humans not for computer
//  //comments are ignored by computer
//  //comments are best when they explain why you did something
//  println("Hmm more code!") //saying print is printing something is not very helpful :)
  //we just adding new instruction but we will want to save some data
  //most likely we will want to branch depending on what that data is
  val myName = readLine("What is your name friend? ") //readLine will return Strings
  val favoriteFood = readLine(s"Hello there $myName what is your favorite food?") //s string interpolation lets us insert values and variables directly into string
  println(s"Cool I like $favoriteFood as well!")
  val currentPrice = readLine(s"How much is/are $favoriteFood per kg in stores right now?").toFloat //getting is/are correct will need if statements
  println(s"Not bad I could buy some $favoriteFood for $currentPrice.")
  val currentMoney = readLine(s"How much money do you have in you wallet $myName?").toFloat
  //TODO find out how much of our favorite food we could afford right now
  val maxPurchaseWeight = currentMoney / currentPrice  //so Scala (in contrast to Java where you have to spell it out) picks up/infers the data type
//  println(s"Congratulations $myName! You can buy ${(maxPurchaseWeight*100).round/100.toDouble} kilos of $favoriteFood right now.")
  println(s"Congratulations $myName! You can buy ${(maxPurchaseWeight*100).round/100.0} kilos of $favoriteFood right now.")

}
