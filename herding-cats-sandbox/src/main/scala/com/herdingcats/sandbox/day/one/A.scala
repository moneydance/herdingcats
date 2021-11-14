package com.herdingcats.sandbox.day.one

import io.circe.generic.JsonCodec, io.circe.syntax._

object A {
  def main(args: Array[String]): Unit = {
    val res = head(Car("Civic") :: Car("CR-V") :: Nil)
    println(res.asJson.spaces4SortKeys)
  }
  def head[A](list: List[A]): A = list(0)
  @JsonCodec case class Car(make: String)
}
