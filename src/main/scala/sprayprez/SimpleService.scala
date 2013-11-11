package sprayprez

import spray.routing.HttpService
import java.io.File

trait SimpleService extends HttpService {

  val simpleRoute = {
    pathPrefix("static") {
      if (new File("src/main/resources/static").exists())
        getFromDirectory("src/main/resources/static")
      else
        getFromResourceDirectory("static")
    } ~
    path("order" / IntNumber) { id =>
      get {
        complete {
          "Received GET request for order " + id
        }
      } ~
      put {
        complete {
          "Received PUT request for order " + id
        }
      }
    } ~
    pathEndOrSingleSlash {
      get {
        complete {
          <html>
            <body>
              <h1>Say hello to <i>spray-routing</i> on <i>spray-can</i>!</h1>
            </body>
          </html>
        }
      }
    }
  }

}
