package ui;

import model.*;
import org.json.simple.parser.ParseException;
import persistence.JsonSaver;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.Scanner;

public class DoctorInterface {


    public static void doctorinterfacemain(ListOfPatients a2) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What would you like to do");
        System.out.println("1 - Search for patient");
        System.out.println("2 - Add new patient");
        System.out.println("3 - Reload all patients");
        System.out.println("4 - Logout");
        System.out.println("5 - Exit");
        int ans = sc.nextInt();
        if (ans == 1) {
            DoctorInterface.searchforpatient(a2);
        } else if (ans == 2) {
            DoctorInterface.addPatient(a2);
        } else if (ans == 3) {
            Authenticate a1 = new Authenticate();
            a1.authenticate();
        } else if (ans == 4) {
        //    System.out.println("Sucessfully Logged Out");
            Authenticate a1 = new Authenticate();
            a1.authenticate();
        } else if (ans == 5) {
            System.exit(0);
        } else {
            DoctorInterface.doctorinterfacemain(a2);
        }
    }

    public static void searchforpatient(ListOfPatients listof) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the name");
        String name = sc.nextLine();
        Patient a1;
        a1 = listof.searchpatient(name);
        displaypatientinfo(a1, listof);
        DoctorInterface.options(a1, listof);

    }

    public static void options(Patient a1, ListOfPatients a2) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What would you like to do with the patient");
        System.out.println("1 - Edit Prescription");
        System.out.println("2 - Edit patient");
        System.out.println("3 - Delete Patient");
        System.out.println("4 - Save Changes");
        System.out.println("5 - Go back");
        int answer = sc.nextInt();
        if (answer == 1) {
            DoctorInterface.editpres(a1,a2);
        }
        if (answer == 2) {
            DoctorInterface.editPatient(a1, a2);
        }
        if (answer == 3) {
            DoctorInterface.askforsave(0,a1);
        }
        if (answer == 4) {
            DoctorInterface.askforsave(1,a1);
        }
        if (answer == 5) {
            DoctorInterface.askforsave(1,a1);
        }
    }

    public static void editpres(Patient a1,ListOfPatients a2) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" what would you like to do with the prescription");
        System.out.println("1 - Add medication");
        System.out.println("2 - Create new prescription");
        int answer = sc.nextInt();
        if (answer == 1) {
            DoctorInterface.addPrescription(a1, a2);
        } else {
            a1.setMeds(new ListOfMedication());
            System.out.println("Created new prescription");
            DoctorInterface.addPrescription(a1,a2);
        }
    }

    public static void displaypatientinfo(Patient a1, ListOfPatients listof) {
        if (a1.getname().equals("test")) {
            System.out.println("Patient not found");
            DoctorInterface.searchforpatient(listof);
        } else {
            System.out.println("Name - " + a1.getname());
            System.out.println("Mobile - " + a1.getMobile());
            System.out.println("Address - " + a1.getAddress());
            System.out.println("Patient History - " + a1.getHistory());
            System.out.println("Current Medications - ");
            DoctorInterface.printMeds(a1);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void printMeds(Patient a1) {
        int count = 0;
        ListOfMedication meds = a1.getMeds();
        if (meds.getsize() > 0) {
            for (int i = 0; i < meds.getsize(); i++) {
                System.out.print(meds.getmed(i).getName() + " - ");
                System.out.print(meds.getmed(i).getDosage() + " - ");
                System.out.println(meds.getmed(i).getInstructions());
            }
        }
    }

    public static void addPrescription(Patient a, ListOfPatients listof) {
        Scanner sc = new Scanner(System.in);
        int ans = 2;
        do {
            System.out.println("Enter the name of the medication");
            String med = sc.nextLine();
            System.out.println("Enter the dosage");
            String dosage = sc.nextLine();
            System.out.println("Enter any additional instructions");
            String inst = sc.nextLine();
            Medication m1 = new Medication(med, dosage, inst);
            a.addmed(m1);
            System.out.println("Medicine added");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Enter 0 to add more and 1 to finish");
            ans = sc.nextInt();
        } while (ans != 1);
        DoctorInterface.displaypatientinfo(a,listof);
        DoctorInterface.options(a,listof);
    }

    public static void addPatient(ListOfPatients listof) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the following details");
        System.out.println("Please enter the name");
        String name = sc.nextLine();
        System.out.println("Please enter the mobile number");
        String mobile = sc.nextLine();
        System.out.println("Please enter the Address");
        String address = sc.nextLine();
        System.out.println("Please enter the History of the patient");
        String history = sc.nextLine();
        System.out.println("New Patient Sucessefully Created");
        Patient a1 = new Patient(name, mobile, address, history,"password",null);
        listof.addpatient(a1);
        System.out.println("Exiting program now");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        DoctorInterface.write(a1);
        System.exit(0);
    }

    public static void editPatient(Patient a1, ListOfPatients listof) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What would you like to edit");
        System.out.println("1 - mobile");
        System.out.println("2 - address");
        System.out.println("3 - History");
        String ans = sc.nextLine();
        System.out.println("Enter the change");
        String aa = sc.nextLine();
        if (ans.equals("1")) {
            DoctorInterface.update(1,aa,a1,listof);
        }
        if (ans.equals("2")) {
            DoctorInterface.update(2,aa,a1,listof);
        }
        if (ans.equals("3")) {
            DoctorInterface.update(3,aa,a1,listof);
        }
    }

    public static void save(Patient a1, int choice) {
        JsonSaver s = new JsonSaver();

        try {
            System.out.println(a1.getMeds().getsize());
            s.jsonSave(a1,choice);
        } catch (ParseException e1) {
            System.out.println(e1);
        } catch (IOException e1) {
            System.out.println(e1);
        }
        Authenticate temp = new Authenticate();
        temp.authenticate();
    }

    public static void askforsave(int n,Patient a1) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to save/delete any changes and logout(Y/N)");
        System.out.println("Disclaimer - Entering N would reset all the changes made");
        String temp = sc.nextLine();
        if (temp.equalsIgnoreCase("Y")) {
            if (n == 0) {
                DoctorInterface.save(a1, 0);
            }
            if (n == 1) {
                DoctorInterface.save(a1, 1);
            }
        } else {
            Authenticate ab = new Authenticate();
            ab.authenticate();
        }
    }

    public static void update(int n,String chng,Patient a1,ListOfPatients lop) {
        if (n == 1) {
            a1.setMobile(chng);
        } else if (n == 2) {
            a1.setAddress(chng);
        } else if (n == 3) {
            a1.setHistory(chng);
        }
        System.out.println("Successfully Updated");
        DoctorInterface.displaypatientinfo(a1,lop);
        DoctorInterface.options(a1,lop);
    }

    public static void write(Patient a1) {
        JsonWriter write = new JsonWriter();
        try {
            write.jsonWrite(a1,null);
        } catch (IOException e1) {
            System.out.println(e1);
        }
    }

}
