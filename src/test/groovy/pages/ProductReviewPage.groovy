package pages

import geb.module.RadioButtons

class ProductReviewPage extends BasePage {
    static at = {
        //title.contains('Product Reviews')
        pageHeader.displayed
    }

    static content = {
        textReviewTitle { $('#AddProductReview_Title') }
        textReview { $('#AddProductReview_ReviewText') }
        radioRating { $(name:'AddProductReview.Rating').module(RadioButtons) }
        buttonSubmitReview { $('name':'add-review') }
        labelReviewAdded { $('div.result') }
        labelReviewTitle { $('.review-title > strong',0) }
        labelReviewFrom { $('span.user', 0) }
        labelReviewBody { $('div.review-text',0) }
        ratingStars() { $('div.rating > div',0) }
    }

    def writeReview(title, text, rate) {
        textReviewTitle << title
        textReview << text
        radioRating = rate
        buttonSubmitReview.click()
    }

    def isReviewAdded(title, text, rate, firstname) {
        waitFor { labelReviewAdded.displayed }
        labelReviewTitle.text() == title &&
                labelReviewBody.text() == text &&
                labelReviewFrom.text().replaceAll('From: ','') == firstname &&
                ratingStars.getAttribute('style') == 'width: ' + rate * 20 + '%;'
    }

    def isValidReviewHeader(productName) {
        waitFor { buttonSubmitReview.displayed }
        pageHeader.text() == 'Product reviews for ' +
                productName
    }
}
