import com.github.valrcs.Utilities

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
//  val url = "http://www.recipepuppy.com/api/?i=onions,garlic&q=omelet&p=3"
//  val recipeString = fromURL(url).mkString
////  println(recipeString) just a raw string
//  val recipePuppy = ujson.read(recipeString).obj
//  val recipes = recipePuppy("results")
//  val recipeArr = recipes.arr
//  recipeArr.foreach(println)
//  val recipeArrObj= recipeArr.toArray.map(_.obj)
//  val recipeArrMap = recipeArrObj.map(recipe => recipe.transform((k,v) => v.str))
//  recipeArrMap.foreach(println)

  //TODO extract recipes for ingredients containing potatoe and name of the dish would be salad
  //you should be able to get the raw and string and see about tranforming it into a sequence
  //TODO remember that we want the results not the whole JSON
  //TODO transform data types into pure Scala types so Sequence of Maps (String, String) Seq[Map[String,String]]
  val saladUrl = "http://www.recipepuppy.com/api/?i=potato&q=salad" //we could use string interpolation to add values
  val saladString = fromURL(saladUrl).mkString
//  println(saladString)
  val recipePuppy = ujson.read(saladString).obj
  val recipes = recipePuppy("results").arr.toSeq //we know it will be array
  recipes.foreach(println)
  val recipeStr = ujson.write(recipes,  indent = 4) //so we create a new JSON formatted string (plain text)
  println(recipeStr)
  val recipesArr = upickle.default.read[Seq[Map[String,String]]](recipeStr) //now we can get the right data types
  recipesArr.foreach(println)

  val relative_save_path = "./src/resources/recipes.json"

  Utilities.saveString(recipeStr, relative_save_path)

  val ingredients = recipesArr.map(recipe => recipe("ingredients"))
  ingredients.foreach(println)

  //TODO make one big string of all  ingredients
  val ingredientString = ingredients.mkString(",")
  println(ingredientString)
  //TODO count the ingredients //we could probably use groupby //or count in buckets
  val individualItems = ingredientString.split(",").map(_.trim)
  println(individualItems.length)
  individualItems.slice(0,10).foreach(println)

  //now lets count occurences
  //rather we will group individual ingredients and then map each ingredients name to their grouping size
  val itemCount = individualItems.groupBy(identity).view.mapValues(_.size) //view is added for efficiency reasons
  itemCount.foreach(println)
  val sortedItems = itemCount.toArray.sortBy(item => item._2).reverse
  //TOP 10 ingredients
  sortedItems.slice(0,10).foreach(println)

  //not sortedMap but ListMap
  //thank you Alvin: https://alvinalexander.com/scala/how-to-sort-map-in-scala-key-value-sortby-sortwith/
  val sortedMap = sortedItems.to(collection.immutable.ListMap) //O(n^2) builder so good for smaller collections
  val ingredientSavePath = "./src/resources/top_ingredients.json"
  val topString = ujson.write(sortedMap,  indent = 4)
  sortedMap.foreach(println)

  Utilities.saveString(topString, ingredientSavePath)

}
