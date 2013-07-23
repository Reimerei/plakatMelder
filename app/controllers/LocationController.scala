package controllers

import play.modules.reactivemongo.MongoController
import play.api.mvc.{Action, Controller}
import java.io.File

/**
 * User: Björn Reimer
 * Date: 7/23/13
 * Time: 12:06 AM
 */
object LocationController extends Controller with MongoController {

  case class Location(
                     name: String,
                     description: String,
                     locationLatitude: String,
                     locationLongitude: String,
                     image: File
                     )

  def index = Action {
    request =>  Ok(views.html.index())
  }






}
