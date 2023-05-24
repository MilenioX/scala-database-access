package com.mundox.databases.core.services

import com.mundox.databases.core.domain.entities.User
import com.mundox.databases.core.domain.valueobjects.Id

import scala.concurrent.Future

trait UserService {

  def getUsers: Future[List[User]]

  def getUserById(id: Id):Future[Option[User]]

  def updateUser(id: Id, user: User): Future[Boolean]

  def addUser(user: User): Future[User]

  def deleteUser(id: Id): Future[Boolean]
}
