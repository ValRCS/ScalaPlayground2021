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
  val coolSecret = myHouse.getSecret
  println(coolSecret)
  println(myHouse.getMutSecret)
  myHouse.setMutSecret("New private iNFo")
  println(myHouse.getMutSecret)
  println(myHouse.myAdd(3,1000))

  val castle = new FancyHouse() //so objects created from FancyHouse blueprints will inherit functionality
  //from House
  castle.prettyPrint() //so prettyPrint was inherited from House
  castle.addNewPainting("No bazn'icas")

  // creating objects from case class does not require new
  val valdis = Person("Valdis", 45, 180.1, 90, "black")
  println(valdis)
  println(valdis.getBMI)
  valdis.hairColor = "green" //so possible to mutate vars but not the best practice
  println(valdis)
  val valdisClone = valdis.copy()
  println(valdis == valdisClone) //just the top level values are copied
  valdisClone.hairColor = "red"
  println(valdis == valdisClone)

  val uljana = Person("Ulja", 60, 214, 120, "gray")
  println(uljana)
  println(valdis == uljana)
  val kristaps = uljana.copy(hairColor = "blonde") // so called shallow copy
  //this means that references to data outsie are copied only at the top level
  //in this case the data are primitive so everything is copied
  println(kristaps == uljana) //so should be false since hairColor changed



}
