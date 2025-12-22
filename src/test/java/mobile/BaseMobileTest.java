package mobile;

import io.qameta.allure.testng.AllureTestNg;
import java.net.MalformedURLException;
import java.util.Arrays;
import mobile.appium.TestConfig;
import mobile.appium.driver.AppFactory;
import mobile.appium.driver.AppiumServerManager;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.RetryAnalyzer;
import utils.listeners.AllureListener;
import utils.listeners.BrowserStackListener;

@Listeners({AllureTestNg.class, AllureListener.class, BrowserStackListener.class})
public class BaseMobileTest {

    @BeforeTest
    public void setUpRetry(ITestContext context) {
        Arrays.stream(context.getAllTestMethods()).forEach(method -> method.setRetryAnalyzerClass(RetryAnalyzer.class));
    }

    @BeforeSuite
    public static void serverStart() {
        if (!TestConfig.environment.isCloud()) {
            AppiumServerManager.start();
        }
    }

    @BeforeMethod
    public void launchApp() throws MalformedURLException {
        AppFactory.launchApp();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        AppFactory.quitApp();
    }

    @AfterSuite
    public static void serverStop() {
        if (!TestConfig.environment.isCloud()) {
            AppiumServerManager.stop();
        }
        AppFactory.resetBuildName();
    }
}
