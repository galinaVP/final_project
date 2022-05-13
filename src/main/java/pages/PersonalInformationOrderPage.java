package pages;

import io.qameta.allure.Step;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PersonalInformationOrderPage extends BasePage {

  private final By firstNameInput = By.id("field-firstname");
  private final By lastNameInput = By.id("field-lastname");
  private final By emailInput = By.id("field-email");
  private final By birthdayInput = By.id("field-birthday");
  private final By customerDataPrivacyCheckbox = By.xpath("//input[@name='customer_privacy']");
  private final By continueConfirmPersonButton = By.xpath(
      "//button[@data-link-action='register-new-customer']");
  private final By addressInput = By.id("field-address1");
  private final By postcodeInput = By.id("field-postcode");
  private final By cityInput = By.id("field-city");
  private final By continueConfirmAddressesButton = By.xpath("//button[@name='confirm-addresses']");
  private final By shippingMethodCheckbox = By.xpath(
      "//label[contains(@for,'delivery_option')]//span[contains(@class,'carrier-name')]");
  private final By continueConfirmDeliveryOptionButton = By.xpath(
      "//button[@name='confirmDeliveryOption']");
  private final By paymentMethodCheckbox = By.xpath("//label[contains(@for,'payment-option')]");
  private final By amountPrice = By.xpath(
      "//div[@id='payment-option-1-additional-information']//dd[contains(text(),'(tax incl.)')]");
  private final By subTotalPrice = By.xpath(
      "//div[@id='cart-subtotal-products']/span[@class='value']");
  private final By shippingPrice = By.xpath(
      "//div[@id='cart-subtotal-shipping']/span[@class='value']");
  private final By iAgreeCheckbox = By.id("conditions_to_approve[terms-and-conditions]");
  private final By placeOrderButton = By.xpath("//div[@id='payment-confirmation']//button");


  // Method for input FirstName
  @Step("Entered First name as [{firstName}]")
  public PersonalInformationOrderPage enteredFirstNameAs(String firstName) {
    find(firstNameInput).sendKeys(firstName);
    return this;
  }

  // Method for input FirstName
  @Step("Entered Last name as [{lastName}]")
  public PersonalInformationOrderPage enteredLastNameAs(String lastName) {
    find(lastNameInput).sendKeys(lastName);
    return this;
  }

  // Method for input Email
  @Step("Entered Email as [{email}]")
  public PersonalInformationOrderPage enteredEmailAs(String email) {
    find(emailInput).sendKeys(email);
    return this;
  }

  // Method for input Birthdate
  @Step("Entered Birthdate input as [{birthdate}]")
  public PersonalInformationOrderPage enteredBirthdateInputAs(Date birthdate) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    String birthdateFormat = simpleDateFormat.format(birthdate);
    find(birthdayInput).sendKeys(birthdateFormat);
    return this;
  }

  @Step("Click [Customer data privacy] checkbox")
  public PersonalInformationOrderPage clickOnCustomerDataPrivacyCheckbox() {
    find(customerDataPrivacyCheckbox).click();
    return this;
  }

  @Step("Click [Continue] button")
  public PersonalInformationOrderPage continueConfirmPersonButton() {
    find(continueConfirmPersonButton).click();
    return new PersonalInformationOrderPage();
  }

  @Step("Entered Address as [{address}]")
  public PersonalInformationOrderPage enterAddressInputAs(String address) {
    wailElementToBeClickable(addressInput, 5).sendKeys(address);
    return this;
  }

  @Step("Entered Postcode as [{postcode}]")
  public PersonalInformationOrderPage enterPostcodeAs(String postcode) {
    find(postcodeInput).sendKeys(postcode);
    return this;
  }

  @Step("Entered City as [{city)}]")
  public PersonalInformationOrderPage enterCityAs(String city) {
    find(cityInput).sendKeys(city);
    return this;
  }

  @Step("Click on [Continue confirm addresses] button")
  public PersonalInformationOrderPage clickOnContinueConfirmAddressesButton() {
    find(continueConfirmAddressesButton).click();
    return this;
  }

  @Step("Select shipping method checkbox - [{shippingMethod}]")
  public PersonalInformationOrderPage selectShippingMethodCheckbox(String shippingMethod) {
    List<WebElement> shippingMethodsElements = getDriver().findElements(shippingMethodCheckbox);
    for (WebElement shippingMethodsElement : shippingMethodsElements) {
      if (shippingMethodsElement.getText().equalsIgnoreCase(shippingMethod)) {
        shippingMethodsElement.click();
      }
    }
    return this;
  }

  @Step("Click on [Continue confirm delivery option] button")
  public PersonalInformationOrderPage clickOnContinueConfirmDeliveryOptionButton() {
    find(continueConfirmDeliveryOptionButton).click();
    return this;
  }

  @Step("Select payment method checkbox: [{paymentMethod}]")
  public PersonalInformationOrderPage selectPaymentMethodCheckbox(String paymentMethod) {
    List<WebElement> paymentMethodsElements = getDriver().findElements(paymentMethodCheckbox);
    for (WebElement payment : paymentMethodsElements) {
      if (payment.getText().equalsIgnoreCase(paymentMethod)) {
        payment.click();
      }
    }
    return this;
  }

  @Step("Check that [Amount] price is correct")
  public boolean isAmountPriceCorrect() {
    Double amountPriceDouble = Double.valueOf(
        find(amountPrice).getText().substring(1).split(" ")[0]);
    Double subTotalPriceDouble = Double.valueOf(find(subTotalPrice).getText().substring(1));
    Double shippingPriceDouble = Double.valueOf(find(shippingPrice).getText().substring(1));
    return amountPriceDouble == new BigDecimal(subTotalPriceDouble + shippingPriceDouble).setScale(
            2, RoundingMode.HALF_EVEN)
        .doubleValue();
  }

  @Step("Click on [I Agree] checkbox")
  public PersonalInformationOrderPage clickOnIAgreeCheckbox() {
    find(iAgreeCheckbox).click();
    return this;
  }

  @Step("Click on [Place order] button")
  public OrderConfirmedPage clickOnPlaceOrderButton() {
    find(placeOrderButton).click();
    return new OrderConfirmedPage();
  }
}