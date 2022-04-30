package blocks;

import io.qameta.allure.Step;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

@Getter
public class MainMenuBlock {

  @Getter
  private static WebDriver driver;

  private final By containsMainMenuButton = By.id("top-menu");
  private final By clothesMainMenuButton = By.xpath("//li[@id='category-3']/a");
  private final By accessoriesMainMenuButton = By.xpath("//li[@id='category-6']/a");
  private final By artMainMenuButton = By.xpath("//li[@id='category-9']/a");
  private final By subMenuListFromClothesMainMenuCategory = By.xpath(
      "//li[@id='category-3']//div[contains(@id,'top_sub_menu')]//a");
  private final By subMenuListFromAccessoriesMainMenuCategory = By.xpath(
      "//li[@id='category-6']//div[contains(@id,'top_sub_menu')]//a");
  private final By subMenuListFromArtMainMenuCategory = By.xpath(
      "//li[@id='category-9']//div[contains(@id,'top_sub_menu')]//a");

  public MainMenuBlock(WebDriver webDriver) {
    driver = webDriver;
  }

  // Main menu - CLOTHES button (hover mouse over)
  @Step("Hover mouse over [CLOTHES] main menu button")
  public MainMenuBlock hoverMouseOverClothesMainMenuButton() {
    BasePage.wailVisibleLocated(containsMainMenuButton, 20);
    BasePage.moveToWebElement(BasePage.wailVisibleLocated(clothesMainMenuButton, 10));
    return this;
  }

  // Main menu - ACCESSORIES button (hover mouse over)
  @Step("Hover mouse over [ACCESSORIES] main menu button")
  public MainMenuBlock hoverMouseOverAccessoriesMainMenuButton() {
    //    BasePage.moveToElement(getDriver(), accessoriesMainMenuButton);
    BasePage.moveToWebElement(BasePage.wailVisibleLocated(accessoriesMainMenuButton, 10));
    return this;
  }

  // Main menu - ART button (hover mouse over)
  @Step("Hover mouse over [ART] main menu button")
  public MainMenuBlock hoverMouseOverArtMainMenuButton() {
    //    BasePage.moveToElement(getDriver(), artMainMenuButton);
    BasePage.moveToWebElement(BasePage.wailVisibleLocated(artMainMenuButton, 10));
    return this;
  }

  // get all [Sub menu] categories from Clothes (main menu)
  @Step("Get all field [Sub menu] from [Clothes] main menu category")
  public List<String> getAllSubMenuFromClothesMainMenuCategory() {
    List<String> allSubMenuItems = new ArrayList<>();
//    if (getDriver().findElement(clothesMainMenuButton).getAttribute("innerHTML")
    if (BasePage.wailVisibleLocated(clothesMainMenuButton, 10).getAttribute("innerHTML")
        .contains("top_sub_menu")) {
      List<WebElement> allSubMenuElements = BasePage.presenceOfAllElementsLocatedBy(
          subMenuListFromClothesMainMenuCategory, 15);
//      List<WebElement> allSubMenuElements = getDriver().findElements(subMenuListFromClothesMainMenuCategory);
      for (WebElement subMenuElement : allSubMenuElements) {
        allSubMenuItems.add(subMenuElement.getText());
      }
    } else {
      allSubMenuItems = null;
    }
    return allSubMenuItems;
  }

  // get all [Sub menu] categories from Accessories (main menu)
  @Step("Get all field [Sub menu] from [Accessories] main menu category")
  public List<String> getAllSubMenuFromAccessoriesMainMenuCategory() {
    List<String> allSubMenuItems = new ArrayList<>();
    if (BasePage.wailVisibleLocated(clothesMainMenuButton, 10).getAttribute("innerHTML")
        .contains("top_sub_menu")) {
      List<WebElement> allSubMenuElements = BasePage.presenceOfAllElementsLocatedBy(
          subMenuListFromAccessoriesMainMenuCategory, 10);
//      List<WebElement> allSubMenuElements = getDriver().findElements(subMenuListFromAccessoriesMainMenuCategory);
      for (WebElement subMenuElement : allSubMenuElements) {
        allSubMenuItems.add(subMenuElement.getText());
      }
    } else {
      allSubMenuItems = null;
    }
    return allSubMenuItems;
  }

  // get all [Sub menu] categories from Art (main menu)
  @Step("Get all field [Sub menu] from [Art] main menu category")
  public List<String> getAllSubMenuFromAArtMainMenuCategory() {
    List<String> allSubMenuItems = new ArrayList<>();
    if (BasePage.wailVisibleLocated(artMainMenuButton, 10).getAttribute("innerHTML")
        .contains("top_sub_menu")) {
      List<WebElement> allSubMenuElements = BasePage.visibilityOfAllElementsLocated(
          subMenuListFromArtMainMenuCategory, 10);
      for (WebElement subMenuElement : allSubMenuElements) {
        allSubMenuItems.add(subMenuElement.getText());
      }
    } else {
      allSubMenuItems = null;
    }
    return allSubMenuItems;
  }
}