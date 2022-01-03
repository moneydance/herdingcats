package com.herdingcats.sandbox.day.one

object C {

  def main(args: Array[String]): Unit = {
    println(Summer.sum(List(1,2,3,4)))
    println(Summer.sum(List(1,2,3,4))(Monoid.MultiMonoid))
    println(Summer.sum(List("a", "b", "c")))
  }
}

object Summer {
  def sum[M[_]: FoldLeft, A: Monoid](xs: M[A]): A = {
    val ws = implicitly[Monoid[A]]
    val folder = implicitly[FoldLeft[M]]
    folder.foldLeft(xs, ws.mzero , ws.mappend)
  }
}

trait FoldLeft[F[_]] {
  def foldLeft[A, B](xs: F[A], b: B, f: (B, A) => B): B
}

object FoldLeft {
  implicit val FoldLeftList: FoldLeft[List] = new FoldLeft[List] {
    def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B): B = xs.foldLeft(b)(f)
  }
}
