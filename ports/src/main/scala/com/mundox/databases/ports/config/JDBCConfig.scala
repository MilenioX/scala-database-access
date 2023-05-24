package com.mundox.databases.ports.config

case class JDBCConfig(
                       url: String,
                       driver: String,
                       server: String,
                       port: Int,
                       database: String,
                       username: String,
                       password: String
                     )
