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

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div/div[2]/div/div/div[1]/a")
    private WebElement submitLinkButton;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div/div[2]/div/div[2]/div[1]/div/textarea")
    private WebElement titleField;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div/div[2]/div/div[2]/div[2]/div/input")
    private WebElement linkField;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div/div[2]/div/div[2]/div[3]/div/textarea")
    private WebElement descriptionField;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div/div[2]/div/div[2]/div[6]/div[2]/div/button")
    private WebElement submitButton;

    @FindBy(xpath = "//span[contains(text(), '[UNCONFIRMED]')]")
    private WebElement newOasis;

    public void clickSubmitLinkButton() {
        submitLinkButton.click();
    }

    public void addTitle(String title) {
        titleField.clear();
        titleField.sendKeys(title);
    }

    public void addLink(String link) {
        linkField.clear();
        linkField.sendKeys(link);
    }

    public void addDescription(String description) {
        descriptionField.clear();
        descriptionField.sendKeys(description);
    }

    public void submit() {
        submitButton.click();
    }

    public String getNewUnconfirmedTitle() {
        return newOasis.getText().split(" ", 2)[1];
    }

}
