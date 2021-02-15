object RandomNums extends App {
  //TODO generate a sequence of 100 random 2 dice throws so and then calculate the average and print frequency of each throw
  val r = scala.util.Random
  r.setSeed(555) //seed makes it so the pseudo random numbers are always the same sequence
  //if you do not specify seed, computer makes seed value from your mouse, keyboard, system time, etc
  //for really random numbers you need some Lava lamps, or radioactive decay, or star bursts etc
  val myRandoms = for (_ <- 1 to 100000) yield r.nextInt(6) + 1 + r.nextInt(6) + 1//so we want two dice throws - 100 of them
  //_ underscore in a for loop signifies that we are not using the actual value, we just need a bunch of elements
  println(myRandoms.slice(0,20))

  println(myRandoms.sum/myRandoms.length.toDouble) //avg value
  val myMin = myRandoms.min //should be 2 but you never know
  val myMax = myRandoms.max //12
  val frequencies = for (n <- myMin to myMax) yield myRandoms.filter(el => el == n).length //if we have a big range could take a while on big data
  println(frequencies)
  val freqCounts = (myMin to myMax).map(n => (n, myRandoms.count(_ == n))) //so we get back the original number in a tuple plus its counts in myRandoms
  println(freqCounts)

  val freqSorted = freqCounts.sorted //so it sorted by the first item in our tuple
  println(freqSorted)

  val freqSortedByOccurrences = freqCounts.sortBy(_._2).reverse //so we sort by 2nd member of our tuple
  println(freqSortedByOccurrences)

}
