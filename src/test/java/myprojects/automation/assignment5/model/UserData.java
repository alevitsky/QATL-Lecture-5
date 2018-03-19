package myprojects.automation.assignment5.model;

public class UserData {
    private String firstname;
    private String lastname;
    private String email;

    private UserData(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public static UserData generate() {
        return new UserData(
                "Tester",
                "Shop",
                String.format("tester%s@example.org", System.currentTimeMillis()));
    }
}
