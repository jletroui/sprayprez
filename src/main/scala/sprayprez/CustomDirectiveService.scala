package sprayprez

import spray.routing.{RequestContext, HttpService}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait CustomDirectiveService extends HttpService {
  val loggingRoute = mapRequestContext(logTheResponseResult) {
    get {
      complete {
        "Hello!"
      }
    }
  }

  def logTheResponseResult(ctx: RequestContext) = ctx.withHttpResponseMapped { resp =>
    if (resp.status.isSuccess)
      incrementCounterDB("success")
    else
      incrementCounterDB("failure")

    resp
  }

  def incrementCounterDB(counterName: String) = Future {
    // Call to the DB
  }
}
