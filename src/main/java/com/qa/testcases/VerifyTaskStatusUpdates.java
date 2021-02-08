package com.qa.testcases;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pages.ResultPage;
import com.qa.factory.DriverFactory;
import com.qa.util.BaseTest;

//This test case is to test the task check box functionality
public class VerifyTaskStatusUpdates extends BaseTest {

	WebDriver driver;

	ExtentTest testCase;

	@Test
	public void VerifyTasksStatus() throws FileNotFoundException, IOException, InterruptedException {
		testCase = extentReport.createTest("To verify we are able to mark the checkbox for task is finished for today");
		ResultPage resultpage = new ResultPage(driver);
		String sessionName = getSessionName();
		String newUrl = "https://dailytodo.org/" + sessionName;
		driver.navigate().to(newUrl);
		testCase.log(Status.INFO, "Successfully navigated to the "+ newUrl);
		resultpage.VerifyTaskStatusToggle();
		testCase.log(Status.INFO, "Successfully verified the daily status checkbox functionality");
		
		testCase.pass("Checkbox functionality is working as expected");

	}

	@BeforeMethod
	public void before() {

		startApp();
		driver = DriverFactory.getDriver();
		

	}

	@AfterMethod
	public void after() {
		driver.close();
		testCase.log(Status.INFO, "Browser closed successfully");
		extentReport.flush();

	}

}
