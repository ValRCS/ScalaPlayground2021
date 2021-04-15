object FunctionExamples extends App {
  //this code works but smells a little
  //  println("Hello")
  //  println("Hello")
  //  println("Hello")
  //  println("Hello")
  //we can use a function to encapsulate a set of actions
  //DRY principle! Do not repeat yourself
  //structuring the code , building building blocks
  def goEat(): Unit = {
    println("Go eat")
    println("Let's order food")
  }

  goEat()

  //  for (_ <- 1 to 5) goEat() //if we do not need the value its good practice to use _
  def orderFood(dish: String): Unit = {
    println(s"I am ordering $dish")
    println(s"${dish.toUpperCase} should be pretty tasty")
  }

  orderFood("potatoes")
  orderFood("ice cream")
  val soup = "beet soup"
  orderFood(soup)

  def eat(foodList: Seq[String]): Unit = {
    println("Greetings!")
    println("Let's order some food!")
    for (food <- foodList) orderFood(food)
    println("Let's eat!")
    println("Let's leave and be happy") //println returns nothing (Unit)
  }

  val myFoods = Seq("snacks", "borsch", "mashed potatoes and gravy", "ice cream")

  eat(myFoods)

  eat(Seq("eggs over easy", "orange juice")) //i can make Sequences on the fly

  def add(a: Double, b: Double) = {
    println(s"Adding $a + $b", a + b)
    a + b //in Scala last line in function is returned automatically
  }

  def div(a:Double, b:Double): Double = a / b

  //now with return value our function results can be saved!
  val addResult = add(3, 6)
  println(addResult)
  val bigAdd = add(add(10, 2), add(5, 3))
  println(bigAdd)

  def multi(a: Double, b: Double) = a * b //even a short function works

  val multiAddResult = multi(add(4, 5), multi(2, 10))
  println(multiAddResult)

  def pow(x: Double = 2, n: Double = 3, isDebug: Boolean = false) = {
    //sometimes you just want a wrapper around an existing function
    //we can provide some extra validation, some extra logic
    val result = Math.pow(x, n).toInt //no need to raise power twice for debugging
    if (isDebug) println("Debugging", result)
    result //so last line is automatically returned
  }

//  println(pow(2, 3))
  println(pow()) //by using default values we can be really lazy now :)
  println(pow(10)) //so x is 10 but n is still 3
  println(pow(n = 8)) //so x stays 2

  println(pow(5, 4, isDebug = true)) //for booleans good style would be to show the name of arguments
  //because we could have many booleans and it is easy to forget order

  def max(x: Int, y: Int) = if (x > y) x else y //we utilize the fact that if else also returns value

  println(max(33, 21))
  println(max(3, 21))

  //generally functions should not be longer than one page of screen, a few lines is enough and one line is often sufficient

  myFoods.foreach(println)
  myFoods.foreach(orderFood) //this is shorthand for fuller syntax below
  myFoods.foreach(food => orderFood(food))
  //so foreach requires function as a parameter
  //food => orderFood(food) is an anonymous function with no name
  //in functional programming we often pass around functions ,so it can be handy to have functions made on spot

  val numbers = Seq(1, 3, 4, 6, 9, 5)
  numbers.foreach(num => println(pow(num)))
  numbers.foreach(num => println(10 + num))

  //think of num => println(10+num) as the function below, except we did not name it
  def myFun(num: Int): Unit = println(10 + num)

  numbers.foreach(myFun) //this works because my function takes a single argument


  //functional style we can map new values
  //so in map we provide a function on how to transform each element one by one
  val newNumbers = numbers.map(num => num * 10 + 50) // num => num*10+50 is an anonymous function with num a parameter
  newNumbers.foreach(println)
  println(newNumbers.mkString(",")) //so we provide a separator as parameter
  val newWords = numbers.map(num => s"Number $num")
  println(newWords.mkString(","))

  val oddNumbers = numbers.filter(num => num % 2 == 1)
  println(oddNumbers.mkString(","))
  val sortedNumbers = numbers.sorted
  println(sortedNumbers.mkString(","))
  val sortedNumbersReversed = sortedNumbers.reverse
  println(sortedNumbersReversed.mkString(","))


}
