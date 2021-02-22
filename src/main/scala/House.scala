class House (houseName: String, var heating:String, var hasGarage: Boolean = false) {
  //so blueprint for creating object instances
  //public properties are by default
  println("the constructor begins")
  val style = "gothic"
  var isForSale = false
  var color = "green"
  var age = 25
  //we can keep information private
  private val mySecret = "my secret"
  private var mutableSecret = "i can be changed"
  //so called information hiding is used in OOP to hide complexity from other programmers/users of your classes
  println("New House created!") //so all the code within class is run when a new object is made from the blueprints
  //in effect this is the constructor for our object, in other languages you have to name it, in Scala you do not
  //class methods will not be run on construction they will be ready for use later on
  //so pretty much all functions are actually methods because they are defined inside classes
  def prettyPrint(): Unit = {
    println(s"House Name: $houseName style:$style, is for sale? : $isForSale color=$color, age=$age, heating:$heating")
    println(s"Has garage:$hasGarage")
  }
  //in many other OOP languages you'd have to specify that these variables/values are from this class object
  //in Scala it is automatic
  def showSecret(): Unit = println(s"My secret is $mySecret")
  //so called getter
  def getSecret: String = {
    //here would be code for validation, verification, extra security checks and so on
    mySecret
  }
  def getMutSecret: String = mutableSecret
  //setter
  def setMutSecret(txt: String): String = {
    // I could check here for txt validity etc.
    mutableSecret = sanitizer(txt)
    "Success!" //I dont have to return anything on setter
  }
  //I could have a public method for just passing in values/arguments and returning a result
  def myAdd(a:Int, b:Int): Int = {
    //no need for curly braces if i didnt have this comment
    a+b
  }
  //so private methods are for internal use only, outside users will not get to use them
  private def sanitizer(txt: String): String = {
    //well i could do more stuff here
    txt.toLowerCase()
  }

  //we can override existing methods that are built in
  //for example to have print give out my custom print
  override def toString: String = s"Object of class: ${getClass.getName} Custom toString house name: $houseName"

  println("New object creation finished")
}
