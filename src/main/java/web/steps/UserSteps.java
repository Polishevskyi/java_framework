package web.steps;

import io.qameta.allure.Step;
import utils.ConfigReader;
import web.pages.LoginPage;
import web.pages.ProductsPage;

public class UserSteps {
    private UserSteps() {}

    @Step("Login as standard user")
    public static ProductsPage loginUser() {
        return new LoginPage()
                .open()
                .enterUsername(ConfigReader.getProperty("web.test.credentials.username"))
                .enterPassword(ConfigReader.getProperty("web.test.credentials.password"))
                .clickLoginButton();
    }
}
