import scala.io.StdIn.readLine

object ForLoops extends App {
  //more commonly use in Scala
  //used when we know the number of cycles/loops/iterations to perform
  //0 to 5 included
  for (i <- 0 to 5) {
    //turns out i is a val fixed for each loop cycle
    println("Alice talked for time", i)
  }
  // println(i) //so i is not available outside the loops
  //you do not want some unneeded variables polluting your outer program scope/area
  //0 until 5 meaning not counting 5
  for (i <- 0 until 5) {
    println("This time Bob talked", i)
  }

  //we can use for loops to loop over a collection of some sort
  val myNumbers = Seq(2,3,5,10,15,88, -55, 3.14)
  for (n <- myNumbers) {
    println("MY number is", n)
  }

  val myName = "Valdis"
  //i could have use i or n, but characters it is more appropriate to use c
  for (c <- myName) {
    println("Letter ",c)
    println("My BIG leters", c.toString.capitalize) //because c is a character not a string
  }
  //for single expressions we can skip curly braces
  for (c <- myName) println("Letter",c)

  //foreach method instead of loop
  //you are telling what to do with each element of your sequence
  //in this case we want to print it
  myName.foreach(println)

  //we can make up instruction on what to do with each element on the fly
  myName.foreach(c => println("Your letter", c))

  //guards
  //so print only the odd ones
  for (i <- 1 to 10 if i % 2 == 1) println(i)

  //for more complicated examples you can split the guards over the lines
  for (i <- 1 to 10
       if i % 2 == 1
       if i > 3
       if i < 8) {
    println(i)
  }

  val mySentence = readLine("Please Enter any sentence")
  val words = mySentence.split(" ") //split sentence into words by whitespace
  //print out results with some basic stats
  for (word <- words) {
    println(s"word $word is ${word.length} characters long")
  }

  for (i <- 1 to 10) {
    if (i % 2 == 0) {
      println(s"$i is even")
    } else {
      println(s"$i is odd")
    }
  }
}
