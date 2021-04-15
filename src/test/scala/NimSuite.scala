import org.scalatest.funsuite.AnyFunSuite
import FunctionExamples.{add,div,multi}
import com.github.valrcs.Nim
import com.github.valrcs.GameState
import com.github.valrcs.Utilities.findNeedle

class NimSuite extends AnyFunSuite{
  test("checking basic arithmetic") {
    assert(add(2,2) == 4)
  }
  test("check division precision") {
    assert(div(5,2) == 2.5)
  }
  test("check division by zero") {
    val inf = div(10,0)
    assert(inf == Double.PositiveInfinity) //turns out we can divide by zero in Scala
//    try {
//      val inf = div(10,0)
//      assert(inf == Infinity)
//    } catch {
//      case default: Throwable => println(s"Got Exception $default")
//    }
  }
  //in TDD - test driven development you'd write tests first

  test("Checking Nim game state init") {
    val state = new GameState()
    assert(state.playerA == "Valdis")
  }

  //testing for specific Exception
  test("testing for Arithmetic exception") {
    assertThrows[ArithmeticException] {
      //code which could throw this exception goes here
      5/2 //this will not throw it so the Test WILL FAIL without next line!
      5/0 //this will throw it so test should pass here
    }
  }

  //of course for each package you'd want to have separate Test Files

  //TODO write 2 tests for  FunctionExamples.multi one with non zero numbers one with zeros

  //TODO write 2 tests for Utilities.findNeedle

}
