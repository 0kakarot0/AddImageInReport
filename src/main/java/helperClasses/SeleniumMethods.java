package helperClasses;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Date;

public class SeleniumMethods {
    private WebDriver driver;
    JavascriptExecutor javascriptExecutor;
    Date date;

    public SeleniumMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void returnWebElement(WebElement element) {
        System.out.println("");
    }

    public String click(By locatorString, By parentLocator,String methodName){
        String stepResult = new String();
        try {
            WebElement element = driver.findElement(locatorString);
           // waitForWebElementVisibility(element);
            if (element.isDisplayed()) {
                element.click();
                return stepResult = "Pass";
            }
        } catch (Exception e) {
            WebElement parentElement = driver.findElement(parentLocator);
            waitForWebElementVisibility(parentElement);
            getFailedElementScreenSHot(parentElement, methodName);
            return stepResult = methodName+"Fail";
        }
        return stepResult;
    }

    public String sendKey(String stringToPass, By locatorString, By parentLocator, String methodName) {
        String stepResult = new String();
        try {
            WebElement element = driver.findElement(locatorString);
            waitForWebElementVisibility(element);
            if (element.isDisplayed()) {
                element.sendKeys(stringToPass);
                return stepResult = "Pass";
            }
        } catch (Exception e) {
            WebElement parentElement = driver.findElement(parentLocator);
            waitForWebElementVisibility(parentElement);
            getFailedElementScreenSHot(parentElement,methodName);
            return stepResult = methodName+"Fail";
        }
        return stepResult;
    }

    public void getFailedElementScreenSHot(WebElement element, String methodName) {
        date = new Date();
        String fileName = methodName + ".png";
        String pathToSaveFile = "src/test/resources/screenShots/" + fileName;

        if (element.isDisplayed()) {
            javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
            var getScreenShot = (TakesScreenshot) driver;
            File screenShot = getScreenShot.getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenShot, new File(pathToSaveFile));
            } catch (Exception exception) {
                exception.getCause();
                exception.getStackTrace();
                exception.getMessage();
            }
        }
    }

    public void waitForWebElementVisibility(WebElement element){
//        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        webDriverWait.until(ExpectedConditions.visibilityOf(element));
//        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
