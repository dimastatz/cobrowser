package com.dimastatz.poc

import akka.http.scaladsl.server.Directives._

class Router {
  def routes() = {
    routeDefault() ~ routeClients()
  }
  def routeDefault() = get {
    pathEndOrSingleSlash {
      complete("Welcome to cowbroser server")
    }
  }
  def routeClients() = pathPrefix("cobrowser" / "clients") {
    path(IntNumber) {
      (clientId) => {
        get {
          complete(s"cobrowser / clients $clientId")
        }
      }
    }
  }
}
