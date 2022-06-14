package Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import Utils.SetUser;
import Utils.User;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    public static final String url = "https://ok.ru/";
    protected static final User user = SetUser.EvgeniyBurovnikov;

    @BeforeEach
    void init() {
        open(url);
    }

    @AfterEach
    void close() {
        closeWindow();
    }
}
