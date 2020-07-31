package pages

import geb.Page
import org.openqa.selenium.By

class SearchPage extends BasePage {
    static url ='/search'
    static at = {
        title.contains('Search')
        pageHeader.displayed
    }
    static content = {
        headerProductName { $('h2 > a') }
    }

    def openProductPage() {
        headerProductName.click(ProductPage)
    }

    def openProductPage(productLinkText) {
        $(By.linkText(productLinkText)).click(ProductPage)
    }
}
