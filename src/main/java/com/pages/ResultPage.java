package com.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ResultPage {
   private WebDriver driver;
	
	//1. by.locators
	private By buttonEdit     = By.linkText("Edit");
	private By labelTasks=By.xpath("//table[@id='tasktable']/tbody/tr/td[1]");
    private By before  =By.xpath("//table[@id='tasktable']/tbody[1]/tr[1]/td[2]/input");
    private By after  =By.xpath("//table[@id='tasktable']/tbody[1]/tr[1]/td[2]/b");
    
    
	
	
	//2. constructor of the page class
	public ResultPage (WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void verifyResultPage()
	{
		boolean actual=driver.findElement(buttonEdit).isDisplayed();
		Assert.assertTrue(actual);
				
	}
	
	
	public void verifyAddedTask(List<String> tasks)
	{
		
		ArrayList<WebElement> taskselements = (ArrayList<WebElement>) driver.findElements(labelTasks);	
		
		System.out.println(taskselements.size());
		
		ArrayList<String> tasknames = new ArrayList<String>(); 	
		
		for(int j=0;j<taskselements.size();j++)
		{
			tasknames.add(taskselements.get(j).getText());		
		}		
		
		
		for(int i=0;i<tasks.size();i++)
		{
			boolean result=tasknames.contains(tasks.get(i));
			Assert.assertTrue(result);
		}
				
	}
	
	public void verifyDeletedTask(List<String> tasks)
	{
		
		ArrayList<WebElement> taskselements = (ArrayList<WebElement>) driver.findElements(labelTasks);	
		
		System.out.println(taskselements.size());
		
		ArrayList<String> tasknames = new ArrayList<String>(); 	
		
		for(int j=0;j<taskselements.size();j++)
		{
			tasknames.add(taskselements.get(j).getText());		
		}		
		
		
		for(int i=0;i<tasks.size();i++)
		{
			boolean result=tasknames.contains(tasks.get(i));
			Assert.assertFalse(result);
		}
				
	}
	public void clickEditLink() {
		
		driver.findElement(buttonEdit).click();
		
		
	}
	
	public void VerifyTaskStatusToggle() throws InterruptedException {
		
		WebElement checkbox = driver.findElement(before);
		WebElement button = driver.findElement(after);
		checkbox.click();
		String attribute1 = driver.findElement(before).getAttribute("style");
		System.out.println(attribute1);
		Assert.assertEquals(attribute1, "display: none;");
		button.click();
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.attributeContains(before, "style", "display: inline-block;"));
		String attribute2 = driver.findElement(before).getAttribute("style");
		Assert.assertEquals(attribute2, "display: inline-block;");
		
		
	}
	

}
