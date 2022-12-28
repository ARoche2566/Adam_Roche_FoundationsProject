package dev.roche.steps;

import dev.roche.pages.*;
import dev.roche.runners.BCRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BCDefectStatusImpl {

    public WebDriver driver = BCRunner.driver;
    public BCLoginPage bcLoginPage = BCRunner.bcLoginPage;
    public BCHomePage bcHomePage = BCRunner.bcHomePage;

    @Given("The tester is on the Home Page")
    public void the_tester_is_on_the_home_page() {
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=17");
        bcLoginPage.usernameInput.clear();
        bcLoginPage.usernameInput.sendKeys("ryeGuy");
        bcLoginPage.passwordInput.clear();
        bcLoginPage.passwordInput.sendKeys("coolbeans");
        bcLoginPage.loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(bcHomePage.welcomeMsg));
        String pageTitle = driver.getTitle();
        System.out.println("Tester is now on :" + pageTitle);
    }

    @Then("The tester can can see only defects assigned to them")
    public void the_tester_can_can_see_only_defects_assigned_to_them() {
        // check if all defects are assigned to ryeGuy
        int totalDefects = bcHomePage.allDefectList.size();

        for (int i = 1; i <= totalDefects; i++) {
            WebElement li = driver.findElement(By.xpath("//*[@id=\"root\"]/ul/li[" + i + "]"));
            li.click();
        }

        List<WebElement> totalAssignedList = driver.findElements(By.xpath("//*[text()='ryeGuy']"));
        int totalAssigned = totalAssignedList.size();
        assertEquals(totalDefects, totalAssigned);
    }

    @When("The tester changes to defect to any status")
    public void the_tester_changes_to_defect_to_any_status() {
        // click on defect status

        // click change status
        bcHomePage.changeStatusBtn.click();

        // check original status?
        String ogStatus = bcHomePage.originalStatus.getText();

        System.out.println(ogStatus);
        // click on any status other than original ones
    }

    @Then("The tester should see the defect has a different status")
    public void the_tester_should_see_the_defect_has_a_different_status() {
        System.out.println("To Be Implemented");
    }
}
