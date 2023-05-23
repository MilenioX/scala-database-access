package com.mundox.databases.ports

import com.mundox.databases.core.domain.entities.User
import com.mundox.databases.core.domain.valueobjects.{Id, Email}

object Main extends App {
  println("Hello World")
  val user: User = User(Id("6ab24d67-736f-4d7c-9e87-9e2d37c6a83b"),"user1","John","Doe",Email("johndoe@example.com"),"rl23qBm9Uh6u4eDcNz1i")
  println(s"USer $user")
}
