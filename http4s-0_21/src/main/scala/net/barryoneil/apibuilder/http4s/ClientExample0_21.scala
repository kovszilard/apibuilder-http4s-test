package net.barryoneil.apibuilder.http4s

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits.toTraverseOps
import io.flow.github.v0.Client
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.implicits.http4sLiteralsSyntax

import scala.concurrent.ExecutionContext.global

object ClientExample0_21 extends IOApp {

  override def run(args: List[String]): IO[ExitCode] =
    BlazeClientBuilder[IO](global).resource.use { httpClient =>

      new Client[IO](httpClient = httpClient, baseUrl = uri"https://api.github.com").tags
        .getTags(owner = "laserdisc-io", repo = "fs2-aws")
        .flatMap { repos =>
          repos.traverse(r => IO.delay(println(r.name)))
        }
    }
      .as(ExitCode.Success)

}
