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

//This Test case is to verify that we are able to add new tasks through edit link.

public class EditDailyTasksadd extends BaseTest {
	WebDriver driver;

	ExtentTest testCase;

	@Test
	public void add_daily_tasks_through_edit() throws InterruptedException {
		testCase = extentReport.createTest("To verify Edit the tasks to add additional tasks");
		CreatePage createpage = new CreatePage(driver);
		ResultPage resultpage = new ResultPage(driver);
		testCase.log(Status.INFO, "Navigated to https://dailytodo.org successfully");
		String sessionName = getSessionName();
		String newUrl = "https://dailytodo.org/" + sessionName;
		driver.navigate().to(newUrl);
		testCase.log(Status.INFO, "Successfully Navigated to the" + newUrl);
		resultpage.clickEditLink();
		testCase.log(Status.INFO, "Clicked the Edit link successfully");
		createpage.verifyeditModeCreatepage();
		testCase.log(Status.INFO, "Successfully verified the edit page");

		ArrayList<String> tasks = new ArrayList<String>();
		tasks.add("Task3");
		tasks.add("Task4");

		createpage.AddTask(tasks);
		testCase.log(Status.INFO, "Added tasks successfully and clicked submit");
		resultpage.verifyResultPage();
		testCase.log(Status.INFO, "Verified the Result page that it should have edit button");
		resultpage.verifyAddedTask(tasks);
		testCase.log(Status.INFO, "Verified added tasks are displayed correctly");
		
		testCase.pass("Adding tasks through edit functionality works as expected");

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
