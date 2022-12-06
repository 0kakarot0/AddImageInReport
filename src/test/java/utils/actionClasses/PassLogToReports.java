package utils.actionClasses;

import base.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import tests.LoginTest;

import java.io.IOException;

public class PassLogToReports extends LoginTest {
    WebDriver driver;
    ExtentReports reports;
    ExtentTest test;
    public PassLogToReports(WebDriver driver) {
        this.driver = driver;
    }


    public void createTestInReport(String testName, String testDescription){
        extentReport.createTest(testName, testDescription);
    }
    public void logTestStepResult(String stepDefinition, String testStepResult) throws IOException {
        String resultString = new String();
        if (testStepResult.equalsIgnoreCase("Pass")){
            resultString = stepDefinition + " is passed ";
            extentReport.logTestPassSteps(resultString);
        }
        else if (testStepResult.contains("Fail")){
            String trimString = testStepResult.replaceAll("Fail.*", "");
            resultString = trimString;
            extentReport.logTestFailedSteps(stepDefinition,resultString);
        }
    }


}
