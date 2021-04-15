import org.scalatest.funsuite.AnyFunSuite

import com.github.valrcs.Nim
import com.github.valrcs.GameState
import com.github.valrcs.Utilities.findNeedle
import com.github.valrcs.Utilities

class NimSuite extends AnyFunSuite{

  //in TDD - test driven development you'd write tests first

  test("Checking Nim game state init") {
    val state = new GameState()
    //it is a single test with 3 assertions
    assert(state.playerA == "Valdis")
    assert(state.playerB == "Liga")
    assert(state.matches == 12)
  }



  //of course for each package you'd want to have separate Test Files




}
