package pages;

import enums.CalendarTabs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;

public class EconomicCalendarPage extends BasePage {

    private final By yesterdayButton = By.xpath("//a[@id='timeFrame_yesterday']");
    private final By todayButton = By.id("timeFrame_today");
    private final By tomorrowButton = By.id("timeFrame_tomorrow");
    private final By thisWeekButton = By.id("timeFrame_thisWeek");
    private final By dateRange = By.xpath("//div[@id='widgetFieldDateRange']");
    private final WebElement iframeElement = getDriver().findElement(By.xpath("//iframe[@title='economicCalendar']"));
    private final By hereFromDisclaimerButton = By.xpath("//a[contains(@href,'risk_warning')]");

    public String checkIsCorrectDate(final CalendarTabs tab) {
//        WebDriver frame = getDriver().switchTo().frame(iframeElement);
        switch (CalendarTabs.valueOf(String.valueOf(tab))) {
            case YESTERDAY:
                WebDriver frame = getDriver().switchTo().frame(iframeElement);
                frame.findElement(yesterdayButton).click();
                new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(
                        ExpectedConditions.attributeContains(find(yesterdayButton), "class", "toggled"));
                waitRefreshedClickable(dateRange,5).getText();
                break;
            case TODAY:
                getDriver().switchTo().frame(iframeElement).findElement(todayButton).click();
//                frame.findElement(todayButton).click();
                new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(
                        ExpectedConditions.attributeContains(find(todayButton), "class", "toggled"));
                waitRefreshedClickable(dateRange,5).getText();
                break;
            case TOMORROW:
                getDriver().switchTo().frame(iframeElement).findElement(tomorrowButton).click();
                new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(
                        ExpectedConditions.attributeContains(find(tomorrowButton), "class", "toggled"));
                break;
            case THIS_WEEK:
                getDriver().switchTo().frame(iframeElement).findElement(thisWeekButton).click();
                new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(
                        ExpectedConditions.attributeContains(find(thisWeekButton), "class", "toggled"));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + CalendarTabs.valueOf(String.valueOf(tab)));
        }
        return getDriver().switchTo().frame(iframeElement).findElement(dateRange).getText();
    }

    public RiskWarningPage clickOnHereFromDisclaimerButton() {
        Utils.scrollToElement(getDriver(), hereFromDisclaimerButton);
        wailElementToBeClickable(hereFromDisclaimerButton, 5).click();
        return new RiskWarningPage();
    }

}