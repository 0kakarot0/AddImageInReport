package pages;

import helperClasses.SeleniumMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Method;

public class LoginPage {

    private WebDriver driver;
    protected SeleniumMethods seleniumMethods;

    private By email = By.name("usernam");
    private By password = By.name("password");
    private By loginBtn = By.xpath("//*[@id=\"loginForm\"]/div[1]/div[3]/button");
    private By errorMessage = By.id("slfErrorAlert");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        seleniumMethods = new SeleniumMethods(driver);
    }


    public String setEmail(String emailString) {

        String methodName = new Object(){} .getClass().getEnclosingMethod().getName();

        By parentLocator = By.xpath("//*[@id=\"loginForm\"]/div/div[1]");
        String stepResult = seleniumMethods.sendKey(emailString, email, parentLocator, methodName);
        return stepResult;
    }

    public String setPassword(String passString) {
        String methodName = new Object(){} .getClass().getEnclosingMethod().getName();
        By parentElement = By.xpath("//*[@id=\"loginForm\"]/div/div[2]");
        String stepResult = seleniumMethods.sendKey(passString,password,parentElement,methodName);
        return stepResult;
    }

    public String clickLoginButton() {
        String methodName = new Object(){} .getClass().getEnclosingMethod().getName();
        By parentElement = By.xpath("//*[@id=\"loginForm\"]/div/div[3]");
        String stepResult = seleniumMethods.click(loginBtn,parentElement,methodName);
        return stepResult;
    }

    private String passMethodName() {
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        return methodName;
    }
}
