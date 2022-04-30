package prestashopTests;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.RegisterAccountPage;

public class RegistrationWithInvalidDataTest extends BaseTest {

  String firstname = "James8";
  String expectedValueBorderColor = "rgba(255, 255, 255, 1)";
  String expectedAlertDangerMessageFromInvalidDataField = "Invalid name";

  @Test(description = "Check that error massage appears when user enter invalid firstname test")
  public void checkThatErrorMassageAppearsWhenUserEnterInvalidFirstnameTest() {
    MainPage mainPage = new MainPage();
    SoftAssertions softAssertions = new SoftAssertions();

    RegisterAccountPage registerAccountPage = mainPage.openMainPage()
        .getTopMenuBlock().clickOnSignIn()
        .clickOnCreationAccountButton()
        .enteredFirstNameAs(firstname)
        .enteredLastNameAs(faker.name().lastName())
        .enteredEmailAs(faker.internet().emailAddress())
        .enteredPasswordAs(faker.internet().password())
        .enteredBirthdateInputAs(faker.date().birthday())
        .clickCustomerDataPrivacyCheckbox()
        .clickSaveButtonWithInvalidData();

    // Check that 'First name' highlighted in red
    String actualValueBorderColor = registerAccountPage.getValueBorderOfTheSelectedColor();
    softAssertions.assertThat(actualValueBorderColor)
        .as("We are waiting border color from firstname field: [" + expectedValueBorderColor
            + "], and received: [" + actualValueBorderColor + "]")
        .isEqualTo(expectedValueBorderColor);

    // Check that pop-up with text 'Invalid name' appear under field
    String actualAlertDangerMessageFromInvalidDataField = registerAccountPage.getAlertDangerMessageFromInvalidDataField();
    softAssertions.assertThat(actualAlertDangerMessageFromInvalidDataField)
        .as("We are waiting alert danger message: ["
            + expectedAlertDangerMessageFromInvalidDataField
            + "], and received: [" + actualAlertDangerMessageFromInvalidDataField + "]")
        .isEqualTo(expectedAlertDangerMessageFromInvalidDataField);

    softAssertions.assertAll();
  }
}