package tests

import pages.HomePage
import pages.ProductPage
import pages.SearchPage
import spock.lang.Stepwise

@Stepwise
class SearchSpec extends BaseSpec {

    def "User can search normally with no Auto complete"() {
        given: "User is at home page"
        to HomePage

        when: "User writes down product name"
        searchProduct(productName)

        then: "It should navigate to search page"
        assert page instanceof SearchPage
    }

    def "User can search using keyword with Auto complete"() {
        given: "User is at home page"
        to HomePage

        when: 'User types a keyword'
        searchByKeyword(helper.generateKeywordFromText(productName))

        then: 'It should navigate to Product Page'
        assert page instanceof ProductPage
    }
}
