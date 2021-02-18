import scala.collection.SortedSet
import scala.util.Random.nextInt

object SetOperations extends App {
  //Sets provide constant(quick!) O(1) time lookup for item existance in the set
  println("Set operations")
//  val r = new scala.util.Random(100) //100 is the seed for our pseudo random generator
  //we needed to initialize our random number generator
  val mySet = Set(1,2,3,3,5,2,3,5,6,2, 66)
  println(mySet)
  //so Sets have only unique values
  val randomNums = (1 to 30 ).map (_ => nextInt(20)) //i could have added toSet.toSeq.sorted here if I wanted
  println(randomNums)
  val uniqueNums = randomNums.toSet
  println(uniqueNums)
  val sortedUniques = uniqueNums.toSeq.sorted
  println(sortedUniques)

  //so if we want to add values to your set then we need to use Mutable Set
 //going from Immutable to Mutable Set
  val mutableSet = collection.mutable.Set.from(mySet)
  println(mutableSet)
  mutableSet.add(12)
  mutableSet.add(2)
  mutableSet.add(12)
  mutableSet.add(15)
  mutableSet.add(-15)
  //So we can add more elements
  mutableSet ++= Set(33,66,2,1,2,-16,-15)
  println(mutableSet)
  println(mutableSet.contains(5)) //so very quick lookup
  //so instead of contains we can use array,seq like syntax
  println(mutableSet(1), mutableSet(4555))

  println(mutableSet)
  mutableSet.clear() //set should be empty now
  println(mutableSet)

  val fruit = Set("apple", "orange", "peach", "banana")
  println(fruit("apple"), fruit("mango"))
  val myNumbers = for (n <- 1 to 10) yield n
  val num1to10 = myNumbers.toSet
  println(num1to10)
  val first6 = myNumbers.slice(0,6).toSet
  println(first6)
  val last6 = myNumbers.slice(4,10).toSet
  println(last6)
  //so set intersection
  val commonNums = first6 & last6
  println(commonNums)
  //so set Union
  //so union is sort of similar to ++= adding
  val unionNums = first6 | last6
  println(unionNums)
  //so this is set A - set B so only values unique to first set
  val firstDiff = first6 &~ last6
  println(firstDiff)

  val bigIntersection = first6 & last6 & mySet
  println(bigIntersection)
  val bigUnion = first6 | last6 | mySet
  println(bigUnion)

  //we can iterate/loop over Set just like Map or Sequence
  for(item <- bigUnion) {
    println(item)
  }
  //so we can get a sorted Set, but in general if we want something sorted we want to use Sequence
  val orderedSet = SortedSet.from(bigUnion)
  println(orderedSet)




}
