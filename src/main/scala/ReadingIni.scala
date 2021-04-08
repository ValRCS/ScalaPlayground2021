import java.io.File

object ReadingIni extends App {

  import org.ini4j.Ini
  import org.ini4j.IniPreferences

  val relative_path = "settings.ini" //relative path starting in our home directory for our project
  val ini = new Ini(new File(relative_path))
  val prefs = new IniPreferences(ini)
  println("My Name is " + prefs.node("People").get("name", null))
}
