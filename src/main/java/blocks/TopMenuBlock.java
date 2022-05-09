package blocks;

import io.qameta.allure.Step;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.LoginPage;

@EqualsAndHashCode
public class TopMenuBlock {

  @Getter
  private static WebDriver driver;
  private final By languagesMenuButton = By.xpath("//button[@aria-label='Language dropdown']");
  private final By signInButton = By.xpath("//span[text()='Sign in']");
  private final By userNameText = By.xpath(
      "//div[@id='_desktop_user_info']//span[@class='hidden-sm-down']");

  public TopMenuBlock(WebDriver webDriver) {
    driver = webDriver;
  }

  // click on 'Language' dropdown in the top menu
  @Step("Click on [Language] dropdown in the top menu")
  public LanguagesBlock clickOnLanguagesMenuButton() {
    BasePage.wailVisibleLocated(languagesMenuButton, 5).click();
    return new LanguagesBlock(driver);
  }

  // click on "Sign in" button
  @Step("Click on [Sign in] button")
  public LoginPage clickOnSignIn() {
    BasePage.wailVisibleLocated(signInButton, 10).click();
    return new LoginPage();
  }

  // get username after login on site
  @Step("Get [username] after login on site")
  public String getUserNameAfterLoginOnSite() {
    return getDriver().findElement(userNameText).getText();
  }
}