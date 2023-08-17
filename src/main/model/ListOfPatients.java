package model;

import ui.DoctorInterface;

import java.util.*;

// Represents a List of patients
public class ListOfPatients {

    List<Patient> listofpatient;

    //MODIFIES - this
    // EFFECTS - initialises and adds a test patient to the list

    public ListOfPatients() {
        listofpatient = new ArrayList<Patient>();
        Patient a1 = new Patient("Adrian", "604 999 771", "5959 Student Union blvd.", "NA","password",null);
        Medication m1 = new Medication("Advil", "4 times a day", "NA");
        Medication m2 = new Medication("Tylenol", "2 times a week", "NA");
        a1.addmed(m1);
        a1.addmed(m2);
        listofpatient.add(a1);

    }

    //EFFECTS - adds patient to the list
    //MODIFIES - this
    public void addpatient(Patient a1) {

        listofpatient.add(a1);
    }

    //EFFECTS - Searches for the patient by using name and then returns the patient if found
    public Patient searchpatient(String name) {
        Patient t = new Patient("test", "1234", "ABCD", "abcd","password",null);
        for (int a = 0; a < listofpatient.size(); a++) {

            Patient a1 = listofpatient.get(a);
            if (a1.getname().equals(name)) {
                t = a1;
            }
        }

        return t;

    }

    //MODIFIES - this
    //EFFECTS - Iterates over the list of patients to find the patient and then deletes it from the list
    public void deletePatient(Patient a1, ListOfPatients listof) {
        Iterator<Patient> iterator = listofpatient.iterator();

        while (iterator.hasNext()) {
            Patient a2 = iterator.next();
            if (a2.getname().equals(a1.getname())) {
                iterator.remove();
                break;
            }
        }
    }

    // MODIFIES - this
    // EFFECTS - updates all the values of the Patient in the list of patients
    public  void updatepatient(Patient p1) {

        for (int a = 0; a < listofpatient.size(); a++) {

            Patient a1 = listofpatient.get(a);
            if (a1.getname().equals(p1.getname())) {
                a1.setMobile(p1.getMobile());
                a1.setName(p1.getname());
                a1.setAddress(p1.getAddress());
                a1.setPassword(p1.getPassword());
            }
        }
    }

    public int getsize() {
        return listofpatient.size();
    }

    public Patient getval(int n) {
        return listofpatient.get(n);
    }
}


