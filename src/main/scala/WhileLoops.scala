import scala.io.StdIn.readLine

object WhileLoops extends App {
  println("Hello World!")
  //so how to repeat this action over an over
  //not very Scala like way of iterating over things but describes the while loop
  var i = 0 //we need something that can be changed to use as a counter/iterable
  //so after each code block inside while is run the while check is performed
  while(i < 5) {
    println("Alice did talk No.", i)
    println(s"Alice did talk No.$i")

    //we can do more stuff here for each cycle of the loop
    i += 2 //i = i + 1 crucial that we change something with our iterator variable i so we do not have a infinite loop
    //goes back to the start of while loop

  }
  //i keeps living here
  println("I is ", i)
  //while loops are best used for uncertain looping, meaning we do not know how long the loop will last
  //it could be user input for one
  var userInput = ""
  //so we are saying for the loop to keep running
  //while user has not enter q exactly
  // and also has not entered any sentence starting with quit (any case)
  while (!userInput.toLowerCase.startsWith("quit") && userInput != "q"
    && !userInput.toLowerCase.contains("exit")) {
    println(s"You entered $userInput")
    userInput = readLine("Do you want to continue? enter 'q' to exit")
  }
  //so you can make user input as lenient/loose as you wish

  //rare type of loop do while
  //do while loops print one loop cycle first then check
  //so like shooting first and asking questions later
  i = 0 //we are recycling i since well i is often used for iterators
  do {
    println("Will print no matter what", i)
    i += 50
  } while (i<10)

  //i keeps living here
  println("I is ", i)



}
