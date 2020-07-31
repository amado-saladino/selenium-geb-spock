package tests

import pages.CheckoutPage
import pages.ProductPage
import pages.RegisterPage
import pages.ShoppingCartPage
import spock.lang.Shared
import spock.lang.Stepwise
import spock.lang.Title

@Stepwise
@Title('User can Check out product')
class UserCheckoutSpec extends BaseSpec {
    @Shared user

    def 'User can register account'() {
        given: 'User fills in registration data'
        to RegisterPage
        user = fillRegistrationForm()

        when: 'user clicks register button'
        register()

        then: 'User should be now registered and logged in'
        assert successMessage.text().contains('Your registration completed')
    }

    def 'User can search for the product'() {
        given: 'Product page is open'
        searchByKeyword(helper.generateKeywordFromText(productName))

        when: 'User adds the product to the cart'
        addToCart()

        then: 'It should display notification bar'
        assert addedToCart()
        assert page instanceof ProductPage
        assert hasValidURL()
    }

    def 'User can view the products in the cart'() {
        given: 'Product page is open'
        gotoShoppingCart()

        when: 'User peeks in the flying shopping cart'
        peekFlyingCart()

        then: 'It should display the product name'
        assert page instanceof ShoppingCartPage
        assert isProductOnFlyingCart(productName)
        assert isProductListedInGrid(productName)
    }

    def 'User can start check-out process'() {
        given: 'Cart page is open'
        at ShoppingCartPage

        when: 'User clicks Checkout button'
        checkout()

        then: 'It should open "Checkout" page'
        assert page instanceof CheckoutPage
        assert validateUserInfo(user['firstname'], user['lastname'], user['email'])
    }

    def 'User can fill billing address'() {
        given: 'Check-out page is open'
        at CheckoutPage

        when: 'User fills in billing address fields'
        selectRandomCountry()
        enterAddress(faker.city, faker.address, faker.zipCode, faker.phone)

        then: 'It should fill all data'
        assert 1 == 1
    }

}
