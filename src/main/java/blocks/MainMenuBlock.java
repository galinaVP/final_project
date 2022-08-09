package blocks;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.EconomicCalendarPage;

@Getter
public class MainMenuBlock {

  @Getter
  private static WebDriver driver;
  private final By researchAndEducationMainMenuButton = By.xpath("//li[@class='main_nav_research']/a");
  private final By economicCalendarMainMenuButton = By.xpath("//a[contains(text(),'Economic Calendar')]");

  public MainMenuBlock(WebDriver webDriver) {
    driver = webDriver;
  }

  public MainMenuBlock clickOnResearchAndEducationMainMenuButton() {
    getDriver().findElement(researchAndEducationMainMenuButton).click();
    return this;
  }

  public EconomicCalendarPage clickOnEconomicCalendarMainMenuButton() {
    getDriver().findElement(economicCalendarMainMenuButton).click();
    return new EconomicCalendarPage();
  }

}