import com.github.valrcs.Nim
import com.github.valrcs.Player
import com.github.valrcs.GameState

object TestingFun extends App {
  //Test usually are run separately from test
  assert(FunctionExamples.add(2,2) == 4)
  assert(Nim.computerMove(1,1) == 1)
  //so it is a bit hard to test things outside the normal program enviroment
//  Nim.init()
//  assert(Nim.computerMove(1,2) == 1) //this will fail sometimes
  assert(FunctionExamples.add(0,0) == 0) //testing zeros can be useful

  val player = Player("Valdis", 10, 8)
  assert(player.name == "Valdis")
  val gameSetting =new GameState()
  assert(gameSetting.playerA == "Valdis")
  assert(gameSetting.playerB == "Maija") //so this should fail
  assert(gameSetting.matches == 500)
  //there are -ea and -da flags for enabling and disabling assertions

}
