package onliner.tests;

import framework.BaseTest;
import onliner.pages.TVPage;
import onliner.pages.HomePage;
import onliner.pages.CatalogPage;
import onliner.pages.TVPageFilterResults;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;


public class TestCatalogFiltering extends BaseTest{

    private TVPage tvPage;
    private HomePage homePage;
    private CatalogPage catalogPage;
    private TVPageFilterResults tvPageFilterResults;

    @Parameters({ "Producer", "MaxPrice", "MinDiagonal", "MaxDiagonal", "Resolution" }) // specified in the testng.xml file
    @Test
    void testCatalogFiltering(String producer, String maxPrice, String minDiagonal, String maxDiagonal, String resolution) {
        homePage = new HomePage(getDriver());
        homePage.navigateSection("Каталог");
        catalogPage = new CatalogPage(getDriver());
        catalogPage.navigateDropdownItem("Электроника", "Телевидение и видео", "Телевизоры");
        tvPage = new TVPage(getDriver());
        tvPage.filterByParameters(producer, maxPrice, minDiagonal, maxDiagonal, resolution);
        tvPageFilterResults = new TVPageFilterResults(getDriver());
        tvPageFilterResults.checkCorrectSearchResults(producer, maxPrice, minDiagonal, maxDiagonal, resolution);
    }
}