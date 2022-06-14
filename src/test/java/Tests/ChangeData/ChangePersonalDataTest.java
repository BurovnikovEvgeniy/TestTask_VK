package Tests.ChangeData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Selenide;

import Pages.BaseSettingsPage;
import Pages.LoginPage;
import Pages.MainPage;
import Pages.Windows.PersonalDataWindow;
import Tests.BaseTest;
import Utils.Gender;
import Utils.RandomValueGenerator;

public class ChangePersonalDataTest extends BaseTest {

    private static final List<String> listOfTown
            = new ArrayList<>(Arrays.asList("г. Ломоносов", "г. Курчатов", "г. Грозный", "г. Мурманск"));

    private PersonalDataWindow personalDataWindow;

    private static final String NEW_FIRST_NAME = "Женя";
    private static final String NEW_LAST_NAME = "Буравников";
    private static Gender NEW_GENDER;
    private static final String NEW_DAY_OF_BIRTH = "5";
    private static final String NEW_MONTH_OF_BIRTH = "11";
    private static final String NEW_YEAR_OF_BIRTH = "2005";
    private static String NEW_TOWN_OF_RESIDENT;
    private static String NEW_HOMETOWN;

    @BeforeEach
    public void doBeginEachTest() {
        NEW_GENDER = new RandomValueGenerator<>(Arrays.asList(Gender.MALE, Gender.FEMALE)).generate();
        RandomValueGenerator<String> randomTown = new RandomValueGenerator<>(listOfTown);
        NEW_TOWN_OF_RESIDENT = randomTown.generate();
        NEW_HOMETOWN = randomTown.generate();
        MainPage mainPage = new LoginPage().logIn(user);
        personalDataWindow = mainPage.goToSettingsPage().openPersonalDataWindow();
    }

    @Test
    public void changeData() {
        personalDataWindow
                .setFirstName(NEW_FIRST_NAME)
                .setLastName(NEW_LAST_NAME)
                .setDayOfBirth(NEW_DAY_OF_BIRTH)
                .setMonthOfBirth(NEW_MONTH_OF_BIRTH)
                .setYearOfBirth(NEW_YEAR_OF_BIRTH)
                .setGender(NEW_GENDER)
                .setTownOfResident(NEW_TOWN_OF_RESIDENT)
                .setHometown(NEW_HOMETOWN)
                .confirmChanges();
        Selenide.refresh();
        personalDataWindow = new BaseSettingsPage().openPersonalDataWindow();
        Assertions.assertEquals(NEW_FIRST_NAME, personalDataWindow.getFirstName());
        Assertions.assertEquals(NEW_LAST_NAME, personalDataWindow.getLastName());
        Assertions.assertEquals(NEW_DAY_OF_BIRTH, personalDataWindow.getDayOfBirth());
        Assertions.assertEquals(NEW_MONTH_OF_BIRTH, personalDataWindow.getMonthOfBirth());
        Assertions.assertEquals(NEW_YEAR_OF_BIRTH, personalDataWindow.getYearOfBirth());
        Assertions.assertEquals(NEW_GENDER.getValue(), personalDataWindow.getGender());
        Assertions.assertTrue(personalDataWindow.getHometown().contains(NEW_HOMETOWN));
        Assertions.assertTrue(personalDataWindow.getTownOfResident().contains(NEW_TOWN_OF_RESIDENT));
    }

    @AfterEach
    public void doAfterEachTest() {
        personalDataWindow
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName());
    }
}
