package dev.roche.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BCLoginPage {
    public WebDriver driver;

    public BCLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html/body/div/fieldset/input[1]")
    public WebElement usernameInput;

    @FindBy(xpath = "/html/body/div/fieldset/input[2]")
    public WebElement passwordInput;

    @FindBy(xpath = "/html/body/div/fieldset/button")
    public WebElement loginButton;

}
