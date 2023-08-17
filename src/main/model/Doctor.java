package model;

// Represents a Doctor with a username and a password

public class Doctor {

    private String name;
    private String pass;

    //MODIFIES - this
    //EFFECTS - As a constructor, defines the default doctor name and password
    public Doctor() {
        name = "admin";
        pass = "12345";
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }
}
