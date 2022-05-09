package pages;

import io.qameta.allure.Step;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ProductInfoPage extends BasePage {

  private final By paperTypeSelect = By.id("group_4");
  private final By productQuantityInput = By.id("quantity_wanted");
  private final By addToCartButton = By.xpath("//button[contains(@class,'add-to-cart')]");
  private final By popupTitle = By.xpath("//h4[@id='myModalLabel']");
  private final By paperTypeValueText = By.xpath("//span[@class='paper type']/strong");
  private final By quantityProductValue = By.xpath("//span[@class='product-quantity']/strong");
  private final By productPriceFromPopup = By.xpath("//p[@class='product-price']");
  private final By totalPriceProducts = By.xpath(
      "//p[@class='product-total']//span[@class='value']");
  private final By productCustomizationInput = By.id("field-textField1");
  private final By saveCustomizationButton = By.xpath("//button[@name='submitCustomizedData']");
  private final By continueShoppingButton = By.xpath("//button[text()='Continue shopping']");
  private final By colorProductValue = By.xpath("//input[@class='input-color']");
  private final By proceedToCheckoutButton = By.xpath("//a[text()='Proceed to checkout']");

  // Change 'Paper type'
  @Step("Select paper type product: [{type}]")
  public ProductInfoPage selectPaperTypeProduct(String type) {
    Select select = new Select(find(paperTypeSelect));
    select.selectByVisibleText(type);
    return this;
  }

  // Change 'Quantity'
  @Step("Set quantity of product: [{quantity}]")
  public ProductInfoPage setQuantityOfProduct(int quantity) {
    String quantityString = Integer.toString(quantity);
    if (!(find(productQuantityInput).getAttribute("value").equals(quantityString))) {
      actions = new Actions(getDriver());
      actions.moveToElement(find(productQuantityInput)).click().sendKeys(Keys.BACK_SPACE)
          .sendKeys(quantityString).build().perform();
    }
    return this;
  }

  // Click 'ADD TO CART' button
  @Step("Click [Add to Cart] button")
  public ProductInfoPage clickAddToCartButton() {
    waitRefreshedClickable(addToCartButton, 5).click();
    return this;
  }

  // get Title for popup after successfully added product to cart
  @Step("Get Title for popup after successfully added product to cart")
  public String getPopupTitle() {
    return wailVisibleLocated(popupTitle, 5).getText().substring(1);
  }

  // get Paper Type value for popup after successfully added product to cart
  @Step("Get Paper Type value for popup after successfully added product to cart")
  public String getPaperTypeValueText() {
    return find(paperTypeValueText).getText();
  }

  // get quantity for popup after successfully added product to cart
  @Step("Get quantity for popup after successfully added product to cart")
  public int getQuantityProductsValue() {
    return Integer.parseInt(find(quantityProductValue).getText());
  }

  // Check that 'Total' calculated correct
  @Step("Check that [Total] calculated correct")
  public boolean isTotalPriceProductsCorrect() {
    double priceProduct = Double.parseDouble(find(productPriceFromPopup).getText().substring(1));
    int quantity = getQuantityProductsValue();
    double actualTotalPrice = priceProduct * quantity;
    double expectedTotalPrice = Double.parseDouble(find(totalPriceProducts).getText().substring(1));
    return actualTotalPrice == expectedTotalPrice;
  }

  // Enter product customization
  @Step("Enter product customization value as: [{customization}]")
  public ProductInfoPage EnterProductCustomizationValueAs(String customization) {
    find(productCustomizationInput).sendKeys(customization);
    return this;
  }

  // click On Save Customization Button
  @Step("Click on [Save Customization] Button")
  public ProductInfoPage clickOnSaveCustomizationButton() {
    find(saveCustomizationButton).click();
    return new ProductInfoPage();
  }

  // click On Continue Shopping Button
  @Step("Click on [Continue Shopping] Button")
  public ProductInfoPage clickOnContinueShoppingButton() {
    wailElementToBeClickable(continueShoppingButton, 10).click();
    return this;
  }

  // select Color Product Value
  @Step("Select color product value: [{color}]")
  public ProductInfoPage selectColorProductValue(String color) {
    List<WebElement> colorsValue = getDriver().findElements(colorProductValue);
    for (WebElement element : colorsValue) {
      if (element.getAttribute("title").equalsIgnoreCase(color)) {
        element.click();
      }
    }
    return this;
  }

  // click On Proceed To Checkout Button
  @Step("Click on [Proceed To Checkout] Button")
  public ShoppingCartPage clickOnProceedToCheckoutButton() {
    wailElementToBeClickable(proceedToCheckoutButton, 10).click();
    return new ShoppingCartPage();
  }
}