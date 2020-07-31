package tests

import pages.ProductReviewPage
import pages.RegisterPage
import spock.lang.Shared
import spock.lang.Stepwise
import spock.lang.Title

@Stepwise
@Title('User can review a product')
class ReviewProductSpec extends BaseSpec {
    @Shared user
    def 'User should be logged in'() {
        given: 'user fills in registration data'
        to RegisterPage
        user = fillRegistrationForm()

        when: 'user clicks register button'
        register()

        then:'It should display successful registration message'
        assert successMessage.text().contains('Your registration completed')
    }

    def 'User wants to review product'() {
        given: 'Product page is open'
        searchByKeyword(helper.generateKeywordFromText(productName))

        when: 'user clicks "Add your review" link'
        reviewProduct()

        then: 'It should navigate to Product Review page'
        assert page instanceof ProductReviewPage
        assert isValidReviewHeader(productName)
    }

    def 'User can add a product review'() {
        given: 'Product review page is open'

        when: 'User fills in review fields'
        def randomRate = faker.generateRandomNumberBetween(1,6)
        def rate = randomRate ==6 ? 5 : randomRate
        def reviewTitle = faker.randomTitle
        def randomMessage = faker.randomMessage
        writeReview(reviewTitle, randomMessage, rate)

        then: 'It should add the review to Existing reviews section'
        assert isReviewAdded(reviewTitle, randomMessage, rate, user['firstname'])
    }
}
