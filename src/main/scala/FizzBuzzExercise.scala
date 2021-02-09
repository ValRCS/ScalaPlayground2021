object FizzBuzzExercise extends App {
  //TODO print a sequence on a screen of the following type
  // 1,2,3,4,Fizz,6, Buzz, 8, 9, Fizz, 11,....34, FizzBuzz, 36, ..., 99, Fizz
  //so the rules are if number divides by 5 and 7 print FizzBuzz
  //if divides by 5 print Fizz
  //if divides by 7 print Buzz
  //otherwise we print the number itself

  //so this exercise tests your ability to write conditionals and also to write a simple loop

  //this exercise is the first screener at an interview to see if a person can program
  //for the first time it is okay to print the results in new line each
  //1
  //2 and so on
  //99
  //Fizz

  for (i <- 1 to 100) {
    if (i % 5 == 0 && i % 7 == 0) println("FizzBuzz")
    else if (i % 5 == 0) println("Fizz")
    else if (i % 7 == 0) println("Buzz")
    else println(i)
  }

  //this exercise is the first screener at an interview to see if a person can program
  var output = ""
  println(output)
  for (i <- 1 to 100) {
    if (i % 5 == 0) {
      output += "Fizz"
    }
    if (i % 7 == 0) {
      output += "Buzz"
    }
    if (i % 5 != 0 && i % 7 != 0) {  //can we somehow make this an "else"?
      output += i.toString
    }
    if (i < 100) {  //no comma after the last one
      output += ", "
    }
  }
  println(output)

  output = "" //reset output
  for (i <- 1 to 100) {

    if (i % 5 != 0 && i % 7 != 0) {  //can we somehow make this an "else"?
      output += i.toString
    } else {
      if (i % 5 == 0) {
        output += "Fizz"
      }
      if (i % 7 == 0) {
        output += "Buzz"
      }
    }
    if (i < 100) {  //no comma after the last one
      output += ", "
    }
  }
  println(output)
  val tokens = output.split(", ")
  for (token <- tokens) println(token)

  //so we used a for loop to create a collection of values
  val mySeq = for {
    i <- 11 to 20
  } yield i
  mySeq.foreach(println)

  //for numbers this is even quicker and less code
  val myNumbers = (21 to 30).toIndexedSeq

  //indexes in programming start at 0
  println(mySeq(0)) //11
  println(mySeq(3)) //expecting 13 but prints 14 because indexes start at 0 !!!

  println(myNumbers(4))

  //very rarely used in Scala but used a lot in Java and other languages
  for (i <- 0 until myNumbers.length) {
    println(s"Index $i value: ${myNumbers(i)}")
  }

  //if we need index in Scala we can use zipWithIndex which creates index automatically
  for ((value, index) <- myNumbers.zipWithIndex) {
    println(s"Index $index value: $value ") //this would be the Scala way
    println(s"Index $index value: ${myNumbers(index)}") //this one not recomended because too and more chance to mess up
  }

  //we can create a sequence of FizzBuzz items for future use by using yield and then if expression for condition
  val myFizzBuzz = for {
    i <- 1 to 40
  } yield if (i % 5 == 0 && i % 7 == 0) "FizzBuzz"
  else if (i % 5 == 0) "Fizz"
  else if (i % 7 == 0) "Buzz"
  else i.toString //best to keep all items same type, to have less of Any (which is too loose)

  myFizzBuzz.foreach(println)
}
