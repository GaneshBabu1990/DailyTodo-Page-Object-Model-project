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

//This Testcase is used to remove tasks and verify that it is no longer displayed


public class EditDailyTasksDelete extends BaseTest {
WebDriver driver;	
ExtentReports extentReport;
ExtentSparkReporter sparkReporter;
ExtentTest testCase;

@Test
public void delete_daily_tasks() {
	testCase=extentReport.createTest("Edit tasks to remove tasks and verify");	 	
	CreatePage createpage=new CreatePage(driver);
	ResultPage resultpage=new ResultPage(driver);		
	String sessionName=getSessionName();
	String newUrl="https://dailytodo.org/"+sessionName;
	driver.navigate().to(newUrl);
	resultpage.clickEditLink();
	createpage.verifyeditModeCreatepage();
	
	ArrayList<String> tasks=new ArrayList<String>();    
	tasks.add("Task3");    
	tasks.add("Task4"); 
	
	createpage.DeleteTask(tasks);
	resultpage.verifyResultPage();
	resultpage.verifyDeletedTask(tasks);	
	
	
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
