package dev.roche.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class BCDefectReporterPage {

    public WebDriver driver;

    public BCDefectReporterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[type='date']")
    public WebElement datePicker;

    @FindBy(xpath = "//textarea[1]")
    public WebElement description;

    @FindBy(xpath = "//textarea[2]")
    public WebElement reproduceStep;

    @FindBy(xpath = "//input[2]")
    public WebElement severity;

    @FindBy(xpath = "//input[2]")
    public WebElement priority;

    @FindBy(xpath = "//button")
    public WebElement reportBtn;

    @FindBy(xpath = "/html/body/div[3]/div/div")
    public WebElement modal;

    @FindBy(xpath = "/html/body/div[3]/div/div/h4")
    public WebElement defectCreatedMsg;

    @FindBy(xpath = "/html/body/div[3]/div/div/button")
    public WebElement closeBtn;

    @FindBy(xpath = "//input[@name='severity']")
    public WebElement severitySlider;

    @FindBy(xpath = "//input[@name='priority']")
    public WebElement prioritySlider;
}

