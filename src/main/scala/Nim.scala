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

  //we need loop for this
  val matchesTaken = readLine("How many matches do you want to take?").toInt //no error checking
  matches -= matchesTaken
  isPlayerATurn = !isPlayerATurn //old trick on toggling boolean value
  //we also need a check if the game has ended, but we can do that in the loop, in Scala we try to avoid break(able)

}
