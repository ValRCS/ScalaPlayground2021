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
  println(orderedSet.count(_ > 5))
  println(bigUnion.min, bigUnion.max, bigUnion.filter(_ < 3).toSeq)
  val myTupleSet = bigUnion.map(n => (n.toString, n*10))
  println(myTupleSet) //guaranteed uniques
  val myMap = myTupleSet.toMap
  println(myMap) //so quick lookup by key
  val mySeq = myTupleSet.toSeq
  println(mySeq) //so ordered but lookup slower unless you know index

  //usually you do not need the set for long you go back to Seq or Map once you've done Set operations
  val zippedSet = bigUnion.zipWithIndex
  println(zippedSet)
  //so we have index in 2nd place we want to reverse key value relation
  val myUnZipMap = zippedSet.map(tup => (tup._2.toString, tup._1)).toMap
//  val myUnZipMap2 = zippedSet.map({(a,b) => (b,a)}).toMap
  val firstMatch = bigUnion.find(_ > 3) //should find first match over 3
  //we get Option since we possible might get Option(None)
  println(firstMatch.getOrElse("didn't find anything"))
  println(mySeq.find(_._2 > 3).getOrElse("no match"))
  println(orderedSet.find(_ > 3).getOrElse("no match here")) //here with ordered we got guaranteed 4 since we had order
  val groupedValues = bigUnion.groupBy(_ % 3)
  println(groupedValues)
  //TODO check syntax on this map operation
//  val groupedValuesSeq = groupedValues.map((k:Int,v:Set[Int]) => (k,v))
  val groupedValuesSeq = groupedValues.map(tup => (tup._1,tup._2.toSeq))
  println(groupedValuesSeq)
  println(groupedValuesSeq(0)(2)) //66 result - so here we use key for map then index for Seq

}
