
case class Produce(category:String, item:String,variety:String,date:String,price:Double,unit:String)
import java.time
case class ProduceWithDate(category:String, item:String,variety:String,date:time.LocalDate,price:Double,unit:String)
