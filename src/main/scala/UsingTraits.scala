object UsingTraits extends App {
  val kermit = new Frog(new Point(3,6))
  kermit.philosophize
  println(kermit.jump(1.2))
  println(kermit)
  val axel = new Frog(new Point(2,7))
  axel.philosophize
  println(axel.jump(30))
  axel.legCount = 2 //we can change it since it is a variable
  println(kermit.legCount, axel.legCount)

  val rect = new Rectangle(new Point(2,1), new Point(7,6))
  println(rect.width)
  var r2 = new Rectangle(new Point(3,4), new Point(8, 10))
  println(rect)
  println(r2)

  val p1 = new Point(3,4)
  val p2 = new Point(100,150)
//  val p3 = p1 + p2 //to do this we'd have to define add
  val p3 = p1.add(p2)
  println(p3.formatCoordinates)
  val p4 = p3+p1
  println(p4.formatCoordinates)



}
