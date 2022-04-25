import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthPage {
    public WebDriver driver;

    public AuthPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id=\"id_login\"]")
    private WebElement loginField;

    @FindBy(xpath = "//*[@id=\"id_password\"]")
    private WebElement passwdField;

    @FindBy(xpath = "//*[@id=\"login_btn\"]")
    private WebElement loginBtn;

    @FindBy(xpath = "/html/body/div[2]/div[3]/div[1]/div/div/span")
    private WebElement captchaBtn;

    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }

    public void inputPasswd(String passwd) {
        passwdField.sendKeys(passwd);
    }

    public void clickLoginBtn() {
        loginBtn.click();
    }

    public void clickCaptcha() {
        captchaBtn.click();
    }
}
