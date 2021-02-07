package com.qa.testcases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.pages.CreatePage;
import com.pages.HomePage;
import com.pages.ResultPage;
import com.qa.factory.DriverFactory;
import com.qa.util.BaseTest;

//This testcase is to create new tasks through Create your daily Todo list button.

public class CreateDailyTasks extends BaseTest{
	
	WebDriver driver;
	ExtentReports extentReport;
	ExtentSparkReporter sparkReporter;
	ExtentTest testCase;
		
	@Test
	public void enter_daily_tasks() throws FileNotFoundException, IOException {
		 testCase=extentReport.createTest("CreateDailyTasks first time");
		
		
		HomePage homepage = new HomePage(driver);
		CreatePage createpage=new CreatePage(driver);
		ResultPage resultpage=new ResultPage(driver);
		String actualTitle=homepage.getHomePageTitle();
		String expectedTitle="Daily Todo";
		if(actualTitle.contains(expectedTitle))
			Assert.assertTrue(true,"Titel is as expected");
		else
			Assert.assertTrue(true,"Titel is not as expected");
		
		homepage.clickCreateYourDailyToDOList();
		createpage.verifyCreatePage();
		
		ArrayList<String> tasks=new ArrayList<String>();    
		tasks.add("Task1");    
		tasks.add("Task2"); 
		
		createpage.AddTask(tasks);
		resultpage.verifyResultPage();
		resultpage.verifyAddedTask(tasks);
		setSessionName();
		
		
	}
	

	
	@BeforeMethod
	public void before() {
		extentReport = new ExtentReports();
		sparkReporter = new ExtentSparkReporter("Spark.html");
		extentReport.attachReporter(sparkReporter);
		startApp();	
		driver=DriverFactory.getDriver();	
		
	}
	
	@AfterMethod
	public void after() {
		driver.close();
		
		
	}
	
	
	

}
