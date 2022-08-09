package TestTasks;

import core.EventDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.BasePage;

public class BaseTest {

  @BeforeMethod
  public void setUp() {
    WebDriver driver;

    String browser = System.getProperty("browser");
    // property for setup browser
    switch (browser) {
      case "firefox":
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        break;
      case "edge":
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        break;
      case "chrome":
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + browser);
    }
    WebDriverListener listener = new EventDriver();
    WebDriver decorated = new EventFiringDecorator(listener).decorate(driver);
    BasePage.setDriver(decorated);
    Integer browserWidth = Integer.parseInt(System.getProperty("browserWidth"));
    Integer browserHeight = Integer.parseInt(System.getProperty("browserHeight"));
    BasePage.getDriver().manage().window().setPosition(new Point(0, 0));
    BasePage.getDriver().manage().window().setSize(new Dimension(browserWidth, browserHeight));
  }

  @AfterMethod(alwaysRun = true)
  public void closeDriver() {
    BasePage.getDriver().quit();
  }
}