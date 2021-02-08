import scala.io.StdIn.readLine

val isRaining = false //not "false" that would be string!
val isCold = true
val a = 2
val b = 3
val pi = 3.1415926
val result = 4
//val a_gt_b = 2 > 3 //so a_gt_b takes on value of our comparison 2 > 3
val a_gt_b = a > b //so a_gt_b takes on value of our comparison 2 > 3

println(a_gt_b)
println(a < b)
println(a*a == result)
println(a*a == b)
print(a <= b) //so <= but not =< !!!
println(a+2 <= result)
println(a >= b)

val newResult = 2*a == result //evaluation is from the right side
println(newResult)

println(2 != 2) //testing for inequality
println(2 != 3) //true
println(a != b) //again true

println(2 > 1.6)
println(b == pi)
println(pi > b) //so we can compare floats and integers

println("Valdis" == "Voldemārs")
println("Valdis" != "Voldemārs") //so testing for inequality

//so what is going on here?
println("Valdis" < "Voldemārs")
println("Vyldis" < "Voldemārs")

println("Valdis".length < "Voldemārs".length)
//so this is comparison by ASCII codes
//http://www.asciitable.com/
//and a < o in the code table
println(pi == Math.PI)
println(Math.PI)

println(2*2 == 4 && 3*3 == 9) //so && is logical and
println(true && true) //true
println(true && false) //false
println(false && true) //false
println(false && false) //false

//one drop of tar spoils the honey
println(true && true && 2+2 == 6)
//so we can keep chaining && but remember as soon
//as one of the statements will be false the whole chain will be false
val isMyPiBig = pi > Math.PI
println(isMyPiBig && 2*2 == 4)

//we can introduce negation ! (not) so reverse
//negation just takese boolean and reverses it
println(!true)
println(!false)
println(!isMyPiBig && 2*2 == 4)

//so ! has precedence over &&
println(!false && false && false) // false because one drop ruins it
println(!(false && false && false)) //true here we reverse everything

//so you can think of false and true as 0 and 1 or flags ON and OFF

//there is also logical or ||
println(2*2 == 5 || 2*2 == 4) //as long as one of the statements is true it will be true
println(true || true) //true
println(true || false) //true
println(false || true) //true
println(false || false) //false

//so one good true statement will make || chain true
println(false || 2*2 == 5 || 3*3 == 9)

println(a == 5 && b == 3)
var c = 10 //again try to use val for everything until needed
println(c == 9 && b == 3)

if (a > 10) {
  println("a is larger than 10", a)
}

