package ui;

import model.Auth;
import model.Doctor;
import model.ListOfPatients;
import model.Patient;
import org.json.simple.parser.ParseException;
import persistence.JsonReader;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Authenticate {

    String user;
    String pass;


    public  void authenticate() {

        ListOfPatients a2 = Authenticate.newjson();
        Auth a1 = new Auth();
        Scanner sc = new Scanner(System.in);
        do {
          //  System.out.println("Enter the username");
           // user = sc.nextLine();
            //System.out.println("Enter the password");
            //pass = sc.nextLine();
            if (a1.authenticator(user,pass,a2) == 1) {
                System.out.println("Login Sucessful");
                System.out.println("Welcome Doctor");
                DoctorInterface admin = new DoctorInterface();
                admin.doctorinterfacemain(a2);
            } else if (a1.authenticator(user,pass,a2) == 2) {
                System.out.println("Login Sucessful");
                System.out.println("Welcome " + user);
                Patient name = a2.searchpatient(user);
                PatientInterface patient = new PatientInterface();
                patient.patientinterfacemain(name,a2);
            } else {
                System.out.println("wrong username or password");
            }
        } while (a1.authenticator(user, pass,a2) != 1 && a1.authenticator(user, pass,a2) != 2);
    }

    public static ListOfPatients newjson() {
        ListOfPatients a2 = new ListOfPatients();
        JsonReader test = new JsonReader();
        try {
            a2 = test.jsonRead();

        } catch (FileNotFoundException e1) {
            System.out.println(e1);
        } catch (ParseException e1) {
            System.out.println(e1);
        } catch (IOException e1) {
            System.out.println(e1);
        }
        return a2;
    }
}
