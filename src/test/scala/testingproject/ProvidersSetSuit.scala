package testingproject

import dispatch.Http
import org.asynchttpclient.Response
import org.junit.runner.RunWith
import org.scalatest.{BeforeAndAfterAll , FunSuite, GivenWhenThen, MustMatchers}
import org.scalatest.junit.JUnitRunner
import play.api.libs.json.{Json}
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

  def getRestContent(groupName: String): Response = {

    val httpResponse = httpClient.prepareGet(url).addQueryParam("groups", groupName).execute()
    httpResponse.get()
  }

  override def afterAll() {
    httpClient.close()
  }


  test("Provider transfer for ANONYMOUS should return ok") {
    new ProviderSet {
      When("Get Response")
      val response = getRestContent(transfer)
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
      val response = getRestContent(internet)
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
      val response = getRestContent(charity)
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
