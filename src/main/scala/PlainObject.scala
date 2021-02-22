object PlainObject {
  val myValue = 42
  val myName = "Valdis"

  def myMethod(): Unit ={
    println(s"$myName likes $myValue")
  }

  def main(args: Array[String]): Unit = {
    println("Hello world!") // so when we extends App we get a main class already premade for us
    myMethod()
  }
}
