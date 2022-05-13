import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import pages.AccountsSettingsPage;
import pages.AuthPage;
import pages.MainPage;
import pages.ProfilePage;
import pages.oasis.OasisPage;
import pages.oasis.OasisPageData;
import utils.Pair;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractTest {
    protected static WebDriver driver;
    protected static AuthPage authPage;
    protected static MainPage mainPage;
    protected static ProfilePage profilePage;
    protected static AccountsSettingsPage accountsSettingsPage;
    protected static OasisPage oasisPage;

    protected static void setupPages(){
        authPage = new AuthPage(driver);
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        accountsSettingsPage = new AccountsSettingsPage(driver);
        oasisPage = new OasisPage(driver);
    }

    @AfterAll
    public static void quit(){
        driver.quit();
    }

    @Test
    public void changeTextAboutProfile(){
        driver.get(ConfProperties.getProperty("profilePage"));
        String about = profilePage.getAbout();
        String test = "test";

        driver.get(ConfProperties.getProperty("accountsSettingsPage"));
        accountsSettingsPage.inputAbout(test);
        accountsSettingsPage.clickSaveBtn();
        driver.get(ConfProperties.getProperty("profilePage"));
        String aboutTest = profilePage.getAbout();

        driver.get(ConfProperties.getProperty("accountsSettingsPage"));
        accountsSettingsPage.inputAbout(about);
        accountsSettingsPage.clickSaveBtn();

        driver.get(ConfProperties.getProperty("profilePage"));
        assertAll(
                () -> assertEquals(test, aboutTest),
                () -> assertEquals(about, profilePage.getAbout())
        );
    }

    @Test
    public void creatingNewsComments(){
        driver.get(ConfProperties.getProperty("mainPage"));
        String comment = "comment" + Math.random();
        mainPage.openFirstNews();
        mainPage.writeComment(comment);
        mainPage.sendComment();
        assertEquals(comment, mainPage.getMyLastComment());
    }

    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {"BTC", "ETH", "USDT"})
    public void currencyFilter(String currency) throws InterruptedException {
        mainPage.setCurrencyFilter(currency);
        Thread.sleep(1000);
        assertTrue(mainPage.isAllNewsRelatedToCurrency(currency));
    }

    @Test
    public void createNewOasis() throws InterruptedException {
        driver.get(ConfProperties.getProperty("oasisPage"));
        Pair<String, String> testData = OasisPageData.getTestData();
        oasisPage.clickSubmitLinkButton();
        oasisPage.addTitle(testData.getLeft());
        oasisPage.addLink(testData.getRight());
        oasisPage.submit();
        Thread.sleep(1000);
        String resultTitle = oasisPage.getNewUnconfirmedTitle();
        assertEquals(testData.getLeft(), resultTitle);
    }
}
