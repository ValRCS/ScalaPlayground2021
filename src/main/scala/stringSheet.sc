val food = "kartupelis" //potatoe in Latvian
println(food.substring(5))
//why does it start at p not u ?
println(food.substring(0,5)) //so we start at 0th and go until element with index 5
//we do not print element with index 5 because that is 6th
//many operations/functions/methods calling some range EXCLUDE last element
println(food(0)) //so 0th element is the first one
println(food(1))


//looping over string characters example
for (c <- food) {
  println(c)
}
println(food.endsWith("elis"))
println(food.startsWith("elis"))
println(food.startsWith("kart"))
println(food.contains("art"))
println(food.contains("ART"))
println(food.toUpperCase)
println(food.length)
println(food.charAt(4)) //so same as food(4)
println(food.replace("p","m"))
//one thing to mention that NONE of the above modify string because it is immutable
val magic = "abracadabra"
//so if we want to change strings we need to save them into new strings
val newMagic = magic.replace("a", "A")
println(newMagic)
println(newMagic.toLowerCase) //again i am only printing conversion not saving