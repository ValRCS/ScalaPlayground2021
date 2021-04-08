import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import scala.collection.mutable.ListBuffer
import scala.beans.BeanProperty
import java.io.{File, FileInputStream}

object ReadingYAML extends App {
  println("Reading YAML")
  val relativePath = "config.yaml" //again in our home directory //possible better place would be special config folder
  val input = new FileInputStream(new File(relativePath))
  val yaml = new Yaml(new Constructor(classOf[GameSettings]))
  //here YAML constructor will use our GameSettings class to automagically retrieve right structure
  val settings = yaml.load(input).asInstanceOf[GameSettings]  //so parsing happens here
  println(settings)

}

/**
 * With the Snakeyaml Constructor approach shown in the main method,
 * this class must have a no-args constructor.
 */
class GameSettings {
  @BeanProperty var defaultMatches = 21
  @BeanProperty var minStartingMatches = 5
  @BeanProperty var maxStartingMatches = 15
  @BeanProperty var playerA = "Valdis"
  @BeanProperty var playerB = "Liga"
//  @BeanProperty var usersOfInterest = new java.util.ArrayList[String]()
  override def toString: String = s"A: $playerA, userB: $playerB defaultMatches: $defaultMatches"
}

//minStartingMatches : 5
//maxStartingMatches : 15
//playerA : Valdis
//playerB : Liga