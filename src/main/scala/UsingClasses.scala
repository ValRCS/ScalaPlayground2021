//in Scala objects define so called singleton objects in OOP terms
//this means only one UsingClasses exists
//it could also be a companion object to class
object UsingClasses extends App {
  val myHouse = new House("Birzmali", "central") //creating new objects from regular classes I need new keyword
  println(myHouse) //by default printing object created from class blueprint will just show memory address
  println(myHouse.age, myHouse.color, myHouse.isForSale, myHouse.style) //we can print properties for our object
  myHouse.prettyPrint()
  val friendsHouse = new House("Piejuras", "wood", hasGarage = true)

  friendsHouse.prettyPrint()
  friendsHouse.isForSale = true
  friendsHouse.color = "red"
  friendsHouse.heating = "gas" //so I can change var properties
  friendsHouse.prettyPrint()
//  println(friendsHouse.isForSale, friendsHouse.color, friendsHouse.age, friendsHouse.style)
  friendsHouse.showSecret() // i do not get access to private values/variables but I can use methods to get them
  val coolSecret = myHouse.getSecret()
  println(coolSecret)
  println(myHouse.getMutSecret())
  myHouse.setMutSecret("New private iNFo")
  println(myHouse.getMutSecret())
  println(myHouse.myAdd(3,1000))

  val castle = new FancyHouse() //so objects created from FancyHouse blueprints will inherit functionality
  //from House
  castle.prettyPrint() //so prettyPrint was inherited from House
  castle.addNewPainting("No bazn'icas")


}
