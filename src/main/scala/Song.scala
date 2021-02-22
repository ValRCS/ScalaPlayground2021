class Song(title:String, author:String, lyrics: Seq[String]) {
  //TODO create a Song class which is constructed with 3 parameters
  //title
  //author
  //Sequence of lyrics
  //when we create a new object from Song class print the title author and that song is made

  //when we create a new object from Song class print the title author and that song is made
  println(s"$author: $title <-- New Song created!")

  //TODO methods to sing song line by line on the screen
  def singSong() = {
    lyrics.foreach(println)
  }
  //TODO method yell (ALL CAPS) the lyrics on the screen
  def yellSong() = {
    for (line <- lyrics)
      println(line.toUpperCase())
  }

  def singBonus(times:Int = -1): Unit = {
    if (times == -1) lyrics.foreach(println)
    else lyrics.slice(0,times).foreach(println)
//    else {
//      for (line <- lyrics.slice(0, times))
//        println(line)
//    }

  }


  //TODO methods to sing song line by line on the screen

  //TODO method yell (ALL CAPS) the lyrics on the screen

  //bonuss add additional parameter maxLines to above methods, giving it default maybe -1
  //so maxLines if not specified would print all lyrics
  //if specified some positive number would print the number of maxLines
}
