class Animal(val hasVertebrae: Boolean = true) {
  def scratchBack = s"scratches"
}

//so in scala we can extend a single class or trait
//then we can add additional traits only with with ... :)
class Frog extends Animal with Philosophical with HasLegs  {
  override def toString = "greenie" //rewriting default functionality of toString
}

trait HasLegs {
  var legCount = 4
  //naming of howFar is not perfect because we do not know the units
  def jump(howFar: Double) = s"Print jump on all $legCount legs $howFar meters far"
}

