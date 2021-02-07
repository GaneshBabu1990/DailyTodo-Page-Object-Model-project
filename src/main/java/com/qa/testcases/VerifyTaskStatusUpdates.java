package com.qa.testcases;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.pages.ResultPage;
import com.qa.factory.DriverFactory;
import com.qa.util.BaseTest;

//This testcase is to test the task checkbox functionality
public class VerifyTaskStatusUpdates extends BaseTest {

	WebDriver driver;
	ExtentReports extentReport;
	ExtentSparkReporter sparkReporter;
	ExtentTest testCase;

	@Test
	public void VerifyTasksStatus() throws FileNotFoundException, IOException, InterruptedException {
		testCase=extentReport.createTest("To check the daily task checkbox functionality");
		ResultPage resultpage = new ResultPage(driver);
		String sessionName = getSessionName();
		String newUrl = "https://dailytodo.org/" + sessionName;
		driver.navigate().to(newUrl);
		resultpage.VerifyTaskStatusToggle();

	}

	@BeforeMethod
	public void before() {
		extentReport = new ExtentReports();
		sparkReporter = new ExtentSparkReporter("Spark.html");
		extentReport.attachReporter(sparkReporter);
		startApp();
		driver = DriverFactory.getDriver();

	}

	@AfterMethod
	public void after() {
		driver.close();
		extentReport.flush();

	}

}
