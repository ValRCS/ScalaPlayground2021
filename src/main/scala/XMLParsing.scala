import scala.xml.XML
//import scala.xml

object XMLParsing extends App {
  //so these XML tags we just made up for this particular exercise, they can match HTML but not necessarily
  val a = <article>An article about something</article> //so we did not even need to write quotes around our text
  println(a) //so a is an xml Elem
  val p = <p>Just a paragraph</p>
  println(p)

  val pod = <pod>Three <peas>Some <justEmpty/>Peaas</peas> in the </pod> //example of Nesting
  println(pod)
  val pod2 = <pod peas="3" strings="true"/> //example of XML tag with attributes self closing
  println(pod2)

  val myName = "Valdis"
  val greetings = <greeting>Hello there {myName}</greeting> // so i can interpolate Scala code into XML element
  //interpolation can be dangerous if we have no control of what goes inside the { }
  //not a good practice for user inputs, an evil user could input some evil code
  println(greetings)
  println(greetings.text)
  println(pod.text) //so we get ALL of the text of parent and its childrend and grandchildren and so on

  val relativePath = "./src/resources/lnb-4.xml"
  //loads XML file and parses
  val xml = XML.loadFile(relativePath) //so val xml is points to the root element of this particular document
  println(xml)

  val digitalObject = xml \ "digitalObject"
  println(digitalObject)



//  val digitalChildren = digitalObject.head.child //of course we'd need to check length here
//  println("*"*40)
//  digitalChildren.foreach(println)

  val xmlChildren = xml.child
  xmlChildren.foreach(el => println(el.label))

  val title = xml \ "title"
  println(title)
  println(s"Title of this Object is ${title.text} ")

  val subjects = xml \ "subject"
  subjects.foreach(println)
  val uris = subjects.map(subject => subject.attribute("uri"))  //of course here we know that uri attribute exists

  subjects.foreach(subject => println(subject.attribute("uri")))

  val copyRight = xml \ "copyright"
  println(copyRight.length)
  println(copyRight.head) //we could possible have multiple matches with copyright tag
  println(copyRight.head.attribute("code"))
  println(copyRight.head.attribute("code").getOrElse("Did not find it"))

  val fileNames = xml \ "files" \ "file"
  for (file <- fileNames) {
    println(file.attribute("name").getOrElse("File Name not found"))
  }

  val fileNameStrings = for (file <- fileNames)  yield file.attribute("name").getOrElse("File Name not found")
  fileNameStrings.foreach(println)

  //you have the whole xml stored in val xml
  //TODO get text contents of subType

  val subTypes = xml \ "subType"
  subTypes.foreach(subType => println(subType.text))
  //remember that most matches are in some sort of sequence because in XML you could have multiple matches
  //TODO get size attribute for each file (this is in bytes)
  val fileSize = xml \ "files" \ "file"
  val fileSizeBytes = for (file <- fileSize) yield file.attribute("size").getOrElse("_")
  println("FileSize in Bytes")
  fileSizeBytes.foreach(println)


  //TODO hardest challenge get size in KBytes from each files <fileMetadata><field name="Size">228 KB</field> .... </fileMetadata>
  val allFields = xml \ "files" \ "file" \ "fileMetadata" \ "field"
//  for (field <- allFields) println(field.attribute("name").getOrElse(""))
//  val allFieldSizes = for (field <- allFields) yield field.attribute("name").getOrElse("")
  println(s"allFieldSizes ${allFields.length}")


  //we had to get convert our value to string
  val allSizes = allFields.filter(field => field.attribute("name").getOrElse("").toString == "Size")
  println(s"We got field elements which have attribute name value Size  ${allSizes.length}")
  allSizes.foreach(println)
  val kbSizes = allSizes.map(node => node.text)
  kbSizes.foreach(println)
  //We will do this after 21:00


//  val allNames = xml \ "files" \ "file" \ "fileMetadata" \ "field" \ "@name"
//  println(s"We have fields with name attribute ${allNames.length}")

  val fieldData = xml \\ "field" //so we get all children/grandchildren etc of whole xml with tag named field
  val filtered = for(node <- fieldData if node.text.contains("KB")) yield node
  filtered.foreach(node => println(node.text))

  val bookPath = "./src/resources/books.xml"

  val bookXML = XML.loadFile((bookPath))

  def nodeToBook(node: scala.xml.Node):Book = {
    val title = (node \ "title") .text
    val author = (node \ "author") .text
    val year = (node \ "year" ).text.toInt
    val price = (node \ "price").text.toDouble
    val category = node.attribute("category").getOrElse("NO CATEGORY").toString
    Book(title,author, year, price, category)
  }
  val bookNodes = bookXML \ "book"
  val books = bookNodes.map(node => nodeToBook(node))
  books.foreach(println)
  //so now our books are ready to be transferred to database or CSV or some other data structrure
  //even gasp back to XML :)
//    <book category="children">
  //        <title>Harry Potter</title>
  //        <author>J K. Rowling</author>
  //        <year>2005</year>
  //        <price>29.99</price>
  //    </book>

}
