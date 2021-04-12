package com.github.valrcs

import java.io.File

import com.github.valrcs.Utilities.clamp

import scala.io.StdIn.readLine
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor

import scala.collection.mutable.ListBuffer
import scala.beans.BeanProperty
import java.io.{File, FileInputStream}
import java.sql.{Connection, DriverManager, PreparedStatement}

//example on how to read .ini files
//object GameConstants {
//  import org.ini4j.Ini
//  import org.ini4j.IniPreferences
//
//  val relative_path = "settings.ini" //relative path starting in our home directory for our project
//  val ini = new Ini(new File(relative_path))
//  val prefs = new IniPreferences(ini)
//  val defaultMatches = prefs.node("Nim").get("defaultMatches", null).toInt
//  val minStartingMatches = prefs.node("Nim").get("minStartingMatches", null).toInt
//  val maxStartingMatches = prefs.node("Nim").get("maxStartingMatches", null).toInt
//  val playerA = prefs.node("Nim").get("playerA", null)
//  val playerB = prefs.node("Nim").get("playerB", null)
//  println(s"defaultMatches $defaultMatches, minStart: $minStartingMatches, maxStart: $maxStartingMatches")
//}
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



object Nim extends App {
  //https://en.wikipedia.org/wiki/Nim#The_21_game
  val r = new scala.util.Random
  val state = init()

  def init():GameState = {
    println("Let's play a game of Nim!")

    //we will want to have some state for our game
    //in our case our game state will be simple just an integer holding count of our matches
    val playerA = readLine(s"What is your name Player A? (Press Enter to use default ${GameConstants.playerA})")

    //idea being that we pass the playerA to our object constructor
    val state = if (playerA.length == 0) new GameState() else new GameState(playerA = playerA)
    //the above logic could have lived in the GameState class

    state.matches = if (readLine("Do you want start with random number of matches? ")
      .toUpperCase
      .startsWith("Y")) {
      r.between(GameConstants.minStartingMatches, GameConstants.maxStartingMatches)
    } else GameConstants.defaultMatches

    state.isPlayerBComputer = readLine("Do you want to play against computer (Y/N)?").toUpperCase.startsWith("Y") //we could have added a more complex if
    if (state.isPlayerBComputer) {
      state.computerLevel = readLine("How strong a computer you want (1-3)? ").toInt
      state.computerLevel = clamp(state.computerLevel, state.minMove, state.maxMove)
    } else state.playerB = readLine("What is your name Player B?")

    //TODO get topPlayers from DataBase
    state.topPlayers = getTopPlayers(GameConstants.dbUrl)
    state.showTopPlayers()
    state
  }

  def migrateTable(conn:Connection) = {
    val statement = conn.createStatement() //Creates a Statement object for sending SQL statements to the database. S

    val sql =
      """
        |CREATE TABLE IF NOT EXISTS players (
        |	playerID INTEGER PRIMARY KEY,
        |   	name TEXT NOT NULL,
        |	wins INTEGER DEFAULT 0,
        | losses INTEGER DEFAULT 0
        |);
        |""".stripMargin

    statement.executeUpdate(sql)
  }
  def savePlayers(db: String, players: Seq[Player]):Unit = {
    val conn = DriverManager.getConnection(db)
    //get current players and insert into databese if the do not exist otherwise update
//    insertPlayer(conn, Player("Valdis", 5, 10)) //FIXME with real player stats
    if (state.topPlayers.exists(p => p.name == state.winner)) {
      val winner:Player = state.topPlayers.find(p => p.name == state.winner).getOrElse(Player("nil", 0,0)) //could improve on this
      updatePlayer(conn, Player(state.winner, winner.win+1, winner.lose))
    } else insertPlayer(conn, Player(state.winner, 1, 0))


    if (state.topPlayers.exists(p => p.name == state.loser)) {
      val loser:Player = state.topPlayers.find(p => p.name == state.loser).getOrElse(Player("nil", 0,0))
      updatePlayer(conn, Player(state.loser, loser.win, loser.lose+1))
    } else insertPlayer(conn, Player(state.loser, 0, 1))

  }

  def updatePlayer(connection: Connection, player: Player):Unit = {
    println(s"Updating Player $player")
    val updateSql = {
      """
        |UPDATE players
        | SET wins = ?,
        |     losses = ?
        |   WHERE
        |   name = ?
      """.stripMargin
      //we are only going to update those rows with matching player name, in this case it will be only one row
    }
    val preparedStmt: PreparedStatement = connection.prepareStatement(updateSql)
    //sql 1 indexed here

    preparedStmt.setInt (1, player.win)
    preparedStmt.setInt (2, player.lose)
    preparedStmt.setString (3, player.name)
    preparedStmt.execute
    preparedStmt.close()
  }


