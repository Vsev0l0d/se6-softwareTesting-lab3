package pages.polls;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PollsPage {
    public WebDriver driver;

    public PollsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[contains(@class,'btn')]")
    private WebElement createPollButton;

    @FindBy(xpath = "//textarea[contains(@name, 'title')]")
    private WebElement questionField;

    @FindBy(xpath = "//input[contains(@name, 'poll_answer_-1')]")
    private WebElement answer1Field;

    @FindBy(xpath = "//input[contains(@name, 'poll_answer_-2')]")
    private WebElement answer2Field;

    @FindBy(xpath = "//button[contains(@type, 'submit')]")
    private WebElement submitButton;

    @FindBy(xpath = "(//span[contains(text(), '[DRAFT]')])[1]")
    private WebElement newPollQuestion;

    public void createNewPoll() {
        createPollButton.click();
    }

    public void fillQuestion(String question) {
        questionField.clear();
        questionField.sendKeys(question);
    }

    public void fillAnswer1(String answer1) {
        answer1Field.clear();
        answer1Field.sendKeys(answer1);
    }

    public void fillAnswer2(String answer2) {
        answer2Field.clear();
        answer2Field.sendKeys(answer2);
    }

    public void submitPoll() {
        submitButton.click();
    }

    public String getNewPollQuestion() {
        return newPollQuestion.getText().split(" ", 2)[1];
    }
}
