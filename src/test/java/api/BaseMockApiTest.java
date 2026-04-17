package api;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseMockApiTest extends BaseApiTest {
    protected static WireMockServer wireMockServer;

    @BeforeClass
    public static void startWireMock() {
        wireMockServer =
                new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
        wireMockServer.start();
    }

    @AfterClass
    public static void stopWireMock() {
        if (wireMockServer != null && wireMockServer.isRunning()) {
            wireMockServer.stop();
        }
    }

    @Override
    @BeforeTest
    public void setUpRetry(ITestContext context) {}

    @Override
    @BeforeMethod
    public void setupTest() {
        super.setupTest();
        wireMockServer.resetMappings();
    }
}
