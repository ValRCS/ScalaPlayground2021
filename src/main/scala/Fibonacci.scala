object Fibonacci extends App {
    //1,1,2,3,5,8,13,21
  //golden ration between two values

  //neat looking recursive solution but very slow after certain numbers just grow the recursion tree
  //in recursion we need the base case/s and then some way of reducing the problem size
  def recFib(n: Int):Int = if (n < 2) 1 else recFib(n-1)+recFib(n-2)

  println(recFib(5))
  for (n <- 0 to 12) println(recFib(n))

  println(recFib(35)) //in Python you would slow down already here
  println(recFib(38))
  println(recFib(45)) //slowing down finally

  //small exercise for you, write fibonacci iteratively (with a loop) for Thursday

}
