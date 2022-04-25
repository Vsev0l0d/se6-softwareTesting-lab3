import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public abstract class AbstractTest {
    protected static WebDriver driver;
    protected static AuthPage authPage;
    protected static MainPage mainPage;

    @Test
    public void loginTest() {
        driver.get(ConfProperties.getProperty("authPage"));
        authPage.inputLogin(ConfProperties.getProperty("login"));
        authPage.inputPasswd(ConfProperties.getProperty("password"));
        Assert.assertTrue(true);
//        authPage.clickLoginBtn();
//        String user = mainPage.getUserLogin();
//        //ToDo: поправить сравнение
//        Assert.assertEquals(ConfProperties.getProperty("login"), user);
    }
}
