package utilities

import io.codearte.jfairy.Fairy
import io.codearte.jfairy.producer.person.PersonProperties
import org.joda.time.LocalDate
import java.text.DateFormatSymbols

class Faker {
    Fairy fairy
    LocalDate localDate
    String day, month, year
    private Random random

    Faker(){
        fairy= Fairy.create()
        random = new Random()
    }

    String getFullName(){
        return fairy.person(PersonProperties.male()).getFullName()
    }

    String getFemaleFirstName(){
        return fairy.person(PersonProperties.female()).getFirstName()
    }

    int generateRandomNumberBetween(min, max) {
        random.nextInt(max) + min
    }

    void generateLocalDate(){
        localDate = fairy.person(PersonProperties.withAge(30)).getDateOfBirth().toLocalDate()
        day = String.valueOf(localDate.getDayOfMonth() )
        month = getMonthName(localDate.getMonthOfYear())
        year = String.valueOf( localDate.getYear() )
    }

    private String getMonthName(int monthNumber) {
        String month = "wrong"
        DateFormatSymbols monthsNames = new DateFormatSymbols()
        String[] months = monthsNames.getMonths()

        if (monthNumber == 12)
            monthNumber--

        if (monthNumber >= 0 && monthNumber <= 11 ) {
            month = months[monthNumber]
        }
        return month
    }

    String getPassword() {
        return fairy.person(PersonProperties.withUsername("Amado")).getPassword()
    }

    String getMaleFirstName() {
        return fairy.person(PersonProperties.male()).getFirstName()
    }

    String getMaleLastName() {
        return fairy.person(PersonProperties.male()).getLastName()
    }

    String getRandomNumber() {
        return fairy.person(PersonProperties.male()).getNationalIdentityCardNumber()
    }

    String getAge() {
        return String.valueOf(  fairy.person(PersonProperties.minAge(14)).getAge()   )
    }

    String getCity() {
        return fairy.person(PersonProperties.female()).getAddress().getCity()
    }

    String getCityMalePerson() {
        return fairy.person(PersonProperties.male()).getAddress().getCity()
    }

    String getEmail() {
        return fairy.person().getEmail()
    }

    String getPhone() {
        return fairy.person().getTelephoneNumber()
    }

    String getAddress(){
        return fairy.person().getAddress().getAddressLine1()
    }

    String getZipCode() {
        fairy.person().getAddress().getPostalCode()
    }

    String getYear() {
        return year
    }

    String getMonth() {
        return month
    }

    String getDayOfMonth() {
        return day
    }

    String getRandomMessage() {
        return fairy.textProducer().sentence()
    }

    String getRandomTitle() {
        fairy.textProducer().latinWord()
    }
}
