package com.herdingcats.sandbox.day.one

object B {
  def main(args: Array[String]): Unit = {
    println(Summer.sum(List(1,2,3,4)))
    println(Summer.sum(List("a", "b", "c")))
  }
}

trait Monoid[A] {
  def mappend(a1:A, a2:A): A
  def mzero: A
}

object Summer {
  def sum[A: Monoid](xs: List[A]): A = {
    val m = implicitly[Monoid[A]]
    xs.foldLeft(m.mzero)(m.mappend)
  }
}

object Monoid {
  val MultiMonoid: Monoid[Int] = new Monoid[Int] {
    def mappend(a1: Int, a2: Int) = a1 * a2
    def mzero = 1
  }

  implicit val IntMonoid: Monoid[Int] = new Monoid[Int] {
    def mappend(a1: Int, a2: Int) = a1 + a2
    def mzero = 0
  }
  implicit val StringMonoid: Monoid[String] = new Monoid[String] {
    def mappend(a1: String, a2: String): String = a1 + a2
    def mzero = ""
  }
}
