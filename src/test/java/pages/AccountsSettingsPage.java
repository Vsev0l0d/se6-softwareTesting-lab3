package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountsSettingsPage {
    public WebDriver driver;

    public AccountsSettingsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "/html/body/div/div[2]/div[1]/div/div[2]/div/div[2]/form/fieldset[2]/div[1]/div/textarea")
    private WebElement aboutField;

    @FindBy(xpath = "/html/body/div/div[2]/div[1]/div/div[2]/div/div[2]/form/div/div[2]/div/input")
    private WebElement saveBtn;

    public void inputAbout(String about) {
        aboutField.clear();
        aboutField.sendKeys(about);
    }

    public void clickSaveBtn() {
        saveBtn.click();
    }
}
