package tests

import com.opencsv.CSVReader
import pages.ContactUsPage
import pages.HomePage
import spock.lang.Shared
import spock.lang.Stepwise
import spock.lang.Title
import spock.lang.Unroll
import utilities.CSVExplorer
import utilities.TextFileReader

@Stepwise
@Title('User can contact us using CSV')
class ContactCSVSpec extends BaseSpec {

    @Shared CSVReader csvReader
    @Shared TextFileReader textFileReader

    @Unroll
    def "User #name with email: #email can contact us anonymously"() {
        given: "User first opens Home Page"
        to HomePage
        scrollBottom()

        when: "User clicks 'Contact Us' link"
        gotoLink('Contact us')
        to ContactUsPage
        and: "User fills in enquiry"
        sendEnquiry(name, email, faker.getRandomMessage())

        then: "It should send the enquiry successfully"
        assert messageSuccess.isDisplayed()

        where:
        [name, email] << new CSVExplorer('data.csv')
    }
}
