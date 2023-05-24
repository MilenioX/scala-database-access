package com.mundox.databases.ports.adapters.databases

import com.mundox.databases.core.domain.entities.User
import com.mundox.databases.core.domain.valueobjects.{Email, Id}
import com.mundox.databases.core.services.UserService

import java.sql.{ResultSet, SQLException}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserServiceImpl extends UserService with DatabaseConnection {

  override def getUsers: Future[List[User]] = {
    println("getUsers service called successfully")
    val res:Future[ResultSet] = executeQuery("select id, username, first_name, last_name, email, password from authentication.users")
    res.map(rs => {
      var users: List[User] = List()
      while (rs.next) {
        val id = rs.getString("id")
        val username = rs.getString("username")
        val first_name = rs.getString("first_name")
        val last_name = rs.getString("last_name")
        val email = rs.getString("email")
        val password = rs.getString("password")
        val user = User(Id(id), username, first_name, last_name, Email(email), password)
        users = users :+ user
      }
      users
    })
  }

  override def getUserById(id: Id): Future[Option[User]] = {
    println(s"getUsersById service called successfully with $id")
    val res: Future[ResultSet] = executeQuery(s"select id, username, first_name, last_name, email, password from authentication.users WHERE id='${id.value}'")
    res.map(rs => {
      if (rs.next()) {
        val id = rs.getString("id")
        val username = rs.getString("username")
        val first_name = rs.getString("first_name")
        val last_name = rs.getString("last_name")
        val email = rs.getString("email")
        val password = rs.getString("password")
        Some(User(Id(id), username, first_name, last_name, Email(email), password))
      } else
        None
    })
  }

  override def updateUser(id: Id, user: User): Future[Boolean] = ???

  override def addUser(user: User): Future[User] =
    Future {
      val con = connect
      try {
        val statement = con.prepareStatement(s"INSERT INTO authentication.users(id,username,first_name,last_name,email,password) VALUES(?,?,?,?,?,?)")
        statement.setString(1, user.id.value)
        statement.setString(2, user.username)
        statement.setString(3, user.firstName)
        statement.setString(4, user.lastName)
        statement.setString(5, user.email.email)
        statement.setString(6, user.password)
        statement.execute()
        user
      } catch {
        case e: SQLException => throw e
      } finally {
        con.close()
      }
    }

  override def deleteUser(id: Id): Future[Boolean] =
    Future {
      val con = connect
      try {
        val statement = con.prepareStatement(s"DELETE FROM authentication.users WHERE id='${id.value}'")
        statement.execute()
      } catch {
        case e: SQLException => throw e
      } finally {
        con.close()
      }
    }

}
