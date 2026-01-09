package web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.testng.AllureTestNg;
import java.util.Arrays;
import org.assertj.core.api.SoftAssertions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import utils.ConfigReader;
import utils.RetryAnalyzer;

@Listeners({AllureTestNg.class})
public class BaseWebTest {
    protected SoftAssertions softly;

    @BeforeTest
    public void setUpRetry(ITestContext context) {
        Arrays.stream(context.getAllTestMethods()).forEach(method -> method.setRetryAnalyzerClass(RetryAnalyzer.class));
    }

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.baseUrl = ConfigReader.getProperty("web.baseUrl");
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;

        SelenideLogger.addListener(
                "AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @BeforeMethod
    public void setupTest() {
        this.softly = new SoftAssertions();
    }

    @AfterMethod
    public void tearDown() {
        softly.assertAll();
        Selenide.closeWebDriver();
    }
}
