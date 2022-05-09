package blocks;

import io.qameta.allure.Step;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

@EqualsAndHashCode
public class LanguagesBlock {

  @Getter
  private static WebDriver driver;
  private final By languagesList = By.xpath("//ul[@aria-labelledby='language-selector-label']/li");

  public LanguagesBlock(WebDriver webDriver) {
    driver = webDriver;
  }

  // get all languages from dropdown in the top menu "Languages"
  @Step("Get all languages from dropdown in the top menu [Languages]")
  public List<String> getAllLanguagesValues() {
    List<String> allLanguagesValues = new ArrayList<>();
    List<WebElement> allLanguagesElements = BasePage.presenceOfAllElementsLocatedBy(languagesList,
        10);
    for (WebElement languageElement : allLanguagesElements) {
      allLanguagesValues.add(languageElement.getText());
    }
    return allLanguagesValues;
  }
}