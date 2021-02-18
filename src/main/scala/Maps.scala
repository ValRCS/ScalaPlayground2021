import scala.collection.mutable

object Maps extends App {
  //Maps are sometimes called
  //key-value stores, dictionaries, hashmaps(special case of Maps in Scala), associate arrays
  val m = Map("one" -> 1, "two" -> 2, "three" -> 3)
  println(m("two")) // 2 //this lookup will happen near instantly even on a large/huge dictionary
  println(m.contains("three")) //this lookup will be faster than the one we performed on sequences
//  println(m("four")) // so this will throw an exception
  //so we could check by hand
  val result = if (m.contains("four")) m("four") else "not found" //possible but better to use Option type
  println(result)

  //Scala's Option[T] type allows you to represent a value that may or may not exist. An Option[T] can either
  //be Some(v: T) indicating that a value is present, or None indicating that it is absent:
  //T stands for any data type
  val getResult = m.get("four")
  println(getResult)
  println(m.get("three"))
  println(m.get("three").getOrElse("None type inside"))
  println(m.get("nosuchkey").getOrElse("None type inside"))
  val mySeq = Seq(("one",10), ("two",20), ("three", 30), ("two", 2020))
  val myMap = mySeq.toMap //so here the first two will be gone and overwritten by 2nd "two"
  println(mySeq)
  println(myMap)
  println(mySeq(1)._2) //so not very easy to find value of 2 when first value is "two"

  val myTuples = mySeq.filter(_._1 == "two") //this would work but has to go through ALL elements of the sequence
  //so if you have billion tuples in your sequence it will have to go through all billion of them
  println(myTuples.head._2) //of course it is possible the we didnt find any then myTuples(0) would throw exception

  //one property of Maps is that keys have to be unique
  for ((key,value) <- myMap) println(s"$key -> $value") //the order of items in a general Map is not guaranteed!
  for(((key,value), index) <- myMap.zipWithIndex) println(s"Item No.$index with $key -> $value") //zero based index
  val mySuperSeq = (for (((key,value), index) <- myMap.zipWithIndex ) yield (index, key, value)).toSeq //it is still a List
  println(mySuperSeq)
  val newMap = for ((key,value) <- myMap) yield (value.toString, key)
  println(newMap)

  val mutMap = collection.mutable.Map("one" -> 1, "two" -> 2, "three" -> 3)
  //we gain ability to add and remove members of the map, but map stays the same because we use val
  mutMap.remove("two") //should be very quick operation
  println(mutMap)
  mutMap("nine") = 9000
  println(mutMap) //again order is not guaranteed because general Map is optimized not to care about the order

  val immutableMap = mutMap.toMap //converstion to immutable Map
  println(immutableMap)
  //https://stackoverflow.com/questions/5042878/how-can-i-convert-immutable-map-to-mutable-map-in-scala answer
  //from rearded for 2020
//  immutableMap("ten") = 10 //intelliJ does not allow us to even try to assign to immutable Map :)
  val mutableMapAgain = mutable.Map.from(immutableMap)
  println(mutableMapAgain)

  //same should also apply to Seq conversions and also to Sets(for next lecture)

  //Mutable Maps have a convenient getOrElseUpdate function, that allows you to look up a value by key, and
  //compute/store the value if there isn't one already present
  println(mutableMapAgain.getOrElseUpdate("three", -1))
  println(mutableMapAgain.getOrElseUpdate("seven", -1)) //should add value -1 to key "seven" if it does not exist
  println(mutableMapAgain)

  println(mutableMapAgain.min) //sure looks like ordered by key string
  println(mutableMapAgain.max) //sure looks like ordered by key string

  //so getting min and max values we use By modified methods
  //https://stackoverflow.com/questions/8750734/how-to-get-min-by-value-only-in-scala-map
  println(mutableMapAgain.minBy(_._2))
  println(mutableMapAgain.maxBy(_._2))

  mutableMapAgain.clear() //in place clearing of mutable map
  println(mutableMapAgain)

  mutableMapAgain += ("name" -> 7) //can't use string as value because we started out with values being integers
  val mutStringMap = collection.mutable.Map.empty[String,String] //for those cases when we need to start with nothing
  //depreceated += so at some point it will go away
  mutStringMap ++= Map("name" -> "Valdis", "food" -> "potatoes") //so i can add multiple key value pairs at once as long as types match
  //in above line i made a map on a spot
  println(mutStringMap)

  mutableMapAgain ++= myMap //so we can merge into mutable map an immutable map //it will overwite values for matching keys
  println(mutableMapAgain)

  val someSeq = for (n <- 1 to 10) yield (n.toString, n*20)//range is a sequence
  println(someSeq)
  val someMap = someSeq.toMap.view.mapValues(_ * 10).toMap
  println(someMap)
  //https://stackoverflow.com/questions/25635803/difference-between-mapvalues-and-transform-in-map
  //so difference between mapvalues and transform is that with transform we can use key also in the value calculation
  val transformedMap = someMap.transform((k,v) => s"key $k : value: $v")
  println(transformedMap)
  //so how about when we want to transform both key and value at once?
  val mapFromMap = for ((k,v) <- transformedMap) yield (s"V$k", v+10) //so here i concatened(added) string 10 to the end of value
  println(mapFromMap)

  val mapFromMapAgain = someMap.map({case (key, value) => (s"VV$key", value + 111)})
  println(mapFromMapAgain)

  //so Maps allow us to access values by key in constant time no matter how large our map is
}
