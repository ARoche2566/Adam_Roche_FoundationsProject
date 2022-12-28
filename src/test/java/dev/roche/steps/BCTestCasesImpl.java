package dev.roche.steps;

import dev.roche.pages.*;
import dev.roche.runners.BCRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

public class BCTestCasesImpl {
    public WebDriver driver = BCRunner.driver;
    public BCLoginPage bcLoginPage = BCRunner.bcLoginPage;
    public BCHomePage bcHomePage = BCRunner.bcHomePage;
    public BCTestCasesPage bcTestCasesPage = BCRunner.bcTestCasesPage;

    public BCCaseEditorPage bcCaseEditorPage = BCRunner.bcCaseEditorPage;

    // For Scenario: Add a test case
    @Given("The tester is on the test case dashboard")
    public void the_tester_is_on_the_test_case_dashboard() {
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=17");
        bcLoginPage.usernameInput.clear();
        bcLoginPage.usernameInput.sendKeys("ryeGuy");
        bcLoginPage.passwordInput.clear();
        bcLoginPage.passwordInput.sendKeys("coolbeans");
        bcLoginPage.loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(bcHomePage.testCasesLink));
        bcHomePage.testCasesLink.click();
    }
    @When("The tester types Description into input with")
    public void the_tester_types_description_into_input_with(String description) throws InterruptedException {
        bcTestCasesPage.description.clear();
        bcTestCasesPage.description.sendKeys(description);
    }
        @When("The tester types Steps into input with")
        public void the_tester_types_steps_into_input_with(String steps) {
            bcTestCasesPage.description.clear();
            bcTestCasesPage.steps.sendKeys(steps);
        }
    @When("The tester presses the submit button")
    public void the_tester_presses_the_submit_button() {
        bcTestCasesPage.submitBtn.click();

    }
    @Then("The test case should appear at the bottom of the table")
    public void the_test_case_should_appear_at_the_bottom_of_the_table() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOf(bcTestCasesPage.bottomDescription)));
        String expectedDescription = "Verify that usernames cannot have illegal characters";
        String actualDescription = bcTestCasesPage.bottomDescription.getText();
        assertEquals(expectedDescription, actualDescription);
    }
    @Then("The test case result should say UNEXECUTED")
    public void the_test_case_result_should_say_unexecuted() {
        assertEquals("UNEXECUTED", bcTestCasesPage.bottomResult.getText());
    }

    // For Scenario: Update a Test Case
    @When("The tester presses on details")
    public void the_tester_presses_on_details() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(bcTestCasesPage.bottomDetailButton));
        bcTestCasesPage.bottomDetailButton.click();

    }
    @Then("A test case modal should appear showing the case ID")
    public void a_test_case_modal_should_appear_showing_the_case_id() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(bcTestCasesPage.modalDialog));

        boolean modalIsPresent = false;
        if(bcTestCasesPage.modalDialog!=null){
            modalIsPresent = true;
        }

        assertTrue(modalIsPresent);
    }
    @Then("The performed by field should say No One")
    public void the_performed_by_field_should_say_no_one() {
        assertEquals("No One", bcTestCasesPage.performedBy.getText());

    }
    @When("The tester presses the close button")
    public void the_tester_presses_the_close_button() {
        bcTestCasesPage.closeBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(bcTestCasesPage.modalDialog));

    }
    @Then("The Modal Should be closed")
    public void the_modal_should_be_closed() {
        boolean modalClosed = false;

        try{
            boolean modalDisplayed= bcTestCasesPage.modalDialog.isDisplayed();
        }catch (NoSuchElementException e){
            e.toString();
            modalClosed = true;
        }

        assertTrue(modalClosed);
    }


    // For Scenario: Edit Existing Case
    @When("The Tester clicks on edit within the modal")
    public void the_tester_clicks_on_edit_within_the_modal() {
        bcTestCasesPage.editBtn.click();
    }
    @Then("The Tester should be on the case editor for that case")
    public void the_tester_should_be_on_the_case_editor_for_that_case() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("caseeditor"));
        String actualTitle = driver.getTitle();
        System.out.println(actualTitle);
        assertEquals("Case Editor", actualTitle);
    }


    @When("The tester clicks on the edit button")
    public void the_tester_clicks_on_the_edit_button() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(bcCaseEditorPage.editBtn));
        bcCaseEditorPage.editBtn.click();
    }
    @When("The tester types in {string} into the description text area")
    public void the_tester_types_in_into_the_description_text_area(String description) {
        bcCaseEditorPage.descriptionBox.clear();
        bcCaseEditorPage.descriptionBox.sendKeys(description);

    }
    @When("The tester types in {string} into the steps text area")
    public void the_tester_types_in_into_the_steps_text_area(String steps) {
        bcCaseEditorPage.stepsBox.clear();
        bcCaseEditorPage.stepsBox.sendKeys(steps);
    }
    @When("The tester clicks on the automated check mark")
    public void the_tester_clicks_on_the_automated_check_mark() {
        bcCaseEditorPage.checkbox.click();
    }
    @When("The tester selects {string} for performed from drop down")
    public void the_tester_selects_for_performed_from_drop_down(String tester) {
        Select dropdown = new Select(bcCaseEditorPage.performBy);
        dropdown.selectByVisibleText(tester);
    }
    @When("The tester selects {string} for test result from drop down")
    public void the_tester_selects_for_test_result_from_drop_down(String result) {
        Select dropdown = new Select(bcCaseEditorPage.testResult);
        dropdown.selectByVisibleText(result);
    }
    @When("The tester types in {string} into the summary text area")
    public void the_tester_types_in_into_the_summary_text_area(String summary) {
        bcCaseEditorPage.summary.sendKeys(summary);
    }
    @When("The tester clicks save on test case page")
    public void the_tester_clicks_save_on_test_case_page() {
        bcCaseEditorPage.saveBtn.click();
    }
    @Then("A confirmation prompt should appear")
    public void a_confirmation_prompt_should_appear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        String alertMsg = driver.switchTo().alert().getText();
        String expectedMsg = "Are you sure you want to update the test case?";
        assertEquals(expectedMsg, alertMsg);
    }
    @When("The tester clicks on Ok")
    public void the_tester_clicks_on_ok() {
        driver.switchTo().alert().accept();
    }
    @Then("An alert says the test case has been saved")
    public void an_alert_says_the_test_case_has_been_saved() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        String alertMsg = driver.switchTo().alert().getText();
        String expectedMsg = "Test Case has been Saved";
        assertEquals(expectedMsg, alertMsg);
        driver.switchTo().alert().accept();
    }

    // For Scenario: Reset Test Case
    @When("The tester clicks on the reset button")
    public void the_tester_clicks_on_the_reset_button() {
        bcCaseEditorPage.resetBtn.click();
    }
    @Then("The fields should be populated to their old values")
    public void the_fields_should_be_populated_to_their_old_values() {
        WebElement resetResult = driver.findElement(By.xpath("//fieldset[2]/p"));
        String resetResultText = resetResult.getText();
        assertEquals("FAIL", resetResultText);
    }
}

