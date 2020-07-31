package pages

class HomePage extends BasePage {
    static url = '/'
    static at = { title == 'nopCommerce demo store' }

    static content = {
        webLogo { $('.header-logo') }
    }

    def gotoLink(txt) {
        find('a',text:txt).click()
    }
}
