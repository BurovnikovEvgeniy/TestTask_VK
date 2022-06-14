package Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class User {
    private final String login;
    private final String password;

    private String firstName;
    private String lastName;


    public User(String login, String password, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public static String readPasswordFromFile(String path) {
        String password = "";
        try {
            password = Files.readString(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return password;
    }
}
