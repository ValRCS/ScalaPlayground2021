object LoopingSeq extends App {
  val persons = Seq("Valdis", "Līga", "Maija", "Rūta", "Ede")
  println(persons(2))
  persons.foreach(println)
  for (person <- persons) {
    //do something with each person
    println(person.toUpperCase)
    println(person.length)
  }

  //how could we filter for only persons with short names of 4 or less?
  //for now we know about yield (there are other ways of doing it
  val shortNames = for (p <- persons if p.length <= 4) yield p
  shortNames.foreach(println)

  val myValues = for (n <- 1 to 20 if n%3== 0) yield n
  myValues.foreach(println)

  //we do not have to yield n we can yield something else
  val myStrings = for (n <- 1 to 20
                       if n%3== 0) yield s"My number $n"
  myStrings.foreach(println)


}
