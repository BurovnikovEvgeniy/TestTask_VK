package Pages;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;

import Utils.Conditions;

import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {
    private static final By USER_PHOTO = new By.ByClassName("viewImageLinkId");
    private static final By USER_NAME = new By.ByXPath("//*[contains(@data-l, \"t,userPage\")]");
    private static final By USER_SETTINGS_BUTTON = new By.ByXPath("//*[contains(@data-l, \"USER_EDIT_CONFIG\")]");

    public boolean checkUserName(String firstName, String lastName) {
        return $(USER_NAME).shouldHave(Condition.text(firstName + " " + lastName)).isDisplayed();
    }

    public BaseSettingsPage goToSettingsPage() {
        $(USER_SETTINGS_BUTTON)
                .shouldBe(Conditions.clickable.because("The user's personal settings button is not displayed")).click();
        return new BaseSettingsPage();
    }

    @Override
    public boolean checkPage() {
        return $(USER_PHOTO).isDisplayed() && $(USER_NAME).isDisplayed();
    }
}
