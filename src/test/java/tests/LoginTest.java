package tests;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.IOException;
import java.lang.reflect.Method;

public class LoginTest extends BaseClass {


    @Test(description = "Login into Instagram website")
    public void LoginInWebSite() throws IOException {
        createTestInReport("LoginInWebSite","Login into Instagram website");
        step = "Entering the Email";
        getReturnString = loginPage.setEmail("abc@mail.com");
        logTestStepResult(step, getReturnString);

        step= "Enter the Password";
        getReturnString = loginPage.setPassword("123123");
        logTestStepResult(step, getReturnString);

        step= "Click on the Login button";
        getReturnString = loginPage.clickLoginButton();
        logTestStepResult(step, getReturnString);
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
