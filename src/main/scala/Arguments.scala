object Arguments extends App {
  //so when you extends App then you get access to command line arguments
  println("Getting some command line arguments")
  for (arg <- args) {
    println(s"Processing command line argument $arg")

  }
}
