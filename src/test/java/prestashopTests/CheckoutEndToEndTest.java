package prestashopTests;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.OrderConfirmedPage;
import pages.PersonalInformationOrderPage;
import pages.ShoppingCartPage;

public class CheckoutEndToEndTest extends BaseTest {

  String textToSearch1 = "Mug";
  String productCustomizableMug = "Customizable Mug";
  String productCustomizationText = "Best mug ever";
  int quantityProductCustomizableMug = 1;
  String textToSearch2 = "T-Shirt";
  String productPrintedTShirt = "Hummingbird Printed T-Shirt";
  String colorTShirt = "Black";
  String shippingMethod = "My carrier";
  String paymentMethod = "Pay by Check";
  String expectOrderConfirmedTitle = "YOUR ORDER IS CONFIRMED";

  @Test(description = "Check that checkout products is correct test")
  public void checkThatCheckoutProductsIsCorrectTest() {

    MainPage mainPage = new MainPage();
    boolean isTotalPriceOnShoppingCartCorrect = mainPage.openMainPage()
        .getMainMenuBlock().enterSearchAs(textToSearch1)
        .clickOnProduct(productCustomizableMug)
        .EnterProductCustomizationValueAs(productCustomizationText)
        .setQuantityOfProduct(quantityProductCustomizableMug)
        .clickOnSaveCustomizationButton()
        .clickAddToCartButton()
        .clickOnContinueShoppingButton()
        .getMainMenuBlock().enterSearchAs(textToSearch2)
        .clickOnProduct(productPrintedTShirt)
        .selectColorProductValue(colorTShirt)
        .clickAddToCartButton()
        .clickOnProceedToCheckoutButton()
        .isTotalPriceOnShoppingCartCorrect();

    SoftAssertions softAssertions = new SoftAssertions();

    // check that 'Total' calculated correct
    softAssertions.assertThat(isTotalPriceOnShoppingCartCorrect)
        .as("Total calculated not correct")
        .isTrue();

    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    boolean isAmountPriceCorrect = shoppingCartPage.clickOnProceedToCheckoutButton()
        .enteredFirstNameAs(faker.name().firstName())
        .enteredLastNameAs(faker.name().lastName())
        .enteredEmailAs(faker.internet().emailAddress())
        .enteredBirthdateInputAs(faker.date().birthday())
        .clickOnCustomerDataPrivacyCheckbox()
        .continueConfirmPersonButton()
        .enterAddressInputAs(faker.address().streetAddress())
        .enterPostcodeAs("00001")
        .enteredCityAs(faker.address().city())
        .clickOnContinueConfirmAddressesButton()
        .selectShippingMethodCheckbox(shippingMethod)
        .clickOnContinueConfirmDeliveryOptionButton()
        .selectPaymentMethodCheckbox(paymentMethod)
        .isAmountPriceCorrect();

    // Check that Amount equal Subtotal+Shipping
    softAssertions.assertThat(isAmountPriceCorrect)
        .as("Amount price not equal Subtotal+Shipping")
        .isTrue();

    PersonalInformationOrderPage personalInformationOrderPage = new PersonalInformationOrderPage();
    String actualOrderConfirmedTitle = personalInformationOrderPage.clickOnIAgreeCheckbox()
        .clickOnPlaceOrderButton()
        .getOrderConfirmedTitle();

    // Check that 'YOUR ORDER IS CONFIRMED' appeared on the next page
    softAssertions.assertThat(actualOrderConfirmedTitle)
        .as("We are waiting title: [" + expectOrderConfirmedTitle + "], and received: ["
            + actualOrderConfirmedTitle + "]")
        .isEqualTo(expectOrderConfirmedTitle);

    OrderConfirmedPage orderConfirmedPage = new OrderConfirmedPage();
    boolean isTotalPriceCalculatedCorrect = orderConfirmedPage.isTotalPriceCalculatedCorrect();

    // Check that 'TOTAL' calculated correct
    softAssertions.assertThat(isTotalPriceCalculatedCorrect)
        .as("Check that TOTAL not calculated correct")
        .isTrue();

    softAssertions.assertAll();
  }
}