package com.mundox.databases.ports

import com.mundox.databases.core.domain.entities.User
import com.mundox.databases.core.domain.valueobjects.{Email, Id}
import com.mundox.databases.ports.config.{Config, DefaultConfigurations, Environment}
import pureconfig.ConfigSource

import java.util.UUID
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import pureconfig._
import pureconfig.generic.auto._

object Main {

  def main(args: Array[String]): Unit = {
    println("Starting service to connect Scala with databases")
    val env = createEnvironment()

    println("SEARCHING...")
    val getResult = env.userQueries.getUsers
    getResult.onComplete {
      case Failure(exception) =>
        println("There was an error retrieving the users information", exception)
      case Success(users) =>
        println("The list of users is the following:")
        println(users)
    }

    println("DELETING...")
    val deleteRes = env.userCommand.deleteUser(Id("da03790c-f484-4c2c-8deb-d77a93d6af94"))
    deleteRes.onComplete {
      case Failure(exception) =>
        println("There was an error deleting the new user", exception)
      case Success(_) => {
        println(s"User with id 2ef3dae3-6e12-4b32-9739-80a29d2ed574 was deleted")
      }
    }

    println("ADDING...")
    var id: Id = Id("Empty")
    val insertRes = env.userCommand.addUser {
      val id: Id = Id(UUID.randomUUID().toString)
      User(id, "user00", "HELLO", "WORLD", Email("example@domain.com"), "abc")
    }
    insertRes.onComplete {
      case Failure(exception) =>
        println("There was an error adding the new user", exception)
      case Success(user) => {
        id = Id(user.id.value)
        println(s"Added the user with id ${user.id.value}")
      }
    }

    println("SEARCHING ONE...")
    val getUserRes = env.userQueries.getUserById(id)
    getUserRes.onComplete {
      case Failure(exception) =>
        println("There was an error adding the new user", exception)
      case Success(user) => user match {
        case Some(u) =>
          println("The user is the following:")
          println(u)
        case None =>
          println(s"The user with the id ${id.value} was not found in the data")
      }
    }

    Thread.sleep(20000)
  }



  private def createEnvironment():Environment = {
    ConfigSource.default.load[Config].fold(error => {
      println("There was an error loading the config properties", error)
      new Environment(DefaultConfigurations.getDefaultConfigurations)
    },config => {
      println(s"Config file loaded successfully $config")
      new Environment(config)
    })
  }
}
