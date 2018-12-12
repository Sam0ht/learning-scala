import org.scalatest.{FunSuite, Matchers}

class PeopleRegisterTest extends FunSuite with Matchers {

  val people = """Adam 18
                  Bob 15
                  Charlie 8
                  Dave 49
                  Elizabeth 12
                  Francis 42
                  George 85
                  Harriet 62
                  Janet 13
                  Karl 8
                  Laura 6
                  Mary 19
                  Nick 16
                  Oliver 54
                  Peter 27
                  Peter 34
                  Robert 36
                  Sally 26
                  Tom 58
                  Tom 13
                  Tom 58
                  Tom 45
                  Vicky 60
                  Wally 28"""

  val register: PeopleRegister = PeopleRegisterParser.parse(people)

  test("should report twenty four people") {
    register.count() should be(24)
  }

  test("should say that Dave is the oldest") {
    register.oldestPerson() should be(Person("George", 85))
  }

  test("should say that Tom is the commonest name") {
    register.commonestName().get should be("Tom")
  }

  test("should say that the youngest Peter is 27 years old") {
    register.youngestCalled("Peter").age should be(27)
  }

  test("should report eight children") {
    register.countOfChildren() should be(8)
  }

  test("should report an adult to child ratio of 2") {
    register.adultToChildRatio() should be(2.0)
  }

  test("should calculate the average age") {
    register.averageAge() should be(33.0)
  }

  test("should calculate the median age") {
    register.medianAge() should be(27.5)
  }

}

class PeopleRegister(val people: Seq[Person]) {

  def count(): Int = ???

  def oldestPerson(): Person = ???

  def commonestName(): Option[String] = ???

  def youngestCalled(sought: String): Person = ???

  def countOfChildren(): Int = ???

  def adultToChildRatio(): Double = {
    ???
  }
  def averageAge(): Double = ???

  def medianAge(): Double = {
    ???
  }

}

case class Person(name: String, age: Int)

object PersonParser {
  def parse(data: String): Person = {
    val params = data.trim().split(" ")
    Person(params(0), params(1).toInt)
  }
}

object PeopleRegisterParser {
  def parse(personData: String): PeopleRegister = new PeopleRegister(personData.trim().split("\n").map(PersonParser.parse))
}