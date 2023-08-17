package model;

import java.util.ArrayList;

// Reprsents a ListofMedications with a list of Medications
public class ListOfMedication {

    ArrayList<Medication> m1;

    // MODIFIES - this
    //EFFECTS - As a constructor, Initializes the list of medication
    public ListOfMedication() {

        this.m1 = new ArrayList<Medication>();

    }

    // MODIFIES - this
    //EFFECTS - adds a new medication to the list
    public void addmeds(Medication meds) {
        m1.add(meds);
    }

    public Medication getmed(int a) {
        return m1.get(a);
    }

    public int getsize() {
        return m1.size();
    }
}
