package dev.roche.steps;

import dev.roche.runners.BCRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static dev.roche.runners.BCRunner.bcDefectReporterPage;
import static dev.roche.runners.BCRunner.bcLoginPage;
import static org.junit.Assert.assertTrue;
public class BCDefectReportNegativeImpl {
    public WebDriver driver = BCRunner.driver;

    @When("The employee selects high priority")
    public void the_employee_selects_high_priority() {
        bcDefectReporterPage.prioritySlider.sendKeys(Keys.ARROW_RIGHT);
    }
    @When("The employee selects low severity")
    public void the_employee_selects_low_severity() {
        bcDefectReporterPage.severitySlider.sendKeys(Keys.ARROW_LEFT);
    }
    @Then("No confirmation dialog appears")
    public void no_confirmation_dialog_appears() {
        boolean noDialog = false;
        try{
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        }catch  (TimeoutException e){
            noDialog = true;
            System.out.println(e.getMessage());
        }
        assertTrue(noDialog);
    }
}
