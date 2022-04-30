import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ChromeTest extends AbstractTest{
    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
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
