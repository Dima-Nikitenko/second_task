package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.time.Duration;

public class SetupBrowser {

    PropertiesHandler obj = new PropertiesHandler();
    ChromeOptions options = new ChromeOptions();
    public WebDriver driver;

    public SetupBrowser() {
        switch (obj.getProperty("browser").trim().toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                options.addArguments("start-maximized");
                driver = new ChromeDriver(options);
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            case "opera" -> {
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
            }
            case "ie" -> {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            }
        }
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(obj.getProperty("pageLoadTimeout"))));
    }
}
