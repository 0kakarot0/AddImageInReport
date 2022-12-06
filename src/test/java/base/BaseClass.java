package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.actionClasses.PassLogToReports;
import utils.extentReportBuilder.MakeExtentReport;

import java.time.Duration;

public class BaseClass {
    public WebDriver driver;
    protected MakeExtentReport extentReport;
    protected LoginPage loginPage;
    protected PassLogToReports logToReports;

    /*Global Variable*/
    public String step;
    public String getReturnString;

    @BeforeSuite
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();


        extentReport = new MakeExtentReport(driver);

        loginPage = new LoginPage(driver);
        extentReport.createReportFile();
        logToReports = new PassLogToReports(driver);

        driver.get("https://www.instagram.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


    }

    @AfterSuite
    public void tearDownDriver() {
        extentReport.createNewReport();
        driver.quit();
    }
}
