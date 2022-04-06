import sbt.ThisBuild

ThisBuild / scalaVersion := "2.13.8"

lazy val commonSettings = Seq(
  ThisBuild / turbo := true,
  organization      := "net.barryoneill",
  organizationName  := "Barry O'Neill",
  addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
  scalacOptions ++= Seq(
    "-encoding",
    "UTF-8",                         // source files are in UTF-8
    "-deprecation",                  // warn about use of deprecated APIs
    "-unchecked",                    // warn about unchecked type parameters
    "-feature",                      // warn about misused language features
    "-language:higherKinds",         // allow higher kinded types without `import scala.language.higherKinds`
    "-language:implicitConversions", // allow use of implicit conversions
    "-language:postfixOps",          // enable postfix ops
    "-Xlint:_,-byname-implicit",     // enable handy linter warnings except byname-implicit (see https://github.com/scala/bug/issues/12072)
    "-Ywarn-macros:after",           // allows the compiler to resolve implicit imports being flagged as unused
    "-Wconf:src=src_managed/.*:silent" // no warnings for generated code
  )
)

lazy val http4s_0_21 = (project in file("http4s-0_21"))
  .settings(
    name := "http4s-0_21",
    commonSettings,
    Dependencies.Http4s("0.21.33")
  )

lazy val http4s_0_22 = (project in file("http4s-0_22"))
  .settings(
    name := "http4s-0_22",
    commonSettings,
    Dependencies.Http4s("0.22.12")
  )

lazy val http4s_0_23 = (project in file("http4s-0_23"))
  .settings(
    name := "http4s-0_23",
    commonSettings,
    Dependencies.Http4s("0.23.11")
  )

lazy val root = (project in file("."))
  .aggregate(
    http4s_0_21,
    http4s_0_22,
    http4s_0_23
  )
  .settings(
    name            := "apibuilder-http4s-test",
    publishArtifact := false,
    addCommandAlias("format", ";scalafmtAll;scalafmtSbt")
  )
  .enablePlugins(ScalafmtPlugin)
