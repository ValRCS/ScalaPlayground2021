import scala.io.Source
import scala.io.Source.fromURL

object ProcessingJSON extends App {
  val relativePath = "./src/resources/employees.json"
  val bufferedSource = Source.fromFile(relativePath)
  val rawString = bufferedSource.mkString("") //we want it all in one big chunk not just by lines
  println(rawString)
  val data = ujson.read(rawString) //here we process/parse our raw JSON string into some Data format
  val seq = data.arr.toArray
  seq.foreach(println)
  val seqMaps = seq.map(item => item.obj.toMap)
  seqMaps.foreach(println)

  val firstEmployee = seqMaps(0)
  print(firstEmployee)
  val firstEmployeeFixed = firstEmployee.transform((k,v) => v.str)
  println(firstEmployeeFixed)
  //if we can fix one employee we can fix all at once
//  val employees = seqMaps.map(employee => employee.transform((k,v) => k.toUpperCase))
  val employees = seqMaps.map(employee => employee.transform((k,v) => v.str))
  employees.foreach(println)

  println("Mr. and Mrs. Smith")
  val smiths = employees.filter(employee => employee("lastName") == "Smith")
  smiths.foreach(println)

  val numbers = upickle.default.read[Seq[Int]]("[1, 2, 3, 4]")
  numbers.foreach(println)

  //so we can in fact extract the structure immediately from JSON string
  val employeesDirectly = upickle.default.read[Seq[Map[String,String]]](rawString)
  employeesDirectly.foreach(println)

  //JSON is the standard way of exchanging information over internet more than XML these days
  val url = "http://www.recipepuppy.com/api/?i=onions,garlic&q=omelet&p=3"
  val recipeString = fromURL(url).mkString
//  println(recipeString) just a raw string
  val recipePuppy = ujson.read(recipeString).obj
  val recipes = recipePuppy("results")
  val recipeArr = recipes.arr
  recipeArr.foreach(println)
  val recipeArrObj= recipeArr.toArray.map(_.obj)
  val recipeArrMap = recipeArrObj.map(recipe => recipe.transform((k,v) => v.str))
  recipeArrMap.foreach(println)

  //TODO extract recipes for ingredients containing potatoe and name of the dish would be salad
  //you should be able to get the raw and string and see about tranforming it into a sequence
  //TODO remember that we want the results not the whole JSON
  //TODO transform data types into pure Scala types so Sequence of Maps (String, String) Seq[Map[String,String]]

}
