package blocks;

import io.qameta.allure.Step;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.SearchResultPage;

@Getter
public class MainMenuBlock {

  @Getter
  private static WebDriver driver;
  private final By clothesMainMenuButton = By.xpath("//li[@id='category-3']");
  private final By accessoriesMainMenuButton = By.xpath("//li[@id='category-6']");
  private final By artMainMenuButton = By.xpath("//li[@id='category-9']");
  private final By subMenuList = By.xpath(".//div[contains(@id,'top_sub_menu')]//a");
  private final By searchInput = By.xpath("//input[@aria-label='Search']");

  public MainMenuBlock(WebDriver webDriver) {
    driver = webDriver;
  }

  // Main menu - CLOTHES button (hover mouse over)
  @Step("Hover mouse over [CLOTHES] main menu button")
  public MainMenuBlock hoverMouseOverClothesMainMenuButton() {
    BasePage.moveToWebElement(BasePage.wailVisibleLocated(clothesMainMenuButton, 10));
    return this;
  }

  // Main menu - ACCESSORIES button (hover mouse over)
  @Step("Hover mouse over [ACCESSORIES] main menu button")
  public MainMenuBlock hoverMouseOverAccessoriesMainMenuButton() {
    BasePage.moveToWebElement(BasePage.wailVisibleLocated(accessoriesMainMenuButton, 10));
    return this;
  }

  // Main menu - ART button (hover mouse over)
  @Step("Hover mouse over [ART] main menu button")
  public MainMenuBlock hoverMouseOverArtMainMenuButton() {
    BasePage.moveToWebElement(BasePage.wailVisibleLocated(artMainMenuButton, 10));
    return this;
  }

  // get all [Sub menu] categories from Clothes (main menu)
  @Step("Get all field [Sub menu] from [Clothes] main menu category")
  public List<String> getAllSubMenuFromClothesMainMenuCategory() {
    List<String> allSubMenuItems = new ArrayList<>();
    if (BasePage.wailVisibleLocated(clothesMainMenuButton, 10).getAttribute("innerHTML")
        .contains("top_sub_menu")) {
      List<WebElement> allSubMenuElements = BasePage.visibilityOfNestedElementsLocatedBy(
          clothesMainMenuButton,
          subMenuList, 10);
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
    if (BasePage.wailVisibleLocated(accessoriesMainMenuButton, 10).getAttribute("innerHTML")
        .contains("top_sub_menu")) {
      List<WebElement> allSubMenuElements = BasePage.visibilityOfNestedElementsLocatedBy(
          accessoriesMainMenuButton,
          subMenuList, 10);
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
      List<WebElement> allSubMenuElements = BasePage.visibilityOfNestedElementsLocatedBy(
          artMainMenuButton,
          subMenuList, 10);
      for (WebElement subMenuElement : allSubMenuElements) {
        allSubMenuItems.add(subMenuElement.getText());
      }
    } else {
      allSubMenuItems = null;
    }
    return allSubMenuItems;
  }

  // entered data from search form
  @Step("Enter search as [{searchText}]")
  public SearchResultPage enterSearchAs(String searchText) {
    getDriver().findElement(searchInput).sendKeys(searchText, Keys.ENTER);
    return new SearchResultPage();
  }
}