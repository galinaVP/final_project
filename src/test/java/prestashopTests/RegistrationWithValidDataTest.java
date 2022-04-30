package prestashopTests;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pages.MainPage;

public class RegistrationWithValidDataTest extends BaseTest {

  String fistName = faker.name().firstName();
  String lastName = faker.name().lastName();
  String expectedUserName = fistName + " " + lastName;

  @Test(description = "Check that username exist after registration with valid credentials test")
  public void checkThatUsernameExistAfterRegistrationWithValidCredentialsTest() {
    MainPage mainPage = new MainPage();
    String actualUserName = mainPage.openMainPage()
        .getTopMenuBlock().clickOnSignIn()
        .clickOnCreationAccountButton()
        .enteredFirstNameAs(fistName)
        .enteredLastNameAs(lastName)
        .enteredEmailAs(faker.internet().emailAddress())
        .enteredPasswordAs(faker.internet().password())
        .enteredBirthdateInputAs(faker.date().birthday())
        .clickCustomerDataPrivacyCheckbox()
        .clickSaveButton()
        .getTopMenuBlock().getUserNameAfterLoginOnSite();

    Assertions.assertThat(actualUserName)
        .as("We are waiting a username: [" + expectedUserName + "], and received: ["
            + actualUserName + "]")
        .isEqualTo(expectedUserName);
  }
}