package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfMedicationTest {

    private ListOfMedication listOfMedication;

    @BeforeEach
    public void setup() {

        listOfMedication = new ListOfMedication();
    }

    @Test
    public void testConstructor() {
        // Check if the list of medication is initialized
        assertEquals(0, listOfMedication.getsize());
    }

    @Test
    public void testAddMeds() {

        Medication medication = new Medication("Medicine A", "10","NA");

        listOfMedication.addmeds(medication);

        // Check if the medication is added
        assertEquals(1, listOfMedication.getsize());
        assertEquals(medication, listOfMedication.getmed(0));
    }

    @Test
    public void testGetMed() {

        Medication medication1 = new Medication("Medicine A", "10","NA");
        Medication medication2 = new Medication("Medicine B", "20","NA");


        listOfMedication.addmeds(medication1);
        listOfMedication.addmeds(medication2);

        // retrieve the medications from the list
        assertEquals(medication1, listOfMedication.getmed(0));
        assertEquals(medication2, listOfMedication.getmed(1));
    }

    @Test
    public void testGetSize() {

        listOfMedication.addmeds(new Medication("Medicine A", "10","NA"));
        listOfMedication.addmeds(new Medication("Medicine B", "20","NA"));

        // Check the size of the list
        assertEquals(2, listOfMedication.getsize());
    }
}
