package Pages;

import org.openqa.selenium.By;

import Utils.Conditions;
import Utils.User;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {
    private static final By EMAIL_FIELD = new By.ById("field_email");
    private static final By PASSWORD_FIELD = new By.ById("field_password");
    private static final By LOG_IN_BUTTON = new By.ByXPath("//*[contains(@data-l, \"t,sign_in\")]");
    private static final By LOG_IN_ERROR = new By.ByXPath("//*[contains(@class, \"input-e login_error\")]");

    public MainPage logIn(User user){
        $(EMAIL_FIELD)
                .shouldBe(visible.because("The login field is not displayed"))
                .setValue(user.getLogin());
        $(PASSWORD_FIELD)
                .shouldBe(visible.because("The password field is not displayed"))
                .setValue(user.getPassword());
        $(LOG_IN_BUTTON)
                .shouldBe(Conditions.clickable.because("The \"login\" button is not displayed"))
                .click();
        return new MainPage();
    }

    @Override
    public boolean checkPage() {
        return $(EMAIL_FIELD).isDisplayed()
                && $(PASSWORD_FIELD).isDisplayed()
                && $(LOG_IN_BUTTON).isDisplayed();
    }

    public boolean checkLoginError() {
        return $(LOG_IN_ERROR).isDisplayed();
    }
}
