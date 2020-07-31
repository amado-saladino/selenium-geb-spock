package tests

import pages.EmailFriendPage
import pages.ProductReviewPage
import pages.RegisterPage
import spock.lang.Shared
import spock.lang.Stepwise
import spock.lang.Title

@Stepwise
@Title('User can send the product to a friend')
class EmailFriendSpec extends BaseSpec {
    @Shared String email

    def init() {
        email = faker.email
    }

    def 'User should be logged in'() {
        given: 'user fills in registration data'
        to RegisterPage
        init()
        selectGender(generateRandomGender())
        enterPersonalData(faker.maleFirstName, faker.maleLastName, email)
        selectBirthdate(faker.day, faker.month, faker.year)
        enterPassword(faker.password)

        when: 'user clicks register button'
        register()
        println email

        then:'It should display successful registration message'
        assert successMessage.text().contains('Your registration completed')
    }

    def 'User sends the product to a friend'() {
        given: 'Product page is open'
        searchByKeyword(helper.generateKeywordFromText(productName))

        when: 'user clicks "Email friend" link'
        emailFriend()

        then: 'It should navigate to Email friend page'
        assert page instanceof EmailFriendPage
        assert isProductTitleValid(productName)
        assert isSentFromUserEmail(email)
    }

    def 'User recommends the product'() {
        given: 'Send to friend form is visible'

        when: 'User fills in email and message fields'
        sendToFriend(faker.email, faker.randomMessage)

        then: 'It should send the message'
        assert isMessageSent()
    }
}
