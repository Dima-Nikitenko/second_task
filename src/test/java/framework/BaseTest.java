package framework;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest extends PropertiesHandler {

    private WebDriver driver;

    @BeforeSuite
    public void setup() {
        SetupBrowser obj = new SetupBrowser();
        driver = obj.driver;
        driver.get(getProperty("url"));
    }

    @AfterSuite
    public void teardown() {
        if(null != driver) {
            driver.close();
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
