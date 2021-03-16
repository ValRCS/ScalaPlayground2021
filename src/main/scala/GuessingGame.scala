import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn.readLine

object GuessingGame extends App {
//TODO Write a guessing game where the user has to guess a secret number.
  //you will need to create a secret number using random
  // After every guess the program tells the user whether their number was too large or too small.
  // At the end the number of tries needed should be printed.
  // It counts only as one try if they input the same number multiple times consecutively.


  //remember we can use readLine
  //most likely a while loop
  //do not need a breakable unless you want to try it out

  //there was a game i think 20 questions where you have to guess something

//  val r = new scala.util.Random
//
////  r.setSeed(55) //we will always get the same secret Number :)
//  val secretNumber = r.nextInt(100)+1
////  println(secretNumber) //this is for myself to see the secret number
//
//  var yourGuess = -1 // just picked up something out of guess range
//  var previousGuess = -2 // just picked up something out of guess range
//  var countsOfGuesses= 1
//  val guessedNumbers = ArrayBuffer[Int]() //this is how you would initialize an empty ArrayBuffer for Int types
//  while (yourGuess != secretNumber) {
//    yourGuess = readLine("Guess a number from 1 to 100 :  ").toInt
//    if (yourGuess == secretNumber)
//      println(s"Congratulations, you made a correct guess - $yourGuess is the secret number! Total nr of your guesses is $countsOfGuesses")
//    else if (yourGuess < secretNumber)
//      println("Your guessed number is smaller than the secret number, try once again! ")
//    else if (yourGuess > secretNumber)
//      println("Your guessed number is bigger than the secret number, try once again! ")
//    if (previousGuess!=yourGuess) {
//      countsOfGuesses +=1
//      previousGuess = yourGuess
//      guessedNumbers += yourGuess
//    }
//  }
//  println(guessedNumbers.mkString(","))

  val maxNum = readLine(s"What is the largest secret number? : ").toInt
  val secretNum = scala.util.Random.nextInt(maxNum)
  var guess = readLine(s"Guess the secret number between 0 and $maxNum :  ").toInt
  var guessList = new ArrayBuffer[Int]()
  while (secretNum != guess) {
    val message = if (guess > secretNum) "too high" else "too low"

    if (guessList.toArray.contains(guess)) {
      readLine(s"You already guessed $guess already, it is $message")
      guess = readLine("guess again : ").toInt
    }
    else {
      println(s"$guess is $message")
      guessList += guess
      guess = readLine("guess again : ").toInt
    }
  }
  println(s"You guessed the secret number ($secretNum) in ${guessList.length+1} tries")
}
