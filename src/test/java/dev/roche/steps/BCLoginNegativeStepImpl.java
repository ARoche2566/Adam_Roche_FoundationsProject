package dev.roche.steps;

import dev.roche.pages.BCLoginPage;
import dev.roche.runners.BCRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
public class BCLoginNegativeStepImpl {

    public WebDriver driver = BCRunner.driver;

    public BCLoginPage bcLoginPage = BCRunner.bcLoginPage;

    // Steps for scenario: correct username, wrong password
    @When("The employee types in g8tor into the username input")
    public void the_employee_types_in_g8tor_into_the_username_input() {
        bcLoginPage.usernameInput.clear();
        bcLoginPage.usernameInput.sendKeys("g8tor");
    }

    @When("The employee types in chomp!! into the password input")
    public void the_employee_types_in_chomp_into_the_password_input() {
        bcLoginPage.passwordInput.clear();
        bcLoginPage.passwordInput.sendKeys("chomp!!");
    }

    @Then("The employee should see an alert saying they have the wrong password")
    public void the_employee_should_see_an_alert_saying_they_have_the_wrong_password() {
        new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(ElementNotInteractableException.class)
                .until(ExpectedConditions.alertIsPresent());
        String expectedMsg = "Wrong password for User";
        String alertMsg = driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();

        assertEquals(expectedMsg, alertMsg);

    }

    // Steps for scenario: wrong username, correct password
    @When("The employee types in sicEmDawgs into the username input")
    public void the_employee_types_in_sic_em_dawgs_into_the_username_input() {
        bcLoginPage.usernameInput.clear();
        bcLoginPage.usernameInput.sendKeys("sicEmDawgs");
    }

    @When("The employee types in natchamps into the password input")
    public void the_employee_types_in_natchamps_into_the_password_input() {
        bcLoginPage.passwordInput.clear();
        bcLoginPage.passwordInput.sendKeys("natchamps");
    }

    @Then("The employee should see an alert saying no user with that username found")
    public void the_employee_should_see_an_alert_saying_no_user_with_that_username_found() {
        new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(ElementNotInteractableException.class)
                .until(ExpectedConditions.alertIsPresent());

        String expectedMsg = "Username not found";
        String alertMsg = driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();

        assertEquals(expectedMsg, alertMsg);

    }
}