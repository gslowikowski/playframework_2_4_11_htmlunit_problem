name := "play-scala"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  jdbc,
  evolutions,
  "com.typesafe.play" %% "anorm" % "2.4.0",
  specs2 % "test"
)

// required for play 2.4.11, not required for 2.4.10
// libraryDependencies += "net.sourceforge.htmlunit" % "htmlunit" % "2.19" % "test"

routesGenerator := InjectedRoutesGenerator
