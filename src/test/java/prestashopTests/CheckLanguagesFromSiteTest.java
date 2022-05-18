package prestashopTests;

import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.MainPage;

public class CheckLanguagesFromSiteTest extends BaseTest {

  int expectedQuantityLanguages = 44;
  String ukrainianLanguage = "Українська";

  @Test(description = "Check that languages exists in the menu test")
  public void checkThatLanguagesExistsInTheMenuTest() {

    MainPage mainPage = new MainPage();
    List<String> allLanguagesFromSite = mainPage.openMainPage()
        .getTopMenuBlock().clickOnLanguagesMenuButton()
        .getAllLanguagesValues();

    SoftAssertions softAssertions = new SoftAssertions();

    // Check that 44 languages exists in 'Language' dropdown in the top menu
    softAssertions.assertThat(allLanguagesFromSite.size())
        .as("We are waiting quantity Languages from site : [" + expectedQuantityLanguages
            + "], and received: ["
            + allLanguagesFromSite.size() + "]")
        .isEqualTo(expectedQuantityLanguages);

    // Check that 'Українська' language exist in dropdown
    softAssertions.assertThat(allLanguagesFromSite)
        .as("We are waiting for the language: [" + ukrainianLanguage
            + "] in the list, but received: [" + allLanguagesFromSite + "]")
        .contains(ukrainianLanguage);

    softAssertions.assertAll();
  }
}