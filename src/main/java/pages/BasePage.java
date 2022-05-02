package pages;

import blocks.FooterMenuBlock;
import blocks.LanguagesBlock;
import blocks.MainMenuBlock;
import blocks.SubscribeBlock;
import blocks.TopMenuBlock;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

  private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();
  private static Actions actions;

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

  // getter for SubscribeBlock
  private SubscribeBlock subscribeBlock = new SubscribeBlock(getDriver());


  public SubscribeBlock getSubscribeBlock() {
    return subscribeBlock;
  }

  // getter for LanguagesBlock
  private LanguagesBlock languagesBlock = new LanguagesBlock(getDriver());

  public LanguagesBlock getLanguagesBlock() {
    return languagesBlock;
  }

  // getter for TopMenuBlock
  private TopMenuBlock topMenuBlock = new TopMenuBlock(getDriver());

  public TopMenuBlock getTopMenuBlock() {
    return topMenuBlock;
  }

  // getter for MainMenuBlock
  private MainMenuBlock mainMenuBlock = new MainMenuBlock(getDriver());
  public MainMenuBlock getMainMenuBlock() {
    return mainMenuBlock;
  }

  // getter for FooterMenuBlock
  private FooterMenuBlock footerMenuBlock = new FooterMenuBlock(getDriver());
  public FooterMenuBlock getFooterMenuBlock() {
    return footerMenuBlock;
  }

  // find
  public WebElement find(By locator) {
    return getDriver().findElement(locator);
  }

  // actions Move to element
  public static void moveToElement(By locator) {
    actions = new Actions(getDriver());
    boolean result = false;
    int attempts = 0;
    while(attempts < 1) {
      try {
        actions.moveToElement(getDriver().findElement(locator)).build().perform();
        result = true;
        break;
      } catch(StaleElementReferenceException e) {
      }
      attempts++;
    }
//    return result;
  }

  // actions Move to element
  public static boolean moveToWebElement(WebElement element) {
    actions = new Actions(getDriver());
    boolean result = false;
    int attempts = 0;
    while(attempts < 2) {
      try {
        actions.moveToElement(element).build().perform();
        result = true;
        break;
      } catch(StaleElementReferenceException e) {
      }
      attempts++;
    }
    return result;
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

  // waiter visibilityOfElementLocated
  public static WebElement wailElementToBeClickable(By locator, int second) {
    return new WebDriverWait(getDriver(), Duration.ofSeconds(second)).until(
        ExpectedConditions.elementToBeClickable(locator));
  }

  // waiter visibilityOfAllElementsLocated
  public static List<WebElement> visibilityOfAllElementsLocated(By locator, int second) {
    return new WebDriverWait(getDriver(), Duration.ofSeconds(second)).until(
        ExpectedConditions.visibilityOfAllElements (getDriver().findElements(locator)));
  }

  public static List<WebElement> presenceOfAllElementsLocatedBy(By locator, int second) {
    return new WebDriverWait(getDriver(), Duration.ofSeconds(second)).until(
        ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
  }

  public static boolean waitRefreshed(By locator, int second) {
    return new WebDriverWait(getDriver(), Duration.ofSeconds(second)).until(
        ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(getDriver().findElement(locator))));
  }
  ///// waiters *** end //////
}