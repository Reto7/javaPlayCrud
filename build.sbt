name := """crud"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final",
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc42",
  cache,
  javaWs
)


fork in run := true


// fuer JPA: Running Play in development mode while using JPA will work fine,
// but in order to deploy the application you will need to add "PlayKeys.externalizeResources := false" to your build.sbt file.

// fuer JPA
lazy val myProject = (project in file(".")).enablePlugins(PlayJava, PlayEbean)
