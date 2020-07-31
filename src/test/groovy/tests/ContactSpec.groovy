package tests

import geb.spock.GebReportingSpec
import pages.ContactUsPage
import pages.HomePage
import pages.RegisterPage
import spock.lang.Shared
import spock.lang.Stepwise
import spock.lang.Title

@Stepwise
@Title('User can contact us')
class ContactSpec extends BaseSpec {
    @Shared name, email, enquiry, firstname, lastname, password

    def "Initiating data for Contact us form"() {
        given: 'set up name, mail and random message'
        name = faker.femaleFirstName
        email = faker.email
        enquiry = faker.getRandomMessage()
    }

    def "User can contact us anonymously"() {
        given: "User first opens Home Page"
        to HomePage

        when: "User clicks 'Contact Us' link"
        gotoLink('Contact us')
        to ContactUsPage
        and: "User fills in enquiry"
        sendEnquiry(name, email, enquiry )

        then: "It should send the enquiry successfully"
        assert messageSuccess.isDisplayed()
    }

    def "Generate registration data"() {
        given: 'Initiating data for registration'
        firstname = faker.femaleFirstName
        lastname = faker.maleLastName
        email = faker.email
        password = faker.password
    }

    def 'User register a new account'() {
        given: "User registers new account"
        header.linkRegister.click()
        at RegisterPage

        when: 'User fills in data'
        enterPersonalData(firstname, lastname, email)
        selectGender('Female')
        selectBirthdate(faker.dayOfMonth, faker.month, faker.year)
        enterPassword(password)
        and: 'user clicks Register button'
        register()

        then: 'It should create a register a new user'
        assert successMessage.text().contains('Your registration completed')
    }

    def 'Logged-in user can contact us using his email and name'() {
        given: 'user navigates to Contact us page'
        footer.linkContact.click()

        when: 'user is ready to send enquiry'

        then: 'Name and Email fields use user data already registered with'
        assert textFullName == firstname + ' ' + lastname
        assert textEmail == email
        assert page instanceof ContactUsPage
    }

    def "user sends enquiry"() {
        given: 'user is at contact us page'
        at ContactUsPage

        when: 'User fills in enquiry for Contact us form'
        textEnquiry = faker.getRandomMessage()
        buttonSubmit.click()

        then: 'It should send the enquiry'
        assert messageSuccess.displayed
    }

    def cleanupSpec() {
        header.logout()
    }
}
