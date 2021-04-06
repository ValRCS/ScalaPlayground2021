package com.github.valrcs

import com.github.valrcs.Utilities.clamp //fully qualified import with full name
//import com.github.valrcs._ //imports everything in my package

import scala.io.StdIn.readLine

object Nim extends App {
  //https://en.wikipedia.org/wiki/Nim#The_21_game
  println("Let's play a game of Nim!")
  //TODO implement 2 player version with 21 matches and up to 3 matches taken in one turn

  //we will want to have some state for our game
  //in our case our game state will be simple just an integer holding count of our matches
  var matches = 21 // our game state
  var isPlayerATurn = true //at the beginning of game Player A starts
//  var isPlayerBComputer = false
  //this is bare minimum for our game
  val minMatches = 1
  val maxMatches = 3
  val r = new scala.util.Random

  var computerLevel = 0 //we could have save a variable and used computer level as a selector meaning computerLevel 0 would be human :)
  val isPlayerBComputer = readLine("Do you want to play against computer (Y/N)?").contains("Y") //we could have added a more complex if
  if (isPlayerBComputer) computerLevel = readLine("How strong a computer you want (1-3)? ").toInt //TODO Clamp function with (minMatches and maxMatches)
  computerLevel = clamp(computerLevel, minMatches, maxMatches)
  //for one off calls this would also work with fullname
//  computerLevel =  com.github.valrcs.Utilities.clamp(computerLevel, minMatches, maxMatches)
//  computerLevel =  Utilities.clamp(computerLevel, minMatches, maxMatches) //works because of wildcard import with _
  //careful with wildcard imports you might sometimes get a collision


  def computerMove(matches: Int, computerLevel:Int): Int = {
    computerLevel match {
      case 1 => 1
      case 2 => r.between(minMatches, maxMatches + 1) //between last number is exclusive
      case 3 => smartComputer(matches)
    }
  }

  def smartComputer(matches:Int): Int = {
    val take = matches match {
      case 4 | 8 | 12 | 16 | 20 => 3
      case 3 | 7 | 11 | 15 | 19 => 2
      case _ => 1
    }
    take //TODO make it more universal not only for 21
  }

  //we need loop for this
  while (matches > 0) {
    println(s"We have $matches left, it is ${getPlayerTurn(isPlayerATurn)} turn")

    var matchesTaken = 0
    //computer check
    if (!isPlayerATurn && isPlayerBComputer) matchesTaken = computerMove(matches, computerLevel)

    while (matchesTaken < minMatches || matchesTaken > maxMatches) {
      println(s"Please choose between $minMatches and $maxMatches matches")
      matchesTaken = readLine(s"How many matches do you want to take ${getPlayerTurn(isPlayerATurn)}?").toInt
    }

    matches -= matchesTaken
    isPlayerATurn = !isPlayerATurn //old trick on toggling boolean value
    //showing Game State

  }

  println(s"Congratulations you won ${getPlayerTurn(isPlayerATurn)}!")

  def getPlayerTurn(isPlayerATurn: Boolean):String = if (isPlayerATurn) "Player A" else if (isPlayerBComputer ) "Computer" else "Player B"

  //we also need a check if the game has ended, but we can do that in the loop, in Scala we try to avoid break(able)

}
