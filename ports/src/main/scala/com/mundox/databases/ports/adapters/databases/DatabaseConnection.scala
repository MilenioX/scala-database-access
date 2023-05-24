package com.mundox.databases.ports.adapters.databases


import com.mundox.databases.ports.config.JDBCConfig

import java.sql.{Connection, DriverManager, ResultSet}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait DatabaseConnection {

  private lazy val url:String = getUrl
  private lazy val driver:String = getDriver
  private lazy val username:String = getUsername
  private lazy val password:String = getPassword

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
  
  def getUrl: String
  def getDriver: String
  def getUsername: String
  def getPassword: String
}