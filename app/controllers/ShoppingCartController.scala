package controllers

import play.api.libs.json.{JsArray, JsNumber, JsString}
import play.api.mvc.{Action, AnyContent, InjectedController}

import javax.inject._

@Singleton
class ShoppingCartController extends InjectedController {
  def calculateCost(): Action[AnyContent] = Action { request =>
    val items = request.body.asJson.get.as[JsArray].value.map{_.as[String]}.toSeq
    val cost = items match {
      case Seq("Apple") => 60
      case Seq() => 0
    }
    Ok(JsNumber(cost))
  }
}
