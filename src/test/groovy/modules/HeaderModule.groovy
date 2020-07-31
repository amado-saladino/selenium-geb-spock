package modules

import geb.Module
import pages.HomePage
import pages.LoginPage
import pages.RegisterPage

class HeaderModule extends Module {
    static content = {
        comboChangeCurrency { $('#customerCurrency') }
        linkRegister(to: RegisterPage) { $('a',text:'Register') }
        linkLogin(to: LoginPage) { $('a',text:'Log in') }
        linkLogout(to: HomePage) { $('a',text:'Log out') }
        linkMyAccount { $('a',text: 'My account') }
        iconCart { $('.ico-cart') }
        flyingProductName(wait:true) { $('#flyout-cart div.name > a') }
    }

    def isLoggedIn() {
        linkLogout.isDisplayed()
    }

    def logout() {
        linkLogout.click(HomePage)
    }
}
