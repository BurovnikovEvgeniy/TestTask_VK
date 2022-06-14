package Tests.InvalidData;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Selenide;

import Pages.BaseSettingsPage;
import Pages.LoginPage;
import Pages.MainPage;
import Pages.Windows.PersonalDataWindow;
import Tests.BaseTest;

public class InvalidDateOfBirthTest extends BaseTest {
    private PersonalDataWindow personalDataWindow;
    private static final Calendar DATE_OF_BIRTH = new GregorianCalendar();
    private static final String NONEXISTENT_DAY = "31";


    @BeforeEach
    public void beginEachTest() {
        DATE_OF_BIRTH.set(2001, Calendar.SEPTEMBER, 12);
        MainPage mainPage = new LoginPage().logIn(user);
        personalDataWindow = mainPage.goToSettingsPage().openPersonalDataWindow();
        personalDataWindow
                .setDayOfBirth(String.valueOf(DATE_OF_BIRTH.get(Calendar.DATE)))
                .setMonthOfBirth((String.valueOf(DATE_OF_BIRTH.get(Calendar.MONTH) + 1)))
                .setYearOfBirth(String.valueOf(DATE_OF_BIRTH.get(Calendar.YEAR)))
                .confirmChanges();
        Selenide.refresh();
        personalDataWindow = new BaseSettingsPage().openPersonalDataWindow();
    }

    @Test
    public void trySetNonexistentDay() {
        Assertions.assertTrue(personalDataWindow.check());
        Assertions.assertEquals(personalDataWindow.getDayOfBirth(), String.valueOf(DATE_OF_BIRTH.get(Calendar.DATE)));
        Assertions.assertEquals(personalDataWindow.getMonthOfBirth(), String.valueOf(DATE_OF_BIRTH.get(Calendar.MONTH) + 1));
        Assertions.assertEquals(personalDataWindow.getYearOfBirth(), String.valueOf(DATE_OF_BIRTH.get(Calendar.YEAR)));
        personalDataWindow
                .setDayOfBirth(NONEXISTENT_DAY)
                .confirmChanges();
        Assertions.assertTrue(personalDataWindow.isHaveMessageError());
        personalDataWindow.cancelChanges();
        Selenide.refresh();
        personalDataWindow = new BaseSettingsPage().openPersonalDataWindow();
        Assertions.assertNotEquals(NONEXISTENT_DAY, personalDataWindow.getDayOfBirth());
    }
}
