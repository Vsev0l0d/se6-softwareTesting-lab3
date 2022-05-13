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

    @FindBy(xpath = "//textarea[@name='about']")
    private WebElement aboutField;

    @FindBy(xpath = "//input[@type='submit' and @name='save']")
    private WebElement saveBtn;

    public void inputAbout(String about) {
        aboutField.clear();
        aboutField.sendKeys(about);
    }

    public void clickSaveBtn() {
        saveBtn.click();
    }
}
