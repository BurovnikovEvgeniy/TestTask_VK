package Tests.EmptyData;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Selenide;

import Pages.BaseSettingsPage;
import Pages.LoginPage;
import Pages.MainPage;
import Pages.Windows.PersonalDataWindow;
import Tests.BaseTest;

public class EmptyLastNameTest extends BaseTest {
    private PersonalDataWindow personalDataWindow;
    private static final String emptyValue = "";

    @BeforeEach
    public void beginEachTest() {
        MainPage mainPage = new LoginPage().logIn(user);
        BaseSettingsPage baseSettingsPage = mainPage.goToSettingsPage();
        personalDataWindow = baseSettingsPage.openPersonalDataWindow();
    }

    @Test
    public void deleteLastName() {
        Assertions.assertTrue(personalDataWindow.check());
        personalDataWindow
                .setLastName(emptyValue)
                .confirmChanges();
        Assertions.assertTrue(personalDataWindow.check());
        Assertions.assertTrue(personalDataWindow.isHaveMessageError());
        personalDataWindow.cancelChanges();
        Selenide.refresh();
        BaseSettingsPage baseSettingsPageAfterTryingChanges = new BaseSettingsPage();
        Assertions.assertTrue(baseSettingsPageAfterTryingChanges.checkPage());
        Assertions.assertTrue(baseSettingsPageAfterTryingChanges.checkUserName(user.getFirstName(), user.getLastName()));
        Assertions.assertEquals(user.getLastName(), baseSettingsPageAfterTryingChanges.openPersonalDataWindow().getLastName());
    }

}
