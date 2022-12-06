package utils.extentReportBuilder;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

public class MakeExtentReport {
    private WebDriver driver;

    private  ExtentSparkReporter sparkReporter;
    private  ExtentReports extentReporter;
    private ExtentTest extentTest;
    JavascriptExecutor javascriptExecutor;
     Date date;



    public MakeExtentReport(WebDriver driver) {
        this.driver = driver;
    }

    public  void createReportFile() {
        // Date object
        date = new Date();

        // Create Unique File name everyTime report is created
        String fileNameToBeSaved = "Spark" + date.getTime() + ".html";

        // File path where file is gonna save with new name
        String  pathToSaveFile = "testReports/" + fileNameToBeSaved;

        //passing file path to Spark reporter
        sparkReporter = new ExtentSparkReporter(pathToSaveFile);

        //Initializing the extentReports object and then attaching the spark report
        extentReporter = new ExtentReports();
        extentReporter.attachReporter(sparkReporter);
    }

    /*Create Test in the extent Report*/
    public void createTest(String testName, String testDescription) {
        extentTest = extentReporter.createTest(testName, testDescription);
    }

    /*Log any info Related to the test in the Extent Report*/
    public void logTestInfo(String testInfoString) {
        extentTest.log(Status.INFO, testInfoString);
    }

    /*Log the info related to the passed Steps in the Extent Report*/
    public void logTestPassSteps(String testSteps) {
        extentTest.pass(testSteps);
    }

    /*Log info Related to Failed steps in the Extent Report*/
    public void logTestFailedSteps(String failedTestStepName, String stepMethodName) throws IOException {
        extentTest.fail(failedTestStepName + " is failed");
        logScreenShotOfFailedStep(stepMethodName);
    }

    /*Log screenshot in the report in the Extent Report*/
    public void logScreenShotOfFailedStep(String imageFileName) throws IOException {
        String encodedString = getBase64Screenshot(imageFileName);
        extentTest.log(Status.FAIL, imageFileName,MediaEntityBuilder.createScreenCaptureFromBase64String(encodedString).build());
    }

    //Convert the Image into Base64
    private static String getBase64Screenshot(String imageFileName) throws IOException {
        File fileName =  new File("C:\\Users\\PC\\IdeaProjects\\FullWebTestingFrameWork\\src\\test\\resources\\screenShots\\"+imageFileName+".png");
        String encodedBase64 = null;

        FileInputStream fileInputStream = null;
        try {
            fileInputStream =new FileInputStream(fileName);
            byte[] bytes =new byte[(int)fileName.length()];
            fileInputStream.read(bytes);
            encodedBase64 = new String(Base64.getEncoder().encode(bytes),  "UTF-8");
            fileInputStream.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } //"data:image/png;base64,"
        return encodedBase64;
    }

    //Clear Previous report data and insert data into new file created
    public void createNewReport() {
        //Flush method is used to erase any previous data on the report and create a new report
        extentReporter.flush();
    }
}
