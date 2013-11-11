package sprayprez

import spray.routing.SimpleRoutingApp
import akka.actor.ActorSystem

object Boot extends App with SimpleRoutingApp {
  implicit val system = ActorSystem("spray-prez")

  val helloWorldRoute =
    get {
      complete {
        "Hello!"
      }
    }

  startServer(interface = "localhost", port = 8080)(helloWorldRoute)
}
