package pages

class EmailFriendPage extends BasePage {

    static at = {
        title.contains('Email A Friend')
        pageHeader.displayed
    }

    static content = {
        productTitle { $('a.product') }
        textFriendsEmail { $('#FriendEmail') }
        textYourEmail { $('#YourEmailAddress') }
        textMessage { $('#PersonalMessage') }
        buttonSend { $(name:'send-email',type:'submit') }
        labelMessageSent { $('div.result') }
    }

    def sendToFriend(friendMail, message) {
        textFriendsEmail << friendMail
        textMessage << message
        buttonSend.click()
    }

    def isProductTitleValid(product) {
        productTitle.text() == product
    }

    def isSentFromUserEmail(email) {
        textYourEmail.value() == email
    }

    def isMessageSent() {
        labelMessageSent.text().contains('Your message has been sent.')
    }
}
