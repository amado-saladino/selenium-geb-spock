package tests

import pages.HomePage
import pages.LoginPage
import spock.lang.Shared
import spock.lang.Stepwise
import spock.lang.Title

@Stepwise
@Title('User can register and log in')
class RegisterSpec extends BaseSpec {

    @Shared String firstName,lastName, email, password, day, month, year

    def setup_class() {
        firstName = faker.maleFirstName
        lastName = faker.maleLastName
        email = faker.email
        password = faker.password
        day = faker.dayOfMonth
        month = faker.month
        year = faker.year
    }

    def 'User can register'() {
        given: 'Go to home page'
        setup_class()
        to HomePage
        header.linkRegister.click()

        when:'User fills in data'
        selectGender('Male')
        enterPersonalData(firstName, lastName, email)
        selectBirthdate(day, month, year)
        enterPassword(password)
        and:'User clicks register button'
        register()

        then:'It should display successful registration message'
        assert successMessage.text().contains('Your registration completed')
    }

    def "User can log in with credentials"() {
        given: "user log out"
        header.linkLogout().click()
        at HomePage

        when: "user logs in again"
        header.linkLogin.click()
        login(email, password)

        then: "User should be logged-in"
        assert page instanceof HomePage
        assert header.isLoggedIn()
    }

    def cleanupSpec() {
        header.logout()
    }
}
