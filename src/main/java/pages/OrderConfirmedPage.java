package pages;

import io.qameta.allure.Step;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.openqa.selenium.By;

public class OrderConfirmedPage extends BasePage {

  private final By orderConfirmedTitle = By.xpath(
      "//section[@id='content-hook_order_confirmation']//h3");
  private final By subTotalPrice = By.xpath(
      "//table//td[text()='Subtotal']//following-sibling::td");
  private final By shippingPrice = By.xpath(
      "//table//td[text()='Shipping and handling']//following-sibling::td");
  private final By totalPrice = By.xpath("//tr[contains(@class,'total-value')]//td[2]");

  @Step("Get order confirmed title")
  public String getOrderConfirmedTitle() {
    return find(orderConfirmedTitle).getText().substring(1);
  }

  @Step("Check that total price calculated is correct")
  public boolean isTotalPriceCalculatedCorrect() {
    Double subTotalPriceDouble = Double.valueOf(find(subTotalPrice).getText().substring(1));
    Double shippingPriceDouble = Double.valueOf(find(shippingPrice).getText().substring(1));
    Double totalPriceDouble = Double.valueOf(find(totalPrice).getText().substring(1));
    return totalPriceDouble == new BigDecimal(subTotalPriceDouble + shippingPriceDouble).setScale(2,
        RoundingMode.HALF_EVEN).doubleValue();
  }
}