package controllers

import play.api.libs.json.{JsArray, JsNumber, JsString}
import play.api.mvc.{Action, AnyContent, InjectedController}

import javax.inject._

@Singleton
class ShoppingCartController extends InjectedController {
  def calculateCost(): Action[AnyContent] = Action { request =>
    val items = request.body.asJson.get.as[JsArray].value.map{_.as[String]}.toSeq
    val costs = items map {
      case "Apple" => 60
      case "Orange" => 25
    }
    val discounts = Seq(
      items.count(_=="Apple") / 2 * 60,
      items.count(_=="Orange") / 3 * 25,
    )
    Ok(JsNumber(costs.sum - discounts.sum))
  }
}
