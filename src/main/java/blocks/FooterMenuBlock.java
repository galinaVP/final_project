package blocks;

import io.qameta.allure.Step;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.PricesDropPage;

@EqualsAndHashCode
public class FooterMenuBlock {

  @Getter
  private static WebDriver driver;

  private final By pricesDropFooterMenuButton = By.id("link-product-page-prices-drop-1");

  public FooterMenuBlock(WebDriver webDriver) {
    driver = webDriver;
  }

  // click on 'Prices drop' from footer menu
  @Step("Click on [Prices drop] footer menu button")
  public PricesDropPage clickOnPricesDropFooterMenuButton() {
    BasePage.wailVisibleLocated(pricesDropFooterMenuButton, 5).click();
    return new PricesDropPage();
  }
}