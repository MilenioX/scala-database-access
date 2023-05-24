package com.mundox.databases.ports.config

import com.mundox.databases.core.commands.UserCommand
import com.mundox.databases.core.queries.UserQueries
import com.mundox.databases.core.services.UserService
import com.mundox.databases.ports.adapters.databases.UserServiceImpl

class Environment {

  private val userService: UserService = new UserServiceImpl;

  val userQueries: UserQueries = new UserQueries(userService)

  val userCommand: UserCommand = new UserCommand(userService)

}