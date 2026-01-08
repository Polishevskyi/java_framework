package web.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class LoginPage extends BasePage<LoginPage> {

    private final SelenideElement usernameField = $("#user-name");
    private final SelenideElement passwordField = $("#password");
    private final SelenideElement loginBtn = $("#login-button");

    @Override
    public String url() {
        return "/";
    }

    public LoginPage enterUsername(String username) {
        enterText(usernameField, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        enterText(passwordField, password);
        return this;
    }

    public ProductsPage clickLoginButton() {
        click(loginBtn);
        return new ProductsPage();
    }

    public boolean isLoginPageDisplayed() {
        return isVisible(usernameField) && isVisible(passwordField) && isVisible(loginBtn);
    }
}
