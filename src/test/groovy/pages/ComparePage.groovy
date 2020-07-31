package pages

class ComparePage extends BasePage {
    static url = 'compareproducts'
    static at = {
        title.contains('Compare Products')
        pageHeader.displayed
    }

    static content = {
        linkFirstProduct { $('tr:nth-of-type(3) > td:nth-child(3) > a') }
        linkSecondProduct { $('tr:nth-of-type(3) > td:nth-child(2) > a') }
        linkClearList { $('a.clear-list') }
        textNoItemsToCompare(wait:true) { $('div.no-data') }
    }

    def productsVisible() {
        linkFirstProduct.displayed && linkSecondProduct.displayed
    }

    def productLinksWithValidText(firstProduct, secondProduct) {
        linkFirstProduct.text().contains(firstProduct) &&
                linkSecondProduct.text().contains(secondProduct)
    }

    def isListEmpty() {
        textNoItemsToCompare.displayed &&
                textNoItemsToCompare.text() == 'You have no items to compare.'
    }
}
