package com.mundox.databases.ports.adapters.databases

import java.sql.{Connection, DriverManager, ResultSet}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait DatabaseConnection {

  private lazy val url:String = "jdbc:postgresql://localhost:5432/postgres"
  private lazy val driver:String = "org.postgresql.Driver"
  private lazy val username:String = "postgres"
  private lazy val password:String = "example"

  protected def connect: Connection = {
    Class.forName(driver)
    DriverManager.getConnection(url, username, password)
  }

  def executeQuery(query: String): Future[ResultSet] = {
    val con: Connection = connect
    Future[ResultSet] {
      try {
        con.createStatement().executeQuery(query)
      } catch {
        case e: Exception => throw e
      } finally {
        con.close()
      }
    }
  }
}
