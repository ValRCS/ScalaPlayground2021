object FunctionExercises extends App {
  def isPalindrome(text:String) = {
    //"alus ari ir sula" that's a palindrome
    //text.reverse should work :)
//    text.toLowerCase().replaceAll(" ", "") == text.reverse.toLowerCase().replaceAll(" ", "")
//    text == text.reverse //this would be okay for first try
    val textWhitespacesRemoved = text.replace(" ", "")
    textWhitespacesRemoved.equalsIgnoreCase(textWhitespacesRemoved.reverse)
  }

  println(isPalindrome("aBBa"))
  println(isPalindrome("nota Palindrome..."))
  println(isPalindrome("alus ari   ira    sula"))
//  def getCityYear
  //Write a function which takes 4 parameters p0, perc, delta, p
  //return integer of years when the population reaches p
  //yearly formula is previous year * percentage increas + delta
  //percentage is in percent so you will need to convert
  //delta is how many people leave or join the city every year
  //probably while loop will work best here
  //also consider that it is possible that city never reaches p, then you should return -1
  //hint: if population does not increase after first year it will never reach p


//    def getCityYear(p0:Double, perc:Double, delta:Double, p:Double) = {
//      var pIteration = p0 + p0 * perc/100.0 + delta
//      var years = 1
//
//      while (pIteration > p0 && pIteration < p ) {
//        pIteration += pIteration * perc/100.0 + delta
//        years += 1
//        //      println(pIteration, years)
//      }
//      //    years
//      if (pIteration > p0) years else -1
//
//
//    }

  def getCityYear(p0:Double, perc:Double, delta:Double, p:Int) = {

    var nextPop:Double = p0
    var year:Int = 0

    while (nextPop < p && year>=0) {
      nextPop = nextPop*(1+perc/100)+delta
      year = year + 1
      if(nextPop < p0) year = -1
    }
    year
  }

    println(getCityYear(1000,2,50,1200))  // should print 3
    println(getCityYear(1000,2,-50,1200)) // should print -1
    println(getCityYear(1500000,2.5,10000,2000000)) // should print 10
}
