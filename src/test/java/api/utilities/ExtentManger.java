package api.utilities;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManger 
{
 
 public static ExtentSparkReporter sparkReporter;
 public static ExtentReports extent;
 public static ExtentTest test;
 
 public static void setExtent() throws IOException 
 {
  sparkReporter= new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\Petstore.html");
  sparkReporter.loadXMLConfig("C:\\Users\\prudh\\eclipse-workspace\\PetStore_RestAssuredAP\\src\\test\\resources\\extent-config.xml");
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
 public static void endReport() {
  extent.flush();
 }
}