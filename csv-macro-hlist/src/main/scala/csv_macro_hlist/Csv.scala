package csv_macro_hlist

import language.experimental.macros
import scala.reflect.makro.Context
import shapeless._
import Nat._
import Sized._
import HList._

object Csv {
  def split(s:String) = macro splitm
  
  def splitm(c:Context)(s:c.Expr[String]):c.Expr[Any] = {
    import c.universe._
    val Literal(Constant(str:String)) = s.tree
    val l = List(str.split(","):_*)
    val nil = reify(shapeless.HNil).tree
    val res = l.map(strToLit(c)(_)).foldRight(nil)((elem, acc) => {
      Apply(Select(acc, newTermName("$colon$colon")), List(elem))
    })
    c.Expr(res)
  }
  
  def strToLit(c:Context)(s:String) = {
    import c.universe._
    
    def doubleParser(s:String) = 
      if ("""^[0-9]+\.[0-9]+$""".r.findFirstIn(s).isDefined)
        Some(s.toDouble)
      else
        None
    
    def intParser(s:String) =
      if ("""^[0-9]+$""".r.findFirstIn(s).isDefined)
        Some(s.toInt)
      else
        None
        
    def stringParser(s:String) = Some(s)
    
    val parsers = List(intParser _, doubleParser _, stringParser _)
    val res = parsers.view.flatMap(_(s)).head
    Literal(Constant(res))
  }
  
}
