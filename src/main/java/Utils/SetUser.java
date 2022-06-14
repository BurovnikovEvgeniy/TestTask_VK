package Utils;

public class SetUser {
    public static final User EvgeniyBurovnikov
            = new User("89111833275",
            User.readPasswordFromFile("src/main/resources/password"),
            "Евгений",
            "Буровников");
}