package ui;

import model.*;
import model.Event;
import org.json.simple.parser.ParseException;
import persistence.JsonReader;
import ui.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// Authenticates the login by comparing with existing database , has a username and password
public class AuthenticateUI implements ActionListener  {

    private JFrame fr;
    private JLabel la;
    private JLabel qa;
    private JPanel pa;

    private JTextField log;
    private JTextField passw;
    private ListOfPatients lst;

    //MODIFIES - this
    //EFFECTS - initializes a JFrame and sets its location on the screen.
    public AuthenticateUI() {
        fr = new JFrame("Doctor's Application - login");
        // f.setMinimumSize(new Dimension(600,400));
        fr.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event e1 : EventLog.getInstance()) {
                    System.out.println(e1.getDescription());
                }
            }
        });
        fr.setLocation(450, 250);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    //REQUIRES - list != null
    //MODIFIES - this
    //EFFECTS - adds a login page to the frame
    public void login(ListOfPatients list) {

        this.lst = list;

        JLabel temp = new JLabel("Login Page");

        temp.setFont(new Font("Sans-Serif", Font.BOLD, 18));

        // create a label to display text

        // create a new button
        JButton b = new JButton("Submit");


        // addActionListener to button
        b.addActionListener(this);

        la = new JLabel("Username - ");
        la.setFont(new Font("Sans-Serif",Font.PLAIN,16));

        qa = new JLabel("Password - ");
        qa.setFont(new Font("Sans-Serif",Font.PLAIN,16));

        // create a object of JTextField with 16 columns and a given initial text
        log = new JTextField("", 16);
        passw = new JPasswordField("", 16);

        // create a panel to add buttons and textfield
        pa = new JPanel();


        displayd(temp,b);

        // add panel to frame
        fr.add(pa);

        // set the size ofr frame
        fr.setSize(640, 480);

        // f.show();
        fr.setVisible(true);
    }

    //EFFECTS - when submit is clicked, gets the username and password and passes it to auth
    //MODIFIES - this
    public void actionPerformed(ActionEvent e) {

        String s = e.getActionCommand();
        if (s.equals("Submit")) {
            String user = log.getText();
            String pass = passw.getText();
            auth(user,pass);
        }
    }

    //EFFECTS - authenticates the doctor or patients and calls the respective methods
    public void auth(String user, String pass) {
        ListOfPatients a2;
        if (lst.getsize() == 1) {
            a2 = newjson();
        } else {
            a2 = lst;
        }
        Auth a1 = new Auth();
        if (a1.authenticator(user, pass, a2) == 1) {
           // System.out.println("Welcome Doctor");
            fr.setVisible(false);
            fr.dispose();
            DoctorInterfaceUI admin = new DoctorInterfaceUI();
            admin.doctorInterfacemain(a2);
        } else if (a1.authenticator(user, pass, a2) == 2) {
            //System.out.println("Welcome " + user);
            Patient name = a2.searchpatient(user);
            // PatientInterface patient = new PatientInterface();
            PatientInterfaceUI h = new PatientInterfaceUI(fr);
            pa.setVisible(false);
            h.displayinfo(name,a2);
        } else {
            fr.setVisible(false); //you can't see me!
            fr.dispose();
            AuthenticateUI temp = new AuthenticateUI();
            temp.login(lst);
        }
    }

    //EFFECTS - adds elements to a frame with appropriate spacing
    //MODIFIES - this
    public void displayd(JLabel temp, JButton b) {
        BufferedImage myPicture = new BufferedImage(200,200,1);
        try {
            myPicture = ImageIO.read(new File("./lib/2.jpg"));

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));

        pa.add(Box.createRigidArea(new Dimension(600, 10)));
        pa.add(temp);
        pa.add(Box.createRigidArea(new Dimension(600, 5)));
        pa.add(la);
        pa.add(Box.createRigidArea(new Dimension(600, 5)));
        pa.add(log);
        pa.add(Box.createRigidArea(new Dimension(600, 5)));
        pa.add(qa);
        pa.add(Box.createRigidArea(new Dimension(600, 5)));
        pa.add(passw);
        pa.add(Box.createRigidArea(new Dimension(600, 5)));
        pa.add(b);
        pa.add(Box.createRigidArea(new Dimension(600, 5)));
        pa.add(picLabel);

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
