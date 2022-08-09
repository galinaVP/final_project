package pages;

import blocks.MainMenuBlock;

import java.time.Duration;
import java.util.List;

import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
public abstract class BasePage {

    private static WebDriver driver;
    protected static Actions actions;

    // Getter for driver
    public static WebDriver getDriver() {
        return driver;
    }

    // Setter for Driver
    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

     private final MainMenuBlock mainMenuBlock = new MainMenuBlock(getDriver());

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

    public static void scrollToElement(WebDriver driver, By element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
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