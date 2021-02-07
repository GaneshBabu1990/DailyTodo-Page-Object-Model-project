package com.qa.testcases;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.pages.CreatePage;
import com.pages.ResultPage;
import com.qa.factory.DriverFactory;
import com.qa.util.BaseTest;

//This Testcase is to verify that we are able to add new tasks through edit link.

public class EditDailyTasksadd extends BaseTest {
	WebDriver driver;
	ExtentReports extentReport;
	ExtentSparkReporter sparkReporter;
	ExtentTest testCase;

	@Test
	public void add_daily_tasks_through_edit() throws InterruptedException {
		testCase=extentReport.createTest("Edit Daily tasks to add tasks");
		CreatePage createpage = new CreatePage(driver);
		ResultPage resultpage = new ResultPage(driver);
		String sessionName = getSessionName();
		String newUrl = "https://dailytodo.org/" + sessionName;
		driver.navigate().to(newUrl);
		resultpage.clickEditLink();
		createpage.verifyeditModeCreatepage();

		ArrayList<String> tasks = new ArrayList<String>();
		tasks.add("Task3");
		tasks.add("Task4");

		createpage.AddTask(tasks);
		resultpage.verifyResultPage();
		resultpage.verifyAddedTask(tasks);
		

	}

	@BeforeMethod
	public void before() {
		extentReport = new ExtentReports();
		sparkReporter = new ExtentSparkReporter("Spark.html");
		extentReport.attachReporter(sparkReporter);
		startApp();
		driver = DriverFactory.getDriver();

	}

	
	  @AfterMethod public void after() { 
		  
	driver.close();
	
	  
	  }
	 

}
