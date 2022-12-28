package dev.roche.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BCTestCasesPage {
    public WebDriver driver;

    public BCTestCasesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//textarea[1]")
    public WebElement description;

    @FindBy(xpath = "//textarea[2]")
    public WebElement steps;

    @FindBy(xpath = "//fieldset/button")
    public WebElement submitBtn;

    @FindBy(xpath = "//tbody/tr[last()]/td[text()=\"Verify that usernames cannot have illegal characters\"]")
    public WebElement bottomDescription;

    @FindBy(xpath = "//tbody/tr[last()]/td[3]")
    public WebElement bottomResult;

    @FindBy(xpath = "//tbody/tr[last()]/td[4]")
    public WebElement bottomDetailButton;

    @FindBy(className = "ReactModal__Content--after-open")
    public WebElement modalDialog;

    @FindBy(xpath = "/html/body/div[3]/div/div/p[6]")
    public WebElement performedBy;

    @FindBy(xpath = "/html/body/div[3]/div/div/button[1]")
    public WebElement closeBtn;

    @FindBy(xpath = "/html/body/div[3]/div/div/button[2]")
    public WebElement editBtn;
}
