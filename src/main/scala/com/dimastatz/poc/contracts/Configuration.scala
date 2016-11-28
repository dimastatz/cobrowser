package com.dimastatz.poc.contracts

import com.typesafe.config.ConfigFactory


case class Configuration
(
  port: Int,
  interface: String
)

object Configuration {
  def load(name: String = "application.conf")= {
    val config = ConfigFactory.load(name)
    Configuration(
      config.getInt("conf.port"),
      config.getString("conf.interface")
    )
  }
}


