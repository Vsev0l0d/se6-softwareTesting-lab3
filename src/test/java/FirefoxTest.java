import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class FirefoxTest extends AbstractTest{
    @BeforeAll
    public static void setup(){
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("geckodriver"));
        driver = new FirefoxDriver();
        setupPages();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get(ConfProperties.getProperty("authPage"));
        authPage.inputLogin(ConfProperties.getProperty("login"));
        authPage.inputPasswd(ConfProperties.getProperty("password"));
        WebDriverWait wait = new WebDriverWait(driver, 600);
        wait.until(ExpectedConditions.urlToBe(ConfProperties.getProperty("mainPage")));
    }
}
