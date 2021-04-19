package com.github.valrcs

object LineCounter extends App {
  println("Getting some command line arguments")
//  for (arg <- args) {
//    println(s"Processing command line argument $arg")
//  }
  var lineCount: Int = 0
  if (args.length > 0) {
    val fileName = args(0)
    println(s"Opening file $fileName")
    val lines= Utilities.getLinesFromFile(fileName)
    lineCount = lines.length
    println(s"There are $lineCount lines in file $fileName")
  }
}
