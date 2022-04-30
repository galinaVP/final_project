package prestashopTests;

import blocks.SubscribeBlock;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.MainPage;

public class SubscribeToTheNewsletterTest extends BaseTest {

  String expectedTextNearTheEmailField = "Get our latest news and special sales";
  String expectedTextUnderEmailField = "You may unsubscribe at any moment. For that purpose, please find my contact info in the legal notice.";
  String expectedValueOfTextSubscribeButton = "uppercase";

  @Test(description = "Check that text from subscribe block test")
  public void checkThatTextFromSubscribeBlockTest() {
    MainPage mainPage = new MainPage();
    SoftAssertions softAssertions = new SoftAssertions();

    SubscribeBlock textsFieldsForSubscribeBlock = mainPage.openMainPage().getSubscribeBlock()
        .getTextsFieldsForSubscribeBlock();

    //  check that text near the email field
    String actualTextNearTheEmailField = textsFieldsForSubscribeBlock.getTextNearTheEmailField();

    softAssertions.assertThat(actualTextNearTheEmailField)
        .as("We are waiting for the text near the email field: [" + expectedTextNearTheEmailField
            + "], and received: ["
            + actualTextNearTheEmailField + "]")
        .isEqualTo(expectedTextNearTheEmailField);

    //  check that text under email field
    String actualTextUnderEmailField = textsFieldsForSubscribeBlock.getTextUnderEmailField();

    softAssertions.assertThat(actualTextUnderEmailField)
        .as("We are waiting for the text under the email field: [" + expectedTextUnderEmailField
            + "], and received: ["
            + actualTextUnderEmailField + "]")
        .isEqualTo(expectedTextUnderEmailField);

    // Check that all characters on 'SUBSCRIBE' button in upper case
    String actualValueOfTextSubscribeButton = textsFieldsForSubscribeBlock.getSubscribeButtonTextValue();

    softAssertions.assertThat(actualValueOfTextSubscribeButton)
        .as("We are waiting value of attribute text on Subscribe button: ["
            + expectedValueOfTextSubscribeButton + "], and received: ["
            + actualValueOfTextSubscribeButton + "]");

    softAssertions.assertAll();
  }
}