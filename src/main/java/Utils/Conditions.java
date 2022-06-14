package Utils;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.and;

public class Conditions {
    public static Condition clickable = and("Can be clickable", Condition.visible, Condition.enabled);
}
