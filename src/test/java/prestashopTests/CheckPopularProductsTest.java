package prestashopTests;

import blocks.ProductBlock;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.MainPage;

public class CheckPopularProductsTest extends BaseTest {

  int quantityProductsFromPopularProductsSection = 8;

  @Test(description = "Check that every product has values")
  public void checkThatEveryProductHasValuesTest() {
    MainPage mainPage = new MainPage();
    SoftAssertions softAssertions = new SoftAssertions();

    // get all products from popular products section on Main page
    List<ProductBlock> productsFromPopularProductsSection = mainPage.openMainPage()
        .getProductsFromPopularProductsSection();

    // Check that 8 products exist in 'POPULAR PRODUCTS' section
    softAssertions.assertThat(productsFromPopularProductsSection.size())
        .as("We are waiting quantity products from Popular Products Section: ["
            + quantityProductsFromPopularProductsSection + "], and received: ["
            + productsFromPopularProductsSection.size() + "]")
        .isEqualTo(quantityProductsFromPopularProductsSection);

    // Check that every product has name field
    List<String> productsWithNameValue = mainPage.getNamesProducts(
        productsFromPopularProductsSection);
    softAssertions.assertThat(productsWithNameValue.size())
        .as("We are waiting quantity products with names from Popular Products Section: ["
            + productsFromPopularProductsSection.size() + "], and received: ["
            + productsWithNameValue.size() + "] with such names: " + productsWithNameValue)
        .isEqualTo(productsFromPopularProductsSection.size());

    // Check that every product has price
    List<String> productsWithPriceValue = mainPage.getPriceProductsFromPopularProductsSection(
        productsFromPopularProductsSection);
    softAssertions.assertThat(productsWithPriceValue)
        .as("We are waiting quantity products with price from Popular Products Section: ["
            + quantityProductsFromPopularProductsSection + "], and received: ["
            + productsWithPriceValue.size() + "] with such prices: " + productsWithPriceValue)
        .hasSameElementsAs(mainPage.getNamesProducts(
            productsFromPopularProductsSection));

    // Check that all prices bigger than 0.00
    List<String> productsWhenPricesBiggerZero = mainPage.getProductsFromPopularProductsSectionWhenPricesBiggerZero(
        productsFromPopularProductsSection);
    softAssertions.assertThat(productsWhenPricesBiggerZero)
        .as("We are waiting quantity products with prices bigger than 0.00 from Popular Products Section: ["
            + quantityProductsFromPopularProductsSection + "], and received: ["
            + productsWhenPricesBiggerZero.size() + "] with such prices: " + productsWhenPricesBiggerZero)
        .hasSameElementsAs(mainPage.getNamesProducts(
            productsFromPopularProductsSection));

    softAssertions.assertAll();
  }
}
