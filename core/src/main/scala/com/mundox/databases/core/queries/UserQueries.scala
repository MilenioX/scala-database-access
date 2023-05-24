package com.mundox.databases.core.queries

import com.mundox.databases.core.domain.entities.User
import com.mundox.databases.core.domain.valueobjects.Id
import com.mundox.databases.core.services.UserService

import scala.concurrent.Future

class UserQueries(service: UserService) {

  def getUsers: Future[List[User]] =
    service.getUsers

  def getUserById(id: Id): Future[Option[User]] =
    service.getUserById(id)
}
