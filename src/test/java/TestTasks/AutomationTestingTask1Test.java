package TestTasks;

import enums.CalendarTabs;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.EconomicCalendarPage;
import pages.MainPage;

import static utils.DateUtils.*;

public class AutomationTestingTask1Test extends BaseTest{

    @Test
    public void checkWasOpenedDocumentInNewTabTest(){

        MainPage mainPage = new MainPage();
        EconomicCalendarPage economicCalendarPage = mainPage.openMainPage().getMainMenuBlock()
                .clickOnResearchAndEducationMainMenuButton().
                clickOnEconomicCalendarMainMenuButton();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(getActualYesterdayDate())
                .as("1")
                .isEqualTo(economicCalendarPage.checkIsCorrectDate(CalendarTabs.YESTERDAY));
        softAssertions.assertThat(getActualTodayDate())
                        .as("1")
                                .isEqualTo(economicCalendarPage.checkIsCorrectDate(CalendarTabs.TODAY));
        softAssertions.assertThat(getActualTomorrowDate())
                .as("1")
                .isEqualTo(economicCalendarPage.checkIsCorrectDate(CalendarTabs.TOMORROW));
        softAssertions.assertThat(getActualThisWeekDate())
                .as("1")
                .isEqualTo(economicCalendarPage.checkIsCorrectDate(CalendarTabs.THIS_WEEK));

//        economicCalendarPage
//                .clickOnHereFromDisclaimerButton()
//                .clickOnHereFromRiskWarning();


        softAssertions.assertAll();



    }
}
