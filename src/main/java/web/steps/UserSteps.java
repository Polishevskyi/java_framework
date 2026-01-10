package web.steps;

import io.qameta.allure.Step;
import utils.ProjectConfig;
import web.pages.LoginPage;
import web.pages.ProductsPage;

public class UserSteps {
    private UserSteps() {}

    @Step("Login as standard user")
    public static ProductsPage loginUser() {
        return new LoginPage()
                .open()
                .enterUsername(ProjectConfig.CONFIG.getWebCredentialsUsername())
                .enterPassword(ProjectConfig.CONFIG.getWebCredentialsPassword())
                .clickLoginButton();
    }
}
