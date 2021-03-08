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

  def buildProduce(rawLine: String):Produce = {
    val tokens = rawLine.split(",").map(_.trim)
    //FIXME the next line could fail if the split did not work
    Produce(tokens(0), tokens(1), tokens(2), tokens(3), tokens(4).toDouble, tokens(5))
  }
  //tail selects everything but the first element, LOOONG Tail
  //we could have used slice(1, lines.length-1) but that is uglier
  val produceArray = lines.tail.map(line => buildProduce(line))

  produceArray.slice(0,10).foreach(println)

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
    val meanPrice = prices.sum / prices.length
    s"Produce $itemName min price $minPrice max: $maxPrice average: $meanPrice"
  }

  println(getProduceStats(beets))

  println(getProduceStats(produceArray, "apples"))
  println(getProduceStats(produceArray, "gala"))


  //TODO get pricing for tulips

  //extra challenge for those who are already comfortable
  //TODO get stats for tulips for each year from 2015 to 2021

  //TODO get 3 top prices


  //TODO after break we will get results including the date
}
