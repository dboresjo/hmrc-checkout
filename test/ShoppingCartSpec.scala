import org.specs2.execute.Results
import play.api.libs.json.{JsArray, JsNumber, JsString}
import play.api.test.{FakeRequest, PlaySpecification, WithApplication}

class ShoppingCartSpec extends PlaySpecification with Results {
  "calculate-cost" should {
    "return zero for zero items" in new WithApplication {
      val givenItems = JsArray(Seq())

      val result = route(app, FakeRequest(GET, "/calculate-cost").withBody(givenItems)).get

      status(result) must equalTo(OK)
      contentAsJson(result) must equalTo(JsNumber(0))
    }

    "return 60 for an apple" in new WithApplication {
      val givenItems = JsArray(Seq(JsString("Apple")))

      val result = route(app, FakeRequest(GET, "/calculate-cost").withBody(givenItems)).get

      status(result) must equalTo(OK)
      contentAsJson(result) must equalTo(JsNumber(60))
    }

    "return 25 for an orange" in new WithApplication {
      val givenItems = JsArray(Seq(JsString("Orange")))

      val result = route(app, FakeRequest(GET, "/calculate-cost").withBody(givenItems)).get

      status(result) must equalTo(OK)
      contentAsJson(result) must equalTo(JsNumber(25))
    }

    "add apples and oranges" in new WithApplication {
      val givenItems = JsArray(Seq(JsString("Apple"), JsString("Orange")))

      val result = route(app, FakeRequest(GET, "/calculate-cost").withBody(givenItems)).get

      status(result) must equalTo(OK)
      contentAsJson(result) must equalTo(JsNumber(85))
    }

    "add all items including duplicates" in new WithApplication {
      val givenItems = JsArray(Seq(JsString("Apple"), JsString("Orange"), JsString("Orange")))

      val result = route(app, FakeRequest(GET, "/calculate-cost").withBody(givenItems)).get

      status(result) must equalTo(OK)
      contentAsJson(result) must equalTo(JsNumber(110))
    }

    "honour offer" should {
      "buy one apple, get one free" in new WithApplication {
        val givenItems = JsArray(Seq(JsString("Apple"), JsString("Apple")))

        val result = route(app, FakeRequest(GET, "/calculate-cost").withBody(givenItems)).get

        status(result) must equalTo(OK)
        contentAsJson(result) must equalTo(JsNumber(60))
      }

      "buy one apple, get (only) one free" in new WithApplication {
        val givenItems = JsArray(Seq(JsString("Apple"), JsString("Apple"), JsString("Apple")))

        val result = route(app, FakeRequest(GET, "/calculate-cost").withBody(givenItems)).get

        status(result) must equalTo(OK)
        contentAsJson(result) must equalTo(JsNumber(120))
      }

      "buy one apple, get one free, twice" in new WithApplication {
        val givenItems = JsArray(Seq(JsString("Apple"), JsString("Apple"), JsString("Apple"), JsString("Apple")))

        val result = route(app, FakeRequest(GET, "/calculate-cost").withBody(givenItems)).get

        status(result) must equalTo(OK)
        contentAsJson(result) must equalTo(JsNumber(120))
      }

      "3 oranges for the price of 2" in new WithApplication {
        val givenItems = JsArray(Seq(JsString("Orange"), JsString("Orange"), JsString("Orange")))

        val result = route(app, FakeRequest(GET, "/calculate-cost").withBody(givenItems)).get

        status(result) must equalTo(OK)
        contentAsJson(result) must equalTo(JsNumber(50))
      }

      "3 oranges for the price of 2, ignoring fractions" in new WithApplication {
        val givenItems = JsArray(Seq(JsString("Orange"), JsString("Orange"), JsString("Orange"), JsString("Orange")))

        val result = route(app, FakeRequest(GET, "/calculate-cost").withBody(givenItems)).get

        status(result) must equalTo(OK)
        contentAsJson(result) must equalTo(JsNumber(75))
      }

      "3 oranges for the price of 2, twice" in new WithApplication {
        val givenItems = JsArray(Seq(JsString("Orange"), JsString("Orange"), JsString("Orange"), JsString("Orange"), JsString("Orange"), JsString("Orange")))

        val result = route(app, FakeRequest(GET, "/calculate-cost").withBody(givenItems)).get

        status(result) must equalTo(OK)
        contentAsJson(result) must equalTo(JsNumber(100))
      }
    }
  }
}
