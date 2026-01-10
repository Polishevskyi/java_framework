package utils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources("file:config.properties")
public interface ProjectConfig extends Config {
    ProjectConfig CONFIG = ConfigFactory.create(ProjectConfig.class);

    // API Configuration
    @Key("api.baseUrl")
    String getApiBaseUrl();

    // Web Configuration
    @Key("web.baseUrl")
    String getWebBaseUrl();

    @Key("web.browser")
    String getWebBrowser();

    @Key("web.browserSize")
    String getWebBrowserSize();

    @Key("web.headless")
    Boolean getWebHeadless();

    @Key("web.credentials.username")
    String getWebCredentialsUsername();

    @Key("web.credentials.password")
    String getWebCredentialsPassword();

    // Mobile General Configuration
    @Key("mobile.platform")
    String getPlatform();

    @Key("mobile.isCloud")
    Boolean getIsCloud();

    // Test Credentials
    @Key("mobile.credentials.username")
    String getMobileCredentialsUsername();

    @Key("mobile.credentials.password")
    String getMobileCredentialsPassword();

    // Appium Server Configuration
    @Key("appium.jsPath")
    String getAppiumJsPath();

    @Key("appium.nodePath")
    String getAppiumNodePath();

    @Key("appium.port")
    Integer getAppiumPort();

    @Key("appium.localUrl")
    String getAppiumLocalUrl();

    // BrowserStack Configuration
    @Key("browserstack.username")
    String getBrowserstackUsername();

    @Key("browserstack.accessKey")
    String getBrowserstackAccessKey();

    @Key("browserstack.appiumVersion")
    String getBrowserstackAppiumVersion();

    @Key("browserstack.hubUrl")
    String getBrowserstackHubUrl();

    @Key("browserstack.projectName")
    String getBrowserstackProjectName();

    // Android Local Configuration
    @Key("android.local.deviceName")
    String getAndroidLocalDeviceName();

    @Key("android.local.platformVersion")
    String getAndroidLocalPlatformVersion();

    @Key("android.local.appPackage")
    String getAndroidLocalAppPackage();

    @Key("android.local.appActivity")
    String getAndroidLocalAppActivity();

    // Android Cloud Configuration
    @Key("android.cloud.deviceName")
    String getAndroidCloudDeviceName();

    @Key("android.cloud.platformVersion")
    String getAndroidCloudPlatformVersion();

    @Key("android.cloud.appPackage")
    String getAndroidCloudAppPackage();

    @Key("android.cloud.appActivity")
    String getAndroidCloudAppActivity();

    @Key("browserstack.android.app")
    String getBrowserstackAndroidApp();

    // iOS Local Configuration
    @Key("ios.local.deviceName")
    String getIosLocalDeviceName();

    @Key("ios.local.platformVersion")
    String getIosLocalPlatformVersion();

    @Key("ios.local.bundleId")
    String getIosLocalBundleId();

    @Key("ios.local.udid")
    String getIosLocalUdid();

    // iOS Cloud Configuration
    @Key("ios.cloud.deviceName")
    String getIosCloudDeviceName();

    @Key("ios.cloud.platformVersion")
    String getIosCloudPlatformVersion();

    @Key("ios.cloud.bundleId")
    String getIosCloudBundleId();

    @Key("browserstack.ios.app")
    String getBrowserstackIosApp();
}
