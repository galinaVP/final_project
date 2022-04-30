package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

  private final By creationAccountButton = By.xpath("//a[@data-link-action='display-register-form']");

  // Click on 'No account? Create one here' link
  @Step("Click on [Creation account] button")
  public RegisterAccountPage clickOnCreationAccountButton(){
    find(creationAccountButton).click();
    return new RegisterAccountPage();
  }
}
