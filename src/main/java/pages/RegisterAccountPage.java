package pages;

import io.qameta.allure.Step;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;

public class RegisterAccountPage extends BasePage {

  private final By firstNameInput = By.id("field-firstname");
  private final By lastNameInput = By.id("field-lastname");
  private final By emailInput = By.id("field-email");
  private final By passwordInput = By.id("field-password");
  private final By birthdateInput = By.id("field-birthday");
  private final By customerDataPrivacyCheckbox = By.xpath("//input[@name='customer_privacy']");
  private final By saveButton = By.xpath("//button[contains(@class, 'form-control-submit')]");
  private final By alertDangerMessage = By.xpath("//div[@class='help-block']//li[1]");

  // Method for input FirstName
  @Step("Entered First name as [{firstName}]")
  public RegisterAccountPage enteredFirstNameAs(String firstName) {
    find(firstNameInput).sendKeys(firstName);
    return this;
  }

  // Method for input FirstName
  @Step("Entered Last name as [{lastName}]")
  public RegisterAccountPage enteredLastNameAs(String lastName) {
    find(lastNameInput).sendKeys(lastName);
    return this;
  }

  // Method for input Email
  @Step("Entered Email as [{email}]")
  public RegisterAccountPage enteredEmailAs(String email) {
    find(emailInput).sendKeys(email);
    return this;
  }

  // Method for input Password
  @Step("Entered Password as [{password}]")
  public RegisterAccountPage enteredPasswordAs(String password) {
    find(passwordInput).sendKeys(password);
    return this;
  }

  // Method for input Birthdate
  @Step("Entered Birthdate input as [{birthdate}]")
  public RegisterAccountPage enteredBirthdateInputAs(Date birthdate) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    String birthdateFormat = simpleDateFormat.format(birthdate);
    find(birthdateInput).sendKeys(birthdateFormat);
    return this;
  }

  @Step("Click [Customer data privacy] checkbox")
  public RegisterAccountPage clickOnCustomerDataPrivacyCheckbox() {
    find(customerDataPrivacyCheckbox).click();
    return this;
  }

  @Step("Click [Save] button")
  public MainPage clickOnSaveButton() {
    find(saveButton).click();
    return new MainPage();
  }

  @Step("Click [Save] button")
  public RegisterAccountPage clickOnSaveButtonWithInvalidData() {
    find(saveButton).click();
    return this;
  }

  @Step("Get value border of the selected color")
  public String getValueBorderOfTheSelectedColor() {
    return find(saveButton).getCssValue("outline-color");
  }

  @Step("Get alert danger message from invalid data field")
  public String getAlertDangerMessageFromInvalidDataField() {
    return find(alertDangerMessage).getText();
  }
}