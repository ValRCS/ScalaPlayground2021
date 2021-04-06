package com.github.valrcs

import com.github.valrcs.Utilities.clamp

import scala.io.StdIn.readLine

//so everything related to game state lives in separate Object
//template for our game truths
class GameState(startingMatchCount: Int = 21, minMatches:Int = 1, maxMatches:Int = 3, startComputerLevel:Int = 0) {
  var matches = startingMatchCount
  val min = minMatches
  val max = maxMatches
  var isPlayerATurn = true //at the beginning of game Player A starts
  var isPlayerBComputer = false
  var computerLevel = startComputerLevel
  //this is bare minimum for our game
//we could have save a variable and used computer level as a selector meaning computerLevel 0 would be human :)
}



object Nim extends App {
  //https://en.wikipedia.org/wiki/Nim#The_21_game
  println("Let's play a game of Nim!")
  //TODO implement 2 player version with 21 matches and up to 3 matches taken in one turn
  val state = new GameState() //creating a new game state object with default parameters
  //we will want to have some state for our game
  //in our case our game state will be simple just an integer holding count of our matches

  val r = new scala.util.Random


  val isPlayerBComputer = readLine("Do you want to play against computer (Y/N)?").contains("Y") //we could have added a more complex if
  if (isPlayerBComputer) state.computerLevel = readLine("How strong a computer you want (1-3)? ").toInt //TODO Clamp function with (minMatches and maxMatches)
  state.computerLevel = clamp(state.computerLevel, state.min, state.max)
  //for one off calls this would also work with fullname
  //  computerLevel =  com.github.valrcs.Utilities.clamp(computerLevel, minMatches, maxMatches)
  //  computerLevel =  Utilities.clamp(computerLevel, minMatches, maxMatches) //works because of wildcard import with _
  //careful with wildcard imports you might sometimes get a collision


  def computerMove(matches: Int, computerLevel: Int): Int = {
    computerLevel match {
      case 1 => 1
      case 2 => r.between(state.min, state.max + 1) //between last number is exclusive
      case 3 => smartComputer(matches)
    }
  }

  def smartComputer(matches: Int): Int = {
    //    val take = matches match {
    //      case 4 | 8 | 12 | 16 | 20 => 3
    //      case 3 | 7 | 11 | 15 | 19 => 2
    //      case _ => 1
    //    }
    //more universal
    val take = matches match {
      case matches if matches % 4 == 0 => 3 //we are using case gourd
      case matches if matches % 4 == 3 => 2
      case matches if matches % 4 == 2 => 1
      //we always want a default case
      //here default is same as matches % 4 == 1
      //this is the case when computer is losing
      case _ => r.between(state.min, state.max + 1) //idea being that if computer is in bad shape it should do random choice
    }
    take
  }

  //we need loop for this
  while (state.matches > 0) {
    println(s"We have ${state.matches} left, it is ${getPlayerTurn(state.isPlayerATurn)} turn")

    var matchesTaken = 0
    //computer check
    if (!state.isPlayerATurn && isPlayerBComputer) matchesTaken = computerMove(state.matches, state.computerLevel)

    while (matchesTaken < state.min || matchesTaken > state.max) {
      println(s"Please choose between ${state.min} and ${state.max} matches")
      matchesTaken = readLine(s"How many matches do you want to take ${getPlayerTurn(state.isPlayerATurn)}?").toInt
    }

    state.matches -= matchesTaken
    state.isPlayerATurn = !state.isPlayerATurn //old trick on toggling boolean value
    //showing Game State

  }

  println(s"Congratulations you won ${getPlayerTurn(state.isPlayerATurn)}!")

  def getPlayerTurn(isPlayerATurn: Boolean): String = if (isPlayerATurn) "Player A" else if (isPlayerBComputer) "Computer" else "Player B"

  //we also need a check if the game has ended, but we can do that in the loop, in Scala we try to avoid break(able)

}
