import scala.collection.mutable.ArrayBuffer
import scala.math.{max, min}

trait Rectangular {
  def topLeft: Point
  def bottomRight: Point
  def left: Int = topLeft.x
  def right: Int = bottomRight.x
  def width: Int = right - left
  def height: Int = topLeft.y - bottomRight.y
  def area: Int = width * height
  private val minX = min(topLeft.x, bottomRight.x)
  private val maxX = max(topLeft.x, bottomRight.x)
  private val minY = min(topLeft.y, bottomRight.y)
  private val maxY = max(topLeft.y, bottomRight.y)

  def isInside(p: Point): Boolean = p.x >= minX && p.x <= maxX && p.y >= minY && p.y <= maxY
//  var frogs: ArrayBuffer[Frog]
}
