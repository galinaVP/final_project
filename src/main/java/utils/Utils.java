package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.BasePage;

public class Utils {

  private static Actions actions;

  // Method for Scroll to element
  public static void scrollToElement(WebDriver driver, By element) {
//    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
    actions = new Actions(driver);
    boolean result = false;
    int attempts = 0;
    while (attempts < 2) {
      try {
        actions.moveToElement(BasePage.wailVisibleLocated(element, 10)).build().perform();
        result = true;
        break;
      } catch (StaleElementReferenceException e) {
      }
      attempts++;
    }
    //    return result;
  }
}