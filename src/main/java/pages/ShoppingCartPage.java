package pages;

import io.qameta.allure.Step;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ShoppingCartPage extends BasePage {

  private final By productPrice = By.xpath(
      "//div[contains(@class, 'product-line-grid-right')]//span[@class='product-price']");
  private final By totalPrice = By.xpath(
      "//div[contains(@class, 'cart-total')]//span[@class='value']");
  private final By proceedToCheckoutButton = By.xpath("//div[contains(@class, 'checkout')]//a");

  // check that 'Total' calculated correct
  @Step("Check that [Total] calculated correct")
  public boolean isTotalPriceOnShoppingCartCorrect() {
    List<WebElement> productsPrice = getDriver().findElements(productPrice);
    double actualTotalPrice = 0;
    for (WebElement element : productsPrice) {
      actualTotalPrice = new BigDecimal(
          actualTotalPrice + Double.parseDouble(element.getText().substring(1))).setScale(2,
              RoundingMode.HALF_EVEN)
          .doubleValue();
    }
    double expectedTotalPrice = Double.parseDouble(find(totalPrice).getText().substring(1));
    return actualTotalPrice == expectedTotalPrice;
  }

  // click On Proceed To Checkout Button
  @Step("Click on [Proceed To Checkout] Button")
  public PersonalInformationOrderPage clickOnProceedToCheckoutButton() {
    find(proceedToCheckoutButton).click();
    return new PersonalInformationOrderPage();
  }
}