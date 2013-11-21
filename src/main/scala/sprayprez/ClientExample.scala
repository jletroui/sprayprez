package sprayprez

import spray.http._
import spray.json.DefaultJsonProtocol
import spray.httpx.encoding.{Gzip, Deflate}
import spray.httpx.SprayJsonSupport._
import spray.client.pipelining._
import akka.actor.ActorSystem
import scala.concurrent.Future


object ClientExample {
  // Business objects
  case class Order(id: Int)
  case class OrderConfirmation(id: Int)

  // JSON Serialization
  object MyJsonProtocol extends DefaultJsonProtocol {
    implicit val orderFormat = jsonFormat1(Order)
    implicit val orderConfirmationFormat = jsonFormat1(OrderConfirmation)
  }
  import MyJsonProtocol._

  // Akka bootup
  implicit val system = ActorSystem()
  import system.dispatcher // execution context for futures



  val pipeline: HttpRequest => Future[OrderConfirmation] = (
      addHeader("X-My-Special-Header", "fancy-value")
      ~> addCredentials(BasicHttpCredentials("bob", "secret"))
      ~> encode(Gzip)
      ~> sendReceive
      ~> decode(Deflate)
      ~> unmarshal[OrderConfirmation]
    )


  val request: HttpRequest = Post("http://example.com/orders", Order(42))
  val response: Future[OrderConfirmation] = pipeline(request)
}
