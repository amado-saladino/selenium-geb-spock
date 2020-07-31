package tests

import geb.spock.GebReportingSpec
import pages.HomePage

class HomePageSpec extends GebReportingSpec {

    def "Home page opens"() {
        when: "Open Home page"
        to HomePage

        then:"Home page should have the right title"
        at HomePage
        and: "Header is visible"
        assert header.isDisplayed() == true
    }

}
