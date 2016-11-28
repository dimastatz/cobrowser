package com.dimastatz.poc

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging
import com.dimastatz.poc.contracts.Configuration

object Boot extends LazyLogging{
  def main(args: Array[String]): Unit = {
    import system.dispatcher
    implicit val system = ActorSystem("co-browser")
    implicit val materializer = ActorMaterializer()

    val config = Configuration.load()
    logger.debug("Configuration loaded")

    val router = new Router()
    val binding = Http().bindAndHandle(router.routes(), config.interface, config.port)
    logger.debug(s"Co-browser service is started on ${config.interface}:${config.port}")

    // add shutdown
    sys addShutdownHook {
      logger.debug(s"Co-browser service is terminating.")
      binding.flatMap(_.unbind()).onComplete(_ => system.terminate())
    }
  }
}
