package com.mundox.databases.ports.config

object DefaultConfigurations {

  def getDefaultConfigurations: Config = {
    Config(
      JDBCConfig(
        "jdbc:postgresql:",
        "org.postgresql.Driver",
        "localhost",
        5432,
        "postgres",
        "postgres",
        "example")
    )
  }

}
