lazy val commonSettings = Seq(scalaVersion := "2.12.10", organization := "com.herdingcats")

lazy val root = project.settings(name := "herdingcats", commonSettings, publish / skip := true).dependsOn(sanbox)

lazy val sandbox = (project in file("herding-cats-sandbox")).settings(
  commonSettings,
  name       := "herdingcats-sandbox",
  version    := "1.0.0-SNAPSHOT",
  isSnapshot := true,
  libraryDependencies ++= Seq("org.typelevel" %% "cats-core" % "2.1.1"),
  Compile / mainClass := Some("coma.herdingcats.sandbox.Main")
)
