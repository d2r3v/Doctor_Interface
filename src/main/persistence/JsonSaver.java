package persistence;

import model.Event;
import model.EventLog;
import model.ListOfMedication;
import model.Patient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ui.Authenticate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonSaver {

    List<String> lop;

    //EFFECTS - initializes list with names of patients files
    // MODIFIES - this
    public JsonSaver() {
        File dir = new File("./data");
        int num = dir.list().length;
        lop = new ArrayList<>();


        for (int i = 1; i <= num; i++) {
            String temp = "data/Patient";
            lop.add(temp + String.valueOf(i) + ".json");
        }
    }

    // EFFECTS - saves a patient by passing it to write after finding the file name of the patient,empties file to del
    public void jsonSave(Patient a1, int choice) throws ParseException, FileNotFoundException, IOException {

        List<String> fnames = lop;
        File f1;
        for (int i = 0; i < fnames.size(); i++) {

            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(fnames.get(i));

            Object jsonObj = parser.parse(reader);

            JSONObject jsonObject = (JSONObject) jsonObj;

            String name = (String) jsonObject.get("name");
            if (name.equals(a1.getname())) {
                f1 = new File(fnames.get(i));
                if (choice == 1) {
                    EventLog.getInstance().logEvent(new Event("Patient Details Updated"));
                    JsonWriter temp = new JsonWriter();
                    temp.jsonWrite(a1, fnames.get(i));
                } else {
                    EventLog.getInstance().logEvent(new Event("Patient Deleted"));
                    JsonWriter temp = new JsonWriter();
                    Patient temp1 = new Patient("null","null","null","null","null",new ListOfMedication());
                    temp.jsonWrite(temp1, fnames.get(i));
                }
            }
        }
    }
}
