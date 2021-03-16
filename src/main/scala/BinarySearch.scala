object BinarySearch extends App {

  //in order for this to work arr has to be sorted
  def iterativeBinarySearch(arr: Seq[Int],
                            Element_to_Search: Int): Int = {

    // Creating start variable to
    // point to the first value
    var low = 0

    // Creating end variable to
    // point to the last value
    var high = arr.length - 1

    // Finding the value in the
    // array iteratively
    while (low <= high)
    {

      // Getting middle element
      val middle = low + (high - low) / 2 //finding roughly middle

      // If element found in the middle index
      if (arr(middle) == Element_to_Search)
        return middle //so remember early return is not very Scala like but here it serves well

      // Searching in the first half
      else if (arr(middle) > Element_to_Search)
        high = middle - 1

      // Searching in the second half
      else
        low = middle + 1
    }


    // so when low meets high this means we did not find anything
    // If value not found in the
    // entire array , return -1
    -1
  }

  println("Checking our binary search")
//  val someNumbers: Seq[Int] = (1 to 20)
  val someNumbers = 11 to 30 by 2
  val needle = 19
  val index = iterativeBinarySearch(someNumbers, needle)

  if (index >= 0) println(s"needle $needle can be found at index: $index which indeed holds ${someNumbers(index)}")
  else println(s"Did not find $needle")
  println(someNumbers.mkString(","))

  // Using the functional programming approach
//  def FunctionalBinarySearch(arr: Array[Int],
//                             Element_to_Search: Int): Int =
//  {
//    def BinarySearch(arr: Array[Int],
//                     Element_to_Search: Int,
//                     low: Int, high: Int): Int =
//    {
//
//      // If element not found
//      if (low > high)
//        return -1
//
//      // Getting middle index
//      var middle = low + (high - low) / 2
//
//      // Pattern matching
//      arr match
//      {
//
//        // If element found , return the index
//        case(arr:Array[Int]) if (arr(middle) ==
//          Element_to_Search) =>
//          middle
//
//        // Call the function for the second half
//        case(arr:Array[Int]) if (arr(middle) <
//          Element_to_Search) =>
//          BinarySearch(arr,
//            Element_to_Search,
//            middle + 1, high)
//
//        // Call the function for the first half
//        case(arr:Array[Int]) if (arr(middle) >
//          Element_to_Search) =>
//          BinarySearch(arr,
//            Element_to_Search,
//            low, middle - 1)
//      }
//    }
//
//    // Calling the Binary Search function
//    BinarySearch(arr, Element_to_Search, 0, arr.length - 1)
//  }
}
