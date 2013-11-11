package sprayprez

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._
import StatusCodes._

class LongerServiceSpec extends Specification with Specs2RouteTest with LongerService {
  def actorRefFactory = system

  "The LongerService" should {

    "return a greeting for GET requests to the root path" in {
      Get() ~> longerRoute ~> check { responseAs[String] must contain("Say hello") }
    }

    "return a 'PONG!' response for GET requests to /ping" in {
      Get("/ping") ~> longerRoute ~> check { responseAs[String] === "PONG!" }
    }

    "leave GET requests to other paths unhandled" in {
      Get("/kermit") ~> longerRoute ~> check { handled must beFalse }
    }

    //# source-quote (for the documentation site)
    "return a MethodNotAllowed error for PUT requests to the root path" in {
      Put() ~> sealRoute(longerRoute) ~> check {
        status === MethodNotAllowed
        responseAs[String] === "HTTP method not allowed, supported methods: GET, POST"
      }
    }
    //#
  }
}