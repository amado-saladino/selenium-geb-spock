import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import utilities.Helper


environments {

    firefox {
        driver = { new FirefoxDriver() }
    }

    chrome {
        driver = {
            ChromeOptions options = new ChromeOptions()
            options.addArguments('disable-infobars','start-maximized')
            new ChromeDriver(options) }
    }

    chromeHeadless {
        driver = {
            ChromeOptions options = new ChromeOptions()
            options.addArguments('headless')
            new ChromeDriver(options)
        }
    }

}

waiting {
    timeout = 10
    retryInterval = 1
}

baseUrl = "https://demo.nopcommerce.com/"
reportsDir = new File("target/output")