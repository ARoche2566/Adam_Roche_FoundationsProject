package dev.roche.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BCCaseEditorPage {
    public WebDriver driver;

    public BCCaseEditorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"root\"]/button")
    public WebElement editBtn;

    @FindBy(xpath = "//fieldset[1]/textarea[1]")
    public WebElement descriptionBox;

    @FindBy(xpath = "//textarea[2]")
    public WebElement stepsBox;

    @FindBy(css = "input[type='checkbox']")
    public WebElement checkbox;

    @FindBy(xpath = "//*[@id=\"root\"]/fieldset[1]/select")
    public WebElement performBy;

    @FindBy(xpath = "//*[@id=\"root\"]/fieldset[2]/select")
    public WebElement testResult;

    @FindBy(xpath = "//fieldset[2]/textarea[1]")
    public WebElement summary;

    @FindBy(xpath = "//button[text()=\"Save\"]")
    public WebElement saveBtn;

    @FindBy(xpath = "//button[text()=\"Reset\"]")
    public WebElement resetBtn;
}
