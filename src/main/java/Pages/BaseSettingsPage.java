package Pages;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;

import Pages.Windows.PersonalDataWindow;
import Utils.Conditions;
import Utils.Gender;

import static com.codeborne.selenide.Selenide.$;

public class BaseSettingsPage extends BasePage {

    private static final By USER_SETTINGS_MENU = new By.ById("hook_Block_UserSettingsMenu");
    private static final By BASE_USER_SETTINGS_BLOCK = new By.ByXPath("//*[contains(@class, \"user-settings __profile\")]");
    private static final By BASIC_SETTINGS = new By.ByXPath("//*[contains(@data-l, \"t,personal_info\")]");
    private static final By PERSONAL_DATA = new By.ByXPath("//div[contains(@class, \"user-settings_i_tx textWrap\")]");

    private final String personalData;

    public BaseSettingsPage () {
        $(BASIC_SETTINGS).shouldBe(Conditions.clickable.because("The basic settings link cannot be used")).click();
        personalData = $(PERSONAL_DATA)
                .shouldBe(Condition.visible)
                .getText();
    }

    public String getAllPersonalData(){
        return personalData;
    }

    public PersonalDataWindow openPersonalDataWindow() {
        $(PERSONAL_DATA).shouldBe(Conditions.clickable.because("The personal settings link cannot be used")).click();

        return new PersonalDataWindow();
    }

    public boolean checkUserName(String firstName, String lastName) {
        String userData = $(PERSONAL_DATA)
                .shouldBe(Condition.visible)
                .getText();
        return userData.contains(firstName) && userData.contains(lastName);
    }

    public boolean checkGender(Gender gender, Map<Gender, String> genderEquivalent) {
        return $(PERSONAL_DATA)
                .shouldBe(Condition.visible)
                .getText()
                .contains(genderEquivalent.get(gender));
    }

    public boolean checkDateOfBirth(String dayOfBirth, String monthOfBirth, String yearOfBirth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(yearOfBirth), Integer.parseInt(monthOfBirth) - 1, Integer.parseInt(dayOfBirth));
        String month = calendar.getDisplayName(Calendar.MONTH,
                Calendar.LONG, new Locale("ru"));
        return $(PERSONAL_DATA)
                .shouldBe(Condition.visible)
                .getText()
                .contains(dayOfBirth + " " + month + " " + yearOfBirth);
    }

    public boolean checkCityInData(String city) {
        return $(PERSONAL_DATA)
                .shouldBe(Condition.visible)
                .getText()
                .contains(city);
    }
    @Override
    public boolean checkPage() {
        return $(USER_SETTINGS_MENU).isDisplayed() && $(BASE_USER_SETTINGS_BLOCK).isDisplayed();
    }
}
