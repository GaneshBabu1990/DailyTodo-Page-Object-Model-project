package com.qa.testcases;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pages.CreatePage;
import com.pages.ResultPage;
import com.qa.factory.DriverFactory;
import com.qa.util.BaseTest;

//This Test case is used to remove tasks and verify that it is no longer displayed

public class EditDailyTasksDelete extends BaseTest {
	WebDriver driver;

	ExtentTest testCase;

	@Test
	public void delete_daily_tasks() {
		testCase = extentReport.createTest("To verify Editing the tasks to remove existing tasks");
		CreatePage createpage = new CreatePage(driver);
		ResultPage resultpage = new ResultPage(driver);
		String sessionName = getSessionName();
		String newUrl = "https://dailytodo.org/" + sessionName;
		driver.navigate().to(newUrl);
		testCase.log(Status.INFO, "Successfully navigated to the "+ newUrl);
		resultpage.clickEditLink();
		testCase.log(Status.INFO, "clicked Edit button successfully");
		createpage.verifyeditModeCreatepage();
		testCase.log(Status.INFO, "successfully verified the edit page");

		ArrayList<String> tasks = new ArrayList<String>();
		tasks.add("Task3");
		tasks.add("Task4");

		createpage.DeleteTask(tasks);
		testCase.log(Status.INFO, "Successfully deleted the tasks");
		resultpage.verifyResultPage();
		testCase.log(Status.INFO, "Successfully verified the resultpage");
		resultpage.verifyDeletedTask(tasks);
		
		testCase.log(Status.INFO, "Successfully verified the resultpage that deleted tasks are not displayed");
		
		testCase.pass("Deleting tasks through edit funcionality works expected");

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
