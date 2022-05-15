import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import pages.*;
import pages.oasis.OasisPage;
import pages.oasis.OasisPageData;
import pages.polls.PollsPage;
import pages.polls.PollsPageData;
import utils.Pair;

import static org.junit.jupiter.api.Assertions.*;
import static utils.ConfProperties.getProperty;

public abstract class AbstractTest {
    protected static WebDriver driver;
    protected static AuthPage authPage;
    protected static MainPage mainPage;
    protected static ProfilePage profilePage;
    protected static AccountsSettingsPage accountsSettingsPage;
    protected static OasisPage oasisPage;
    protected static PollsPage pollsPage;
    protected static AccountsCommentsPage accountsCommentsPage;

    protected static void setupPages(){
        authPage = new AuthPage(driver);
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        accountsSettingsPage = new AccountsSettingsPage(driver);
        oasisPage = new OasisPage(driver);
        pollsPage = new PollsPage(driver);
        accountsCommentsPage = new AccountsCommentsPage(driver);
    }

    @AfterAll
    public static void quit(){
        driver.quit();
    }

    @Test
    public void changeTextAboutProfile(){
        driver.get(getProperty("profilePage"));
        String about = profilePage.getAbout();
        String test = "test";

        driver.get(getProperty("accountsSettingsPage"));
        accountsSettingsPage.inputAbout(test);
        accountsSettingsPage.clickSaveBtn();
        driver.get(getProperty("profilePage"));
        String aboutTest = profilePage.getAbout();

        driver.get(getProperty("accountsSettingsPage"));
        accountsSettingsPage.inputAbout(about);
        accountsSettingsPage.clickSaveBtn();

        driver.get(getProperty("profilePage"));
        assertAll(
                () -> assertEquals(test, aboutTest),
                () -> assertEquals(about, profilePage.getAbout())
        );
    }

    @Test
    public void creatingNewsComments(){
        driver.get(getProperty("mainPage"));
        String comment = "comment" + Math.random();
        mainPage.openFirstNews();
        mainPage.writeComment(comment);
        mainPage.sendComment();
        assertEquals(comment, mainPage.getMyLastComment());
    }

    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {"BTC", "ETH", "USDT"})
    public void currencyFilter(String currency) throws InterruptedException {
        mainPage.setCurrencyFilter(currency);
        Thread.sleep(1000);
        assertTrue(mainPage.isAllNewsRelatedToCurrency(currency));
    }

    @Test
    public void createNewOasis() throws InterruptedException {
        driver.get(getProperty("oasisPage"));
        Pair<String, String> testData = OasisPageData.getTestData();
        oasisPage.clickSubmitLinkButton();
        oasisPage.fillTitleField(testData.getLeft());
        oasisPage.fillLinkField(testData.getRight());
        oasisPage.submitOasis();
        Thread.sleep(5000);
        assertEquals(testData.getLeft(), oasisPage.getNewUnconfirmedTitle());
    }

    @Test
    public void commentedFilter() throws InterruptedException {
        driver.get(getProperty("mainPage"));
        mainPage.setCommentedFilter();
        Thread.sleep(1000);
        assertTrue(mainPage.isAllNewsHasBeenCommented());
    }

    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {"like", "dislike", "lol", "save", "positive", "negative", "important"})
    public void reactions(String vote) throws InterruptedException {
        driver.get(getProperty("mainPage"));
        mainPage.openFirstNews();
        mainPage.clickOnReact(vote);
        Thread.sleep(1000);
        assertTrue(mainPage.isReacted(vote));
        mainPage.clickOnReact(vote);
        Thread.sleep(1000);
        assertFalse(mainPage.isReacted(vote));
    }

    @Test
    public void createNewPoll() throws InterruptedException {
        driver.get(getProperty("pollsPage"));
        String testData = PollsPageData.getTestData();
        pollsPage.createNewPoll();
        pollsPage.fillQuestion(testData);
        pollsPage.fillAnswer1("Yes");
        pollsPage.fillAnswer2("No");
        pollsPage.submitPoll();
        Thread.sleep(5000);
        assertEquals(testData, pollsPage.getNewPollQuestion());
    }

    @Test
    public void voteInPoll() throws InterruptedException {
        driver.get(getProperty("pollsPage"));
        pollsPage.clickFirstPoll();
        Integer initialAmount = pollsPage.getVotesAmount();
        pollsPage.vote1Answer();
        pollsPage.vote();
        Thread.sleep(4000);
        assertEquals(initialAmount + 1, pollsPage.getVotesAmount());
    }

    @Test
    public void deleteComment() throws InterruptedException {
        driver.get(getProperty("accountsCommentsPage"));
        accountsCommentsPage.scroll();
        accountsCommentsPage.clickLastComment();
        accountsCommentsPage.openTools();
        accountsCommentsPage.clickDeleteComment();
        accountsCommentsPage.confirmDelete();
        Thread.sleep(500);
        assertEquals("[Deleted]", accountsCommentsPage.getCommentStatus());
    }
}
