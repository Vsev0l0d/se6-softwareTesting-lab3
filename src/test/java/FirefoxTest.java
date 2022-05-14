import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static utils.ConfProperties.getProperty;

public class FirefoxTest extends AbstractTest{
    @BeforeAll
    public static void setup(){
        System.setProperty("webdriver.chrome.driver", getProperty("geckodriver"));
        driver = new FirefoxDriver();
        setupPages();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get(getProperty("authPage"));
        authPage.inputLogin(getProperty("login"));
        authPage.inputPasswd(getProperty("password"));
        WebDriverWait wait = new WebDriverWait(driver, 600);
        wait.until(ExpectedConditions.urlToBe(getProperty("mainPage")));
    }
}
