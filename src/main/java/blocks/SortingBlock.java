package blocks;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SortingBlock {

  @Getter
  private static WebDriver driver;
  private final By sortByButton = By.xpath("//button[@aria-label='Sort by selection']");
  private final By sortByNameFromAToZ = By.xpath(
      "//div[contains(@class,'products-sort-order')]//a[contains(text(),'Name, A to Z')]");
  private final By sortByNameFromZToA = By.xpath(
      "//div[contains(@class,'products-sort-order')]//a[contains(text(),'Name, Z to A')]");
  private final By sortByPriceFromLowToHigh = By.xpath(
      "//div[contains(@class,'products-sort-order')]//a[contains(text(),'Price, low to high')]");
  private final By sortByPriceFromHighToLow = By.xpath(
      "//div[contains(@class,'products-sort-order')]//a[contains(text(),'Price, high to low')]");

  public SortingBlock(WebDriver webDriver) {
    driver = webDriver;
  }

  // Sorting: Name, A to Z
  @Step("Sorting products: Name, A to Z")
  public void sortByNameFromAToZ() {
    getDriver().findElement(sortByButton).click();
    getDriver().findElement(sortByNameFromAToZ).click();
  }

  // Sorting: Name, Z to A
  @Step("Sorting products: Name, Z to A")
  public void sortByNameFromZToA() {
    getDriver().findElement(sortByButton).click();
    getDriver().findElement(sortByNameFromZToA).click();
  }

  // Sorting: Price, Low to High
  @Step("Sorting products: Price, Low to High")
  public void sortByPriceFromLowToHigh() {
    getDriver().findElement(sortByButton).click();
    getDriver().findElement(sortByPriceFromLowToHigh).click();
  }

  // Sorting: Price, High to Low
  @Step("Sorting products: Price, High to Low")
  public void sortByPriceFromHighToLow() {
    getDriver().findElement(sortByButton).click();
    getDriver().findElement(sortByPriceFromHighToLow).click();
  }
}