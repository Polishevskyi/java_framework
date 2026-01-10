package mobile.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import java.net.MalformedURLException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import utils.ProjectConfig;

public class AppFactory {

    private static AppiumDriver driver;
    private static volatile String suiteBuildName = null;

    private static Map<String, Object> getBrowserstackOptions() {
        Map<String, Object> options = new HashMap<>();
        options.put("userName", ProjectConfig.CONFIG.getBrowserstackUsername());
        options.put("accessKey", ProjectConfig.CONFIG.getBrowserstackAccessKey());
        options.put("appiumVersion", ProjectConfig.CONFIG.getBrowserstackAppiumVersion());
        options.put("buildName", getBuildName());
        options.put("projectName", getProjectName());
        return options;
    }

    private static String getBuildName() {
        synchronized (AppFactory.class) {
            if (suiteBuildName == null) {
                String platform = ProjectConfig.CONFIG.getPlatform();
                String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                suiteBuildName = platform + " - " + date;
            }
            return suiteBuildName;
        }
    }

    private static String getProjectName() {
        return Objects.requireNonNullElse(
                ProjectConfig.CONFIG.getBrowserstackProjectName(),
                System.getProperty("browserstack.projectName", "Mobile Automation"));
    }

    public static void resetBuildName() {
        suiteBuildName = null;
    }

    private static URI getServerUrl() {
        String url = Boolean.TRUE.equals(ProjectConfig.CONFIG.getIsCloud())
                ? ProjectConfig.CONFIG.getBrowserstackHubUrl()
                : ProjectConfig.CONFIG.getAppiumLocalUrl();
        return URI.create(url);
    }

    private static UiAutomator2Options createAndroidOptions() {
        UiAutomator2Options options = new UiAutomator2Options();
        boolean isCloud = Boolean.TRUE.equals(ProjectConfig.CONFIG.getIsCloud());

        if (isCloud) {
            options.setDeviceName(ProjectConfig.CONFIG.getAndroidCloudDeviceName())
                    .setPlatformVersion(ProjectConfig.CONFIG.getAndroidCloudPlatformVersion())
                    .setAppPackage(ProjectConfig.CONFIG.getAndroidCloudAppPackage())
                    .setAppActivity(ProjectConfig.CONFIG.getAndroidCloudAppActivity())
                    .setFullReset(false)
                    .setNoReset(false)
                    .setAutoGrantPermissions(true);
            String appPath = ProjectConfig.CONFIG.getBrowserstackAndroidApp();
            if (appPath != null && !appPath.isEmpty()) {
                options.setApp(appPath).setCapability("bstack:options", getBrowserstackOptions());
            }
        } else {
            options.setDeviceName(ProjectConfig.CONFIG.getAndroidLocalDeviceName())
                    .setPlatformVersion(ProjectConfig.CONFIG.getAndroidLocalPlatformVersion())
                    .setAppPackage(ProjectConfig.CONFIG.getAndroidLocalAppPackage())
                    .setAppActivity(ProjectConfig.CONFIG.getAndroidLocalAppActivity())
                    .setFullReset(false)
                    .setNoReset(false)
                    .setAutoGrantPermissions(true);
        }

        return options;
    }

    private static XCUITestOptions createIosOptions() {
        XCUITestOptions options = new XCUITestOptions();
        boolean isCloud = Boolean.TRUE.equals(ProjectConfig.CONFIG.getIsCloud());

        if (isCloud) {
            options.setDeviceName(ProjectConfig.CONFIG.getIosCloudDeviceName())
                    .setPlatformVersion(ProjectConfig.CONFIG.getIosCloudPlatformVersion())
                    .setBundleId(ProjectConfig.CONFIG.getIosCloudBundleId())
                    .setFullReset(false)
                    .setNoReset(false);
            String appPath = ProjectConfig.CONFIG.getBrowserstackIosApp();
            if (appPath != null && !appPath.isEmpty()) {
                options.setApp(appPath).setCapability("bstack:options", getBrowserstackOptions());
            }
        } else {
            options.setDeviceName(ProjectConfig.CONFIG.getIosLocalDeviceName())
                    .setPlatformVersion(ProjectConfig.CONFIG.getIosLocalPlatformVersion())
                    .setBundleId(ProjectConfig.CONFIG.getIosLocalBundleId())
                    .setUdid(ProjectConfig.CONFIG.getIosLocalUdid())
                    .setFullReset(false)
                    .setNoReset(false);
        }

        return options;
    }

    public static void launchApp() throws MalformedURLException {
        URI serverUrl = getServerUrl();

        if ("ios".equalsIgnoreCase(ProjectConfig.CONFIG.getPlatform())) {
            XCUITestOptions iosOptions = createIosOptions();
            driver = new IOSDriver(serverUrl.toURL(), iosOptions);
        } else {
            UiAutomator2Options androidOptions = createAndroidOptions();
            driver = new AndroidDriver(serverUrl.toURL(), androidOptions);
        }

        AppDriver.setDriver(driver);
    }

    public static void quitApp() {
        AppiumDriver currentDriver = (AppiumDriver) AppDriver.getCurrentDriver();
        if (currentDriver != null) {
            currentDriver.quit();
            AppDriver.setDriver(null);
        }
    }
}
