class Point(val x: Int, val y: Int) {
  //blank constructor for now
  def formatCoordinates = s"x:$x - y: $y"
  def add(that: Point) = new Point(this.x+that.x, this.y+that.y)
  def +(that: Point) = new Point(this.x+that.x, this.y+that.y)
}

class Rectangle(val topLeft: Point, val bottomRight: Point) extends Rectangular {
  override def toString: String = s"Rect topLeft:${topLeft.formatCoordinates} " +
    s"to bottomRight: ${bottomRight.formatCoordinates}"


}
