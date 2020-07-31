package pages

import geb.module.RadioButtons
import geb.module.Select
import org.openqa.selenium.By

class CheckoutPage extends BasePage {
    static url ='onepagecheckout'
    static at = {
        title.contains('Checkout')
        pageHeader.text() == 'Checkout'
    }

    static content = {
        textFirstName { $('#BillingNewAddress_FirstName') }
        textLastName { $('#BillingNewAddress_LastName') }
        textEmail { $('#BillingNewAddress_Email') }
        comboCountry { $('#BillingNewAddress_CountryId') }
        textCity { $('#BillingNewAddress_City') }
        textAddress { $('#BillingNewAddress_Address1') }
        textZipCode { $('#BillingNewAddress_ZipPostalCode') }
        textPhoneNumber { $('#BillingNewAddress_PhoneNumber') }
        radioShipping(wait:true) { $(name:'shippingoption').module(RadioButtons) }
        radioPayment(wait: true) { $(name: 'paymentmethod').module(RadioButtons) }
        buttonContinue(wait: true) { $('input',value:'Continue') }
    }

    def validateUserInfo(firstname, lastname, email) {
        textFirstName.value() == firstname &&
                textLastName.value() == lastname &&
                textEmail.value() == email
    }

    def selectRandomCountry() {
        comboCountry = selectComboRandomItem(comboCountry,1)
    }

    def enterAddress(city, address1,zipcode,phone) {
        textCity << city
        textAddress << address1
        textZipCode << zipcode
        textPhoneNumber << phone
        clickContinueButton(0)
        selectShippingMethod('Ground')
        clickContinueButton(2)
        selectPaymentMethod('Check')
        clickContinueButton(3)
        waitForElement(buttonContinue.findAll().get(4).singleElement()).click()
    }

    def selectShippingMethod(method) {
        radioShipping = method
    }

    def selectPaymentMethod(method) {
        radioPayment = method
    }

    def clickContinueButton(index) {
        buttonContinue.findAll().get(index).click()
    }
}
