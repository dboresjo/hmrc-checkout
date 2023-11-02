import org.specs2.execute.Results
import play.api.libs.json.{JsArray, JsNumber}
import play.api.test.{FakeRequest, PlaySpecification, WithApplication}

class ShoppingCartSpec extends PlaySpecification with Results {
  "calculate-cost" should {
    "return zero for zero items" in new WithApplication {
      val givenItems = JsArray(Seq())

      val result = route(app, FakeRequest(GET, "/calculate-cost").withBody(givenItems)).get

      status(result) must equalTo(OK)
      contentAsJson(result) must equalTo(JsNumber(0))
    }
  }
}
