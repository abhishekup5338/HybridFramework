package com.vTiger.utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class CommanActions {
	
	private WebDriver driver;
	public WebDriverWait wait;
	private ExtentTest logger;
	
	//When we want to extends the class we have to remove the private WebDriver from any one class.
	
	public CommanActions(WebDriver driver, ExtentTest logger)
	{
		this.driver = driver;
		this.logger = logger;
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}

	
	public void type(WebElement elm ,String value,String msg)
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(elm));
			elm.clear();
			elm.sendKeys(value);
			logger.pass(msg+"   <a href+"+getscreenshot()+"><span class='label start-time'>Screenshot</span></a>"); //This code for screenshot.
		}
		catch(Exception e)
		{
			System.out.println("Exeption="+e.getMessage());
			logger.fail("Step failed due to exception:"+e.getMessage()+"   <a href+"+getscreenshot()+"><span class='label start-time'>Screenshot</span></a>");
		}
	}
	
	
	
	
	
	
	public void click(WebElement elm,String msg)
	{
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(elm));
			elm.click();
			logger.pass(msg);
		}
		catch(Exception e)
		{
			System.out.println("Exeption="+e.getMessage());
			logger.fail("Step failed due to exception:"+e.getMessage());
		}
	}
	
	
	
	
	public void SelectText(WebElement elm ,String value,String msg)
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(elm));
			Select s = new Select(elm);
			s.selectByVisibleText(value);
			logger.pass(msg);
			
		}
		catch(Exception e)
		{
			System.out.println("Exeption="+e.getMessage());
			logger.fail("Step failed due to exception:"+e.getMessage());
		}
	}
	
	
	public void SelectValue(WebElement elm ,String value,String msg)
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(elm));
			Select s = new Select(elm);
			s.selectByValue(value);
			logger.pass(msg);
			
		}
		catch(Exception e)
		{
			System.out.println("Exeption="+e.getMessage());
			logger.fail("Step failed due to exception:"+e.getMessage());
		}
	}
	
	
	public void SelectIndex(WebElement elm ,String value,String msg)
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(elm));
			Select s = new Select(elm);
			s.selectByIndex(1);
			logger.pass(msg);

			
		}
		catch(Exception e)
		{
			System.out.println("Exeption="+e.getMessage());
			logger.fail("Step failed due to exception:"+e.getMessage());
		}
	}
	
	
	public  String getscreenshot()
	{
		DateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
		Date d = new Date();
		String str = f.format(d);
		TakesScreenshot scrShot = ((TakesScreenshot)driver); 
		//Call get ScreenshotAs method to create image file
		File scrFile = scrShot.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/src/test/java/com/vtiger/reports//screenshots/image"+str+".png";
		//Move image file to new destination
		File DestFile= new File(path);
		//Copy file at destination
		try
		{
			FileUtils.copyFile(scrFile, DestFile);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return path;
	}
	
	
	public void CheckDisplay(WebElement elm,String msg) //For checking the elements are displaying or not.
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(elm));
			elm.isDisplayed();
			logger.pass(msg);
		}
		catch(Exception e)
		{
			System.out.println("Exeption="+e.getMessage());
			logger.fail("Step failed due to exception:"+e.getMessage());
		}
	}
	
	
	public void getTextandValidate(WebElement elm ,String value,String msg)
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(elm));
			if(elm.getText().equals(value))
			{
			logger.pass(msg+"   <a href+"+getscreenshot()+"><span class='label start-time'>Screenshot</span></a>"); //This code for screenshot.
			}
			else
			{
				logger.fail("Text did not match"+"   <a href+"+getscreenshot()+"><span class='label start-time'>Screenshot</span></a>");
			}
			}
		catch(Exception e)
		{
			System.out.println("Exeption="+e.getMessage());
			logger.fail("Step failed due to exception:"+e.getMessage()+"   <a href+"+getscreenshot()+"><span class='label start-time'>Screenshot</span></a>");
		}
	}
	
	
	
	
	
	
	
	
}
