package scala.tasty.internal
package dotc
package core

trait TTypeApplications {
  self: API =>

  import Types._
  import Symbols._
  import Decorators._
  import Names._
  import NameOps._
  import Flags._
  import StdNames._
  import util.Positions.Position
  import config.Printers._
  import collection.mutable

  class TypeApplications(val self: Type) /*extends AnyVal*/ {
    def isInstantiatedLambda: Boolean = ???

    final def argInfos(interpolate: Boolean): List[Type] = {
      val initType = self.initType
      initType match {
        case g.NoType => Nil
        case _ => convertTypes(initType.typeArgs)
      }
    }

    final def argInfos: List[Type] = argInfos(interpolate = true)

    final def withoutArgs(typeArgs: List[Type]): Type = typeArgs match {
      case _ :: typeArgs1 =>
        val RefinedType(tycon, _) = self
        tycon.withoutArgs(typeArgs1)
      case nil =>
        self
    }
  }
}
