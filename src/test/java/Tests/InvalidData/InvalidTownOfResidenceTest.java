package Tests.InvalidData;

import java.util.ArrayList;
import java.util.Arrays;

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
import Utils.RandomValueGenerator;

public class InvalidTownOfResidenceTest extends BaseTest {
    private static final String TOWN_OF_RESIDENCE = "г. Кронштадт";
    private static final int lengthRandomString = 12;
    RandomValueGenerator<String> randomOfInvalidResidentTown;
    private PersonalDataWindow personalDataWindow;

    @BeforeEach
    public void preparePageAndData() {
        randomOfInvalidResidentTown
                = new RandomValueGenerator<>(new ArrayList<>(Arrays.asList("", RandomStringGenerator.generateString(lengthRandomString))));
        MainPage mainPage = new LoginPage().logIn(user);
        personalDataWindow = mainPage.goToSettingsPage().openPersonalDataWindow();
        personalDataWindow
                .setTownOfResident(TOWN_OF_RESIDENCE)
                .confirmChanges();
        Selenide.refresh();
        personalDataWindow = new BaseSettingsPage().openPersonalDataWindow();
    }

    @Test
    public void changeTownOfResidenceToInvalidValue() {
        Assertions.assertTrue(personalDataWindow.check());
        Assertions.assertTrue(personalDataWindow.getTownOfResident().contains(TOWN_OF_RESIDENCE));
        Assertions.assertThrows(Throwable.class, () ->
                personalDataWindow
                        .setTownOfResident(randomOfInvalidResidentTown.generate()));
        personalDataWindow.confirmChanges();
        Assertions.assertTrue(personalDataWindow.isHaveMessageError());
        personalDataWindow.cancelChanges();
        Selenide.refresh();
        BaseSettingsPage baseSettingsPageAfterTryingChanges = new BaseSettingsPage();
        Assertions.assertTrue(baseSettingsPageAfterTryingChanges.checkPage());
        Assertions.assertTrue(baseSettingsPageAfterTryingChanges.getAllPersonalData().contains(TOWN_OF_RESIDENCE));
    }
}
