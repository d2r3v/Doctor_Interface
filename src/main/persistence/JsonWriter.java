package persistence;

import model.Event;
import model.EventLog;
import model.ListOfMedication;
import model.Patient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ui.Authenticate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter {

    //EFFECTS - writes new patient to file or updates old records if given file name
    public void jsonWrite(Patient a1,String given) throws IOException {
        JSONObject obj = new JSONObject();

        obj.put("name",a1.getname());
        obj.put("Address",a1.getAddress());
        obj.put("Mobile",a1.getMobile());
        obj.put("History",a1.getHistory());
        obj.put("Password",a1.getPassword());
        JSONArray meds = JsonWriter.med(a1);
        obj.put("Medications",meds);

        String fname = JsonWriter.getfile(given,a1);
        try {

            FileWriter file = new FileWriter(fname);
            file.write(obj.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //EFFECTS - takes all medications and converts them to a JsonArray to store
    public static JSONArray med(Patient a1) {

        JSONArray meds = new JSONArray();
        for (int i = 0; i < a1.getMeds().getsize();i++) {

            JSONArray temp = new JSONArray();
            temp.add(a1.getMeds().getmed(i).getName());
            temp.add(a1.getMeds().getmed(i).getDosage());
            temp.add(a1.getMeds().getmed(i).getInstructions());
            meds.add(temp);
        }

        return meds;

    }

    //EFFECTS - Gets the file name where the data needs to be written
    public static String getfile(String given,Patient a1) {
        String fname;
        if (given != null) {
            fname = given;
        } else {
            EventLog.getInstance().logEvent(new Event("New Patient Added"));
            File dir = new File("./data");
            int num = dir.list().length + 1;
            fname = "data/Patient" + String.valueOf(num) + ".json";
        }
        return fname;
    }
}

