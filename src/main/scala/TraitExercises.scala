object TraitExercises extends App {
  //you can use RandomNums as inspiration
  //TODO generated sequence of 10 random frogs located in 0<=x<=10, 0<=y<=100
  //TODO generate 10 random rectangles with topLeft again from 0 to 100 both coordinates
  val r = scala.util.Random
  r.setSeed(77) //to keep same pseudo random numbers for testing

  def rPoint(maxX: Int = 100, maxY: Int = 100) = new Point(r.nextInt(maxX), r.nextInt(maxY)) //helper method to generate new random points
//  val frogs = for (n <- 1 to 10) yield new Frog(new Point(r.nextInt(100), r.nextInt(100)))
  val frogs = for (_ <- 1 to 5) yield new Frog(rPoint()) //if I am not using loop variable also rPoint() gives me new Point with default random
  frogs.foreach(println)
  //val frogs: Seq[Frog]

  val gardens = for (_ <- 1 to 4) yield new Rectangle(rPoint(), rPoint())

  val somePoint = rPoint()
  println(somePoint.x)
  println(somePoint.y)


  for (garden <- gardens) {
    println(s"Checking $garden")
    frogs.foreach(frog => if (garden.isInside(frog.getLoc)) println(s"froggie $frog inside") else println(s"outside $frog"))
  }

  //we could do with 2 foreach loops
  gardens.foreach(garden => frogs.foreach(frog => if (garden.isInside(frog.getLoc)) println(s"froggie $frog inside") else println(s"outside $frog")))

  //also could do with two for loops
  for (garden <- gardens) {
    println(s"Checking $garden")
    for (frog <- frogs) {
      if (garden.isInside(frog.getLoc)) println(s"froggie $frog inside") else println(s"outside $frog")
    }
  }

  val newFrog = new Frog(rPoint())
  println(newFrog.location)

//  val newPolly = new PollyWog((rPoint()))
  val newPolly = new PollyWog(new Point(3,5), 33)
//  frogs.foreach(frog => println(s"${frog.location}"))

  //TODO and bottom right coordinates being also from 0 to 100
  //TODO of course bottom right should be to the right and to the bottom of topLeft
  //val gardens: Seq[Rectangle]
  //TODO create method that checks if frog is in the rectangle

  val checkFrogs = {
  //TODO for now just print which frogs live inside which gardens
  }
  //
}
