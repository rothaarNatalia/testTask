package testingproject.matchers

import org.scalatest.Matchers._
import org.scalatest.matchers.{BeMatcher, MatchResult}
import play.api.libs.json.JsValue
import play.api.libs.json.Json

/**
  * Created by rothaar on 04.11.2017.
  */
trait JSONResponseMatchers {

  class ResponseIsJSONMatcher extends BeMatcher[String] {
    def apply(left: String) =

      MatchResult(
        (Json.parse(left)).isInstanceOf[JsValue],
         //catch{ case e: JsonParseException => false },
        left.toString + " wasn't json",
        left.toString + " was json"
      )

  }

  val json = new ResponseIsJSONMatcher
  val anotherFormat = not(json)

  class ResultCodeMatcher extends BeMatcher[JsValue] {
    def apply(left: JsValue) =

      MatchResult(
        (left \ "resultCode").asOpt[String] == Some("OK"),
        left.toString + " wasn't OK",
        left.toString + " was OK"

      )

  }

  val withResultCodeOk = new ResultCodeMatcher
  val anotherResultCode = not(withResultCodeOk)

  class GroupIdsMatcher extends BeMatcher[(JsValue, String)] {
    def apply(left: (JsValue, String)) =

      MatchResult(
        (left._1 \\ "groupId").toList forall (g => g.asOpt[String] == Some(left._2)),
        left.toString + " groupId isn't OK",
        left.toString + " groupId is OK"
      )

  }

  val correctGroupIds = new GroupIdsMatcher
  val invalidGroupIds = not(correctGroupIds)


  class LastNameMatcher extends BeMatcher[JsValue] {
    def apply(left: JsValue) =

      MatchResult(
        (left \\ "lastName").toList forall (g => g.asOpt[String] == Some("Фамилия")),
        left.toString + " lastName isn't OK",
        left.toString + " lastName is OK"
      )

  }

  val correctLastName = new LastNameMatcher
  val invalidLastName = not(correctLastName)

}
object JSONResponseMatchers extends JSONResponseMatchers{

}
