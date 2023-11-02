package controllers

import play.api.libs.json.JsNumber
import play.api.mvc.{Action, AnyContent, InjectedController}

import javax.inject._

@Singleton
class ShoppingCartController extends InjectedController {
  def calculateCost(): Action[AnyContent] = Action {
    Ok(JsNumber(0))
  }
}
