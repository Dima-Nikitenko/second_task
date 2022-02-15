package onliner.pages;

import framework.BasePage;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.ArrayList;

public class TVPageFilterResults extends BasePage {

    private static String txtUniqueElement = "//div[@class = 'schema-header']//h1[contains(%s, 'Телевизоры')]";

    private String lblItemTitle = "//div[@id = 'schema-products']//div[@class = 'schema-product__title']//span";
    private String lblItemPrice = "//div[@id = 'schema-products']//div[@class = 'schema-product__price']//span[contains(@data-bind, 'price')]";
    private String txtItemDescription = "//div[@id = 'schema-products']//div[@class = 'schema-product__description']//span";
    private String lblBeautifulScroll = "//div[@id = 'schema-products']//div[@class = 'schema-product__title']//span";
    private String schemaToWaitAfterFiltering = "//div[@class = 'schema-product__group']";

    private boolean isCorrectParameter;
    private String getTextFromParameter;
    private List<WebElement> itemElement;
    private ArrayList<WebElement> tags = new ArrayList<>();
    private WebElement elementForBeautifulScroll;
    private WebElement schemaToWait;

    public TVPageFilterResults(WebDriver driver) {
        super(driver, txtUniqueElement);
    }

    public void checkCorrectSearchResults(String producer, String maxPrice, String minDiagonal, String maxDiagonal, String resolution) {
        softAssert = new SoftAssert();

        schemaToWait = driver.findElement(By.xpath(schemaToWaitAfterFiltering));
        waitForPresenceOfElementLocated(By.xpath(schemaToWaitAfterFiltering));
        waitRefreshedStalenessOf(schemaToWait);

        elementForBeautifulScroll = driver.findElement(By.xpath(lblBeautifulScroll));
        scrollToElement(elementForBeautifulScroll);

        softAssert.assertTrue(isCorrectProducer(producer),"The \"Producer\" parameter does not match the search criteria.");
        softAssert.assertTrue(isCorrectPrice(maxPrice),"The \"Price\" parameter does not match the search criteria.");
        softAssert.assertTrue(isCorrectDiagonal(minDiagonal, maxDiagonal),"The \"Diagonal\" parameter does not match the search criteria.");
        softAssert.assertTrue(isCorrectResolution(resolution),"The \"Resolution\" parameter does not match the search criteria.");
        softAssert.assertAll();
    }

    private boolean isCorrectProducer(String producer) {
        itemElement = findListOfElements(lblItemTitle);
        tags.addAll(itemElement);

        for (WebElement tag : tags) {
            getTextFromParameter = tag.getText();
            isCorrectParameter = getTextFromParameter.contains(producer);
            if(!isCorrectParameter) {
                tags.clear();
                return false;
            }
        }
        tags.clear();
        return true;
    }

    private boolean isCorrectPrice(String maxPrice) {
        String isolatedPrice;
        String priceWithDot;
        double priceToDouble;

        itemElement = findListOfElements(lblItemPrice);
        tags.addAll(itemElement);

        for (WebElement tag : tags) {
            getTextFromParameter = tag.getText();
            isolatedPrice = getTextFromParameter.substring(0, tag.getText().length()-3);
            priceWithDot = isolatedPrice.replace(',', '.');
            priceToDouble = Double.parseDouble(priceWithDot);
            isCorrectParameter = priceToDouble < Integer.parseInt(maxPrice);
            if(!isCorrectParameter) {
                tags.clear();
                return false;
            }
        }
        tags.clear();
        return true;
    }

    private boolean isCorrectDiagonal(String minDiagonal, String maxDiagonal) {
        String soughtDiagonal;
        double soughtDiagonalToDouble;
        double minDiagonalToDouble = Double.parseDouble(minDiagonal.replace("\"", ""));
        double maxDiagonalToDouble = Double.parseDouble(maxDiagonal.replace("\"", ""));

        itemElement = findListOfElements(txtItemDescription);
        tags.addAll(itemElement);

        for (WebElement tag : tags) {
            getTextFromParameter = tag.getText();
            soughtDiagonal = getTextFromParameter.substring(0, getTextFromParameter.indexOf("\""));
            soughtDiagonalToDouble = Double.parseDouble(soughtDiagonal);
            isCorrectParameter = soughtDiagonalToDouble >= minDiagonalToDouble & soughtDiagonalToDouble <= maxDiagonalToDouble;
            if(!isCorrectParameter) {
                tags.clear();
                return false;
            }
        }
        tags.clear();
        return true;
    }

    private boolean isCorrectResolution(String resolution) {
        itemElement = findListOfElements(txtItemDescription);
        tags.addAll(itemElement);

        for (WebElement tag : tags) {
            getTextFromParameter = tag.getText();
            isCorrectParameter = getTextFromParameter.contains(resolution);
            if(!isCorrectParameter) {
                tags.clear();
                return false;
            }
        }
        tags.clear();
        return true;
    }
}
