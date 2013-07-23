package controllers

import play.modules.reactivemongo.MongoController
import play.api.mvc.{Action, Controller}
import models.Location
import reactivemongo.api.gridfs.GridFS
import reactivemongo.bson.BSONValue

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


  def submit = Action(gridFSBodyParser(gridFS)) {
    implicit request => {

      // create location object using submit data
      val location: Location = Location.form.bindFromRequest().get

      // the bodyparser saved the image to gridfs, save imageId
      val futureFile = request.body.files.head.ref

      futureFile.map {
        file =>

          location.copy(imageId = file.id)


          Async {
            Location.save(location).map {
              message => Ok(message)
            }
          }
      }
    }
  }


}
