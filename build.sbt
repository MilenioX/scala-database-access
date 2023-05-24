import Dependencies._

ThisBuild / scalaVersion     := "2.13.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.mundox"
ThisBuild / organizationName := "mundox"

lazy val ports = (project in file("ports"))
  .aggregate(core)
  .dependsOn(core)
  .settings(
    name := "database",
    libraryDependencies ++= Seq(
      munit % Test,
      postgreSQL,
      pureConfig
    )
  )

lazy val core = (project in file("core"))
  .settings(
    name := "core",
    libraryDependencies ++= Seq(
      munit % Test
    )
  )
