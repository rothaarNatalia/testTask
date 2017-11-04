name := "testingproject"

version := "1.0"

scalaVersion := "2.12.1"


// grading libraries
libraryDependencies += "junit" % "junit" % "4.10" % Test
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.6"

libraryDependencies ++= Seq(
  // For the console exercise, the logback dependency
  // is only important if you want to see all the
  // debugging output. If you don't want that, simply
  // omit it.
  "ch.qos.logback"          %  "logback-classic" % "1.2.3",
  "net.databinder.dispatch" %% "dispatch-core"   % "0.13.2"
)