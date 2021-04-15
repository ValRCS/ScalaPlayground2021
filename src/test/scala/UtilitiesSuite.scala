import com.github.valrcs.Utilities
import com.github.valrcs.Utilities.findNeedle
import org.scalatest.funsuite.AnyFunSuite

class UtilitiesSuite extends AnyFunSuite {

  //this is a bit of integration test since we are testing two Utilities functions together
  test("check finding needle") {
    val lines = Utilities.getLinesFromFile("./src/resources/poetry_1922.txt")
    assert(findNeedle(lines, "BIBLIOGRAPHY") == 336)
  }
  test("check finding simple needle") {
    val lines = Array("Valdis", "Liga", "Maija", "Ruta")
    assert(findNeedle(lines, "aija") == 2)
  }

  test("check clamp performance") {
    //some testing methologies would say to split this test into 3
    assert(Utilities.clamp(5,2,10) == 5)
    assert(Utilities.clamp(5,7,10) == 7)
    assert(Utilities.clamp(5,2,4) == 4)
    assert(Utilities.clamp(-15,-20,4) == -15)
    assert(Utilities.clamp(5,-20,-4) == -4)

  }

  test("check file reading line by line") {
    val lines = Utilities.getLinesFromFile("./src/resources/readTest.txt")
    //so we had a gotcha where Array != Array
    //that stems from the fact that Arrays are from Java and in Java == checks for reference equality
    //meaning it would be only true if both values point to same actual object
    //https://nrinaudo.github.io/scala-best-practices/unsafe/array_comparison.html
    assert(lines.toSeq == Seq("Valdis", "Līga", "Maija"))
  }

  test("another file reading test") {
    val lines = Utilities.getLinesFromFile("./src/resources/readTest.txt")
    assert(lines sameElements Array("Valdis", "Līga", "Maija"))
  }

}
