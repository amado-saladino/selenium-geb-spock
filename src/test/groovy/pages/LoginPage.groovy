package pages

class LoginPage extends BasePage {
    static url ='login'
    static at = {
        title.contains('Login')
        pageHeader.displayed
    }
    static content = {
        textEmail { $('#Email') }
        textPassword { $('#Password') }
        buttonLogin { $('input.button-1.login-button') }
    }

    def login(email, password) {
        textEmail << email
        textPassword << password
        buttonLogin.click(HomePage)
    }
}
