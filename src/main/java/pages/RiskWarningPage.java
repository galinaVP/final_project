package pages;

import org.openqa.selenium.By;
import utils.Utils;

public class RiskWarningPage extends BasePage{

    private final By hereFromRiskWarningButton = By.xpath("//a[contains(@href,'Risk-Disclosures')]");

    public void clickOnHereFromRiskWarning() {
        Utils.scrollToElement(getDriver(), hereFromRiskWarningButton);
        find(hereFromRiskWarningButton).click();
    }

//    public static void switchTabs(int expectedWindowsCount,int SwitchToWindow) throws Exception {
//        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.numberOfWindowsToBe(expectedWindowsCount));
//        ArrayList<String> tabs2 = new ArrayList<String>(getDriver().getWindowHandles());
//        getDriver().switchTo().window(tabs2.get(SwitchToWindow));
//    }

}
