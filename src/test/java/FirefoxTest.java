import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(value = PER_CLASS)
public class FirefoxTest {
    private final FirefoxDriver driver = new FirefoxDriver();
    private final String url = "https://cryptopanic.com/";

    @BeforeClass
    public static void setup(){
        System.setProperty("webdriver.chrome.driver", "/geckodriver.exe");
    }

    @Test
    public void test(){
        driver.get(url);
    }
}
