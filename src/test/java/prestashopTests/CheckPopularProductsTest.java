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
    softAssertions.assertThat(productsFromPopularProductsSection)
        .as("We are waiting quantity products from Popular Products Section: ["
            + quantityProductsFromPopularProductsSection + "], and received: ["
            + productsFromPopularProductsSection.size() + "]")
        .hasSize(quantityProductsFromPopularProductsSection);

    // Check that every product has name field
    softAssertions.assertThat(productsFromPopularProductsSection)
        .as("Every products has not name")
        .allMatch(n -> n.getNameAsString() != null);

    // Check that every product has price
    softAssertions.assertThat(productsFromPopularProductsSection)
        .as("Every products has not price")
        .allMatch(p -> p.getPriceDouble() != null);

    // Check that all prices bigger than 0.00
    softAssertions.assertThat(productsFromPopularProductsSection)
        .as("Every products has not prices bigger than 0.00")
        .allMatch(p -> p.getPriceDouble() > 0.0);
    softAssertions.assertAll();
  }
}