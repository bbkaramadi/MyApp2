name := "sample2"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "org.mongodb.morphia" % "morphia-logging-slf4j" % "0.108",
  "org.mongodb.morphia" % "morphia" % "0.108"
)    

play.Project.playJavaSettings
