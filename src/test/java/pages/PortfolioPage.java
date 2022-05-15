package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PortfolioPage {
    private final WebDriver driver;

    public PortfolioPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public Integer getTotalAmountBTC() {
        return Integer.valueOf(driver.findElement(By.xpath("//table//tr//a[contains(text(), 'Bitcoin')]//parent::div//parent::td//parent::tr/td[8]/div")).getText());
    }
}
