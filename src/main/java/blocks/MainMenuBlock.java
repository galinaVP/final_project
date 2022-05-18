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

  // Categories enum
  public enum Categories {
    CLOTHES,
    ACCESSORIES,
    ART;
  }

  // Hover mouse over Categories for Main menu
  @Step("Hover mouse over [{category}}] main menu button")
  public MainMenuBlock hoverMouseOverOn(Categories category) {
    switch (category) {
      case CLOTHES:
        BasePage.moveToWebElement(BasePage.wailVisibleLocated(clothesMainMenuButton, 10));
        break;
      case ACCESSORIES:
        BasePage.moveToWebElement(BasePage.wailVisibleLocated(accessoriesMainMenuButton, 10));
        break;
      case ART:
        BasePage.moveToWebElement(BasePage.wailVisibleLocated(artMainMenuButton, 10));
        break;
    }
    return this;
  }

  // get all [Sub menu] categories from Clothes (main menu)
  @Step("Get all field [Sub menu] from [{category}}] main menu category")
  public List<String> getAllSubMenuFromMainMenuCategory(Categories category) {
    List<String> allSubMenuItems = new ArrayList<>();
    switch (category) {
      case CLOTHES:
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
        break;
      case ACCESSORIES:
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
        break;
      case ART:
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
          break;
        }
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