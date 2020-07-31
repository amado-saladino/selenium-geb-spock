package modules

import geb.Module
import pages.ProductPage

class SearchModule extends Module {
    static content = {
        textSearch  { $('#small-searchterms') }
        buttonSearch { $('input.button-1.search-box-button') }
        listProductsFound(to:ProductPage) { $('#ui-id-1') }
    }
}
