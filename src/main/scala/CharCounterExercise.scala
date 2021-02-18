import scala.io.StdIn.readLine

object CharCounterExercise extends App {
  //TODO ask user to enter a sentence
  //TODO calculate and save character frequency into a Map
  //you can use mutable and/or immutable Map
  //print out this map with character frequencies
  //so your Map most likely will be of type [Char,Int]
  //also i think getOrElseUpdate method will be helpful
  val sentence = readLine("Enter a sentence here: ")
  val map = collection.mutable.Map.empty[Char, Int]
  for (chr <- sentence) {
    if (map.contains(chr))
      map(chr) += 1 // so same as map(chr) = map(chr) + 1
    else
      map += ((chr, 1))
  }
  println(map)

  def mapString(s: String): Map[Char, Int] = {
//    s.toSeq.groupBy(c => c).view.mapValues({case(c,str) => c -> str.size})
    s.toSeq.groupBy(c => c).view.mapValues(_.size).toMap
  }
  println(mapString(sentence))

 //currently as of Scala 2.13 full short version
  val myFreq = sentence.toSeq.groupBy(identity).view.mapValues(_.size).toMap
  println(myFreq)
  //so difference between MapView and Map that MapView is referring to original data somewhere, so MapView is sort of half-baked

  val mutCharMap = collection.mutable.Map.empty[Char,Int]
  for (key <- sentence) mutCharMap(key) = mutCharMap.getOrElse(key, 0) + 1
  println(mutCharMap)

  val topChars = myFreq.toSeq.sortBy(_._2).reverse
  println(s"Top three characters: ${topChars.slice(0,3)}")
}
