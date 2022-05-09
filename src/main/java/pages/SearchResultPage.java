package pages;

import blocks.ProductBlock;
import io.qameta.allure.Step;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchResultPage extends BasePage {

  private final By productContainer = By.xpath("//div[contains(@class,'thumbnail-container')]");

  // get all products from Search Result page
  @Step("Get all products from Search Result page")
  public List<ProductBlock> getProductsFromSearchResultPage() {
    List<ProductBlock> products = new ArrayList<>();
    List<WebElement> containers = getDriver().findElements(productContainer);
    for (WebElement container : containers) {
      ProductBlock productBlock = new ProductBlock(container);
      products.add(productBlock);
    }
    return products;
  }

  @Step("Click on product: [{productName}]")
  public ProductInfoPage clickOnProduct(String productName) {
    List<ProductBlock> products = getProductsFromSearchResultPage();
    for (ProductBlock product : products) {
      if (product.getNameAsStringFromProductsPages().equals(productName)) {
        product.getNameAsWebElementFromProductsPages().click();
      }
    }
    return new ProductInfoPage();
  }
}