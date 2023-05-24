package com.mundox.databases.core.commands

import com.mundox.databases.core.domain.entities.User
import com.mundox.databases.core.domain.valueobjects.Id
import com.mundox.databases.core.services.UserService

import scala.concurrent.Future

class UserCommand(service: UserService) {

  def addUser(user: User): Future[User] =
    service.addUser(user)

  def deleteUser(id: Id): Future[Boolean] =
    service.deleteUser(id)

  def updateUser(id: Id, user: User): Future[Boolean] =
    service.updateUser(id, user)
}
