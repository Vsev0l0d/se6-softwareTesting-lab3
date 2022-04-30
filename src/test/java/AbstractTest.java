import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.AccountsSettingsPage;
import pages.AuthPage;
import pages.MainPage;
import pages.ProfilePage;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractTest {
    protected static WebDriver driver;
    protected static AuthPage authPage;
    protected static MainPage mainPage;
    protected static ProfilePage profilePage;
    protected static AccountsSettingsPage accountsSettingsPage;

    protected static void setupPages(){
        authPage = new AuthPage(driver);
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        accountsSettingsPage = new AccountsSettingsPage(driver);
    }

    @AfterClass
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
}
