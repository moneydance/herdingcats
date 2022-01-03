package com.herdingcats.sandbox.day.one

import io.circe.generic.JsonCodec, io.circe.syntax._

object A {
  def main(args: Array[String]): Unit = {
    // Parametric Polymorphism
    ParametricPolymorphism.run()
  }
}

// Parametric polymorphism refers to when the type of a value contains one or more (unconstrained) type variables,
// so that the value may adopt any type that results from substituting those variables with concrete types.
object ParametricPolymorphism {
  def run(): Unit = {
    val res = head(Car("Civic") :: Car("CR-V") :: Nil)
    println(res.asJson)
  }
  private def head[A](list: List[A]): A = list(0)
  @JsonCodec case class Car(make: String)
}

object SubtypePolymorphism {
  // We'd like to define a generic plus function but implementation depends on the generic type
  def plus[A](a1: A, a2: A): A = ???
  // We could define a trait
  trait PlusIntf[A] {
    def plus(a2: A): A
  }
  // And only allow plusBySubtype to function wiht generics that implement this trait
  // However, this isn't flexible since the trait needs to me mixed in during the
  // definition of the datatype. Obviously, we dont have control of this for system
  // or external datypes
  def plusBySubtype[A <: PlusIntf[A]](a1: A, a2: A): A = a1.plus(a2)
}

object AdHocPolymorphism {
  trait CanPlus[A] {
    def plus(a1:A, a2:A): A
  }
  def plus[A: CanPlus](a1: A, a2: A): A = implicitly[CanPlus[A]].plus(a1, a2)
}

// covariant implies that for two types A and B where B is a subtype of A, then List[B] is a subtype of List[A]. e.g a list of cats is a list of animals
// contravariant implies that for two types A and B where A is a subtype of B, Printer[B] is a subtype of Printer[A]. e.g An animal printer should be able to be passed for a cat printer
// Implicitly is roughly defined as def implicitly[T](implicit e: T): T = e implicitly is a function that looks for an implicit definition for the generic type T and return that value

