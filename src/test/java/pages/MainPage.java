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

    @FindBy(xpath = "//div[@class='news']/div[contains(@class, 'news-row news-row-link')]")
    private List<WebElement> news;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div/div[1]/div[3]/div[1]/div[2]/div/a[2]")
    private WebElement firstNews;

    @FindBy(xpath = "//div[@class='filter-content filter-menu']/a[text()='Commented']")
    private WebElement commentedFilter;

    @FindBy(xpath = "//div[@class='filter-content filter-menu']/a[contains(text(),'My votes')]")
    private WebElement myVotesFilter;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div/div[1]/div[1]/div[1]/div[2]")
    private  WebElement dropdownFilter2;

    @FindBy(xpath = "//textarea[@placeholder='What are your thoughts?']")
    private WebElement commentField;

    @FindBy(xpath = "//button[@class='btn btn-outline-primary' and contains(text(), 'Comment')]")
    private WebElement commentSendBtn;

    public void openFirstNews() {
        firstNews.click();
    }

    public void writeComment(String text) {
        commentField.sendKeys(Keys.TAB);
        commentField.clear();
        commentField.sendKeys(text);
    }

    public void sendComment() {
        commentSendBtn.click();
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
        return news.stream().allMatch(x -> x.findElements(By.xpath(
                "//a[text()='" + currency.toUpperCase() + "']")).size() > 0 ||
                x.findElements(By.xpath("//div[@class='news-cell nc-currency']/span[text()='...']")).size() > 0);
    }

    public void setCommentedFilter(){
        dropdownFilter2.click();
        commentedFilter.click();
    }

    public void setMyVotesFilter(){
        dropdownFilter2.click();
        myVotesFilter.click();
    }

    public boolean isAllNewsHasBeenCommented(){
        return news.stream().allMatch(x -> x.findElements(By.xpath(
                "//div[@class='news-votes news-cell nc-votes']/span[contains(@title, 'comments votes')]"
        )).size() > 0);
    }

    public boolean isAllNewsHasBeenVotedByMe(){
        return news.stream().allMatch(x -> x.findElements(By.xpath(
                "//div[@class='news-votes news-cell nc-votes']/span[not(contains(@title, 'comments votes'))]/span[contains(@class, 'active')]"
        )).size() > 0);
    }

    public void clickOnReact(String vote){
        driver.findElement(By.xpath(
                "//div[@class='votes-grid']/div/a[contains(@class, 'vote-" + vote + "')]"
        )).click();
    }

    public boolean isReacted(String vote) {
        return driver.findElements(By.xpath("//div[@class='votes-grid']/div/a[contains(@class, 'vote-" + vote + " active')]")).size() > 0 && driver.findElements(By.xpath("//div[@class='news-row news-row-link active']//div[@class='news-votes news-cell nc-votes']/span[contains(@title, '" + vote + " votes')]/span[contains(@class, 'active')]")).size() > 0;
    }

    public void openBTCPage() {
        driver.findElement(By.xpath("//div[contains(@class, 'currencies-scroll')]/a[contains(@class, 'currency')]/span[@class='name' and text()='BTC']")).click();
    }

    public void openBTCPortfolio() {
        driver.findElement(By.xpath("//a[@class='pane-tab' and contains(text(), 'Portfolio')]")).click();
    }

    public void clickToAddNewRow() {
        driver.findElement(By.xpath("//a[text()='Add new row']")).click();
    }

    public void fillLabelField(String label) {
        driver.findElement(By.xpath("(//input[contains(@placeholder, 'eg. exchange or wallet name')])[last()]")).sendKeys(label);
    }

    public void fillAmount(String amount) {
        driver.findElement(By.xpath("(//input[contains(@name, 'amount_')])[last()]")).sendKeys(amount);
    }

    public void saveAdding() {
        driver.findElement(By.xpath("//span[contains(text(), 'Save')]/parent::a")).click();
    }

    public void switchTheme(){
        driver.findElement(By.xpath(
                "//div[@class='theme-switch']/a[@class='toggle-light-link']"
        )).click();
    }
}
