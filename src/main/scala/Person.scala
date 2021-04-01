//so arguments are values by default
//we can add vars to case classes but generally not recommended
case class Person(name: String, age: Int, height: Double, weight: Double, var hairColor: String) {
  def getBMI: Double =  {
    val heightMeters = height/100
    weight / (heightMeters*heightMeters)
  }
}

case class USPerson(Name: String, LastName: String, Company: String, Address: String, City: String,
                    Country: String, State: String, Zip: Int, Phone1: String, Phone2: String, Email: String, Web: String)
