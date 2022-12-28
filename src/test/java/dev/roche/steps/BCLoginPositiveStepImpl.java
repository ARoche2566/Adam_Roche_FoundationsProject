package dev.roche.steps;

import dev.roche.pages.BCLoginPage;
import dev.roche.pages.BCHomePage;
import dev.roche.runners.BCRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class BCLoginPositiveStepImpl {

    public WebDriver driver = BCRunner.driver;
    public BCLoginPage bcLoginPage = BCRunner.bcLoginPage;
    public BCHomePage bcHomePage = BCRunner.bcHomePage;

    @Given("The employee is on the login page")
    public void the_employee_is_on_the_login_page() {
        // driver.get("url") will load a webpage in our automated browser
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=17");
    }
    @When("The employee types {string} into username input")
    public void the_employee_types_correct_username_into_username_input(String username) {
        bcLoginPage.usernameInput.clear();
        bcLoginPage.usernameInput.sendKeys(username);
    }

    @When("The employee types {string} into password input")
    public void the_employee_types_correct_password_into_password_input(String password) {
        bcLoginPage.passwordInput.clear();
        bcLoginPage.passwordInput.sendKeys(password);
    }
    @When("The employee clicks on the login button")
    public void the_employee_clicks_on_the_login_button() {
        bcLoginPage.loginButton.click();
    }

    @Then("the employee should be on the {string} page")
    public void the_employee_should_be_on_the_role_page (String role) {


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(bcHomePage.welcomeMsg));
        String actualTitle = driver.getTitle();
        assertEquals(role+" Home", actualTitle);

    }

    @Then("The employee should see their name {string} {string} on the home page")
    public void the_employee_should_see_their_name_fname_lname_on_the_home_page (String fname, String lname) {
        assertEquals("Welcome "+ fname+" "+ lname, bcHomePage.welcomeMsg.getText());
    }

}
