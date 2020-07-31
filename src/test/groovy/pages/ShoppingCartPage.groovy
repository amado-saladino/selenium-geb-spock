package pages

import geb.module.Checkbox

class ShoppingCartPage extends BasePage {
    static at = {
        title.contains('Shopping Cart')
        pageHeader.text() == 'Shopping cart'
    }
    static url ='cart'
    static content = {
        productNameGrid { $('a.product-name') }
        buttonCheckout(to:CheckoutPage) { $('#checkout') }
        checkboxTermsOfService { $('#termsofservice').module(Checkbox) }
    }

    def isProductOnFlyingCart(productName) {
        waitFor { header.flyingProductName.isDisplayed() }
        header.flyingProductName.text() == productName
    }

    def isProductListedInGrid(productName) {
        productNameGrid.text() == productName
    }

    def checkout() {
        checkboxTermsOfService.check()
        buttonCheckout.click()
    }
}
