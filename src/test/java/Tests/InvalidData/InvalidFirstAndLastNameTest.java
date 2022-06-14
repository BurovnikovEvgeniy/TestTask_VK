package Tests.InvalidData;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Selenide;

import Pages.BaseSettingsPage;
import Pages.LoginPage;
import Pages.MainPage;
import Pages.Windows.PersonalDataWindow;
import Tests.BaseTest;
import Utils.RandomStringGenerator;

public class InvalidFirstAndLastNameTest extends BaseTest {

    private PersonalDataWindow personalDataWindow;

    private static String NEW_FIRST_NAME;
    private static String NEW_LAST_NAME;
    private static final int LENGTH_RANDOM_STRING = 12;


    @BeforeEach
    public void preparePageAndData() {
        MainPage mainPage = new LoginPage().logIn(user);
        personalDataWindow = mainPage.goToSettingsPage().openPersonalDataWindow();
        personalDataWindow
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .confirmChanges();
        Selenide.refresh();
        personalDataWindow = new BaseSettingsPage().openPersonalDataWindow();
        NEW_FIRST_NAME = RandomStringGenerator.generateString(LENGTH_RANDOM_STRING);
        NEW_LAST_NAME = RandomStringGenerator.generateString(LENGTH_RANDOM_STRING);
    }

    @Test
    public void changeData() {
        personalDataWindow
                .setFirstName(NEW_FIRST_NAME)
                .setLastName(NEW_LAST_NAME)
                .confirmChanges();
        Selenide.refresh();
        personalDataWindow = new BaseSettingsPage().openPersonalDataWindow();
        Assertions.assertEquals(user.getFirstName(), personalDataWindow.getFirstName());
        Assertions.assertEquals(user.getLastName(), personalDataWindow.getLastName());
    }
}
