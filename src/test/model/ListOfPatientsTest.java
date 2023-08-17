package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ListOfPatientsTest {

    private ListOfPatients listOfPatients;

    @BeforeEach
    public void setup() {

        listOfPatients = new ListOfPatients();
    }

    @Test
    public void testAddPatient() {

        Patient patient = new Patient("John", "123456789", "123 Main St.", "NA","password",null);


        listOfPatients.addpatient(patient);

        // Check if the patient is added
        assertEquals(2, listOfPatients.getsize());
        assertEquals(patient, listOfPatients.searchpatient("John"));
    }

    @Test
    public void testSearchPatient() {
        // Search for an existing patient
        Patient foundPatient = listOfPatients.searchpatient("Adrian");

        // Check for patient
        assertEquals("Adrian", foundPatient.getname());
    }

    @Test
    public void testSearchPatientNotFound() {
        // Search for a patient that doesn't exist
        Patient notFoundPatient = listOfPatients.searchpatient("Unknown");
        // Check if it returns a default patient
        assertEquals("test",notFoundPatient.getname());
    }

    @Test
    public void testDeletePatient() {

        Patient patientToDelete = new Patient("John", "123456789", "123 Main St.", "NA","password",null);


        listOfPatients.addpatient(patientToDelete);

        assertEquals(2, listOfPatients.getsize());

        listOfPatients.deletePatient(patientToDelete, listOfPatients);

        // Check if the patient is deleted
        assertEquals(1, listOfPatients.getsize());
        assertEquals("test",listOfPatients.searchpatient("John").getname());
    }

    @Test
    public void testUpdatePatient() {

        Patient patientToUpdate = new Patient("John", "123456789", "123 Main St.", "NA","password",null);

        listOfPatients.addpatient(patientToUpdate);

        Patient updatedPatient = new Patient("John", "987654321", "456 New Ave.", "NA","password",null);

        listOfPatients.updatepatient(updatedPatient);

        // Check if information is updated correctly
        Patient foundPatient = listOfPatients.searchpatient("John");
        assertEquals("987654321", foundPatient.getMobile());
        assertEquals("456 New Ave.", foundPatient.getAddress());
        assertEquals("password", foundPatient.getPassword());
    }

    @Test
    public void testDeletePatientFound() {

        Patient patientToDelete = new Patient("John", "123456789", "123 Main St.", "NA","password",null);


        listOfPatients.addpatient(patientToDelete);

        // Check if the patient is added
        assertEquals(2, listOfPatients.getsize());


        listOfPatients.deletePatient(patientToDelete, listOfPatients);

        // Check if the patient is deleted
        assertEquals(1, listOfPatients.getsize());
        assertEquals("test",listOfPatients.searchpatient("John").getname());
    }

    @Test
    public void testDeletePatientNotFound() {

        Patient patientToDelete = new Patient("Unknown", "123456789", "123 Main St.", "NA","password",null);


        listOfPatients.deletePatient(patientToDelete, listOfPatients);

        // Check if list remains unchanged
        assertEquals(1, listOfPatients.getsize());
    }
}

