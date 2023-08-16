package testScenarios;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.*;

import businessComponents.FunctionalComponents;
import businessComponents.FunctionalComponentsMobile;

public class WebTestCases extends FunctionalComponents {

	FunctionalComponents fc = new FunctionalComponents();
	FunctionalComponentsMobile fcM = new FunctionalComponentsMobile();
	
	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter(
			"/Users/I532546/git/TestAutothonFramework/TestAutothonFramework/src/test/resources/HTMLReports/ExtentReport1.html");

	@BeforeTest
	public void reportConfiguration() {
		extent.attachReporter(spark);

	}

	@Test
	public void TC01_Youtube_Video_Quality_Change() throws InterruptedException {

		ExtentTest test = extent.createTest("Youtube Video Quality Change");
		try {
			fc.launchApplication("https://www.youtube.com/watch?v=_pG4QLtuRYw");
			fc.gotoYoutubeVideoSettings();
			fc.changeVideoQuality("144p");
			test.log(Status.PASS, "Quality Change Successful.");
		} catch (Exception e) {

			test.log(Status.FAIL, "The Test Case is failed. ");
			test.log(Status.FAIL, "The Failure Reason: "+e.toString());
		}

	}

	
	
	@AfterTest
	public void closeTest() {
		try {
			fc.closeBrowser();
		}catch (Exception e) {
		}
		try {
			fcM.closeBrowser();
		}catch (Exception e) {
		}
		extent.flush();

	}

}