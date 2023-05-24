# Scala project with Databases

This project is a practice to test how to access the database data with Scala and using some practices like CQRS and Hexagonal Architecture

## Tech Stack
- Sbt 1.8.1
- Scala 2.13.10
- PureConfig 0.17.4
- PostgreSQL 15.2 (docker)

## Skunk
A data access library for Scala + Postgres.
https://typelevel.org/skunk/

## Doobie
Pure functional JDBC Layer for Scala
https://tpolecat.github.io/doobie/index.html

## Slick (Scala Language Integrated Connection Kit)
A modern Database query and access library for Scala
https://scala-slick.org/

## Java JDBC compatibility
The goal is to access to the database information using the Java JDBC library, with this implementation practice PrepareStatements concept.
- Postgresql driver 42.6.0