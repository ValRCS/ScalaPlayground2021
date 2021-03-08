object ParsingCSV extends App{
  val relativePath = "src/resources/fruitvegprices-8mar21.csv"
  val lines = Utilities.getLinesFromFile(relativePath)

  println(s"Got ${lines.length} lines")
  lines.slice(0,10).foreach(println)

//  val cols = lines.map(line => line.split(","))
  //we split each line into tokens by , separator an then trim whitespace from each token
  val cols = lines.map(line => line.split(",").map(_.trim))
  //so we obtained 2D Array that Array of Arrays of Strings
  cols.slice(0,10).foreach(col => println(col.mkString("||")))

  //lets get some carrots
  val carrots = cols.filter(tokenizedLine => tokenizedLine(1) == "carrots")
  println(carrots.length)
//  carrots.slice(0,10).foreach(col => println(col.mkString("\\o/")))
  carrots.slice(0,10).foreach(col => println(col.mkString("\t")))

  val carrotPrices = carrots.map(carrot => carrot(4).toFloat) //conversion could fail here with bad CSV
  println(carrotPrices.slice(0,10).mkString(","))

  //lets get our min, max and average
  val carrotMin = carrotPrices.min
  val carrotMax = carrotPrices.max
  val carrotAvg = carrotPrices.sum / carrotPrices.length

  println(s"Carrots in UK: top price $carrotMax , lowest $carrotMin and average is $carrotAvg")

  //TODO convert our Array of Array of strings into Array of PriceEntries

  def buildProduce(rawLine: String, sep: String =","):Produce = {
    val tokens = rawLine.split(sep).map(_.trim)
    //FIXME the next line could fail if the split did not work
    if (tokens.length == 6)
    Produce(tokens(0), tokens(1), tokens(2), tokens(3), tokens(4).toDouble, tokens(5)) //everything is great!
    else Produce("EMPTY", "", "", "",0.0, "") //so we delay the act of failing to actual parsing of data
    //better would be to also log the information to log file that something failed and deal with that
  }

  import java.time
  def buildProduceWithDate(rawLine: String, sep: String =","):ProduceWithDate = {
    val tokens = rawLine.split(sep).map(_.trim)
    //FIXME the next line could fail if the split did not work
    if (tokens.length == 6) {
//      val dateTokens = tokens(3).split("-")
//      val year = dateTokens(0)
//      val month = dateTokens(1)
//      val day = dateTokens(2)
      import java.time.format.DateTimeFormatter
      val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
      import java.time.LocalDate
      val localDate = LocalDate.parse(tokens(3), formatter)
      ProduceWithDate(tokens(0), tokens(1), tokens(2), localDate, tokens(4).toDouble, tokens(5)) //everything is great!
    }
    else {
      import java.time.LocalDate
      val blankDate = LocalDate.parse("00-00-00")
      ProduceWithDate("EMPTY", "", "", blankDate,0.0, "")
    } //so we delay the act of failing to actual parsing of data
    //better would be to also log the information to log file that something failed and deal with that
  }
  //tail selects everything but the first element, LOOONG Tail
  //we could have used slice(1, lines.length-1) but that is uglier
  val produceArray = lines.tail.map(line => buildProduce(line))
  val produceArrayDates = lines.tail.map(line => buildProduceWithDate(line))

  produceArray.slice(0,10).foreach(println)

  println("*"*40)
  produceArrayDates.slice(0,10).foreach(println)

  val beets = produceArray.filter(produce => produce.item.contains("beet"))
  beets.slice(0,5).foreach(println)

  def getProduceStats(produceArray : Array[Produce], itemName: String = ""):String = {

    val filteredProduce = produceArray.filter(line => line.variety.contains(itemName))
    //Scala discourages early return but we can do it here
    //sometimes early return might make things simpler
    if (filteredProduce.length == 0) return s"Can't find $itemName Nothing to calculate!"

    val prices = filteredProduce.map(produce => produce.price)
    val minPrice = prices.min
    val maxPrice = prices.max
    val meanPrice = Math.round(prices.sum * 100/ prices.length ) / 100 // kind of ugly a better round function would be nice
    s"Produce $itemName min price $minPrice max: $maxPrice average: $meanPrice"
  }

  println(getProduceStats(beets))

  println(getProduceStats(produceArray, "apples"))
  println(getProduceStats(produceArray, "gala"))


  //TODO get pricing for tulips
  println(getProduceStats(produceArray, "tulip"))

  def getProduceStatsYearly(produceArray : Array[Produce], itemName: String = "", year: String=""):String = {

//    val filteredProduce = produceArray.filter(line => line.variety.contains(itemName) && line.date.slice(0,4).contains(year))
    val filteredProduce = produceArray.filter(line => line.variety.contains(itemName) && line.date.startsWith(year))
    //Scala discourages early return but we can do it here
    //sometimes early return might make things simpler
    if (filteredProduce.length == 0) return s"Can't find $itemName Nothing to calculate!"


    val prices = filteredProduce.map(produce => produce.price)
    val minPrice = prices.min
    val maxPrice = prices.max
    val meanPrice = Math.round(prices.sum * 100/ prices.length ) / 100.0 // kind of ugly a better round function would be nice
    s"Produce $itemName min price $minPrice max: $maxPrice average: $meanPrice"
  }

  //use a copied function with YEAR separation built in
  for (year <- 2015 to 2021) {
    println(s"year $year:")
    println(getProduceStatsYearly(produceArray, "tulips", year.toString))
  }
  //extra challenge for those who are already comfortable
  //TODO get stats for tulips for each year from 2015 to 2021

  //TODO get 3 top prices
  val tulips = produceArray.filter(produce => produce.item.contains("tulips"))
  println("Top three prices per stem:")
  //most expensive dates
  tulips.sortBy(-_.price).filter(produce => produce.unit.contains("stem")).slice(0,3).foreach(println) //- before price reverses order
  //slightly different results since we do not have tiebreak rule after price
  tulips.sortBy(_.price).filter(produce => produce.unit.contains("stem")).reverse.slice(0,3).foreach(println)

  //cheapest prices
  tulips.sortBy(_.price).filter(produce => produce.unit.contains("stem")).slice(0,3).foreach(println) //- before price reverses order

  val top10tulips = tulips.sortBy(-_.price).filter(produce => produce.unit.contains("stem")).slice(0,10)

  val savePath =  "src/resources/topTulips.csv"

  //helper function for transform of Produce back into CSV ready format line
  def getProduceCSV(pr: Produce): String = s"${pr.category},${pr.item},${pr.variety},${pr.date},${pr.price},${pr.unit}"
  
  val columnNames = Array("category,item,variety,date,price,unit")
  val tulipProduceStrings = top10tulips.map(line => getProduceCSV(line))
  val rawTopTulips = columnNames.concat(tulipProduceStrings)

  Utilities.saveLines(rawTopTulips, savePath)
}
