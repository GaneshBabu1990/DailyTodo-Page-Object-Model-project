package com.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CreatePage {
private WebDriver driver;
	
	//1. by.locators
	private By textBoxAddTask = By.xpath("//*[@name='tasks']");
	private By buttonSubmit  = By.className("big");
	private By buttonCancel  = By.className("red");
	private By PageHeader = By.xpath("//input[@value=\"Tasks for today\"]");
	
	
	//2. constructor of the page class
	public CreatePage (WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void verifyCreatePage() 
	{
		   String actual=driver.getCurrentUrl();
		   String expected="https://dailytodo.org/create";
		   Assert.assertEquals(actual,expected);
	}
	
	
	public void AddTask(ArrayList<String> tasks) 
	{
		String currentTask=driver.findElement(textBoxAddTask).getText();
		System.out.println(currentTask);
		driver.findElement(textBoxAddTask).clear();
		 for(String task:tasks) 
		 {
			 if(currentTask.equalsIgnoreCase(""))
			 currentTask=task;
			 else
			 currentTask=currentTask+"\n"+task;
			 
		 }		 
		 driver.findElement(textBoxAddTask).sendKeys(currentTask);
		 driver.findElement(buttonSubmit).click();
		 
			  
	}
	
	public void DeleteTask(ArrayList<String> tasks) 
	{
		String currentTask=driver.findElement(textBoxAddTask).getText();
		System.out.println(currentTask);
		driver.findElement(textBoxAddTask).clear();
		 for(String task:tasks) 
		 {
			 if(currentTask.contains(task)) {
				 currentTask = currentTask.replace(task, "");
			}
			 
			 
		 }		 
		 driver.findElement(textBoxAddTask).sendKeys(currentTask);
		 driver.findElement(buttonSubmit).click();
		 
			  
	}
	
	public void verifyeditModeCreatepage() 
	{
		 boolean displayed = driver.findElement(PageHeader).isDisplayed();
		 Assert.assertTrue(displayed);
	}
	
	
	
}
