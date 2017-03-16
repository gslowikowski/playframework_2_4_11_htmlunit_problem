import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

import org.fluentlenium.core.filter.FilterConstructor._

@RunWith(classOf[JUnitRunner])
class IntegrationSpec extends Specification {
  
  "Application" should {
    
    "work from within a browser" in {
      running(TestServer(3333), HTMLUNIT) { browser =>
        browser.goTo("http://localhost:3333/")
        
        browser.$("header h1").first.getText must equalTo("Play sample application — Computer database")
        browser.$("section h1").first.getText must equalTo("574 computers found")
        
        browser.$("#pagination li.current").first.getText must equalTo("Displaying 1 to 10 of 574")
        
        browser.$("#searchbox").text("Apple") // does not work for play 2.4.11 and htmlunit 2.18
        browser.$("#searchsubmit").click()
        
        browser.$("section h1").first.getText must equalTo("13 computers found")
      }
    }
    
  }
  
}