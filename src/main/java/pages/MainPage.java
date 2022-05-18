package pages;

import blocks.ProductBlock;
import io.qameta.allure.Step;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Utils;

public class MainPage extends BasePage {

  private final By pageLoadingProcess = By.id("loadingMessage");
  private final By productContainer = By.xpath("//div[contains(@class,'thumbnail-container')]");
  private final By allProductsButton = By.xpath("//a[contains(@class, 'all-product-link')]");

  // open MAIN page
  @Step("Open [Main] page")
  public MainPage openMainPage() {
    getDriver().get("https://demo.prestashop.com/");
    wailInvisibleLocated(pageLoadingProcess, 20);
    getDriver().switchTo().frame("framelive");
    return this;
  }

  // get all products from popular products section on Main page
  @Step("get all products from [Popular products] section on Main page")
  public List<ProductBlock> getProductsFromPopularProductsSection() {
    List<ProductBlock> products = new ArrayList<>();
    List<WebElement> containers = getDriver().findElements(productContainer);
    for (WebElement container : containers) {
      ProductBlock productBlock = new ProductBlock(container);
      products.add(productBlock);
    }
    return products;
  }

  // click on All Products button from Popular Products section
  @Step("Click on [All Products] button from Popular Products section")
  public AllProductsPage clickOnAllProductsButton() {
    Utils.scrollToElement(getDriver(), allProductsButton);
    find(allProductsButton).click();
    return new AllProductsPage();
  }
}