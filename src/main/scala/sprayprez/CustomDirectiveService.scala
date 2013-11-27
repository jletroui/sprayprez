package sprayprez

import spray.routing.HttpService
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait CustomDirectiveService extends HttpService {
  val countingRoute =
    countResponseResults {
      get {
        complete {
          "Hello!"
        }
      }
    }

  val countResponseResults =
    mapRequestContext {
      _.withHttpResponseMapped { response =>

        if (response.status.isSuccess) incrementCounterDB("response.success")
        else incrementCounterDB("response.failure")

        response
      }
    }

  def incrementCounterDB(counterName: String) = Future {
    // Call to the DB
  }
}
