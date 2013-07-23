package models

import play.api.data._
import play.api.data.Forms._
import play.api.libs.json.{Format, Json}
import play.modules.reactivemongo.json.collection.JSONCollection
import play.modules.reactivemongo.ReactiveMongoPlugin
import scala.concurrent.{ExecutionContext, Future}
import play.api.Play.current
import ExecutionContext.Implicits.global
import scala.reflect.io.File

/**
 * User: Björn Reimer
 * Date: 7/23/13
 * Time: 11:16 PM
 */

case class Location(
                     name: String,
                     description: String,
                     locationLatitude: String,
                     locationLongitude: String,
                     imageId: String
                     )


object Location {

  val locationCollection: JSONCollection = ReactiveMongoPlugin.db.collection[JSONCollection]("locations")

  implicit val locationFormat: Format[Location] = Json.format[Location]

  def save(location: Location): Future[String] = {
    locationCollection.insert(location).map {
      lastError => if (lastError.ok) {
        "Saved: " + location.toString
      } else {
        "Not Saved. Error: " + lastError.stringify
      }
    }
  }

  val form = Form(
    mapping(
      "name" -> text,
      "description" -> text,
      "locationLatitude" -> text,
      "locationLongitude" -> text,
      "image" -> text
    )(Location.apply)(Location.unapply)
  )

}