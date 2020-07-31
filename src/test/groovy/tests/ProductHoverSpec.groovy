package tests

import pages.HomePage
import spock.lang.Stepwise

@Stepwise
class ProductHoverSpec extends BaseSpec {
    String category = propertyReader.getProperty('category')
    String subCategory = propertyReader.getProperty('sub_category')

    def 'User can hover the mouse over the menus'() {
        given: 'User is at home page'
        to HomePage

        when: 'User hovers the mouse over computers and notebook menu'
        openMenu(category, subCategory)

        then: 'It should display notebook page'
        assert isAtRightPage(subCategory)
    }
}
