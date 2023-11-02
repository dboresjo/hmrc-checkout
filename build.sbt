name := "hmrc-checkout"

organization := "com.boresjo"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.13.12"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(specs2)
