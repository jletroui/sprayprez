package sprayprez

import spray.routing.HttpService
import java.io.File

trait SimpleService extends HttpService {
  def route = {
    pathPrefix("static") {
      if (new File("src/main/resources/static").exists())
        getFromDirectory("src/main/resources/static")
      else
        getFromResourceDirectory("static")
    } ~
    pathEndOrSingleSlash {
      get {
        complete {
          "Hello!"
        }
      }
    }
  }
}
