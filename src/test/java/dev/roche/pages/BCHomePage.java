package dev.roche.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BCHomePage {
    public WebDriver driver;

    public BCHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p")
    public WebElement welcomeMsg;

    @FindBy(xpath = "//a[1]")
    public WebElement matricesLink;
    @FindBy(xpath = "//a[2]")
    public WebElement testCasesLink;
    @FindBy(xpath = "//a[3]")
    public WebElement reportDefectLink;

    @FindBy(xpath = "//a[4]")
    public WebElement defectOverviewLink;
    @FindBy(xpath = "//a[5]")
    public WebElement logoutBtn;

    @FindBy(xpath = "//*[@id=\"root\"]/button")
    public WebElement newMatrixBtn;

    @FindBy(xpath = "//*[@id=\"root\"]/input")
    public WebElement matrixTitleField;

    @FindBy(xpath = "//*[@id=\"root\"]/fieldset/table/tbody/tr/td[1]/input")
    public WebElement userStoryField;

    @FindBy(xpath = "//*[@id=\"root\"]/fieldset/button")
    public WebElement addRequirementBtn;

    @FindBy(xpath = "//*[@id=\"root\"]/button")
    public WebElement createMatrixBtn;

    @FindBy(xpath = "//h3")
    public WebElement defectList;

    @FindBy(xpath = "//*[@id=\"root\"]/table/tbody/tr[1]")
    public WebElement firstDefect;


    @FindBy(xpath = "//*[@id=\"root\"]/table/tbody/tr[1]/td[3]/button")
    public WebElement selectDefectBtn;

    @FindBy(xpath = "//*[@id=\"root\"]/div/h4")
    public WebElement boldText;

    @FindBy(xpath = "//*[@id=\"root\"]/div/input")
    public WebElement employeeList;

    @FindBy(xpath = "//*[@id=\"root\"]/div/button")
    public WebElement assignBtn;

    @FindAll({
            @FindBy(tagName = "li")
    })
    public List<WebElement> allDefectList;

    @FindBy(xpath = "(//button[text()=\"Change Status\"])[1]")
    public WebElement changeStatusBtn;

    @FindBy(xpath = "(//p/b[2])[1]")
    public WebElement originalStatus;

}
