package prestashopTests;

import blocks.MainMenuBlock;
import blocks.MainMenuBlock.Categories;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.MainPage;

public class SubMenuExistsInMainMenuTest extends BaseTest {

  @Test(description = "Check that sub menu appears in main menu test")
  public void checkThatSubMenuAppearsInMainMenuTest() {
    MainPage mainPage = new MainPage();
    MainMenuBlock mainMenuBlock = mainPage.openMainPage().getMainMenuBlock();

    // Check that 'MEN' and 'WOMEN' sub menu items appears
    List<String> actualAllSubMenuFromClothesMainMenuCategory = mainMenuBlock.hoverMouseOverOn(
            Categories.CLOTHES)
        .getAllSubMenuFromMainMenuCategory(Categories.CLOTHES);

    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(actualAllSubMenuFromClothesMainMenuCategory)
        .as("We are waiting sub menu items appears from main category CLOTHES: [MEN, WOMEN]"
            + " , and received: ["
            + actualAllSubMenuFromClothesMainMenuCategory + "]")
        .containsExactlyInAnyOrder("MEN", "WOMEN");

    // Check that 'STATIONERY' and 'HOME ACCESSORIES' sub menu items appears
    List<String> actualAllSubMenuFromAccessoriesMainMenuCategory = mainMenuBlock.hoverMouseOverOn(
            Categories.ACCESSORIES)
        .getAllSubMenuFromMainMenuCategory(Categories.ACCESSORIES);
    softAssertions.assertThat(actualAllSubMenuFromAccessoriesMainMenuCategory)
        .as("We are waiting sub menu items appears from main category CLOTHES: [STATIONERY, HOME ACCESSORIES]"
            + " , and received: ["
            + actualAllSubMenuFromAccessoriesMainMenuCategory + "]")
        .containsExactlyInAnyOrder("STATIONERY", "HOME ACCESSORIES");

    // Check that not any sub category appears from ART
    List<String> actualAllSubMenuFromAArtMainMenuCategory = mainMenuBlock.hoverMouseOverOn(
            Categories.ART)
        .getAllSubMenuFromMainMenuCategory(Categories.ART);
    softAssertions.assertThat(actualAllSubMenuFromAArtMainMenuCategory)
        .as("We expect that there are no submenus, but there are such submenus: "
            + actualAllSubMenuFromAArtMainMenuCategory)
        .isNull();

    softAssertions.assertAll();
  }
}