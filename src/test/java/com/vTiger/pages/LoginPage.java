package com.vTiger.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.vTiger.utilities.CommanActions;

public class LoginPage extends CommanActions{
	
	
	
	//With the help of this we can re-use the code.
	//String user = "user_name";  some are also use this.
	
	/*
	By uid = By.name("user_name");
	By password = By.name("user_password");
	By login = By.name("login");
	
	
	public void login(String userid, String pwd)
	{
		driver.findElement(uid).clear();
		driver.findElement(uid).sendKeys("admin");
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys("admin");
		driver.findElement(login).click();
	}
	*/
	
	
	public LoginPage(WebDriver driver, ExtentTest logger)
	{
		super(driver, logger);
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="user_name")
	WebElement tb_username;
	
	@FindBy(name="user_password")
	WebElement tb_password;
	
	@FindBy(name="login")
	WebElement tb_login;
	
	/*
	public void login(String userid, String pwd)
	{
		tb_username.clear();
		tb_username.sendKeys("admin");
		tb_password.clear();
		tb_password.sendKeys("admin");
		tb_login.click();
	}
	*/
	
	//With help of extends class CommanActions we define the better.
	public void login(String userid, String pwd)
	{
		type(tb_username,userid, userid +" has been entered into username field");
		type(tb_password,pwd,"Password"+pwd+" has been entered into password field");
		click(tb_login,"Login button clicked successfully");	
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
