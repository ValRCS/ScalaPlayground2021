object LargestIncrease extends App {
  val dataPath = "./src/resources/myInts.txt"
  val lines = Utilities.getLinesFromFile(dataPath)
  val integers = lines.map(_.toInt)
  println(integers.max, integers.min, integers.sum.toDouble/integers.length)


  //the task get the largest increase from the start towards end
  //in other words if this was stock price
  //what is the largest profit when you buy at some point only to sell later

  //the first idea would be just max - min right ? that's simple?
  //Seq(5,8,3,6,7,1,4) the biggest increase would be from 3 to 7 and neither is min or max
  //but there's a catch it, if min is after max it might not lead to biggest delta(profit)



  val testSeq = Seq(5,9,3,7,8,6,7,1,4)

  //we can always start with a brute force solution
  //this simply means we try every value combination in this case
  def findBiggestIncrease(ints: Seq[Int]):Int = {
    //we could see if we can get a combination of 2 out of all
    var increase = 0 //maybe negative infinity
    //now we need to loop through all the value pairs
    for ((v, i) <- ints.zipWithIndex) {
      //possible off by one erros
      for (vAfter <- ints.slice(i+1, ints.length)) {
        if (vAfter - v > increase) {

          increase = vAfter - v
//          println(s"New winner! ${increase} from $vAfter - $v")
        }
      }
    }
    increase
  }

  println(findBiggestIncrease(testSeq))


  //TODO solve biggest increase in so called Linear time - meaning single loop
  def findBiggestIncreaseLin(ints: Seq[Int]):Int = {
    var min = ints(0)
//    var max = ints(0)
    var relativeWinner = 0
    var absoluteWinner = 0
    for ((n, i) <- ints.tail.zipWithIndex) {
      val delta = n - min //this should work because ints indexing is one less than our values
      if (delta > relativeWinner) relativeWinner = delta
      if (relativeWinner > absoluteWinner) absoluteWinner = relativeWinner
      if (n < min) {
        min = n //so we have a new minimum we need to start keeping track of new records for diff
        relativeWinner = 0 //this is important because we will need to calculate diff again
      }
    }

    absoluteWinner
  }
  println("Testing Linear Algorithm")
  println(findBiggestIncreaseLin(testSeq))
  println(findBiggestIncreaseLin((1 to 10)))
  println(findBiggestIncreaseLin((10 to 1 by -1 )))
  println(findBiggestIncreaseLin(Seq(4,7,2,10,1,6,10,3,6)))

  var t0 = System.nanoTime()
  val increase1 = findBiggestIncreaseLin(integers)
  var t1 = System.nanoTime()
  println("Elapsed time: " + (t1 - t0) + "ns")

  t0 = System.nanoTime()
  val increase2 = findBiggestIncrease(integers)
  t1 = System.nanoTime()
  println("Elapsed time: " + (t1 - t0) + "ns")

  println(s"Biggest Increase is $increase1 == $increase2")



  //it could actually be a couple loops but the key being that they are not nested
  //so 3 loops in a row (not within each other) would still be Linear
  //because number 3 does not change as more data is added

  //in this case we only need 1 loop (but if you need 2 loops that's finetoo).

  //meaning our calculations grow linearly as more data is added
  //our brute force solution is so called quadratic meaning
  // our complexity grows quadratically based on our data, this is so called O(n^2) complexity

  //come up with a solution to find the biggest increase
  //you could just write the code first in comments then try to implement it
  //you could Google for it too, there are multiple solutions possible

//  def findBiggestIncreaseLinear(ints: Seq[Int]):Int = {
//    var sumDiff = 0
//    var maxSumDiff = ints.min - ints.max - 1
//    var maxDiffIndex = -1
//    var minSumDiff = - maxSumDiff
//    var minDiffIndex = -1
//
//    var previous = ints(0)
//    for (i <- 1 to ints.length-1) {
//      val diff = ints(i) - previous
//      sumDiff = sumDiff + diff
//
//      if (sumDiff > maxSumDiff) {
//        maxSumDiff = sumDiff
//        maxDiffIndex = i
//      }
//
//      if (sumDiff < minSumDiff) {
//        minSumDiff = sumDiff
//        minDiffIndex = i
//      }
//      previous = ints(i)
//    }
//    val increase = ints(maxDiffIndex) - ints(minDiffIndex)
//    increase
//  }

//  println(findBiggestIncreaseLinear(testSeq))

}
