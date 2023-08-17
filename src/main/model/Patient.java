package model;

import java.util.ArrayList;
import java.util.List;

// Represents a Patient that has a name,mobile,address,list of prescribed meds , patient history and a password
public class Patient {

    String name;
    String mobile;
    String address;
    ListOfMedication meds;
    String history;
    String password;

    //MODIFIES - this
    //EFFECTS - Initializes all the variables for patient
    public Patient(String name,String mobile,String address,String history,String pass,ListOfMedication medication) {
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.history = history;
        this.password = pass;
        if (medication == null) {
            meds = new ListOfMedication();
        } else {
            meds = medication;
        }
    }

    //MODIFIES - this
    //EFFECTS - adds a medication to the list of meds
    public void addmed(Medication m1) {
        meds.addmeds(m1);
    }

    public String getPassword() {
        return password;
    }

    public void setMeds(ListOfMedication meds) {
        this.meds = meds;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getname() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getHistory() {
        return history;
    }

    public ListOfMedication getMeds() {
        return meds;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setName(String name) {
        this.name = name;
    }
}
