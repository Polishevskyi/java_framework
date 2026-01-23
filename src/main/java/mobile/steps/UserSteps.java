package mobile.steps;

import io.qameta.allure.Step;
import mobile.screens.LoginScreen;
import mobile.screens.MenuScreen;
import mobile.screens.ProductsScreen;
import utils.ProjectConfig;

public class UserSteps {
    private UserSteps() {}

    @Step("Login as standard user")
    public static void loginUser() {
        MenuScreen menuScreen = new ProductsScreen().openMenu();
        LoginScreen loginScreen = menuScreen.navigateToLogin();
        loginScreen.enterUsername(ProjectConfig.CONFIG.getMobileCredentialsUsername());
        loginScreen.enterPassword(ProjectConfig.CONFIG.getMobileCredentialsPassword());
        loginScreen.tapLoginButton();
    }
}
