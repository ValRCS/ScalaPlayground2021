object UsingTraits extends App {
  val kermit = new Frog
  kermit.philosophize
  println(kermit.jump(1.2))
  println(kermit)
  val axel = new Frog
  axel.philosophize
  println(axel.jump(30))
  axel.legCount = 2 //we can change it since it is a variable
  println(kermit.legCount, axel.legCount)
}
