package blocks;

import io.qameta.allure.Step;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Utils;

@EqualsAndHashCode
public class SubscribeBlock {

  @Getter
  private static WebDriver driver;
  private final By subscribeContainer = By.id("blockEmailSubscription_displayFooterBefore");
  private final By textNearTheEmailField = By.id("block-newsletter-label");
  private final By textUnderEmailField = By.xpath(
      "//div[@class='col-xs-12']/p");
  private final By subscribeButtonTextRegister = By.xpath("//input[@value='Subscribe']");

  public SubscribeBlock(WebDriver webDriver) {
    driver = webDriver;
  }

  @Step("Get [Text Near the email] field")
  public String getTextNearTheEmailField() {
    Utils.scrollToElement(getDriver(), textNearTheEmailField);
    return getDriver().findElement(textNearTheEmailField).getText();
  }

  @Step("Get [Text Under the email] field")
  public String getTextUnderEmailField() {
    Utils.scrollToElement(getDriver(), textUnderEmailField);
    return getDriver().findElement(textUnderEmailField).getText();
  }

  @Step("Get text value from [Subscribe] button")
  public String getSubscribeButtonTextValue() {
    Utils.scrollToElement(getDriver(), subscribeButtonTextRegister);
    return getDriver().findElement(subscribeButtonTextRegister).getCssValue("text-transform");
  }
}