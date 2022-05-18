package pages;

import blocks.FooterMenuBlock;
import blocks.LanguagesBlock;
import blocks.MainMenuBlock;
import blocks.SubscribeBlock;
import blocks.TopMenuBlock;
import java.time.Duration;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
public abstract class BasePage {

  private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();
  protected static Actions actions;

  // setter box DRIVER_THREAD_LOCAL
  public static void setDriverThreadLocal(WebDriver driver) {
    DRIVER_THREAD_LOCAL.set(driver);
  }

  // getter box DRIVER_THREAD_LOCAL
  public static ThreadLocal<WebDriver> getDriverThreadLocal() {
    return DRIVER_THREAD_LOCAL;
  }

  // getter driver
  public static WebDriver getDriver() {
    return DRIVER_THREAD_LOCAL.get();
  }

  private final SubscribeBlock subscribeBlock = new SubscribeBlock(getDriver());
  private final LanguagesBlock languagesBlock = new LanguagesBlock(getDriver());
  private final TopMenuBlock topMenuBlock = new TopMenuBlock(getDriver());
  private final MainMenuBlock mainMenuBlock = new MainMenuBlock(getDriver());
  private final FooterMenuBlock footerMenuBlock = new FooterMenuBlock(getDriver());

  // find
  public WebElement find(By locator) {
    return getDriver().findElement(locator);
  }

  // actions Move to element
  public static void moveToWebElement(WebElement element) {
    actions = new Actions(getDriver());
    int attempts = 0;
    while (attempts < 2) {
      try {
        actions.moveToElement(element).build().perform();
        break;
      } catch (StaleElementReferenceException e) {
      }
      attempts++;
    }
  }

  ///// waiters *** begin //////
  // waiter invisibilityOfElementLocated
  public static boolean wailInvisibleLocated(By locator, int second) {
    return new WebDriverWait(getDriver(), Duration.ofSeconds(second)).until(
        ExpectedConditions.invisibilityOfElementLocated(locator));
  }

  // waiter visibilityOfElementLocated
  public static WebElement wailVisibleLocated(By locator, int second) {
    return new WebDriverWait(getDriver(), Duration.ofSeconds(second)).until(
        ExpectedConditions.visibilityOfElementLocated(locator));
  }

  // waiter elementToBeClickable
  public static WebElement wailElementToBeClickable(By locator, int second) {
    return new WebDriverWait(getDriver(), Duration.ofSeconds(second)).until(
        ExpectedConditions.elementToBeClickable(locator));
  }

  // waiter presenceOfAllElementsLocatedBy
  public static List<WebElement> presenceOfAllElementsLocatedBy(By locator, int second) {
    return new WebDriverWait(getDriver(), Duration.ofSeconds(second)).until(
        ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
  }

  // waiter refreshed / stalenessOf
  public static boolean waitRefreshed(By locator, int second) {
    return new WebDriverWait(getDriver(), Duration.ofSeconds(second)).until(
        ExpectedConditions.refreshed(
            ExpectedConditions.stalenessOf(getDriver().findElement(locator))));
  }

  // waiter elementToBeClickable
  public static WebElement waitRefreshedClickable(By locator, int second) {
    return new WebDriverWait(getDriver(), Duration.ofSeconds(second)).until(
        ExpectedConditions.refreshed(
            ExpectedConditions.elementToBeClickable(locator)));
  }

  // waiter visibilityOfNestedElementsLocatedBy
  public static List<WebElement> visibilityOfNestedElementsLocatedBy(By parent, By child,
      int second) {
    return new WebDriverWait(getDriver(), Duration.ofSeconds(second)).until(
        ExpectedConditions.visibilityOfNestedElementsLocatedBy(parent, child));
  }
  ///// waiters *** end //////
}