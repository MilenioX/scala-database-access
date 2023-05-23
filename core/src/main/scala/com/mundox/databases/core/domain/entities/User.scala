package com.mundox.databases.core.domain.entities

import com.mundox.databases.core.domain.valueobjects.{Email, Id}

case class User(id: Id, username: String, firstName: String, lastName: String, email: Email, password: String)
