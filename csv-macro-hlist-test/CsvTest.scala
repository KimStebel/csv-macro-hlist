package csv_macro_hlist_test

import shapeless._
import Nat._
import Sized._

object CsvTest {
  
  def main(args: Array[String]) {
    val list = com.think_scala.dependent.Csv.split("1,plus,1.5,is,2.5")
    list.toList.foreach(println)
    val ru = scala.reflect.runtime.universe
    def sr(expr:ru.Expr[Any]) = println(ru.showRaw(expr.tree))
    sr(ru.reify(shapeless.HNil))
    val t = HNil.::(1)
  }
}
