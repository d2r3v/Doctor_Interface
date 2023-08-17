package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PatientTest {

    private Patient patient;

    @BeforeEach
    public void setup() {

        patient = new Patient("John", "123456789", "123 Main St.", "History","password",null);
    }

    @Test
    public void testConstructorAndGetters() {
        // Check if the patient is initialized
        assertEquals("John", patient.getname());
        assertEquals("123456789", patient.getMobile());
        assertEquals("123 Main St.", patient.getAddress());
        assertEquals("History", patient.getHistory());
        assertEquals("password", patient.getPassword());
        assertNotNull(patient.getMeds());
    }

    @Test
    public void testAddMed() {

        Medication medication = new Medication("Advil", "4 times a day", "Take with food");


        patient.addmed(medication);

        // Check if it is added
        ListOfMedication medsList = patient.getMeds();
        assertEquals(1, medsList.getsize());
        assertEquals(medication, medsList.getmed(0));
    }

    @Test
    public void testSetters() {
        Medication medication = new Medication("Advil", "4 times a day", "Take with food");
        ListOfMedication temp = new ListOfMedication();
        temp.addmeds(medication);
        patient.setMobile("987654321");
        patient.setAddress("456 New Ave.");
        patient.setHistory("New History");
        patient.setPassword("newpassword");
        patient.setMeds(temp);

        // Check if information is updated
        assertEquals("987654321", patient.getMobile());
        assertEquals("456 New Ave.", patient.getAddress());
        assertEquals("New History", patient.getHistory());
        assertEquals("newpassword", patient.getPassword());
        assertEquals(1,patient.getMeds().getsize());
    }
}
