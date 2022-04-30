package pages;

import blocks.ProductBlock;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PricesDropPage extends BasePage {

  private final By productContainer = By.xpath("//div[contains(@class,'thumbnail-container')]");

  // get all products from Prices Drop page
  public List<ProductBlock> getProductsFromPricesDropPage() {
    List<ProductBlock> products = new ArrayList<>();
    List<WebElement> containers = getDriver().findElements(productContainer);
    for (WebElement container : containers) {
      ProductBlock productBlock = new ProductBlock(container);
      products.add(productBlock);
    }
    return products;
  }

  // get Names all products from popular products section on Main page
  public List<String> getNamesProducts(List<ProductBlock> products) {
    List<String> namesProductsPopularProductsSection = new ArrayList<>();
    for (ProductBlock product : products) {
      if (product.getNameAsStringFromProductsPages() != null) {
        namesProductsPopularProductsSection.add(product.getNameAsStringFromProductsPages());
      }
    }
    return namesProductsPopularProductsSection;
  }

  // get Names all products has old and new price from Prices Drop page
  public List<String> getAllProductsWithOldAndNewPrice(List<ProductBlock> products) {
    List<String> productsWithOldAndNewPrice = new ArrayList<>();
    for (ProductBlock product : products) {
      if (product.getOldPriceDouble() != null && product.getPriceDouble() != null) {
        productsWithOldAndNewPrice.add(product.getNameAsStringFromProductsPages());
      }
    }
    return productsWithOldAndNewPrice;
  }

  // get Names all products that promo price calculates correct
  public List<String> getAllProductsWithCorrectCalculatesPromoPrice(List<ProductBlock> products) {
    List<String> productsWithCorrectCalculatesPromoPrice = new ArrayList<>();
    for (ProductBlock product : products) {
      if (product.getOldPriceDouble() != null && product.getPriceDouble() != null) {
        // rounding to two characters after the floating point
        double actualPrice = new BigDecimal(
            product.getOldPriceDouble() - (product.getOldPriceDouble() * (
                product.getDiscountValueDouble() * 0.01))).setScale(2, RoundingMode.HALF_EVEN)
            .doubleValue();
        if (actualPrice == product.getPriceDouble()) {
          productsWithCorrectCalculatesPromoPrice.add(product.getNameAsStringFromProductsPages());
        }
      }
    }
    return productsWithCorrectCalculatesPromoPrice;
  }
}