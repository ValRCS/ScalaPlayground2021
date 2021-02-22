object SongExercise extends App {
  //TODO create 2 song objects with authors, title and lyrics
  //TODO call sing and yell methods
  val putVejiniLyrics: Seq[String] = Seq("Pūt, vējiņi, dzen laiviņu",
    "Aizdzen mani Kurzemē",
    "Kurzemniece man solīja",
    "Sav’ meitiņu malējiņ’",
    "Solīt sola, bet nedeva",
    "Teic man lielu dzērājiņ’")

  //this is called object instantiation using Song blueprint
  val putVejini = new Song("Pūt, vējiņi", "Unknown", putVejiniLyrics)

  putVejini.yellSong()
  putVejini.singSong()
  putVejini.singBonus(2)
  putVejini.singBonus()

  val trisLietasLyrics:Seq[String] = Seq("Trīs lietas man zāļu skapītī stāv",
    "Ko meklēju brīžos, kad šķiet -",
    "Vairs peldot es nesniegšu malu",
    "Asaras, dziesmas un alus")

  val trisLietas = new Song("Trīs lietas", "R.Pauls", trisLietasLyrics)

  trisLietas.singBonus(2)  //prints all it has, worked with 8 times entered
  trisLietas.singBonus(12)  //prints all it has, worked with 8 times entered
}
