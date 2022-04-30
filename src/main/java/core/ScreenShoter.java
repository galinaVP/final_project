package core;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.BasePage;

public class ScreenShoter {

  @Attachment(value = "{fileName}", type = "image/png")
  public static byte[] takeScreenShot(String fileName){
    return ((TakesScreenshot) BasePage.getDriver()).getScreenshotAs(OutputType.BYTES);
  }
}