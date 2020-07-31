package pages

import geb.Page
import modules.FooterModule
import modules.HeaderModule
import modules.SearchModule
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class BasePage extends Page {
    static content = {
        header { module HeaderModule }
        Search { module SearchModule }
        footer { module FooterModule }
        pageHeader { $('h1',0) }
    }

    def searchProduct(productName) {
        Search.textSearch << productName
        Search.buttonSearch.click(SearchPage)
    }

    def searchByKeyword(keyword) {
        Search.textSearch << keyword
        waitFor { Search.listProductsFound.displayed }
        Search.listProductsFound.click()
    }

    def isAtRightPage(String headerText) {
        $('h1',text:headerText).displayed
    }

    def scrollBottom() {
        js."scrollTo(0, document.body.scrollHeight)"
    }

    def waitForPageLoad() {
        waitFor { js."document.readyState" == 'complete' }
    }

    def waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10) ;
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    def openMenu(menu, submenu) {
       interact {
           moveToElement($('a',text:menu,0))
       }
        $('a',text:submenu,0).click()
    }

    def peekFlyingCart() {
        interact {
            moveToElement(header.iconCart)
        }
    }

    def selectComboRandomItem(combo, skip=0) {
        def options = combo.find(By.tagName('option'))
        //int optioncount = combo.find(By.tagName('option')).size()
        Random random = new Random()
        //int randomindex = random.nextInt(optioncount) + skip
        //new org.openqa.selenium.support.ui.Select(combo.firstElement()).selectByIndex(randomindex)
        int randomIndex = random.nextInt(options.size()) + skip
        def randomOption = options.getAt(randomIndex)
        def randomOptionText = randomOption.text()
        println 'Random option: ' + randomOptionText
        randomOptionText
    }
}
