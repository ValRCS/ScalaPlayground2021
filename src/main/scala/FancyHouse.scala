class FancyHouse extends House(houseName = "Fancy", heating="nuclear") {
  val paintings = scala.collection.mutable.ArrayBuffer("Leonardo", "Boticelli")
  //mutable Sequence we could change values but not add
  //generally ArrayBuffer would be converted to some immutable Sequence later on

  def addNewPainting(paintName:String) = {
    paintings += paintName
    paintings.foreach(println)
  }
}
