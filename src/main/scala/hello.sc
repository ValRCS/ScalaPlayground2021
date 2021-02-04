println("Hello there Scala fans!")
//worksheet is mostly for experimenting
//think of it like a sandbox
//so classical imperative style of programming we are just writing down
//lots of instruction for the compute to process

//so data have data types
//Scala has preference for immutable data (meaning data which we do not change)
val a = 5+6 //we are saying that a should be of data type of whatever 5+6 produces
//so val means a will still fixed for the duration of our program
println(a)
print("Hello")
print("hmmm ")
println(math.pow(2, 8)) //this explains why 32 bit systems are not sufficient
var b = 10
println(a+b)
b = b + 5 //intelliJ will pick up on these errors automatically
//Scala also have variables which we use when fixed/immutable values will not suffice
var myVar = 50
myVar = myVar + 30
println(myVar)
val result = a + b //so we name our variables starting with lowercase letters
val bigResult = myVar + math.pow(2,16) //if our variable has 2 or more names we use camelCaseReallyLong
//double is really a double sized floating point number
println(result.getClass, bigResult.getClass)
//so we have string
val myName = "Valdis" // " for strings
println(myName, myName.getClass)
val singleChar = 'v' //so ' for chars
println(singleChar, singleChar.getClass)
val myByte : Byte = 120
println(myByte, myByte.getClass)
val anotherByte : Byte = 10
val resultByte = (myByte + anotherByte).toByte
//so you can cast one data type to another (not all conversions will work)
val myNumberString = "125628"
val myNumber = myNumberString.toInt
println(myNumberString, myNumber) //printing does not show that those are two differrent data types
println(myNumberString.getClass, myNumber.getClass) //printing does not show that those are two differrent data types
//boolean data types, these we will use when we need some logic in our program
val isRaining = false
val isSnowing = true
val isCold = true