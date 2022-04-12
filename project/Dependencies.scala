import sbt.Keys.libraryDependencies
import sbt._

object Dependencies {

  def Http4s(version: String) = Seq(
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-circe"        % version withSources (),
      "org.http4s" %% "http4s-dsl"          % version withSources (),
      "org.http4s" %% "http4s-client"       % version withSources (),
      "org.http4s" %% "http4s-blaze-server" % version,
      "org.http4s" %% "http4s-blaze-client" % version,
      "org.http4s" %% "http4s-circe" % version
    )
  )

}
