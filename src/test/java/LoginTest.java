import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    public static AuthPage authPage;
    public static MainPage mainPage;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        authPage = new AuthPage(driver);
        mainPage = new MainPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("authPage"));
    }

    @Test
    public void loginTest() throws InterruptedException {
        authPage.inputLogin(ConfProperties.getProperty("login"));
        authPage.inputPasswd(ConfProperties.getProperty("password"));
        Thread.sleep(2000);
        authPage.clickLoginBtn();
        String user = mainPage.getUserLogin();
        //ToDo: поправить сравнение
        Assert.assertEquals(ConfProperties.getProperty("login"), user);
    }
}
