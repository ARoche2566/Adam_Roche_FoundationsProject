package dev.roche.steps;

import dev.roche.pages.*;
import dev.roche.runners.BCRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static org.junit.Assert.*;


public class BCDefectReportPositiveImpl {
    public WebDriver driver = BCRunner.driver;
    public BCLoginPage bcLoginPage = BCRunner.bcLoginPage;
    public BCHomePage bcHomePage = BCRunner.bcHomePage;
    public BCDefectReporterPage bcDefectReporterPage = BCRunner.bcDefectReporterPage;

    @Given("The employee is on the Defect Reporter Page")
    public void the_employee_is_on_the_defect_reporter_page() {
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=17");
        bcLoginPage.usernameInput.clear();
        bcLoginPage.usernameInput.sendKeys("ryeGuy");
        bcLoginPage.passwordInput.clear();
        bcLoginPage.passwordInput.sendKeys("coolbeans");
        bcLoginPage.loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(bcHomePage.reportDefectLink));
        bcHomePage.reportDefectLink.click();
    }
    @When("The employee selects todays date")
    public void the_employee_selects_todays_date() {
        LocalDate today = java.time.LocalDate.now(); // YYYY-MM-DD
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = today.format(formatter);

        System.out.println("Today is: "+ date);

        bcDefectReporterPage.datePicker.sendKeys(date);
    }
    @When("The employee types in Description with")
    public void the_employee_types_in_description_with(String description) {
        bcDefectReporterPage.description.clear();
        bcDefectReporterPage.description.sendKeys(description);
    }
    @When("The employee types in Steps with")
    public void the_employee_types_in_steps_with(String steps) {
        bcDefectReporterPage.reproduceStep.clear();
        bcDefectReporterPage.reproduceStep.sendKeys(steps);
    }
    @When("The employee selects {string} priority")
    public void the_employee_selects_priority(String priority) {
        Actions action = new Actions(driver);
        action.click(bcDefectReporterPage.priority).build().perform();
        if(priority == "Medium"){
            action.sendKeys(Keys.ARROW_LEFT).build().perform();
        } else if ( priority == "LOW"){
            for(int i=0; i<2;i++){
                action.sendKeys(Keys.ARROW_LEFT).build().perform();
            }
        }
    }
    @When("The employee selects {string} severity")
    public void the_employee_selects_severity(String severity) {
        Actions action = new Actions(driver);
        action.click(bcDefectReporterPage.severity).build().perform();
        if(severity == "Medium"){
            action.sendKeys(Keys.ARROW_LEFT).build().perform();
        } else if ( severity == "LOW"){
            for(int i=0; i<2;i++){
                action.sendKeys(Keys.ARROW_LEFT).build().perform();
            }
        }
    }
    @When("The employee clicks the report button")
    public void the_employee_clicks_the_report_button() {
        bcDefectReporterPage.reportBtn.click();
    }
    @Then("There should be a confirmation box")
    public void there_should_be_a_confirmation_box() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        String alertMsg = driver.switchTo().alert().getText();
        assertEquals("Confirm Bug Report?", alertMsg);

    }
    @When("The employee clicks Ok")
    public void the_employee_clicks_ok() {
        driver.switchTo().alert().accept();
    }
    @Then("A modal should appear with a Defect ID")
    public void a_modal_should_appear_with_a_defect_id() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(bcDefectReporterPage.modal));
        assertNotNull(bcDefectReporterPage.defectCreatedMsg);

    }
    @When("The employee clicks close")
    public void the_employee_clicks_close() {

        bcDefectReporterPage.closeBtn.click();
    }
    @Then("The modal should disappear")
    public void the_modal_should_disappear() {

        boolean disappeared = false;
        try {
            boolean displayed = bcDefectReporterPage.modal.isDisplayed();
        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            disappeared = true;
        } catch (StaleElementReferenceException e){
            System.out.println(e.getMessage());
            disappeared =true;
        }

        assertTrue(disappeared);
    }

}
