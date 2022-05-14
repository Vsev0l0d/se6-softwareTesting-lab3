package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static utils.ConfProperties.getProperty;

public class MainPage {
    public WebDriver driver;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id=\"messages\"]/div/div")
    private WebElement loginSuccessLabel;

    @FindBy(xpath = "//div[@class='news']/div[contains(@class, 'news-row news-row-link')]")
    private List<WebElement> news;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div/div[1]/div[3]/div[1]/div[2]/div/a[2]")
    private WebElement firstNews;

    @FindBy(xpath = "//div[@class='filter-content filter-menu']/a[text()='Commented']")
    private WebElement commentedFilter;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div/div[1]/div[1]/div[1]/div[2]")
    private  WebElement dropdownFilter2;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div/div[2]/div/div[2]/div[1]/a[1]")
    private WebElement likeBtn;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div/div[2]/div/div[2]/div[1]/a[2]")
    private WebElement dislikeBtn;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div/div[2]/div/div[2]/div[1]/a[3]")
    private WebElement lolBtn;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div/div[2]/div/div[2]/div[1]/a[4]")
    private WebElement saveBtn;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div/div[2]/div/div[2]/div[2]/a[1]")
    private WebElement bullishBtn;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div/div[2]/div/div[2]/div[2]/a[2]")
    private WebElement bearishBtn;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div/div[2]/div/div[2]/div[2]/a[3]")
    private WebElement importantBtn;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div/div[2]/div/div[2]/div[2]/a[4]")
    private WebElement toxicBtn;

    @FindBy(xpath = "//textarea[@placeholder='What are your thoughts?']")
    private WebElement commentField;

    @FindBy(xpath = "//button[@class='btn btn-outline-primary' and contains(text(), 'Comment')]")
    private WebElement commentSendBtn;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div[2]/div/div[3]/ul/li[2]/a/span[1]")
    private WebElement subscribeCommentBtn;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div[1]/div[1]/div[2]/span/span[2]")
    private WebElement filterValue;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[3]/a[3]")
    private WebElement currentCoinPrice;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div[2]/div/div[1]/div[1]/div/a")
    private WebElement followCoinBtn;

    public String getUserLogin() {
        return loginSuccessLabel.getText();
    }

    public void openFirstNews() {
        firstNews.click();
    }

    public void voteLike() {
        likeBtn.click();
    }

    public void voteDislike() {
        dislikeBtn.click();
    }

    public void voteLol() {
        lolBtn.click();
    }

    public void voteSave() {
        saveBtn.click();
    }

    public void voteBullish() {
        bullishBtn.click();
    }

    public void voteBearish() {
        bearishBtn.click();
    }

    public void voteImportant() {
        importantBtn.click();
    }

    public void voteToxic() {
        toxicBtn.click();
    }

    public void writeComment(String text) {
        commentField.sendKeys(Keys.TAB);
        commentField.clear();
        commentField.sendKeys(text);
    }

    public void sendComment() {
        commentSendBtn.click();
    }

    public void subscribeComment() {
        subscribeCommentBtn.click();
    }

    public String getFilterValue() {
        return filterValue.getText();
    }

    public void clickCoin() {
        currentCoinPrice.click();
    }

    public void followCoin() {
        followCoinBtn.click();
    }

    public String getMyLastComment() {
        WebElement myLastComment = driver.findElement(By.xpath(
                "(//div[@class='comment-content']/a[@class='user-name' and contains(text(),'" + getProperty("login")
                        + "')])[last()]/../span[@class='comment-body']/span"
        ));
        while (myLastComment.getText().isEmpty()){}
        return myLastComment.getText();
    }

    public void setCurrencyFilter(String currency) {
        driver.findElement(By.xpath(
                ".//div[contains(@class, 'currencies-scroll')]/a[contains(@class, 'currency')]/span[@class='name' and contains(text(), '"
                        + currency.toUpperCase() + "')]"
        )).click();
    }

    public boolean isAllNewsRelatedToCurrency(String currency){
        return news.stream().allMatch(x -> x.findElement(By.xpath(
                "//a[text()='" + currency.toUpperCase() + "']")).isEnabled() ||
                x.findElement(By.xpath("//div[@class='news-cell nc-currency']/span[text()='...']")).isEnabled());
    }

    public void setCommentedFilter(){
        dropdownFilter2.click();
        commentedFilter.click();
    }

    public boolean isAllNewsHasBeenCommented(){
        return news.stream().allMatch(x -> x.findElement(By.xpath(
                "//div[@class='news-votes news-cell nc-votes']/span[contains(@title, 'comments votes')]"
        )).isEnabled());
    }
}
