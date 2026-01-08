package mobile;

import com.github.javafaker.Faker;
import io.qameta.allure.testng.AllureTestNg;
import java.net.MalformedURLException;
import java.util.Arrays;
import mobile.appium.TestConfig;
import mobile.appium.driver.AppFactory;
import mobile.appium.driver.AppiumServerManager;
import mobile.listeners.BrowserStackListener;
import org.assertj.core.api.SoftAssertions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.RetryAnalyzer;

@Listeners({AllureTestNg.class, BrowserStackListener.class})
public class BaseMobileTest {
    protected static final Faker FAKER = new Faker();
    protected SoftAssertions softly;

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
        this.softly = new SoftAssertions();
        AppFactory.launchApp();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        softly.assertAll();
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
