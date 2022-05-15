package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountsCommentsPage {
    private final WebDriver driver;
    private String lastDeletedCommentId;

    public AccountsCommentsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        lastDeletedCommentId = "";
    }

    @FindBy(xpath = "(//div[@class='news-row']/descendant-or-self::a[@class='click-area'])[last()]")
    private WebElement lastComment;

    @FindBy(xpath = "(//a[contains(@href, 'kirillova200133') and contains(@class, 'user-name router-link-active')]/parent::div//span[contains(@class, 'tool-dots')])[1]")
    private WebElement tools;

    @FindBy(xpath = "(//a[contains(@href, 'kirillova200133') and contains(@class, 'user-name router-link-active')]/parent::div/parent::div/parent::div)[1]")
    private WebElement comment;

    public void scroll() {
        ((JavascriptExecutor) driver).executeScript("document.querySelector('div.news').scrollIntoView(false)");
    }

    public void clickLastComment() {
        lastComment.click();
    }

    public void openTools() {
        tools.click();
    }

    public void clickDeleteComment() {
        driver.findElement(By.xpath("//div[contains(text(), 'Delete comment')]")).click();
    }

    public void confirmDelete() {
        lastDeletedCommentId = comment.getAttribute("id");
        driver.findElement(By.xpath("//button[contains(text(), 'Delete')]")).click();
    }

    public String getCommentStatus() {
        return driver.findElement(By.xpath("//div[contains(@id, 'cm254901')]//span[contains(@class, 'user-name')]")).getText().split(" ", 2)[0];
    }
}
