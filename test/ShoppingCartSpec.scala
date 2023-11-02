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
  }
}
