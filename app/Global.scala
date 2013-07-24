/**
 * User: Björn Reimer
 * Date: 5/25/13
 * Time: 4:27 PM
 */

import info.schleichardt.play.embed.mongo.DynamicEmbedMongoPort
import play.api.GlobalSettings
import play.api.mvc.EssentialAction
import scala.collection.JavaConverters._
import play.api.http.HeaderNames._


object Global extends GlobalSettings with DynamicEmbedMongoPort {

  // tell reactive mongo the port of the memory database created by embed mongo
  override def additionalEmbedMongoPortSettings(port: Int) = Map("mongodb.servers" -> List(s"localhost:$port").asJava)

}
