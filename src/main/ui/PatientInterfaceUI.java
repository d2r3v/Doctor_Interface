package ui;

import model.*;
import model.Event;
import org.json.simple.parser.ParseException;
import persistence.JsonSaver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.io.IOException;
import java.util.EventListener;
import java.util.Scanner;

import javax.swing.*;

// Runs the patient Interface of the application
public class PatientInterfaceUI implements EventListener,ActionListener {

    private JPanel pa;
    private JFrame fr;
    private Patient a1;
    private ListOfPatients lst;
    JPasswordField cpassw;
    JPasswordField passw;
    JPasswordField npassw;
    JPanel pc;
    WindowListener wind;

    //EFFECTS - initializes the JFrame to keep it consistent
    //MODIFIES - this
    public PatientInterfaceUI(JFrame f) {
        this.fr = f;
        wind = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event e1 : EventLog.getInstance()) {
                    System.out.println(e1.getDescription());

                }
            }
        };
        fr.addWindowListener(wind);
    }

    //EFFECTS - displays patient information
    public void displayinfo(Patient a1, ListOfPatients listof) {

        pa = new JPanel();

        if (a1.getname().equals("test")) {
           // System.out.println("Patient not found");
           // DoctorInterface.searchforpatient(listof);
            newauth(null);
        } else {
            this.a1 = a1;
            this.lst = listof;
            labeldisplay();

            pa.setVisible(true);
            printmeds(a1);
            options(a1,listof);
            fr.add(pa);
            fr.setSize(640, 480);
            fr.setVisible(true);
        }
    }

    //EFFECTS - prints the medications of the patient
    public void printmeds(Patient a1) {
        int count = 0;
        ListOfMedication meds = a1.getMeds();
        pa.add(Box.createRigidArea(new Dimension(640, 2)));
        pa.add(new JLabel("Current Medications - "));
        if (meds.getsize() > 0) {
            for (int i = 0; i < meds.getsize(); i++) {
                pa.add(Box.createRigidArea(new Dimension(640, 5)));
                pa.add(new JLabel(meds.getmed(i).getName() + " - "));
                pa.add(Box.createRigidArea(new Dimension(10, 10)));
                pa.add(new JLabel(meds.getmed(i).getDosage() + " - "));
                pa.add(Box.createRigidArea(new Dimension(10, 10)));
                pa.add(new JLabel(meds.getmed(i).getInstructions()));
                pa.add(Box.createRigidArea(new Dimension(10, 10)));
            }
        }
    }

    //EFFECTS - Displays the buttons for the options that can be chosen by the patient
    public void options(Patient a1,ListOfPatients lst) {
        pa.add(Box.createRigidArea(new Dimension(640, 2)));
        pa.add(new JLabel("What would you like to do?"));

        JButton chng = new JButton("Change Password");

        chng.addActionListener(this);

        JButton logout = new JButton("Logout");

        logout.addActionListener(this);

        JButton exit = new JButton("Exit");

        exit.addActionListener(this);

        pa.add(Box.createRigidArea(new Dimension(640, 2)));
        pa.add(chng);
        pa.add(Box.createRigidArea(new Dimension(10, 10)));
        pa.add(logout);
        pa.add(Box.createRigidArea(new Dimension(10, 10)));
        pa.add(exit);
        pa.add(Box.createRigidArea(new Dimension(10, 10)));
    }

    //EFFECTS - catches any and all button clicks and does the appropriate action
    //MODIFIES - this
    public void actionPerformed(ActionEvent e) {

        String s = e.getActionCommand();
        if (s.equals("Exit")) {
            printEvents();
            System.exit(0);
        }
        if (s.equals("Logout")) {
            newauth(new ListOfPatients());
        }
        if (s.equals("Change Password")) {
            fr.remove(pa);
            changepass(a1,lst);
        }

        if (s.equals("Submit")) {
            fr.remove(pc);
            checkpasschange(String.valueOf(cpassw.getPassword()),String.valueOf(passw.getPassword()),
                                                                            String.valueOf(npassw.getPassword()));
        }
        if (s.equals("Yes")) {
            jsave(a1);
        }
        if (s.equals("No")) {
            newauth(new ListOfPatients());
        }
    }

    //EFFECTS - shows a panel to change password
    //MODIFIES - this
    public void changepass(Patient a1, ListOfPatients lst) {


        pc = new JPanel();
        // create a new button
        JButton b = new JButton("Submit");

        b.addActionListener(this);

        JLabel l = new JLabel("enter the current password - ");
        l.setFont(new Font("Sans-Serif",Font.PLAIN,16));

        JLabel q = new JLabel("enter the new password - ");
        q.setFont(new Font("Sans-Serif",Font.PLAIN,16));

        JLabel s = new JLabel("enter the new password again - ");
        q.setFont(new Font("Sans-Serif",Font.PLAIN,16));

        // create a object of JTextField with 16 columns and a given initial text

        cpassw = new JPasswordField("", 16);
        passw  = new JPasswordField("", 16);
        npassw = new JPasswordField("", 16);

        addelements(l,q,s,b);

        fr.setSize(640,480);
        // add panel to frame
        fr.add(pc);
        fr.setVisible(true);
        pc.setVisible(true);

    }

    //EFFECTS - prints all Events
    public void printEvents() {
        for (Event e1 : EventLog.getInstance()) {
            System.out.println(e1.getDescription());
        }
    }


    //EFFECTS - checks if the provided information is correct for password change
    //MODIFIES - this
    public void checkpasschange(String cpass, String npass,String npass1) {
        if (cpass.equals(a1.getPassword())) {
            if (npass.equals(npass1)) {
                a1.setPassword(npass);
                passsave(a1);
            } else {
                changepass(a1, lst);
            }
        } else {
            changepass(a1, lst);
        }
    }

    //EFFECTS - prompts with a screen to confirm the password change before saving to file
    //MODIFIES - this
    public void passsave(Patient a1) {

        JPanel pl = new JPanel();
        // create a new button
        JButton b = new JButton("Yes");
        JButton c = new JButton("No");

        b.addActionListener(this);
        c.addActionListener(this);

        JLabel l = new JLabel("Are you sure you want to save the new password?");
        l.setFont(new Font("Sans-Serif",Font.PLAIN,16));

        JLabel q = new JLabel("Disclaimer - Pressing No would reset all the changes made");
        q.setFont(new Font("Sans-Serif",Font.PLAIN,16));

        pl.add(Box.createRigidArea(new Dimension(600, 120)));
        pl.add(l);
        pl.add(Box.createRigidArea(new Dimension(600, 10)));
        pl.add(q);
        pl.add(Box.createRigidArea(new Dimension(640, 2)));
        pl.add(b);
        pl.add(Box.createRigidArea(new Dimension(20, 20)));
        pl.add(c);

        fr.setSize(640,480);
        // add panel to frame
        fr.add(pl);
        fr.setVisible(true);
        pl.setVisible(true);

    }

    //EFFECTS - Calls the jsonSave method to save details to file
    public void jsave(Patient a1) {
        JsonSaver s = new JsonSaver();
        try {
            s.jsonSave(a1,1);
            newauth(lst);
        } catch (ParseException e1) {
            System.out.println(e1);
        } catch (IOException e1) {
            System.out.println(e1);
        }
    }

    //EFFECTS - disposes the current JFrame and calls login for reauthentication
    public void newauth(ListOfPatients lst) {
        fr.setVisible(false);
        disp();
        AuthenticateUI temp = new AuthenticateUI();
        temp.login(lst);
    }

    //EFFECTS - adds elements to a frame with appropriate spacing
    //MODIFIES - this
    public void addelements(JLabel l,JLabel q,JLabel s,JButton b) {
        pc.add(Box.createRigidArea(new Dimension(600, 120)));
        pc.add(l);
        pc.add(Box.createRigidArea(new Dimension(600, 10)));
        pc.add(cpassw);
        pc.add(Box.createRigidArea(new Dimension(600, 10)));
        pc.add(q);
        pc.add(Box.createRigidArea(new Dimension(600, 10)));
        pc.add(passw);
        pc.add(Box.createRigidArea(new Dimension(600, 10)));
        pc.add(s);
        pc.add(Box.createRigidArea(new Dimension(600, 10)));
        pc.add(npassw);
        pc.add(Box.createRigidArea(new Dimension(600, 10)));
        pc.add(b);
        pc.add(Box.createRigidArea(new Dimension(600, 10)));
    }

    //EFFECTS - adds elements to a frame with appropriate spacing
    //MODIFIES - this
    public void labeldisplay() {
        pa.add(Box.createRigidArea(new Dimension(60, 60)));
        JLabel s = new JLabel("Patient info :");
        pa.add(s);
        pa.add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel n = new JLabel("Name - " + a1.getname());
        pa.add(n);
        pa.add(Box.createRigidArea(new Dimension(10, 10)));

        JLabel m = new JLabel("Mobile - " + a1.getMobile());
        pa.add(m);
        pa.add(Box.createRigidArea(new Dimension(10, 0)));

        JLabel a = new JLabel("Address - " + a1.getAddress());
        pa.add(a);
        pa.add(Box.createRigidArea(new Dimension(10, 0)));
    }


    public  void disp() {
        fr.removeWindowListener(wind);
        fr.dispose();
        fr.addWindowListener(wind);
    }
}

