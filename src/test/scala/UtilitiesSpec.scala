import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import com.github.valrcs.Utilities

//meant for BDD testing style
//BDD is Behaviour-driven development
class UtilitiesSpec extends AnyFlatSpec with Matchers {
  "A Max Function" should
    "have a value of 30" in {
    val maxVal = Utilities.myMax(Seq(0,30,20,5))
    maxVal should be (30)
  }
  it should "throw an Arithmethic Error if divided by zero" in {
    an [ArithmeticException] should be thrownBy {
      5/0 //instead of testing build in div we could test our own div as well
    }
  }
}
