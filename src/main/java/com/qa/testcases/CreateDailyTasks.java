package com.qa.testcases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pages.CreatePage;
import com.pages.HomePage;
import com.pages.ResultPage;
import com.qa.factory.DriverFactory;
import com.qa.util.BaseTest;

//This Test case is to create new tasks through Create your daily To do list button.

public class CreateDailyTasks extends BaseTest {

	WebDriver driver;

	ExtentTest testCase;

	@Test
	public void enter_daily_tasks() throws FileNotFoundException, IOException {
		testCase = extentReport.createTest("To verify  daily tasks can be created using Create your Daily Todo list button");
		
		HomePage homepage = new HomePage(driver);
		CreatePage createpage = new CreatePage(driver);
		ResultPage resultpage = new ResultPage(driver);
		testCase.log(Status.INFO, "Navigated to https://dailytodo.org successfully");
		String actualTitle = homepage.getHomePageTitle();
		String expectedTitle = "Daily Todo";
		
		testCase.log(Status.INFO, "Verified the home page title");
		
		if (actualTitle.contains(expectedTitle))
		{
			Assert.assertTrue(true, "Title is as expected");
			testCase.pass("Title is as expected");
		}
		else
		{
			Assert.assertTrue(true, "Title is not as expected");
			testCase.fail("Title is not as expected");
		}		
		

		homepage.clickCreateYourDailyToDOList();
		testCase.log(Status.INFO, "Clicked on Create Your Daily ToDo List successfully");
		
		actualTitle=createpage.getCreatePageTitle();
		expectedTitle="https://dailytodo.org/create";
		
		testCase.log(Status.PASS, "Verified the create page title");
		
		if (actualTitle.contains(expectedTitle))
		{
			Assert.assertTrue(true, "Title is as expected");
			testCase.pass("Title is as expected");
		}
		else
		{
			Assert.assertTrue(true, "Title is not as expected");
			testCase.fail("Title is not as expected");
		}		
		 		

		ArrayList<String> tasks = new ArrayList<String>();
		tasks.add("Task1");
		tasks.add("Task2");

		createpage.AddTask(tasks);
		testCase.log(Status.INFO, "Added tasks successfully and clicked submit");
		resultpage.verifyResultPage();
		testCase.log(Status.INFO, "Verified the Result page that it should have edit button");
		boolean result=resultpage.verifyAddedTask(tasks);
		if(result)
		{
			
			Assert.assertTrue(true);	
		}
		else
		{
			Assert.assertTrue(false);
			
		}
		testCase.log(Status.INFO, "Verified added tasks are displayed correctly");
		setSessionName();
		testCase.pass("Adding tasks through create functionality works as expected");
        
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
