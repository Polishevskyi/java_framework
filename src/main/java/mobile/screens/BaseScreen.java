package mobile.screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Allure;
import java.time.Duration;
import java.util.stream.Stream;
import mobile.driver.AppDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseScreen {

    private static final int DEFAULT_TIMEOUT_SECONDS = 30;

    protected WebElement waitUntilElementPresent(By locator) {
        WebDriverWait wait =
                new WebDriverWait(AppDriver.getCurrentDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement findElement(By locator) {
        return AppDriver.getCurrentDriver().findElement(locator);
    }

    protected void enterText(By locator, String text) {
        Allure.step("Enter text '" + text + "' into field: " + formatLocator(locator), () -> {
            waitUntilElementPresent(locator);
            findElement(locator).clear();
            findElement(locator).sendKeys(text);
        });
    }

    protected void tap(By locator) {
        Allure.step("Tap on element: " + formatLocator(locator), () -> {
            waitUntilElementPresent(locator).click();
        });
    }

    protected String getText(By locator) {
        return Allure.step("Get text from element: " + formatLocator(locator), () -> {
            String str = "";
            if (AppDriver.getCurrentDriver() instanceof AndroidDriver) {
                str = findElement(locator).getText();
            } else if (AppDriver.getCurrentDriver() instanceof IOSDriver) {
                str = waitUntilElementPresent(locator).getDomAttribute("label");
            }
            return str;
        });
    }

    private String formatLocator(By locator) {
        String str = locator.toString();
        if (str.contains("accessibilityId:")) {
            return str.substring(str.indexOf(":") + 2);
        }
        if (str.contains("xpath:")) {
            return Stream.of("content-desc='", "@text='", "@name='")
                    .filter(str::contains)
                    .findFirst()
                    .map(attr -> {
                        int start = str.indexOf(attr) + attr.length();
                        return str.substring(start, str.indexOf("'", start));
                    })
                    .orElse("xpath");
        }
        return str.startsWith("By.") ? str.substring(3, str.indexOf(':')) : str;
    }
}
