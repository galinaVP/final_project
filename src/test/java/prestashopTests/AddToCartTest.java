package prestashopTests;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.ProductInfoPage;

public class AddToCartTest extends BaseTest {

  String textToSearch = "Bear";
  String product = "Brown Bear Notebook";
  int quantityOfProduct = 5;
  String paperTypeValue = "Doted";
  String expectedSuccessfullyAddedProductToCartTitle = "Product successfully added to your shopping cart";

  @Test(description = "Check that added product to the cart is correctly test")
  public void checkThatAddedProductToTheCartIsCorrectlyTest() {
    MainPage mainPage = new MainPage();
    String actualSuccessfullyAddedProductToCartTitle = mainPage.openMainPage()
        .getMainMenuBlock().enterSearchAs(textToSearch)
        .clickOnProduct(product)
        .selectPaperTypeProduct(paperTypeValue)
        .setQuantityOfProduct(quantityOfProduct)
        .clickAddToCartButton()
        .getPopupTitle();

    ProductInfoPage productInfoPage = new ProductInfoPage();
    String actualPaperTypeValueText = productInfoPage.getPaperTypeValueText();
    int actualQuantityProductValue = productInfoPage.getQuantityProductsValue();

    SoftAssertions softAssertions = new SoftAssertions();

    // Check that new window with title 'Product successfully added to your shopping cart' appears
    softAssertions.assertThat(actualSuccessfullyAddedProductToCartTitle)
        .as("We are waiting title: [" + expectedSuccessfullyAddedProductToCartTitle
            + "], and received: [" + actualSuccessfullyAddedProductToCartTitle + "]")
        .isEqualTo(expectedSuccessfullyAddedProductToCartTitle);

    // Check that correct Paper Type is shown on the left side of the window
    softAssertions.assertThat(actualPaperTypeValueText)
        .as("We are waiting Paper Type: [" + paperTypeValue
            + "], and received: [" + actualPaperTypeValueText + "]")
        .isEqualTo(paperTypeValue);

    // Check that correct 'Quantity' is shown on the left side of the window
    softAssertions.assertThat(actualQuantityProductValue)
        .as("We are waiting quantity: [" + quantityOfProduct
            + "], and received: [" + actualQuantityProductValue + "]")
        .isEqualTo(quantityOfProduct);

    // Check that 'Total' calculated correct
    softAssertions.assertThat(productInfoPage.isTotalPriceProductsCorrect())
        .as("Total calculated is not correct")
        .isTrue();

    softAssertions.assertAll();
  }
}