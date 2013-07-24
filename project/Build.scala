import sbt._
import play.Project._

object ApplicationBuild extends Build {

  val appName = "plakatMelder"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    "org.reactivemongo" %% "play2-reactivemongo" % "0.9" ,
    "info.schleichardt" %% "play-embed-mongo" % "0.2"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
