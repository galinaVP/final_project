package blocks;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

@Getter
@Setter
@EqualsAndHashCode
public class ProductBlock {

  private WebElement img;
  private String discountValueString;
  private Double discountValueDouble;
  private WebElement addToWishListButton;
  private WebElement nameAsWebElement;
  private String nameAsString;
  private WebElement nameAsWebElementFromProductsPages;
  private String nameAsStringFromProductsPages;
  private String oldPriceSting;
  private Double oldPriceDouble;
  private String priceString;
  private Double priceDouble;

  public ProductBlock(WebElement container) {
    try {
      this.img = container.findElement(By.xpath(".//a[contains(@class,'product-thumbnail')]"));
    } catch (NoSuchElementException e) {
      this.img = null;
    }

    try {
      this.discountValueString = container.findElement(
              By.xpath(".//li[contains(@class,'discount')]"))
          .getText();
      this.discountValueDouble = Double.parseDouble(
          discountValueString.substring(1, discountValueString.length() - 1));
    } catch (NoSuchElementException e) {
      this.discountValueString = null;
      this.discountValueDouble = null;
    }

    try {
      this.addToWishListButton = container.findElement(
          By.xpath(".//button[@class='wishlist-button-add']"));
    } catch (NoSuchElementException e) {
      this.addToWishListButton = null;
    }

    try {
      this.nameAsWebElement = container.findElement(
          By.xpath(".//h3[contains(@class,'product-title')]/a"));
      this.nameAsString = nameAsWebElement.getText();
    } catch (NoSuchElementException e) {
      this.nameAsWebElement = null;
      this.nameAsString = null;
    }

    try {
      this.nameAsWebElementFromProductsPages = container.findElement(
          By.xpath(".//h2[contains(@class,'product-title')]/a"));
      this.nameAsStringFromProductsPages = nameAsWebElementFromProductsPages.getText();
    } catch (NoSuchElementException e) {
      this.nameAsWebElementFromProductsPages = null;
      this.nameAsStringFromProductsPages = null;
    }

    try {
      this.oldPriceSting = container.findElement(By.xpath(".//span[@class='regular-price']"))
          .getText();
      this.oldPriceDouble = Double.parseDouble(oldPriceSting.substring(1));
    } catch (NoSuchElementException e) {
      this.oldPriceSting = null;
      this.oldPriceDouble = null;
    }

    try {
      this.priceString = container.findElement(By.xpath(".//span[@class='price']")).getText();
      this.priceDouble = Double.parseDouble(priceString.substring(1));
    } catch (NoSuchElementException e) {
      this.priceString = null;
      this.priceDouble = null;
    }
  }
}