package com.github.valrcs

import scala.io.StdIn.readLine

object Nim extends App {
  //https://en.wikipedia.org/wiki/Nim#The_21_game
  println("Let's play a game of Nim!")
  //TODO implement 2 player version with 21 matches and up to 3 matches taken in one turn

  //we will want to have some state for our game
  //in our case our game state will be simple just an integer holding count of our matches
  var matches = 21 // our game state
  var isPlayerATurn = true //at the beginning of game Player A starts
  //this is bare minimum for our game
  val minMatches = 1
  val maxMatches = 3

  //we need loop for this
  while (matches > 0) {
    println(s"We have $matches left, it is ${getPlayerTurn(isPlayerATurn)} turn")

    var matchesTaken = 0
    while (matchesTaken < minMatches || matchesTaken > maxMatches) {
      println(s"Please choose between $minMatches and $maxMatches matches")
      matchesTaken = readLine(s"How many matches do you want to take ${getPlayerTurn(isPlayerATurn)}?").toInt
    }

    matches -= matchesTaken
    isPlayerATurn = !isPlayerATurn //old trick on toggling boolean value
    //showing Game State

  }

  println(s"Congratulations you won ${getPlayerTurn(isPlayerATurn)}")

  def getPlayerTurn(isATurn: Boolean):String = if (isPlayerATurn) "Player A" else "Player B"

  //we also need a check if the game has ended, but we can do that in the loop, in Scala we try to avoid break(able)

}
