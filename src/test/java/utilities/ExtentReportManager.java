package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.TestBase;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter; // UI of Report
	public ExtentReports extent; // populate common info of report
	public ExtentTest test; // creating test entries in the report & update status of the test method
	String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	String reportName = "TestReport-" + timestamp + ".html";

	public void onStart(ITestContext context) {

		sparkReporter = new ExtentSparkReporter("./reports/" + reportName);

		sparkReporter.config().setDocumentTitle("Automation Report"); // Report Title
		sparkReporter.config().setReportName("Function Testing"); // Report Name
		sparkReporter.config().setTheme(Theme.DARK); // Report Theme

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester Name", System.getProperty("user.name"));
		extent.setSystemInfo("OS", context.getCurrentXmlTest().getParameter("osname"));
		extent.setSystemInfo("Browser Name", context.getCurrentXmlTest().getParameter("browser"));

		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}

	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName()); // create an entry in the report
		test.assignCategory(result.getMethod().getGroups()); // to display group name in the report
		test.log(Status.PASS, "Test case PASSED is: " + result.getName()); // update status pass/fail/skip
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName()); // create an entry in the report
		test.assignCategory(result.getMethod().getGroups()); // to display group name in the report

		test.log(Status.FAIL, "Test case FAILED is: " + result.getName()); // update status pass/fail/skip
		test.log(Status.FAIL, "Test case FAILED cause is: " + result.getThrowable()); // reason of test failure

		try {
			// Attach Screenshot
			String screenshotPath = new TestBase().captureScreen(result.getName());
			test.addScreenCaptureFromPath(screenshotPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName()); // create an entry in the report
		test.assignCategory(result.getMethod().getGroups()); // to display group name in the report
		test.log(Status.SKIP, "Test case SKIPPED is: " + result.getName()); // update status pass/fail/skip
	}

	public void onFinish(ITestContext context) {
		extent.flush();

		// Open report automatically
		String pathofEntentReport = "./reports/" + reportName;
		File extentReport = new File(pathofEntentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*-
		// Send email
		try {
			URL url = new URI("file:///" + pathofEntentReport).toURL();
			FileInputStream propfile = new FileInputStream("./src/test/resources/config.properties");
			Properties prop = new Properties();
			prop.load(propfile);
		
			// Create email
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.google.com");
			email.setSmtpPort(465);
			email.setAuthenticator(
					new DefaultAuthenticator(prop.getProperty("SMTP_USERNAME"), prop.getProperty("SMTP_PASSWORD")));
			email.setSSLOnConnect(true);
			email.setFrom("test@gmail.com"); // sender
			email.setSubject("Test Results");
			email.setMsg("Please find attached the test report...");
			email.addTo("tester@gmail.com"); // receiver
			email.attach(url, "extent report", "please check report....");
			email.send(); // finally send email
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		-*/

	}
}
