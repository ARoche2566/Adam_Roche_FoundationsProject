package dev.roche.runners;

import dev.roche.pages.*;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/dev/roche/features", glue = "dev.roche.steps")
public class BCRunner {

    // Starts the execution of our tests
    // Need an instance of our WebDriver
    public static WebDriver driver;
    public static BCLoginPage bcLoginPage;
    public static BCHomePage bcHomePage;
    public static BCMatrixPage bcMatrixPage;
    public static BCTestCasesPage bcTestCasesPage;
    public static BCCaseEditorPage bcCaseEditorPage;
    public static BCDefectReporterPage bcDefectReporterPage;
    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        bcLoginPage = new BCLoginPage(driver);
        bcHomePage = new BCHomePage(driver);
        bcMatrixPage = new BCMatrixPage(driver);
        bcTestCasesPage = new BCTestCasesPage(driver);
        bcCaseEditorPage = new BCCaseEditorPage(driver);
        bcDefectReporterPage = new BCDefectReporterPage(driver);
    }

    @AfterClass
    public static void teardown() {
        driver.quit();
    }
}


