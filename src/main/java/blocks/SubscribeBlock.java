package blocks;

import io.qameta.allure.Step;
import java.util.HashMap;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
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

  // map collection with texts field for subscribe block
  private Map<String, String> textsFieldsForSubscribeBlock;

  public SubscribeBlock getTextsFieldsForSubscribeBlock() {
    textsFieldsForSubscribeBlock = new HashMap<>();
    Utils.scrollToElement(getDriver(), textNearTheEmailField);
    textsFieldsForSubscribeBlock.put("textNearTheEmailField",
        BasePage.wailVisibleLocated(textNearTheEmailField, 5).getText());
    textsFieldsForSubscribeBlock.put("textUnderEmailField",
        BasePage.wailVisibleLocated(textUnderEmailField, 5).getText());
    textsFieldsForSubscribeBlock.put("subscribeButtonTextRegister",
        BasePage.wailVisibleLocated(subscribeButtonTextRegister, 5).getCssValue("text-transform"));
    return this;
  }

  @Step("Get [Text Near the email] field")
  public String getTextNearTheEmailField() {
    return textsFieldsForSubscribeBlock.get("textNearTheEmailField");
  }

  @Step("Get [Text Under the email] field")
  public String getTextUnderEmailField() {
    return textsFieldsForSubscribeBlock.get("textUnderEmailField");
  }

  @Step("Get text value from [Subscribe] button")
  public String getSubscribeButtonTextValue() {
    return textsFieldsForSubscribeBlock.get("subscribeButtonTextRegister");
  }
}