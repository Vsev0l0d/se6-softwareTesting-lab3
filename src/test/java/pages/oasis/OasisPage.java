package pages.oasis;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OasisPage {
    public WebDriver driver;

    public OasisPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[contains(@class,'btn')]")
    private WebElement submitLinkButton;

    @FindBy(xpath = "//textarea[contains(@name, 'title')]")
    private WebElement titleField;

    @FindBy(xpath = "//input[contains(@name, 'url')]")
    private WebElement linkField;

    @FindBy(xpath = "//button[contains(@type, 'submit')]")
    private WebElement submitButton;

    @FindBy(xpath = "(//span[contains(text(), '[UNCONFIRMED]')])[1]")
    private WebElement newOasis;

    public void clickSubmitLinkButton() {
        submitLinkButton.click();
    }

    public void fillTitleField(String title) {
        titleField.clear();
        titleField.sendKeys(title);
    }

    public void fillLinkField(String link) {
        linkField.clear();
        linkField.sendKeys(link);
    }

    public void submitOasis() {
        submitButton.click();
    }

    public String getNewUnconfirmedTitle() {
        return newOasis.getText().split(" ", 2)[1];
    }

}
