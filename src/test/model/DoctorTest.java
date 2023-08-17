package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoctorTest {

    @Test
    public void testDefaultConstructor() {

        Doctor doctor = new Doctor();

        // Check default name and password
        assertEquals("admin", doctor.getName());
        assertEquals("12345", doctor.getPass());
    }
}
