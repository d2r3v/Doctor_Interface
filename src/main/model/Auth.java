package model;

// Authenticates the login by comparing with existing database , has a username and password
public class Auth {

    String user;
    String password;

    //EFFECTS - compares given login and password with given database and produces 1 for doctor login
    // 2 for patient and 3 otherwise
    public int authenticator(String username, String pass, ListOfPatients listof) {
        this.user = username;
        this.password = pass;

        if (user.equals("admin") && password.equals("12345")) {
            EventLog.getInstance().logEvent(new Event("Logged in"));
            return 1;
        } else {
            for (int a = 0; a < listof.getsize(); a++) {
                Patient a1 = listof.getval(a);
                if (a1.getname().equals(username) && a1.getPassword().equals(password)) {
                    EventLog.getInstance().logEvent(new Event("Logged in"));
                    return 2;
                }
            }
            return 3;
        }
    }
}


