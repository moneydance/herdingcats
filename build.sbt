lazy val commonSettings = Seq(scalaVersion := "2.12.10", organization := "com.herdingcats")

lazy val root = (project in file (".")).settings(
  name := "herding-cats",
  commonSettings,
).aggregate(sandbox)

lazy val sandbox = (project in file("herding-cats-sandbox")).settings(
  commonSettings,
  name       := "herding-cats-sandbox",
  version    := "1.0.0",
  resolvers += Resolver.sonatypeRepo("releases"),
  addCompilerPlugin(
    "org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full
  ),
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-core" % "2.1.1",
    "io.circe" %% "circe-core" % "0.14.1" ,
    "io.circe" %% "circe-generic" % "0.14.1",
    "io.circe" %% "circe-parser" % "0.14.1"
  ),
  mainClass / run := Some("com.herdingcats.sandbox.dayOne.A", "com.herdingcats.sandbox.dayOne.B", "com.herdingcats.sandbox.dayOne.C")
)
