package sprayprez

import spray.routing.SimpleRoutingApp
import akka.actor.ActorSystem

object Boot extends App with SimpleRoutingApp with SimpleService {
  implicit val system = ActorSystem("spray-prez")

  sys.addShutdownHook {
    system.shutdown()
  }

  startServer(interface = "localhost", port = 8080)(route)
}
