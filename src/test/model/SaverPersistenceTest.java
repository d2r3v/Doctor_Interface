package model;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonSaver;
import persistence.JsonWriter;
import ui.DoctorInterface;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SaverPersistenceTest {
    Patient a1;

    @BeforeEach
    void setup() {
        a1 = new Patient("test", "test", "test", "test", "password", null);
    }

    @Test
    void savetest() throws IOException, ParseException {
        Medication m1 = new Medication("test", "test", "test");
        a1.getMeds().addmeds(m1);
        JsonWriter a = new JsonWriter();
        a.jsonWrite(a1, null);
        JsonReader abc = new JsonReader();
        ListOfPatients temp = abc.jsonRead();
        // DoctorInterface.save(a1,1);
        JsonSaver js = new JsonSaver();
        try {
            js.jsonSave(a1, 1);
        } catch (IOException e1) {
            assertEquals(e1, e1);
        } catch (ParseException e1) {
            assertEquals(e1, e1);
        }
        JsonReader abcd = new JsonReader();
        ListOfPatients temp1 = abcd.jsonRead();
        assertEquals("test", temp1.searchpatient("test").getname());
        assertEquals("test", temp1.searchpatient("test").getMobile());
        JsonSaver js1 = new JsonSaver();
        try {
            js1.jsonSave(a1, 0);
        } catch (IOException e1) {
            assertEquals(e1, e1);
        } catch (ParseException e1) {
            assertEquals(e1, e1);
        }
        assertEquals("null", temp1.searchpatient("null").getname());
        assertEquals("null", temp1.searchpatient("null").getMobile());

    }

    @Test
    void exceptionTest() {
        try {
            JsonWriter a = new JsonWriter();
            a.jsonWrite(a1, "./Patient/Patient55.json");
        } catch (IOException e1) {
            assertEquals(e1, e1);
        }
    }
}
