package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedicationTest {

    @Test
    public void testConstructorAndGetters() {

        Medication medication = new Medication("Advil", "4 times a day", "Take with food");

        // Check if the medication is initialized correctly
        assertEquals("Advil", medication.getName());
        assertEquals("4 times a day", medication.getDosage());
        assertEquals("Take with food", medication.getInstructions());
    }

}
