# Simple Shopping Cart

Demostrates TDD and YAGNI approach to a Simple Shopping Cart microservice written in Scala 
as a pre-interview exercise for developer candidates.

To run the tests, use:
> sbt test

To run the microservice, use:
> sbt run

Example request:
> curl -d '["Apple"]' -H "Content-Type: application/json" -X GET http://localhost:9000/calculate-cost