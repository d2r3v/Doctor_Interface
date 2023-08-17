package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthTest {

    private Auth auth;
    private ListOfPatients listOfPatients;

    @BeforeEach
    public void setup() {
        auth = new Auth();

        listOfPatients = new ListOfPatients();
        listOfPatients.addpatient(new Patient("John", "123","test1","NA","password",null));
        listOfPatients.addpatient(new Patient("Alice", "456","test2","NA","password",null));
        listOfPatients.addpatient(new Patient("Adrian","12345","test3","NA","password",null));
    }

    @Test
    public void testAuthenticatorAdmin() {
        // Test when the user is admin
        int result = auth.authenticator("admin", "12345", listOfPatients);
        assertEquals(1, result);
    }

    @Test
    public void testAuthenticatorAdminInvalidPassword() {
        // Test when the admin exists but the password is incorrect
        int result =auth.authenticator("admin","password",listOfPatients);
        assertEquals(3,result);
    }

    @Test
    public void testAuthenticatorPatient() {
        // Test when the user is a patient
        int result = auth.authenticator("John", "password", listOfPatients);
        assertEquals(2, result);
    }

    @Test
    public void testAuthenticatorInvalidUser() {
        // Test when the user does not match any
        int result = auth.authenticator("UnknownUser", "password", listOfPatients);
        assertEquals(3, result);
    }

    @Test
    public void testAuthenticatorInvalidPassword() {
        // Test when the user exists, but the password is incorrect
        int result = auth.authenticator("John", "wrongpassword", listOfPatients);
        assertEquals(3, result);
    }

    @Test
    public void testAuthenticatorEmptyDatabase() {
        // Test when the patient database is empty
        ListOfPatients emptyList = new ListOfPatients();
        int result = auth.authenticator("user", "password", emptyList);
        assertEquals(3, result);
    }

}
