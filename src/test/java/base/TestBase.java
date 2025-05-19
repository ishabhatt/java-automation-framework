package base;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

@SuppressWarnings("deprecation")
public class TestBase {

	public static WebDriver driver;
	public Logger logger;
	public Properties prop;
	public FileInputStream propfile;

	@BeforeClass(groups = { "sanity", "regression", "functional", "master" })
	@Parameters({ "browser", "osname" })
	public void setup(String browser, String osname) {

		logger = LogManager.getLogger(this.getClass());
		try {
			propfile = new FileInputStream("./src/test/resources/config.properties");
			prop = new Properties();
			prop.load(propfile);
		} catch (Exception e) {
			logger.error("Error reading Properties: " + propfile.toString());
			e.printStackTrace();
		}

		logger.info("Opening Browser:" + browser);
		logger.info("Execution Env:" + prop.getProperty("EXECUTION_ENV"));

		if (prop.getProperty("EXECUTION_ENV").equalsIgnoreCase("remote")) {

			DesiredCapabilities capabilities = new DesiredCapabilities();

			if (osname.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WINDOWS);
			} else if (osname.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else if (osname.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			} else {
				System.out.println("No matching OS");
				return;
			}

			switch (browser.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;
			default:
				System.out.println("No matching browser");
				return;
			}

			try {
				String hub_host = "http://" + prop.getProperty("HUB_HOST") + ":" + prop.getProperty("HUB_PORT");
				driver = new RemoteWebDriver(new URI(hub_host).toURL(), capabilities);
			} catch (MalformedURLException | URISyntaxException e) {
				e.printStackTrace();
			}

		} else {
			switch (browser.toLowerCase()) {
			case "chrome":
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-web-security");
				options.addArguments("--no-proxy-server");

				Map<String, Boolean> prefs = new HashMap<String, Boolean>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);

				options.setExperimentalOption("prefs", prefs);
				driver = new ChromeDriver(options);

				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "safari":
				driver = new SafariDriver();
				break;
			default:
				System.out.println("Invalid browser value: " + browser);
				return;
			}
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get(prop.getProperty("appURL"));
		driver.manage().window().maximize();
	}

	@AfterClass(groups = { "sanity", "regression", "functional", "master" })
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		String randomString = RandomStringUtils.randomAlphanumeric(8);
		return randomString;
	}

	public String randomNumber() {
		String randomNumber = RandomStringUtils.randomNumeric(10);
		return randomNumber;
	}

	public String randomAlphaNumber() {
		String randomAlphaNumber = RandomStringUtils.randomAlphanumeric(10);
		return randomAlphaNumber;
	}

	public String captureScreen(String testname) {

		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		// take full screenshot
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);

		String ssFilepath = System.getProperty("user.dir") + "/screenshots/" + testname + "_" + timestamp + ".png";
		File targetfile = new File(ssFilepath);

		sourceFile.renameTo(targetfile);

		return ssFilepath;
	}

}
