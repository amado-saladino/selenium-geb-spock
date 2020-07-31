package tests

import pages.ComparePage
import pages.HomePage
import pages.ProductPage
import spock.lang.Stepwise

@Stepwise
class CompareProductsSpec extends BaseSpec {
    String firstProductName = propertyReader.getProperty('productName')
    String secondProductName = propertyReader.getProperty('SecondProductName')

    def 'User can add the first product'() {
        given: 'User is at Home page'
        to HomePage

        when: 'User types a keyword'
        searchByKeyword(helper.generateKeywordFromText(firstProductName))
        addToCompareList()

        then: 'It should show notification bar'
        assert isAddedToCompareList()
        assert page instanceof ProductPage
    }

    def 'User can add the second product'() {
        given: 'User searches for the second product #secondProductName'
        searchByKeyword(helper.generateKeywordFromText(secondProductName))

        when:'User adds the product to compare'
        addToCompareList()

        then: 'It should show notification bar'
        assert isAddedToCompareList()
        assert page instanceof ProductPage
    }

    def 'User compare the products'() {
        given: 'User navigates to compare page'
        gotoComparePage()

        when: 'Compare page is open'
        assert isAtRightPage('Compare products')

        then: 'It should display products to compare'
        assert page instanceof ComparePage
        assert productsVisible()
        assert productLinksWithValidText(firstProductName, secondProductName)
    }

    def 'User clears the comparison list'() {
        given: 'It is compare page'
        at ComparePage

        when: 'User clears the list of products to compare'
        linkClearList.click()

        then: 'It should clear the list'
        assert isListEmpty()
    }
}
