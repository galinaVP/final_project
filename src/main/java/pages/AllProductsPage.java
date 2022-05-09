package pages;

import blocks.ProductBlock;
import blocks.SortingBlock;
import io.qameta.allure.Step;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AllProductsPage extends BasePage {

  private final SortingBlock sortingBlock = new SortingBlock(getDriver());
  private final By productContainer = By.xpath(
      "//div[contains(@class,'thumbnail-container')]");

  // getter for SortingBlock
  public SortingBlock getSortingBlock() {
    return sortingBlock;
  }

  // get all products from [All Products] page
  @Step("get all products from [All Products] page")
  public List<ProductBlock> getProductsFromAllProductsPage() {
    List<ProductBlock> products = new ArrayList<>();
    if (waitRefreshed(productContainer, 5)) {
      List<WebElement> containers = getDriver().findElements(productContainer);
      for (WebElement container : containers) {
        ProductBlock productBlock = new ProductBlock(container);
        products.add(productBlock);
      }
    }
    return products;
  }

  // get names products from All Products page
  @Step("Get names products from All Products page")
  public List<String> getNamesProductsFromPage(List<ProductBlock> products) {
    List<String> namesProducts = new ArrayList<>();
    for (ProductBlock product : products) {
      namesProducts.add(product.getNameAsStringFromProductsPages());
    }
    return namesProducts;
  }

  // get price products from All Products page
  @Step("Get price products from All Products page")
  public List<Double> getPriceProductsFromPage(List<ProductBlock> products) {
    List<Double> priceProducts = new ArrayList<>();
    for (ProductBlock product : products) {
      priceProducts.add(product.getPriceDouble());
    }
    return priceProducts;
  }

  // check that sorting "Name A to Z" is correct
  @Step("Check that sorting [Name A to Z] is correct")
  public boolean isSortedNamesFromAToZ(List<ProductBlock> products) {
    List<String> namesProducts = getNamesProductsFromPage(products);
    List<String> copy = new ArrayList<>(namesProducts);
    Collections.sort(copy);
    return copy.equals(namesProducts);
  }

  // check that sorting "Name Z to A" is correct
  @Step("Check that sorting [Name Z to A] is correct")
  public boolean isSortedNameFromZToA(List<ProductBlock> products) {
    List<String> namesProducts = getNamesProductsFromPage(products);
    List<String> copy = new ArrayList<>(namesProducts);
    copy.sort(Collections.reverseOrder());
    return copy.equals(namesProducts);
  }

  // check that sorting "Price, low to high" is correct
  @Step("Check that sorting [Price, low to high] is correct")
  public boolean isSortedPriceFromLowToHigh(List<ProductBlock> products) {
    List<Double> priceProducts = getPriceProductsFromPage(products);
    List<Double> copy = new ArrayList<>(priceProducts);
    Collections.sort(copy);
    return copy.equals(priceProducts);
  }

  // check that sorting "Price, high to low" is correct
  @Step("Check that sorting [Price, high to low] is correct")
  public boolean isSortedPriceFromHighToLow(List<ProductBlock> products) {
    List<Double> priceProducts = getPriceProductsFromPage(products);
    List<Double> copy = new ArrayList<>(priceProducts);
    copy.sort(Collections.reverseOrder());
    return copy.equals(priceProducts);
  }
}