package com.herdingcats.sandbox.day
import cats._, cats.syntax.all._

package object two {
  // Eq
  1 === 1
  1 === "foo"
  (Some(1): Option[Int]) =!= (Some(2): Option[Int])

  // Ord
  1 compare 2.0
  1.0 max 2.0

  // PartialOrder
  1 tryCompare 2
  1.0 tryCompare Double.NaN
  def lt[A: PartialOrder](a1: A, a2:A) = a1 <= a2
  lt(1, 2.0)
  // Show
  3.show
  "hello".show
  case class Person(name: String)
  case class Car(model: String)

  {
    implicit val personShow = Show.show[Person](person => person.name)
    Person("Alice").show
  }

  {
    implicit val carShow = Show.fromToString[Car]
    Car("CR-V")
  }
}
