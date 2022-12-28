package dev.roche.steps;

import dev.roche.pages.BCHomePage;
import dev.roche.pages.BCLoginPage;
import dev.roche.pages.BCMatrixPage;
import dev.roche.runners.BCRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BCMatrixImpl {

    public WebDriver driver = BCRunner.driver;
    public BCLoginPage bcLoginPage = BCRunner.bcLoginPage;
    public BCHomePage bcHomePage = BCRunner.bcHomePage;
    public BCMatrixPage bcMatrixPage = BCRunner.bcMatrixPage;
    // For Scenario: Create a New Matrix
    @When("The manager chooses to create a new matrix")
    public void the_manager_chooses_to_create_a_new_matrix() {
        bcHomePage.newMatrixBtn.click();
    }

    @When("The manager creates a title for the matrix")
    public void the_manager_creates_a_title_for_the_matrix() {
        bcHomePage.matrixTitleField.sendKeys("Test Title");
    }

    @When("The manager adds requirements to the matrix")
    public void the_manager_adds_requirements_to_the_matrix() {
        bcHomePage.userStoryField.sendKeys("Test Rule");
        bcHomePage.addRequirementBtn.click();
    }

    @When("The manager saves the matrix")
    public void the_manager_saves_the_matrix() {
        bcHomePage.createMatrixBtn.click();
    }

    @Then("An alert with a success message should appear")
    public void an_alert_with_a_success_message_should_appear() {
        new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(ElementNotInteractableException.class)
                .until(ExpectedConditions.alertIsPresent());

        String alertMsg = driver.switchTo().alert().getText();
        System.out.println(alertMsg);
        driver.switchTo().alert().accept();
        assertTrue(alertMsg.contains("Matrix with ID "));
    }

    // For scenario: Update defects
    @Given("The manager is on the matrix homepage")
    public void the_manager_is_on_the_matrix_homepage() {
        bcHomePage.matricesLink.click();
        System.out.println(driver.getTitle());
    }

    @Given("The manager has selected the matrix")
    public void the_manager_has_selected_the_matrix() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(bcMatrixPage.showAirlineMatrixBtn));
        bcMatrixPage.showAirlineMatrixBtn.click();

    }

    @When("The manager adds a defect")
    public void the_manager_adds_a_defect() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(bcMatrixPage.editAirlineFirstRuleBtn));
        bcMatrixPage.editAirlineFirstRuleBtn.click();
        bcMatrixPage.defectIDField.sendKeys("901");
        bcMatrixPage.addDefectBtn.click();
    }

    @When("The manager confirms their changes")
    public void the_manager_confirms_their_changes() {
        bcMatrixPage.saveBtn.click();
    }

    @Then("Then the matrix should saved")
    public void then_the_matrix_should_saved() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        String actualAlert = driver.switchTo().alert().getText();

        assertEquals("Matrix Saved", actualAlert);
        driver.switchTo().alert().accept();
    }


    // For scenario: Update test cases
    @When("The manager adds a Test Cases")
    public void the_manager_adds_a_test_cases() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(bcMatrixPage.editAirlineFirstRuleBtn));
        bcMatrixPage.editAirlineFirstRuleBtn.click();
        bcMatrixPage.testCaseIDField.sendKeys("801");
        bcMatrixPage.addTestCaseBtn.click();
    }

}
