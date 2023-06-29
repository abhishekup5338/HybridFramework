package com.vTiger.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	//In this we have the common test cases.
	
	public WebDriver driver;
	public Properties prop;
	public Map<String,Map<String,String>> td;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	@BeforeSuite
	public void Initiation()
	{
		CreateReport(); //We can call create report anytime.
		
		prop = getProperties();
		td = GetTestData(System.getProperty("user.dir")+"/src/test/resources/TestData/Data.xlsx","Sheet1");
		System.out.println(td);
		System.exit(0);
		lauchApp();
	}
	
	@AfterSuite
	public void tierdown()
	{
		driver.quit();
	}
	
	public void lauchApp()
	{
		if(prop.getProperty("BrowserName").equals("chrome"))
				{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
				}
		else if(prop.getProperty("BrowserName").equals("edge"))
				{
			      WebDriverManager.edgedriver().setup();
			      driver = new EdgeDriver();
				}
		driver.get(prop.getProperty("AppUrl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(System.getProperty("ImplicitWait"))));
	}
	
	
	public Properties getProperties()
	{
		
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/Config/setting.properties");
			prop.load(fis);
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	
	
	public void CreateReport()
	{
		//"vtigerExtentReport_2023061583512.html"
		Date d = new Date();
		DateFormat ft = new SimpleDateFormat("ddMMyyyyhhmmss");
		String fileName = ft.format(d);
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/src/test/java/com/vtiger/reports/ExtentReport"+fileName+".html");
		//Create an object of extent report.
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Automation Test Hub");
		    extent.setSystemInfo("Enviroment", "Test");
		extent.setSystemInfo("User Name", "Abhishek U");
		htmlReporter.config().setDocumentTitle("Title of the report comes here");
		   //Name of the report
		htmlReporter.config().setReportName("Name of the Report Comes here");
		  // DARK THEME
		htmlReporter.config().setTheme(Theme.STANDARD);
		
	}

	
	public Map<String,Map<String,String>> GetTestData(String file, String Sheet)
	{
		Map<String,Map<String,String>> td = new HashMap<String,Map<String,String>>();
		try
		{
		Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection(file);
		String strQuery="Select * from "+Sheet;
		Recordset recordset=connection.executeQuery(strQuery);
		List<String> colums = recordset.getFieldNames();
		while(recordset.next()){
		//System.out.println(recordset.getField("Details"));
		Map<String,String> rowdata = new HashMap<String,String>();
		for(String colm:colums)
		{
			String colmName = colm;
			String colmValue = recordset.getField(colmName);
			rowdata.put(colmName, colmValue);
		}
		td.put(recordset.getField("TCName"), rowdata);
		}
		
		recordset.close();
		connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		 
		return td;
	}
	
	
	
	
	
	
	
	
	
	
	
}
