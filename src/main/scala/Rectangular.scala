import scala.collection.mutable.ArrayBuffer

trait Rectangular {
  def topLeft: Point
  def bottomRight: Point
  def left: Int = topLeft.x
  def right: Int = bottomRight.x
  def width: Int = right - left
  def height: Int = topLeft.y - bottomRight.y
  def area: Int = width * height
//  var frogs: ArrayBuffer[Frog]
}
