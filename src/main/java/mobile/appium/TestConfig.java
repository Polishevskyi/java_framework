package mobile.appium;

import mobile.appium.enums.Environment;
import mobile.appium.enums.Platform;
import utils.ConfigReader;

public class TestConfig {
    public static final Platform platform = Platform.fromString(ConfigReader.getProperty("platform"));
    public static final Environment environment = Environment.fromString(ConfigReader.getProperty("isCloud"));
}
