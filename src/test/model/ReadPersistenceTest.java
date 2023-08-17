package model;

import org.json.simple.JSONArray;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import static org.junit.jupiter.api.Assertions.*;

public class ReadPersistenceTest {

        private JsonReader jsonReader;

        @BeforeEach
        public void setUp() {
            jsonReader = new JsonReader();
        }

        @Test
        public void testLopInitialization() {

        }

    @Test
    public void testJsonRead() {
        try {
            ListOfPatients patients = jsonReader.jsonRead();
            assertNotNull(patients);
        } catch (Exception e) {
            fail("Exception should not be thrown.");
        }
    }

    @Test
    public void testAddmeds() {
        JSONArray medications = new JSONArray();
        JSONArray med1 = new JSONArray();
        med1.add("Medicine1");
        med1.add("Dosage1");
        med1.add("Instructions1");
        medications.add(med1);

        JSONArray med2 = new JSONArray();
        med2.add("Medicine2");
        med2.add("Dosage2");
        med2.add("Instructions2");
        medications.add(med2);

        ListOfMedication listOfMedication = jsonReader.addmeds(medications);
        assertNotNull(listOfMedication);
        assertEquals(2, listOfMedication.getsize());
        }
}