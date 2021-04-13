import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

object LogTest extends App {
  println("Testing Logging functionality")
  //TODO LOG something
  val myName = "Valdis"

  val parentLogger = LoggerFactory.getLogger("com.github.valrcs")
  parentLogger.info("simple log message, we do not really need other loggers")

  val logger = Logger("name")


  logger.info("Hello there logging")
  logger.debug(s"Hello there $myName")
  logger.warn("Achtung! Warning, something is not quite right")

  val loggerSecondary = Logger("secondary")
  loggerSecondary.info("This is also a log")

  def myMethod(): Unit = loggerSecondary.debug("Testing")

  myMethod()
  myMethod()
  myMethod()

  parentLogger.info(s"This should also give a greeting to $myName")
}
