package pages

import geb.Page
import org.openqa.selenium.By

class ContactUsPage extends BasePage {
    static url = 'contactus'
    static at = {
        title.contains('Contact Us')
        pageHeader.displayed
    }

    static content = {
        textFullName { $('#FullName') }
        textEmail { $('#Email') }
        textEnquiry { $('#Enquiry') }
        buttonSubmit { $('input',name:'send-email') }
        messageSuccess { $(By.cssSelector('div.result')) }
    }

    def sendEnquiry(name ,email, message) {
        textFullName << name
        textEmail.firstElement().sendKeys(email)
        textEnquiry << message
        buttonSubmit.click()
    }
}
