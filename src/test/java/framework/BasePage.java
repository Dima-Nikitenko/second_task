package framework;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class BasePage extends PropertiesHandler {

    private WebDriverWait wait;
    protected WebDriver driver;
    protected WebElement uniqueElement;
    protected Select select;
    protected SoftAssert softAssert;
    protected boolean correctPage;

    protected String translateText = "translate(text(), ' ', ' ')";

    public BasePage(WebDriver driver, String pathToUniqueElement) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(getProperty("durationOfSeconds"))));
        waitForPageToLoad();
        verifyOpenedPage(pathToUniqueElement);
    }

    private void verifyOpenedPage(String pathToUniqueElement) {
        uniqueElement = driver.findElement(By.xpath(String.format(pathToUniqueElement, translateText)));
        correctPage = uniqueElement.isDisplayed();
        Assert.assertTrue(correctPage, "Invalid page opened.");
    }

    public void scrollToElement(WebElement obj) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", obj);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, +300)");
    }

    protected void waitForElementToBecomeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitForElementLocatedToBecomeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForPresenceOfElementLocated(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void waitRefreshedStalenessOf(WebElement element) {
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
    }

    protected void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(getProperty("pageLoadTimeout"))));
        try {
            wait.until((ExpectedCondition<Boolean>) d -> {
                if (!(d instanceof JavascriptExecutor)) {
                    return true;
                }
                Object result = ((JavascriptExecutor) d)
                        .executeScript("return document['readyState'] ? 'complete' == document.readyState : true");
                return result instanceof Boolean && (Boolean) result;
            });
        } catch (Exception e) {
            System.out.println("Browser.page.timeout");
        }
    }

    protected WebElement findElement(String pathToElement) {
        waitForElementLocatedToBecomeVisible(By.xpath((pathToElement)));
        return driver.findElement(By.xpath(pathToElement));
    }

    protected WebElement findElementContainsText(String pathToElement, String text) {
        waitForElementLocatedToBecomeVisible(By.xpath(String.format(pathToElement, translateText, text)));
        return driver.findElement(By.xpath(String.format(pathToElement, translateText, text)));
    }

    protected List<WebElement> findListOfElements(String pathToTypicalElements) {
        waitForElementLocatedToBecomeVisible(By.xpath((pathToTypicalElements)));
        return driver.findElements(By.xpath(pathToTypicalElements));
    }
}
