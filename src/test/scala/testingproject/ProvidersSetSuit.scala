package testingproject

import dispatch.Http
import org.asynchttpclient.Response
import org.junit.runner.RunWith
import org.scalatest.{BeforeAndAfterAll , FunSuite, GivenWhenThen, MustMatchers}
import org.scalatest.junit.JUnitRunner
import org.scalatest.BeforeAndAfter
import utils._
import org.scalatest.Matchers._
import play.api.libs.json.{JsValue, Json}
import testingproject.matchers.{HttpResponseMatchers, JSONResponseMatchers}

/**
  * Created by rothaar on 04.11.2017.
  */


@RunWith(classOf[JUnitRunner])
class ProvidersSetSuit extends FunSuite with MustMatchers with HttpResponseMatchers with GivenWhenThen with JSONResponseMatchers with BeforeAndAfterAll{

  val url = "https://www.tinkoff.ru/api/v1/providers"

  trait ProviderSet {
    val transfer = "Переводы"
    val internet = "Интернет"
    val charity = "Благотворительность"
  }

  val httpClient =  Http.default.client

  def getRestContent(url: String, groupName: String): Response = {

    val httpResponse = httpClient.prepareGet(url).addQueryParam("groups", groupName).execute()
    val entity = httpResponse.get()
    entity
  }

  override def afterAll() {
    httpClient.close()
  }


  test("Provider transfer for ANONYMOUS should return ok") {
    new ProviderSet {
      When("Get Response")
      //val groupName = "Переводы"
//      val response = HttpHandler.getRestContent(url, transfer)
val response = getRestContent(url, transfer)
      //assertResult(200, response.getStatusCode)
      Then("Check Status Code 2xx")
      response.getStatusCode must be(success)
      Then("Check Text Status OK")
      response.getStatusText must be(ok)
      Then("Check response in JSON")
      val responseBody = response.getResponseBody
      responseBody must be(json)
      Then("Check resultCode OK")
      val jsonBody = Json.parse(responseBody)
      jsonBody must be(withResultCodeOk)
      Then("All values groupId corresponds goup in request")
      (jsonBody, transfer) must be(correctGroupIds)
      Then("All values lastName contains Фамилия")
    }
  }

  test("Provider internet for ANONYMOUS should return ok") {
    new ProviderSet {
      When("Get Response")
      //val groupName = "Переводы"
      //val response = HttpHandler.getRestContent(url, internet)
      val response = getRestContent(url, internet)
      //assertResult(200, response.getStatusCode)
      Then("Check Status Code 2xx")
      response.getStatusCode must be(success)
      Then("Check Text Status OK")
      response.getStatusText must be(ok)
      Then("Check response in JSON")
      val responseBody = response.getResponseBody
      responseBody must be(json)
      Then("Check resultCode OK")
      val jsonBody = Json.parse(responseBody)
      jsonBody must be(withResultCodeOk)
      Then("All values groupId corresponds goup in request")
      (jsonBody, internet) must be(correctGroupIds)
      Then("All values lastName contains Фамилия")
    }
  }

  test("Provider charity for ANONYMOUS should return ok") {
    new ProviderSet {
      When("Get Response")
      //val groupName = "Переводы"
      //val response = HttpHandler.getRestContent(url, charity)
      val response = getRestContent(url, charity)
      //assertResult(200, response.getStatusCode)
      Then("Check Status Code 2xx")
      response.getStatusCode must be(success)
      Then("Check Text Status OK")
      response.getStatusText must be(ok)
      Then("Check response in JSON")
      val responseBody = response.getResponseBody
      responseBody must be(json)
      Then("Check resultCode OK")
      val jsonBody = Json.parse(responseBody)
      jsonBody must be(withResultCodeOk)
      Then("All values groupId corresponds goup in request")
      (jsonBody, charity) must be(correctGroupIds)
      Then("All values lastName contains Фамилия")
    }
  }

}
