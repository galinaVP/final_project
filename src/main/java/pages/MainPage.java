package pages;

import org.openqa.selenium.By;

public class MainPage extends BasePage {

  private final By startDropdown = By.xpath("//button[text()='ACCEPT ALL']");

  // open MAIN page
  public MainPage openMainPage() {
    getDriver().get("https://www.xm.com/");
    if (find(startDropdown).isDisplayed()) {
//      wailInvisibleLocated(startDropdown, 20);
      find(startDropdown).click();
    }
    return this;
  }

}