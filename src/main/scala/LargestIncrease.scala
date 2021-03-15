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



  val testSeq = Seq(5,9,3,8,6,7,1,4)

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
          println(s"New winner! ${increase} from $vAfter - $v")
        }
      }
    }
    increase
  }

  println(findBiggestIncrease(testSeq))
  println(findBiggestIncrease(integers))

  //TODO solve biggest increase in so called Linear time - meaning single loop
  //meaning our calculations grow linearly as more data is added
  //our brute force solution is so called quadratic meaning
  // our complexity grows quadratically based on our data, this is so called O(n^2) complexity

  //come up with a solution to find the biggest increase
  //you could just write the code first in comments then try to implement it
  //you could Google for it too, there are multiple solutions possible

  def findBiggestIncreaseLinear(ints: Seq[Int]):Int = {
    0 //FIXME
  }


}
