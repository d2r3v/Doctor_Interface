package persistence;

import model.ListOfMedication;
import model.ListOfPatients;
import model.Medication;
import model.Patient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.*;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.IOException;

//EFFECTS - this class reads all the json files and converts the data to patients and stores it in list of patients
public class JsonReader {

    List<String> lop;

    //EFFECTS - Initlalizes a list with all the file names in the folder
    // MODIFIES - this
    public JsonReader() {
        lop = new ArrayList<>();

        String folderPath = "./data";
        File folder = new File(folderPath);

        File[] files = folder.listFiles();
        for (File file : files) {
            lop.add("data/" + file.getName());
        }
    }

    // EFFECTS - reads all the files and converts all data to patients and add all patients to list of patients
    // returns a listofpatients with all patients.
    public ListOfPatients jsonRead() throws ParseException, FileNotFoundException, IOException {

        ListOfPatients lst = new ListOfPatients();

        List<String> fnames = lop;
        for (int i = 0; i < fnames.size(); i++) {

            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(fnames.get(i));

            Object jsonObj = parser.parse(reader);

            JSONObject jsonObject = (JSONObject) jsonObj;

            String name = (String) jsonObject.get("name");

            String hist = (String) jsonObject.get("History");

            String mob = (String) jsonObject.get("Mobile");

            String addr = (String) jsonObject.get("Address");

            String pass = (String) jsonObject.get("Password");

            JSONArray medications = (JSONArray) jsonObject.get("Medications");

            ListOfMedication  med = JsonReader.addmeds(medications);

            reader.close();

            lst = init(name, hist, mob, addr, med,pass, lst);
        }
        return lst;
    }

    //EFFECTS - Initializes the patient with all the given data and adds the patient to a list which is returned
    public static ListOfPatients init(
            String name,String hist, String mobile,
            String addr,ListOfMedication meds, String pass,
            ListOfPatients lst) {

        Patient temp = new Patient(name,mobile,addr,hist,pass,meds);
        lst.addpatient(temp);
        return lst;
    }

    //EFFECTS - interprets the JsonArray and converts it to a list of medications.
    public static ListOfMedication addmeds(JSONArray medications) {

        List<List<String>> allMedications = new ArrayList<>();
        ListOfMedication temp = new ListOfMedication();
        for (int a = 0; a < medications.size(); a++) {
            JSONArray medicationList = (JSONArray) medications.get(a);
            List<String> medication = new ArrayList<>();

            for (int b = 0; b < medicationList.size(); b++) {
                medication.add(medicationList.get(b).toString());
            }
            allMedications.add(medication);
        }

        for (int s = 0; s < allMedications.size();s++) {
            List<String> s1 = allMedications.get(s);
            temp.addmeds(new Medication(s1.get(0),s1.get(1),s1.get(2)));
        }

        return temp;
    }
}