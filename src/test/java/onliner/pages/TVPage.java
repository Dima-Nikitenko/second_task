package onliner.pages;

import framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class TVPage extends BasePage {

    private static String txtUniqueElement = "//div[@class = 'schema-header']//h1[contains(%s, 'Телевизоры')]";
    private String btnProducer = "//div[@class = 'schema-filter__label']/span[contains(text(), 'Производитель')]/../following-sibling::div//div[@class = 'schema-filter-control__item']";
    private String chkProducer = "//div[@class = 'schema-filter-popover__title'][contains(text(), 'Производитель' )]/..//span[@class = 'schema-filter__checkbox-text'][contains(%1$s, '%2$s')]";
    private String txtMaxPrice = "//div[@class = 'schema-filter__label']/span[contains(text(), 'Минимальная цена')]/../following-sibling::div//input[contains(@data-bind, 'value: facet.value.to')]";
    private String ddlMinDiagonal = "//div[@class = 'schema-filter__label']/span[contains(text(), 'Диагональ')]/../following-sibling::div//select[contains(@data-bind, 'value: facet.value.from')]";
    private String ddlMaxDiagonal = "//div[@class = 'schema-filter__label']/span[contains(text(), 'Диагональ')]/../following-sibling::div//select[contains(@data-bind, 'value: facet.value.to')]";
    private String chkResolution = "//div[@class = 'schema-filter__label']/span[contains(text(), 'Разрешение')]/../following-sibling::div//span[contains(%1$s, '%2$s')]";

    public TVPage(WebDriver driver) {
        super(driver, txtUniqueElement);
    }

    public void filterByParameters(String producer, String maxPrice, String minDiagonal, String maxDiagonal, String resolution) {
        filterByProducer(producer);
        filterByMaxPrice(maxPrice);
        filterByMinDiagonal(minDiagonal);
        filterByMaxDiagonal(maxDiagonal);
        filterByResolution(resolution);
    }

    private void filterByProducer(String producer) {
        scrollToElement(findElement(txtMaxPrice));
        findElement(btnProducer).click();
        findElementContainsText(chkProducer, producer).click();
    }

    private void filterByMaxPrice(String maxPrice) {
        scrollToElement(findElement(txtMaxPrice));
        findElement(txtMaxPrice).sendKeys(maxPrice);
    }

    private void filterByMinDiagonal(String minDiagonal) {
        scrollToElement(findElement(ddlMinDiagonal));
        select = new Select(findElement(ddlMinDiagonal));
        select.selectByVisibleText(minDiagonal);
    }

    private void filterByMaxDiagonal(String maxDiagonal) {
        scrollToElement(findElement(ddlMaxDiagonal));
        select = new Select(findElement(ddlMaxDiagonal));
        select.selectByVisibleText(maxDiagonal);
    }

    private void filterByResolution(String resolution) {
        scrollToElement(findElementContainsText(chkResolution, resolution));
        findElementContainsText(chkResolution, resolution).click();
    }
}