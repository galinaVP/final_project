package prestashopTests;

import blocks.ProductBlock;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.PricesDropPage;

public class PriceDropProductTests extends BaseTest {

  @Test(description = "Check that promo price for product calculates correct test")
  public void checkThatPromoPriceForProductCalculatesCorrectTest() {
    MainPage mainPage = new MainPage();
    SoftAssertions softAssertions = new SoftAssertions();

    List<ProductBlock> productsFromPricesDropPage = mainPage.openMainPage()
        .getFooterMenuBlock()
        .clickOnPricesDropFooterMenuButton()
        .getProductsFromPricesDropPage();

    PricesDropPage pricesDropPage = new PricesDropPage();
    List<String> namesAllProductsFromPriceDropPage = pricesDropPage.getNamesProducts(
        productsFromPricesDropPage);

    // Check that every product has old and new price
    List<String> productsWithOldAndNewPrice = pricesDropPage.getAllProductsWithOldAndNewPrice(
        productsFromPricesDropPage);

    softAssertions.assertThat(productsWithOldAndNewPrice)
        .as("We are waiting quantity products with old and new price: ["
            + productsFromPricesDropPage.size() + "], and received: ["
            + productsWithOldAndNewPrice.size() + "]")
        .hasSameElementsAs(namesAllProductsFromPriceDropPage);

    // Check that promo price for every product calculates correct
    List<String> productsWithCorrectCalculatesPromoPrice = pricesDropPage.getAllProductsWithCorrectCalculatesPromoPrice(
        productsFromPricesDropPage);

    softAssertions.assertThat(productsWithCorrectCalculatesPromoPrice)
        .as("We are waiting quantity products with correct calculates promo price: ["
            + productsFromPricesDropPage.size() + "], and received: ["
            + productsWithCorrectCalculatesPromoPrice.size() + "]")
        .hasSameElementsAs(namesAllProductsFromPriceDropPage);

    softAssertions.assertAll();
  }
}