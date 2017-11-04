package utils

import dispatch.Http
import org.asynchttpclient.Response

/**
  * Created by rothaar on 04.11.2017.
  */
object HttpHandler {

  def getRestContent(url: String, groupName: String): Response = {

    val httpClient =  Http.default.client
    val httpResponse = httpClient.prepareGet(url).addQueryParam("groups", groupName).execute()
    val entity = httpResponse.get()
    //httpClient.close()
    entity
  }

}
