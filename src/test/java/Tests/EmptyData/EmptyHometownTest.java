package Tests.EmptyData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Selenide;

import Pages.BaseSettingsPage;
import Pages.LoginPage;
import Pages.MainPage;
import Pages.Windows.PersonalDataWindow;
import Tests.BaseTest;
import Utils.RandomValueGenerator;

public class EmptyHometownTest extends BaseTest {
    private static String HOMETOWN;
    private static String TOWN_OF_RESIDENCE;
    private static final String EMPTY_VALUE = "";
    private PersonalDataWindow personalDataWindow;

    @BeforeEach
    public void beginEachTest() {
        RandomValueGenerator<String> randomOfTown
                = new RandomValueGenerator<>(new ArrayList<>(Arrays.asList("Курск", "Москва", "г. Кронштадт", "Ульяновск")));
        HOMETOWN = randomOfTown.generate();
        TOWN_OF_RESIDENCE = randomOfTown.generate();
        MainPage mainPage = new LoginPage().logIn(user);
        personalDataWindow = mainPage.goToSettingsPage().openPersonalDataWindow();
        personalDataWindow
                .setTownOfResident(TOWN_OF_RESIDENCE)
                .setHometown(HOMETOWN)
                .confirmChanges();
        Selenide.refresh();
        personalDataWindow = new BaseSettingsPage().openPersonalDataWindow();
    }

    @Test
    public void deleteHometown() {
        Assertions.assertTrue(personalDataWindow.check());
        Assertions.assertTrue(personalDataWindow.getHometown().contains(HOMETOWN));
        Assertions.assertTrue(personalDataWindow.getTownOfResident().contains(TOWN_OF_RESIDENCE));
        personalDataWindow.
                setHometown(EMPTY_VALUE)
                .confirmChanges();
        Selenide.refresh();
        BaseSettingsPage baseSettingsPageAfterTryingChanges = new BaseSettingsPage();
        if (!HOMETOWN.equals(TOWN_OF_RESIDENCE)) {
            Assertions.assertFalse(baseSettingsPageAfterTryingChanges.getAllPersonalData().contains(HOMETOWN));
        }
        Assertions.assertEquals(EMPTY_VALUE, baseSettingsPageAfterTryingChanges.openPersonalDataWindow().getHometown());
    }
}
