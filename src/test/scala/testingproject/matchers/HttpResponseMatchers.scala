package testingproject.matchers

import org.scalatest.Matchers._
import org.scalatest.matchers.{BeMatcher, MatchResult}

trait HttpResponseMatchers {

  class ResponseCodeMatcher extends BeMatcher[Int] {
    def apply(left: Int) =
      MatchResult(
        ((left >= 200) && (left < 300)),
        left.toString + " was failed",
        left.toString + " was successed"
      )
  }
  val success = new ResponseCodeMatcher
  val feiled = not (success)

  class ResponseTextCodeMatcher extends BeMatcher[String] {
    def apply(left: String) =
      MatchResult(
        left == "OK",
        left.toString + " was failed",
        left.toString + " was successed"
      )
  }
  val ok = new ResponseTextCodeMatcher
  val fail = not (ok)
}


object HttpResponseMatchers extends HttpResponseMatchers{

}
