package api;

import io.qameta.allure.testng.AllureTestNg;
import java.util.Arrays;
import org.assertj.core.api.SoftAssertions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import utils.RetryAnalyzer;

@Listeners({AllureTestNg.class})
public class BaseApiTest {
    protected SoftAssertions softly;

    @BeforeTest
    public void setUpRetry(ITestContext context) {
        Arrays.stream(context.getAllTestMethods()).forEach(method -> method.setRetryAnalyzerClass(RetryAnalyzer.class));
    }

    @BeforeMethod
    public void setupTest() {
        this.softly = new SoftAssertions();
    }

    @AfterMethod
    public void afterTest() {
        softly.assertAll();
    }
}
