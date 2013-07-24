package controllers

import play.modules.reactivemongo.MongoController
import play.api.mvc.{Action, Controller}
import models.Location
import reactivemongo.api.gridfs.GridFS

/**
 * User: Björn Reimer
 * Date: 7/23/13
 * Time: 12:06 AM
 */


object LocationController extends Controller with MongoController {

  val gridFS = new GridFS(db)
  gridFS.ensureIndex()

  def index = Action {
    request => Ok(views.html.index(Location.form))

  }


  def submit = Action {
    implicit request => {
      // create location object using submit data
      val location: Location = Location.form.bindFromRequest().get
      Async {
        Location.save(location).map {
          message => Ok(message)
        }
      }
    }
  }
}
