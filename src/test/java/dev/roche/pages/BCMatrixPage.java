package dev.roche.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BCMatrixPage {

    public WebDriver driver;

    public BCMatrixPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="(//button[text()=\"Show\"])[1]")
    public WebElement showAirlineMatrixBtn;

    @FindBy(xpath="(//button[text()=\"Edit\"])[1]")
    public WebElement editAirlineFirstRuleBtn;

    @FindBy(xpath = "/html/body/div/ul/li[1]/div/div/div/ul[1]/li/input")
    public WebElement testCaseIDField;

    @FindBy(xpath = "/html/body/div/ul/li[1]/div/div/div/ul[1]/li/button")
    public WebElement addTestCaseBtn;
    @FindBy(xpath = "/html/body/div/ul/li[1]/div/div/div/ul[2]/li/input")
    public WebElement defectIDField;

    @FindBy(xpath="/html/body/div/ul/li[1]/div/div/div/ul[2]/li/button")
    public WebElement addDefectBtn;

    @FindBy(xpath = "/html/body/div/ul/li[1]/div/div/div/button")
    public WebElement saveBtn;
}