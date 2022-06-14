package Pages.Windows;


import java.util.Objects;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;

import Utils.Conditions;
import Utils.Gender;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PersonalDataWindow {
    private static final By FIRST_NAME_FIELD = new By.ById("field_name");
    private static final By LAST_NAME_FIELD = new By.ById("field_surname");
    private static final By DAY_OF_BIRTH = new By.ByXPath("//*[contains(@id, \"field_bday\")]");
    private static final By DAY_LIST = new By.ByXPath("//*[contains(@id, \"field_bday\")]//option");
    private static final By MONTH_OF_BIRTH = new By.ByXPath("//*[contains(@id, \"field_bmonth\")]");
    private static final By MONTH_LIST = new By.ByXPath("//*[contains(@id, \"field_bmonth\")]//option");
    private static final By YEAR_OF_BIRTH = new By.ByXPath("//*[contains(@id, \"field_byear\")]");
    private static final By YEAR_LIST = new By.ByXPath("//*[contains(@id, \"field_byear\")]//option");
    private static final By SELECTED_DAY = new By.ByXPath("//*[contains(@id, \"field_bday\")]//*[contains(@selected, \"selected\")]");
    private static final By SELECTED_MONTH = new By.ByXPath("//*[contains(@id, \"field_bmonth\")]//*[contains(@selected, \"selected\")]");
    private static final By SELECTED_YEAR = new By.ByXPath("//*[contains(@id, \"field_byear\")]//*[contains(@selected, \"selected\")]");
    private static final By CITY_OF_RESIDENCE_FIELD = new By.ById("field_citySugg_SearchInput");
    private static final By HOMETOWN_FIELD = new By.ById("field_cityBSugg_SearchInput");
    private static final By LIST_OF_TOWN = new By.ByXPath("//*[contains(@class, \"suggest_li\")]//*[contains(@class, \"ellip\")]");
    private static final By CONFIRM_CHANGES_BUTTON = new By.ByXPath("//*[contains(@name, \"button_savePopLayerEditUserProfileNew\")]");
    private static final By CANCEL_CHANGES_BUTTON = new By.ByXPath("//*[contains(@name, \"button_cancelPopLayerEditUserProfileNew\")]");
    private static final String RADIO_BUTTON_GENDER = "//*[contains(@name, \"fr.gender\") and contains(@value, \"%d\")]";
    private static final By CHECKED_GENDER = new By.ByXPath("//*[contains(@name, \"fr.gender\") and contains(@checked, \"checked\")]");
    private static final By MESSAGE_ERROR_FIELD = new By.ByXPath("//*[contains(@class, \"form_i form_i__error\")]");
    private static final By PERSONAL_DATA_WINDOW = new By.ByXPath("//*[contains(@id, \"hook_Form_PopLayerEditUserProfileNewForm\")]");

    public PersonalDataWindow setFirstName(String firstName) {
        $(FIRST_NAME_FIELD)
                .shouldBe(Condition.visible.because("The first name input field is not displayed"))
                .setValue(firstName);
        return this;
    }

    public String getFirstName() {
        return $(FIRST_NAME_FIELD)
                    .shouldBe(Condition.visible.because("The first name input field is not displayed"))
                    .getValue();
    }

    public PersonalDataWindow setLastName(String lastName) {
        $(LAST_NAME_FIELD)
                .shouldBe(Condition.visible.because("The last name input field is not displayed"))
                .setValue(lastName);
        return this;
    }

    public String getLastName() {
        return $(LAST_NAME_FIELD)
                .shouldBe(Condition.visible.because("The last name input field is not displayed"))
                .getValue();
    }

    public PersonalDataWindow setDayOfBirth(String day) {
        $(DAY_OF_BIRTH)
                .shouldBe(Conditions.clickable.because("Date of birth cannot be selected"))
                .click();
        $$(DAY_LIST)
                .findBy(Condition.value(day))
                .click();
        return this;
    }

    public String getDayOfBirth() {
        return $(SELECTED_DAY)
                    .shouldBe(Condition.visible)
                    .getValue();
    }

    public PersonalDataWindow setMonthOfBirth(String month) {
        $(MONTH_OF_BIRTH)
                .shouldBe(Conditions.clickable.because("Month of birth cannot be selected"))
                .click();
        $$(MONTH_LIST)
                .findBy(Condition.value(month))
                .click();
        return this;
    }

    public String getMonthOfBirth() {
        return $(SELECTED_MONTH)
                .shouldBe(Condition.visible)
                .getValue();
    }

    public PersonalDataWindow setYearOfBirth(String year) {
        $(YEAR_OF_BIRTH)
                .shouldBe(Conditions.clickable.because("Year of birth cannot be selected"))
                .click();
        $$(YEAR_LIST)
                .findBy(Condition.value(year))
                .click();
        return this;
    }

    public String getYearOfBirth() {
        return $(SELECTED_YEAR)
                .shouldBe(Condition.visible)
                .getValue();
    }
    public PersonalDataWindow setTownOfResident(String town) {
        $(CITY_OF_RESIDENCE_FIELD)
                .shouldBe(Condition.visible.because("The city of residence field is not displayed"))
                .setValue(town);
        $$(LIST_OF_TOWN).findBy(Condition.text(town)).click();
        return this;
    }
    public String getTownOfResident() {
        return $(CITY_OF_RESIDENCE_FIELD)
                .shouldBe(Condition.visible.because("The city of residence field is not displayed"))
                .getValue();
    }

    public PersonalDataWindow setHometown(String hometown) {
        $(HOMETOWN_FIELD)
                .shouldBe(Condition.visible.because("The hometown field is not displayed"))
                .setValue(hometown);
        if (!"".equals(hometown)){
            $$(LIST_OF_TOWN).findBy(Condition.text(hometown)).click();
        }
        return this;
    }

    public String getHometown() {
        return $(HOMETOWN_FIELD)
                .shouldBe(Condition.visible.because("The hometown field is not displayed"))
                .getValue();
    }

    public PersonalDataWindow setGender(Gender gender) {
        $(new By.ByXPath(String.format(RADIO_BUTTON_GENDER, gender.getValue())))
                .shouldBe(Condition.visible.because("The gender selection radio button is not displayed"))
                .click();
        return this;
    }

    public int getGender() {
        return Integer.parseInt((Objects.requireNonNull($(CHECKED_GENDER).getValue())));
    }

    public void confirmChanges() {
        $(CONFIRM_CHANGES_BUTTON)
                .shouldBe(Conditions.clickable.because("The save changes button is not active"))
                .click();
    }

    public void cancelChanges() {
        $(CANCEL_CHANGES_BUTTON)
                .shouldBe(Conditions.clickable.because("The cancel changes button is not active"))
                .click();
    }

    public boolean isHaveMessageError(){
        return $(MESSAGE_ERROR_FIELD).shouldBe(Condition.visible).isDisplayed();
    }

    public boolean check() {
        return $(PERSONAL_DATA_WINDOW).shouldBe(Condition.visible).isDisplayed();
    }
}