  def insertPlayer(connection: Connection, player: Player):Unit = {
    println(s"Inserting Person $player")
    val insertSql =
      """
        |INSERT INTO players(
        |    name,
        |    wins,
        |    losses)
        |    VALUES(?,?,?)
      """.stripMargin
    val preparedStmt: PreparedStatement = connection.prepareStatement(insertSql)
    //sql 1 indexed here
    preparedStmt.setString (1, player.name)
    preparedStmt.setInt (2, player.win)
    preparedStmt.setInt (3, player.lose)
    preparedStmt.execute
    preparedStmt.close()
  }


  def getTopPlayers(db: String): Seq[Player] = {
    println(s"Getting my top players from database at $db")
    import java.sql.DriverManager

    val conn = DriverManager.getConnection(db)
    migrateTable(conn) //creating table if it does not exist //TODO move it out of getTopPlayers
    val statement = conn.createStatement()
    val sql =
      """
        |SELECT * from players
        |ORDER BY wins DESC
        |""".stripMargin
//so I took off LIMIT 10 since I can easily hold all players in memory

    val resultSet = statement.executeQuery(sql)
    val playerList = scala.collection.mutable.ListBuffer.empty[Player]

    while ( resultSet.next() ) {
      val player = Player(resultSet.getString("Name"),
        resultSet.getString("wins").toInt,
        resultSet.getString("losses").toInt)
      playerList.append(player)
      //    println(row.size)
    }
    conn.close()
    playerList.toSeq
  }
  //for one off calls this would also work with fullname
  //  computerLevel =  com.github.valrcs.Utilities.clamp(computerLevel, minMatches, maxMatches)
  //  computerLevel =  Utilities.clamp(computerLevel, minMatches, maxMatches) //works because of wildcard import with _
  //careful with wildcard imports you might sometimes get a collision


  def computerMove(matches: Int, computerLevel: Int): Int = computerLevel match {
    case 1 => stupidComputer()
    case 2 => randomComputer() //between last number is exclusive
    case 3 => smartComputer(matches)
    case _ => 1 //TODO add logging here, this should never happen
  }

  def stupidComputer(): Int = 1
  def randomComputer(): Int = r.between(state.minMove, state.maxMove + 1)

  def smartComputer(matches: Int): Int = {
    //    val take = matches match {
    //      case 4 | 8 | 12 | 16 | 20 => 3
    //      case 3 | 7 | 11 | 15 | 19 => 2
    //      case _ => 1
    //    }
    //more universal
//    val take = matches match {
//      case matches if matches % 4 == 0 => 3 //we are using case gourd
//      case matches if matches % 4 == 3 => 2
//      case matches if matches % 4 == 2 => 1
//      //we always want a default case
//      //here default is same as matches % 4 == 1
//      //this is the case when computer is losing
//      case _ => r.between(state.minMove, state.maxMove + 1) //idea being that if computer is in bad shape it should do random choice
//    }
    val take = matches match {
      case matches if matches % 4 == 1 => r.between(state.minMove, state.maxMove + 1) //computer is going to lose so let it do something random
      case _ => (matches-1) % 4 //matches + 3 or matches +7 would work just as well
    }
    take
  }

  //main game loop
  while (state.matches > 0) {
    println(s"We have ${state.matches} left, it is ${getPlayerTurn(state)} turn")

    var matchesTaken = 0
    //computer check
    if (!state.isPlayerATurn && state.isPlayerBComputer) matchesTaken = computerMove(state.matches, state.computerLevel)

    while (matchesTaken < state.minMove || matchesTaken > state.maxMove) {
      println(s"Please choose between ${state.minMove} and ${state.maxMove} matches")
      matchesTaken = readLine(s"How many matches do you want to take ${getPlayerTurn(state)}?").toInt
    }

    state.matches -= matchesTaken
    state.isPlayerATurn = !state.isPlayerATurn //old trick on toggling boolean value
    //showing Game State

  }

  println(s"Congratulations you won ${getPlayerTurn(state)}!")
  state.winner = getPlayerTurn(state)
  //we will do an empty turn to get loser's name
  state.isPlayerATurn = !state.isPlayerATurn //old trick on toggling boolean value
  state.loser = getPlayerTurn(state)
  //TODO save player information here
  savePlayers(GameConstants.dbUrl, state.topPlayers) //FIXME with real players

  def getPlayerTurn(state:GameState): String = {
    if (state.isPlayerATurn) state.playerA
    else if (state.isPlayerBComputer) "Computer"
    else state.playerB
  }

  //we also need a check if the game has ended, but we can do that in the loop, in Scala we try to avoid break(able)

}
