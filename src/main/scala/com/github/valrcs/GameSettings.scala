package com.github.valrcs

import java.io.{File, FileInputStream}

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor

import scala.beans.BeanProperty


/**
 * With the Snakeyaml Constructor approach shown in the main method,
 * this class must have a no-args constructor.
 */
class GameSettings {
  @BeanProperty var defaultMatches = 21
  @BeanProperty var minStartingMatches = 5
  @BeanProperty var maxStartingMatches = 15
  @BeanProperty var playerA = "ValdisDefault"
  @BeanProperty var playerB = "Liga"
  @BeanProperty var databaseLocation = "./src/resources/db/nim.db" //we could leave it empty
  //  @BeanProperty var usersOfInterest = new java.util.ArrayList[String]()
  override def toString: String = s"A: $playerA, userB: $playerB defaultMatches: $defaultMatches"
}

//we could have combined GameSettings and GameConstants into a one class
object GameConstants {
  println("Reading YAML")
  val relativePath = "config.yaml" //again in our home directory //possible better place would be special config folder
  val input = new FileInputStream(new File(relativePath))
  val yaml = new Yaml(new Constructor(classOf[GameSettings]))
  //here YAML constructor will use our GameSettings class to automagically retrieve
  // right structure
  val settings: GameSettings = yaml.load(input).asInstanceOf[GameSettings]  //so parsing happens here
  println(settings)
  val defaultMatches: Int = settings.defaultMatches
  val playerA: String = settings.playerA
  val playerB: String = settings.playerB
  val minStartingMatches: Int = settings.minStartingMatches
  val maxStartingMatches: Int = settings.maxStartingMatches
  val dbUrl: String = s"jdbc:sqlite:${settings.databaseLocation}" //for now we only support sqlite
}

case class Player(name: String, win: Int, lose: Int)

//so everything related to game state lives in separate Object
//template for our game truths
class GameState(var matches: Int = GameConstants.defaultMatches,
                val minMove:Int = 1,
                val maxMove:Int = 3,
                var computerLevel:Int = 0,
                var isPlayerBComputer:Boolean = false,
                var isPlayerATurn:Boolean = true,
                var playerA:String = GameConstants.playerA,
                var playerB:String = GameConstants.playerB, //of course for more than say 3 players you'd want them stored in a Map or Sequence
                var topPlayers:Seq[Player] = Seq.empty[Player],
                var winner:String = "",
                var loser:String = ""
               ) {
  //our constructor starts here
  println(s"Instantiated our GameState object with matches:$matches, computerLevel: $computerLevel")
  //at the beginning of game Player A starts
  //this is bare minimum for our game
  //we could have save a variable and used computer level as a selector meaning computerLevel 0 would be human :)
  //we creating a helper method inside our gamestate
  def showTopPlayers(limit:Int = 10):Unit = {
    println("TOP players so far are")
    println("*"*40)
    //    topPlayers.foreach(println) //TODO create a print that prints player name then WINS:1414 versus LOSSES:4342 for example
    topPlayers.slice(0,limit).foreach(player => println(s"${player.name}: ${player.win} wins, ${player.lose} loses"))
    println("*"*40)
  }
}