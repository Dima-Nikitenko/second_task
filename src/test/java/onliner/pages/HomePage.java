package onliner.pages;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static String lblUniqueElement = "//header/h2/a[contains(%s, 'Каталог')]";
    private String lblSection = "//header/h2/a[contains(%1$s, '%2$s')]";

    public HomePage(WebDriver driver) {
        super(driver, lblUniqueElement);
    }

    public void navigateSection(String sectionName) {
        findElementContainsText(lblSection, sectionName).click();
    }
}
