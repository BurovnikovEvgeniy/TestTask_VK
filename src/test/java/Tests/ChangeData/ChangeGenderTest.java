package Tests.ChangeData;

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

public class ChangeGenderTest extends BaseTest {

        private PersonalDataWindow personalDataWindow;

        @BeforeEach
        public void beginEachTest() {
            MainPage mainPage = new LoginPage().logIn(user);
            personalDataWindow = mainPage.goToSettingsPage().openPersonalDataWindow();
            personalDataWindow
                    .setGender(Gender.MALE)
                    .confirmChanges();
            Selenide.refresh();
            personalDataWindow = new BaseSettingsPage().openPersonalDataWindow();
        }

        @Test
        public void changeGender() {
            Assertions.assertTrue(personalDataWindow.check());
            Assertions.assertEquals(Gender.MALE.getValue(), personalDataWindow.getGender());
            personalDataWindow
                    .setGender(Gender.FEMALE)
                    .confirmChanges();
            Selenide.refresh();
            BaseSettingsPage baseSettingsPageAfterTryingChanges = new BaseSettingsPage();
            Assertions.assertEquals(Gender.FEMALE.getValue(), baseSettingsPageAfterTryingChanges.openPersonalDataWindow().getGender());
        }
}
