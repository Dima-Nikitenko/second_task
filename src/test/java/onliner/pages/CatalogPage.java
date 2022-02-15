package onliner.pages;

import framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class CatalogPage extends BasePage {

    private Actions action;

    private static String txtUniqueElement = "//div[@class = 'catalog-navigation__title'][contains(%s, 'Каталог')]";
    private String btnMenu = "//span[@class= 'catalog-navigation-classifier__item-title']/span[contains(%1$s, '%2$s')]";
    private String lblSubMenu = "//div[@class = 'catalog-navigation-list__category' and @style = 'display: block;']//div[contains(%1$s, '%2$s')]";
    private String btnDropdownMenu = "//div[contains(@class, 'aside-item_active')]//span[contains(@class, 'dropdown-title')][contains(%1$s, '%2$s')]";

    public CatalogPage(WebDriver driver) {
        super(driver, txtUniqueElement);
    }

    public void navigateDropdownItem(String menuItem, String subMenuItem, String dropdownMenuItem) {
        action = new Actions(driver);
        findElementContainsText(btnMenu, menuItem).click();
        action.moveToElement(findElementContainsText(lblSubMenu, subMenuItem)).build().perform();
        action.moveToElement(findElementContainsText(btnDropdownMenu, dropdownMenuItem)).click().build().perform();
    }
}