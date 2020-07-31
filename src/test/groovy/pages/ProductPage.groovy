package pages

import geb.Page
import tests.BaseSpec

class ProductPage extends BasePage {
    static at = {
        title.contains(productNameHeader.text())
    }

    static content = {
        productNamebreadCrumb { $('strong.current-item') }
        productNameHeader { $('h1') }
        buttonEmailFriend(to:EmailFriendPage) { $('input.button-2.email-a-friend-button') }
        labelPrice { $('span.price-value-4') }
        buttonAddWishList { $('add-to-wishlist-button-4') }
        linkAddReview { $('a', text:'Add your review') }
        buttonCompare { $('div.compare-products > input.button-2.add-to-compare-list-button') }
        buttonAddCart { $('input[value="Add to cart"]',0) }
        topBarNotification(wait:true) { $('#bar-notification') }
        linkGotoCompare { topBarNotification.find('a') }
    }

    def isAtProductPage(productName) {
        title.contains(productName) &&
                productNameHeader.text() == productName
    }

    def addedToCart() {
        waitFor { topBarNotification.displayed }
        topBarNotification.displayed
    }

    def hasValidURL() {
        getDriver().getCurrentUrl().contains(getPageUrl())
    }

    def addToCart() {
        buttonAddCart.click()
    }

    def gotoShoppingCart() {
        linkGotoCompare.click(ShoppingCartPage)
        waitForPageLoad()
    }

    def emailFriend() {
        buttonEmailFriend.click()
    }

    def addToCompareList() {
        buttonCompare.click()
    }

    def isAddedToCompareList() {
        waitFor { topBarNotification.displayed }
    }

    def gotoComparePage() {
        linkGotoCompare.click(ComparePage)
    }

    def reviewProduct() {
        linkAddReview.click(ProductReviewPage)
    }

    def getProductUrl() {
        productNameHeader.text().toLowerCase().replaceAll(' ','-')
    }
}
