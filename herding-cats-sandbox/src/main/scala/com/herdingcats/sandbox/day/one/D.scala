package com.herdingcats.sandbox.day.one


object D {
  import Monoid.syntax._

  def main(args: Array[String]): Unit = {
    1
  }
    println(1 |+| 2)
  }

trait Monoid[A] {
  def mappend(a: A, b: A): A
  def mzero: A
}

object Monoid {

  object syntax extends MonoidSyntax

  implicit val IntMonoid: Monoid[Int] = new Monoid[Int] {
    def mappend(a: Int, b: Int): Int = a + b

    def mzero: Int = 0
  }
  implicit val StringMonoid: Monoid[String] = new Monoid[String] {
    def mappend(a: String, b: String): String = a + b

    def mzero: String = ""
  }
}

trait MonoidSyntax {
  implicit final def syntaxMonoid[A: Monoid](a: A) : MonoidOps[A] = new MonoidOps[A](a)
}

final class MonoidOps[A: Monoid](lhs: A) {
  def |+|(rhs: A): A = implicitly[Monoid[A]].mappend(lhs, rhs);
}
