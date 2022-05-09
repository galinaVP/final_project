package core;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

@Slf4j
public class EventDriver implements WebDriverListener {

  @Override
  public void beforeGet(WebDriver driver, String url) {
    log.info("Opening page with URL [{}]", url);
  }

  @Override
  public void afterGet(WebDriver driver, String url) {
    log.info("Page with URL [{}] was successfully opened", url);
  }

  @Override
  public void beforeClick(WebElement element) {
    log.info("Clicking on the element {}", element);
  }

  @Override
  public void afterClick(WebElement element) {
    log.info("Clicking on the element {} was successful", element);
  }

  @Override
  public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
    log.info("Typing {} into field {}", keysToSend, element);
  }

  @Override
  public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
    log.info("Text {} was saved at the element {}", keysToSend, element);
  }

  @Override
  public void beforeFindElement(WebDriver driver, By locator) {
    log.info("Finding the element by {}", locator);
  }

  @Override
  public void afterFindElement(WebDriver driver, By locator, WebElement result) {
    log.info("Found the element by {}, {}", locator, result);
  }

  @Override
  public void beforeFindElements(WebDriver driver, By locator) {
    log.info("Finding the elements by {}", locator);
  }

  @Override
  public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
    log.info("Found the elements by {}, {}", locator, result);
  }

  @Override
  public void beforeGetAttribute(WebElement element, String name) {
    log.info("Get Attribute {} from element {}", name, element);
  }

  @Override
  public void afterGetAttribute(WebElement element, String name, String result) {
    log.info("Got Attribute {} from element {}", name, element);
  }

  @Override
  public void beforeGetCssValue(WebElement element, String propertyName) {
    log.info("Get Css value {} from element {}", propertyName, element);
  }

  @Override
  public void afterGetCssValue(WebElement element, String propertyName, String result) {
    log.info("Got Css value {} from element {} - {}", propertyName, element, result);
  }

  @Override
  public void beforeGetText(WebElement element) {
    log.info("Get text from element {}", element);
  }

  @Override
  public void afterGetText(WebElement element, String result) {
    log.info("Got text {} from element {}", result, element);
  }

}