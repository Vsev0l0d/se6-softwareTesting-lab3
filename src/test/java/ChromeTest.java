import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(value = PER_CLASS)
public class ChromeTest {
    private final ChromeDriver driver = new ChromeDriver();
    private final String url = "https://cryptopanic.com/";

    @BeforeClass
    public static void setup(){
        System.setProperty("webdriver.chrome.driver", "/chromedriver.exe");
    }

    @Test
    public void test(){
        driver.get(url);
    }
}
