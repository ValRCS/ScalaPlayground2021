object RandomNums extends App {
  //TODO generate a sequence of 100 random 2 dice throws so and then calculate the average and print frequency of each throw
  val r = scala.util.Random
  val myRandoms = for (i <- 1 to 5) yield r.nextInt(100) //so we want two dice throws - 100 of them
  println(myRandoms)
}
