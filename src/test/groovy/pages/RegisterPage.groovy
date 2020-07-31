package pages

import geb.module.RadioButtons
import org.openqa.selenium.By

class RegisterPage extends BasePage {
    static url ='register'
    static at = {
        title.contains('Register')
        pageHeader.displayed
    }
    static content = {
        textFirstname { $('#FirstName') }
        textLastname { $('#LastName') }
        textEmail { $('#Email') }
        radioGender { $(name:'Gender').module(RadioButtons) }
        comboDay { $(By.name('DateOfBirthDay')) }
        comboMonth { $(By.name('DateOfBirthMonth')) }
        comboYear { $('select', name:'DateOfBirthYear') }
        textPassword { $('#Password') }
        textConfirmPassword { $('#ConfirmPassword') }
        buttonRegister { $('#register-button') }
        successMessage(wait: true) { $('div.result') }
    }

    def enterPersonalData(firstname, lastname, email) {
        textFirstname << firstname
        textLastname << lastname
        textEmail << email
    }

    def enterPassword(password) {
        textPassword << password
        textConfirmPassword << password
    }

    def selectGender(gender) {
        radioGender = gender
    }

    def selectBirthdate(day, month, year) {
        comboDay = day
        comboMonth = month
        comboYear = year
    }

    def register() {
        buttonRegister.click()
    }
}
