package ui;

import model.ListOfPatients;
import model.Patient;
import org.json.simple.parser.ParseException;
import persistence.JsonSaver;

import java.io.IOException;
import java.util.*;

import java.util.List;

public class PatientInterface {

    public static void patientinterfacemain(Patient user, ListOfPatients listof) {
        Scanner sc = new Scanner(System.in);
        PatientInterface.displaypatientinfo(user, listof);
        System.out.println("What would you like to do " + user.getname());
        System.out.println("1 - change password");
        System.out.println("2 - Logout");
        System.out.println("3 - Exit");
        int ans = sc.nextInt();
        if (ans == 1) {
            PatientInterface.changepass(user, listof);
        }
        if (ans == 2) {
            Authenticate au = new Authenticate();
            au.authenticate();
        }

        if (ans == 3) {
            System.exit(0);
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
            System.out.println("Current Medications - ");
            DoctorInterface.printMeds(a1);
        }
    }

    public static void changepass(Patient p, ListOfPatients listof) {
        int count = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the current password");
        String cpass = sc.nextLine();
        while (count == 0) {
            if (cpass.equals(p.getPassword())) {
                System.out.println("Enter the new password");
                String npass = sc.nextLine();
                System.out.println("Enter the new password again");
                String npass1 = sc.nextLine();
                if (npass.equals(npass1)) {
                    p.setPassword(npass);
                    System.out.println(p.getPassword());
                    count++;
                    PatientInterface.passsave(p);
                } else {
                    System.out.println("The passwords dont match");
                }
            } else {
                System.out.println("Wrong Password");
                PatientInterface.changepass(p,listof);
            }
        }
    }

    public static void passsave(Patient a1) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you sure you want to save the new password (Y/N)");
        System.out.println("Disclaimer - Entering N would reset all the changes made");
        String ans = sc.nextLine();
        if (ans.equalsIgnoreCase("Y")) {
            JsonSaver s = new JsonSaver();
            try {
                System.out.println(a1.getMeds().getsize());
                s.jsonSave(a1,1);
            } catch (ParseException e1) {
                System.out.println(e1);
            } catch (IOException e1) {
                System.out.println(e1);
            }
        } else {
            Authenticate ab = new Authenticate();
            ab.authenticate();
        }
    }
}
