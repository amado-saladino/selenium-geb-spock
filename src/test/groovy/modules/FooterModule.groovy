package modules

import geb.Module
import pages.ContactUsPage

class FooterModule extends Module {
    static content = {
        linkContact(to: ContactUsPage) { $('a', text: 'Contact us') }
    }
}
