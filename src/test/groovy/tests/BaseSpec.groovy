package tests

import geb.spock.GebReportingSpec
import org.openqa.selenium.interactions.Actions
import spock.lang.Shared
import utilities.Faker
import utilities.Helper
import utilities.PropertyReader

class BaseSpec extends GebReportingSpec {

    @Shared PropertyReader propertyReader
    @Shared Helper helper
    @Shared Faker faker
    @Shared productName

    def setupSpec() {
        helper = new Helper()
        faker = new Faker()
        faker.generateLocalDate()
        propertyReader = new PropertyReader('env.properties')
        productName = propertyReader.getProperty('productName')
    }

    def generateRandomGender() {
        final String[] genders = new String[2];
        genders[0] = "Female"
        genders[1] = "Male"
        genders[faker.generateRandomNumberBetween(0,2)]
    }

    def fillRegistrationForm() {
        def registeredUser = [:]
        registeredUser['firstname'] = faker.femaleFirstName
        registeredUser['lastname'] = faker.maleLastName
        registeredUser['email'] = faker.email
        registeredUser['password'] = faker.password

        selectGender(generateRandomGender())
        enterPersonalData(registeredUser['firstname'], registeredUser['lastname'], registeredUser['email'])
        selectBirthdate(faker.day, faker.month, faker.year)
        enterPassword(registeredUser['password'])

        registeredUser
    }

}
