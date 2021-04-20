import org.scalatest.funsuite.AnyFunSuite
import FunctionExamples.{add,div,multi}

class FunctionSuite extends AnyFunSuite {
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

  test("Simple multiplication") {
    assert(multi(15,3) == 45)
  }
  test("Multiplication with zero") {
    assert(multi(0,200) == 0)
  }

  //testing for specific Exception
  test("testing for Arithmetic exception") {
    assertThrows[ArithmeticException] {
      //code which could throw this exception goes here
      //this will not throw it so the Test WILL FAIL without next line!
      5/0 //this will throw it so test should pass here
    }
  }
}
