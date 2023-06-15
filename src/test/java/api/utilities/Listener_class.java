package api.utilities;

import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listener_class implements ITestListener 
{
	public static ExtentSparkReporter sparkReporter;
	 public static ExtentReports extent;
	 public static ExtentTest test;
	 public void onStart(ITestContext context) 
	 {
		 sparkReporter= new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\Petstore.html");
		  sparkReporter.config().setDocumentTitle("Automation Test Report");
		  sparkReporter.config().setReportName("Petstore Report");
		  sparkReporter.config().setTheme(Theme.DARK);
		  
		  extent = new ExtentReports();
		  extent.attachReporter(sparkReporter);
		  
		  extent.setSystemInfo("HostName", "MyHost");
		  extent.setSystemInfo("ProjectName", "PetStore");
		  extent.setSystemInfo("Tester", "Ramya vadimgati");
		  extent.setSystemInfo("OS", "Win10");
		  extent.setSystemInfo("Browser", "Chrome");
	}

	 public void onTestSuccess(ITestResult result) 
	 {
		 test=extent.createTest(result.getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.createNode(result.getName());
		 test.log(Status.PASS,"Test Passed");
	 
	 }
	 public void onTestFailure(ITestResult result)
	 {
		 test=extent.createTest(result.getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.createNode(result.getName());
		 test.log(Status.FAIL,"Test Failed");
		 test.log(Status.FAIL,result.getThrowable().getMessage());
	 }

	 public void onTestSkipped(ITestResult result)
	 {
		 test=extent.createTest(result.getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.createNode(result.getName());
		 test.log(Status.SKIP,"Test Skipped");
		 test.log(Status.SKIP,result.getThrowable().getMessage());
	 }
	
	 public void onFinish(ITestContext context) {
	  extent.flush();
	 }
	}
