package ui;

import model.*;
import model.Event;
import org.json.simple.parser.ParseException;
import persistence.JsonSaver;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

//Runs the Doctor Interface of the app
public class DoctorInterfaceUI implements ActionListener {

    private JFrame fr;
    private ListOfPatients lst;
    private JTextField name;
    private JPanel pa;
    private JPanel pl;
    private JPanel ne;
    private Patient a1;
    private Boolean del;
    private JPanel ed;
    private JPanel ch;
    private JTextField chnm;
    private JPanel editpres;
    private JTextField nm;
    private JTextField dg;
    private JTextField inst;
    private JPanel am;
    private JPanel adp;
    private JTextField nam;
    private JTextField mobi;
    private JTextField addre;
    private JTextField hist;
    private JPanel dispa;
    WindowListener wind;

    //EFFECTS - Initializes the frame and sets its location on the screen
    //MODIFIES - this
    public DoctorInterfaceUI() {
        this.fr = new JFrame("Doctor Interface");
        wind = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event e1 : EventLog.getInstance()) {
                    System.out.println(e1.getDescription());

                }
            }
        };
        fr.addWindowListener(wind);

        del = false;
        fr.setLocation(450, 250);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //EFFECTS - Displays all the options that can be done by the doctor
    //MODIFIES - this
    public void doctorInterfacemain(ListOfPatients a2) {
        this.lst = a2;
        JLabel wel = new JLabel("Welcome Doctor");

        JLabel op = new JLabel("What would you like to do?");

        JButton search = new JButton("Search for patient");
        search.addActionListener(this);

        JButton add = new JButton("Add new patient");
        add.addActionListener(this);

        JButton disp = new JButton("Display all patients");
        disp.addActionListener(this);

        JButton reload = new JButton("Reload all patients");
        reload.addActionListener(this);

        JButton logout = new JButton("Logout");
        logout.addActionListener(this);

        JButton exit = new JButton("Exit");
        exit.addActionListener(this);

        pa = new JPanel();

        mainaddfr(wel,op,search,add,reload,logout,exit,disp);

        fr.add(pa);
        fr.setSize(640, 480);
        fr.setVisible(true);
    }

    //EFFECTS - adds elements to a frame with appropiate spacing
    //MODIFIES - this
    public void mainaddfr(JLabel wel,JLabel op,JButton search,JButton add,
                          JButton reload,JButton logout,JButton exit,JButton disp) {
        pa.add(Box.createRigidArea(new Dimension(640, 10)));
        pa.add(wel);
        pa.add(Box.createRigidArea(new Dimension(600, 10)));
        pa.add(op);
        pa.add(Box.createRigidArea(new Dimension(600, 10)));
        pa.add(search);
        pa.add(Box.createRigidArea(new Dimension(10, 10)));
        pa.add(add);
        pa.add(Box.createRigidArea(new Dimension(10, 10)));
        pa.add(disp);
        pa.add(Box.createRigidArea(new Dimension(600, 10)));
        pa.add(reload);
        pa.add(Box.createRigidArea(new Dimension(10, 10)));
        pa.add(logout);
        pa.add(Box.createRigidArea(new Dimension(10, 10)));
        pa.add(exit);
        pa.add(Box.createRigidArea(new Dimension(10, 10)));

    }

    //EFFECTS - Catches any button clicks and takes the appropriate action
    //MODIFIES - this
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.equals("Search for patient")) {
            fr.remove(pa);
            searchforpatient();
        }
        if (s.equals("Add new patient")) {
            fr.remove(pa);
            addpat();
        }
        if (s.equals("Reload all patients")) {
            fr.setVisible(false);
            disp();
            AuthenticateUI temp = new AuthenticateUI();
            temp.login(new ListOfPatients());
        }
        if (s.equals("Logout")) {
            newauth(new ListOfPatients());
        }
        if (s.equals("Exit")) {
            printEvents();
            System.exit(0);
        }
        actioncheck(s);
    }

    //EFFECTS - Prints all events
    public void printEvents() {
        for (Event e1 : EventLog.getInstance()) {
            System.out.println(e1.getDescription());
        }
    }


    public void disp() {
        fr.removeWindowListener(wind);
        fr.dispose();
        fr.addWindowListener(wind);
    }

    //EFFECTS - displays a frame to search the patient
    //MODIFIES - this
    public void searchforpatient() {

        JLabel nm = new JLabel("Please enter the name of the patient");

        name = new JTextField("", 16);

        JButton bt = new JButton("Search");

        bt.addActionListener(this);

        pl = new JPanel();

        pl.add(Box.createRigidArea(new Dimension(600, 10)));
        pl.add(nm);
        pl.add(Box.createRigidArea(new Dimension(600, 10)));
        pl.add(name);
        pl.add(Box.createRigidArea(new Dimension(600, 10)));
        pl.add(bt);
        fr.add(pl);
        fr.setVisible(true);
    }

    //EFFECTS - Displays the information of the patient
    //MODIFIES - this
    public void displaypatientinfo(Patient a1) {

        if (a1.getname().equals("test")) {
            // System.out.println("Patient not found");
            // DoctorInterface.searchforpatient(lst);
            searchforpatient();
        } else {
            ne = new JPanel();
            labeldisplay(a1);
            ne.setVisible(true);
            printmeds(a1);
            addoptions(a1);
            fr.add(ne);
            fr.setSize(640, 480);
            fr.setVisible(true);
        }
    }

    //EFFECTS - adds elements to a frame
    //MODIFIES - this
    public void labeldisplay(Patient a1) {
        ne.add(Box.createRigidArea(new Dimension(60, 60)));
        JLabel s = new JLabel("Patient info :");
        ne.add(s);
        ne.add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel n = new JLabel("Name - " + a1.getname());
        ne.add(n);
        ne.add(Box.createRigidArea(new Dimension(10, 10)));

        JLabel m = new JLabel("Mobile - " + a1.getMobile());
        ne.add(m);
        ne.add(Box.createRigidArea(new Dimension(10, 0)));

        JLabel a = new JLabel("Address - " + a1.getAddress());
        ne.add(a);
        ne.add(Box.createRigidArea(new Dimension(10, 0)));
        JLabel h = new JLabel("History - " + a1.getHistory());
        ne.add(h);
        ne.add(Box.createRigidArea(new Dimension(10, 0)));
    }

    //EFFECTS - displays medication of the patient
    //MODIFIES - this
    public void printmeds(Patient a1) {
        ListOfMedication meds = a1.getMeds();
        ne.add(Box.createRigidArea(new Dimension(640, 2)));
        ne.add(new JLabel("Current Medications - "));
        if (meds.getsize() > 0) {
            for (int i = 0; i < meds.getsize(); i++) {
                ne.add(Box.createRigidArea(new Dimension(640, 5)));
                ne.add(new JLabel(meds.getmed(i).getName() + " - "));
                ne.add(Box.createRigidArea(new Dimension(10, 10)));
                ne.add(new JLabel(meds.getmed(i).getDosage() + " - "));
                ne.add(Box.createRigidArea(new Dimension(10, 10)));
                ne.add(new JLabel(meds.getmed(i).getInstructions()));
                ne.add(Box.createRigidArea(new Dimension(10, 10)));
            }
        }
    }

    //EFFECTS - Displays options that can be done to the patient
    //MODIFIES - this
    public void addoptions(Patient a1) {
        JLabel in = new JLabel("What would you like to do with the patient");

        JButton editpre = new JButton("Edit Prescription");

        editpre.addActionListener(this);

        JButton editpa = new JButton("Edit Patient");
        editpa.addActionListener(this);

        JButton del = new JButton("Delete Patient");
        del.addActionListener(this);

        JButton savec = new JButton("Save Changes");
        savec.addActionListener(this);


        ne.add(Box.createRigidArea(new Dimension(640, 5)));
        ne.add(in);
        ne.add(Box.createRigidArea(new Dimension(640, 5)));
        ne.add(editpre);
        ne.add(Box.createRigidArea(new Dimension(10, 10)));
        ne.add(editpa);
        ne.add(Box.createRigidArea(new Dimension(10, 10)));
        ne.add(del);
        ne.add(Box.createRigidArea(new Dimension(10, 10)));
        ne.add(savec);
        ne.add(Box.createRigidArea(new Dimension(10, 10)));
    }

    //EFFECTS - catches any button click and does the appropriate action
    //MODIFIES - this
    public void actioncheck(String s) {

        if (s.equals("Edit Prescription")) {
            fr.remove(ne);
            editpre();
        }
        if (s.equals("Edit Patient")) {
            fr.remove(ne);
            editpat();
        }
        if (s.equals("Delete Patient")) {
            fr.remove(ne);
            del = true;
            savep();
        }
        if (s.equals("Save Changes")) {
            fr.remove(ne);
            savep();
        }
        editpatop(s);
        tem(s);
    }

    //EFFECTS - catches button clicks and does the appropriate action
    //MODIFIES - this
    public void tem(String s) {

        if (s.equals("Yes")) {
            if (del) {
                del = false;
                lst.deletePatient(a1,lst);
                jsave(a1, 0);
            } else {
                jsave(a1, 1);
            }
        }
        if (s.equals("No")) {
            newauth(new ListOfPatients());
        }

        if (s.equals("Display all patients")) {
            fr.remove(pa);
            dispall();
        }
    }

    //EFFECTS - Displays a save page that prompts if the user wants to write all the changes to file
    //MODIFIES - this
    public void savep() {

        JPanel pl = new JPanel();
        // create a new button
        JButton b = new JButton("Yes");
        JButton c = new JButton("No");

        b.addActionListener(this);
        c.addActionListener(this);

        JLabel l = new JLabel("Are you sure you want to save/delete?");
        l.setFont(new Font("Sans-Serif", Font.PLAIN, 16));

        JLabel q = new JLabel("Disclaimer - Pressing No would reset all the changes made");
        q.setFont(new Font("Sans-Serif", Font.PLAIN, 16));

        pl.add(Box.createRigidArea(new Dimension(600, 120)));
        pl.add(l);
        pl.add(Box.createRigidArea(new Dimension(600, 10)));
        pl.add(q);
        pl.add(Box.createRigidArea(new Dimension(640, 2)));
        pl.add(b);
        pl.add(Box.createRigidArea(new Dimension(20, 20)));
        pl.add(c);

        fr.setSize(640, 480);
        // add panel to frame
        fr.add(pl);
        fr.setVisible(true);

    }

    //EFFECTS - writes all the changes to file
    //MODIFIES - this
    public void jsave(Patient a1, int ch) {
        JsonSaver s = new JsonSaver();
        try {
            s.jsonSave(a1, ch);
            newauth(lst);
        } catch (ParseException e1) {
            System.out.println(e1);
        } catch (IOException e1) {
            System.out.println(e1);
        }
    }

    //EFFECTS - Disposes the current frame and sends for reauthentication
    //MODIFIES - this
    public void newauth(ListOfPatients lst) {
        fr.setVisible(false);
        disp();
        AuthenticateUI temp = new AuthenticateUI();
        temp.login(lst);
    }

    //EFFECTS - adds buttons to the frame to edit patient details
    //MODIFIES - this
    public void editpat() {

        ed = new JPanel();
        temp1();
        JButton nam = new JButton("Name");
        nam.addActionListener(this);
        ed.add(nam);
        ed.add(Box.createRigidArea(new Dimension(10, 0)));
        JButton mob = new JButton("Mobile");
        mob.addActionListener(this);
        ed.add(mob);
        ed.add(Box.createRigidArea(new Dimension(10, 0)));
        JButton add = new JButton("Address");
        add.addActionListener(this);
        ed.add(add);
        ed.add(Box.createRigidArea(new Dimension(10, 0)));
        JButton hist = new JButton("History");
        hist.addActionListener(this);
        ed.add(hist);

        fr.add(ed);
        ed.setVisible(true);
        fr.setVisible(true);

    }

    //EFFECTS - Adds a frame that allows the doctor to edit the patient
    //MODIFIES - this
    public void temp1() {
        JLabel edi = new JLabel("Please select what you would like to edit");

        ed.add(Box.createRigidArea(new Dimension(60, 60)));
        JLabel s = new JLabel("Patient info :");
        ed.add(s);
        ed.add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel n = new JLabel("Name - " + a1.getname());
        ed.add(n);
        ed.add(Box.createRigidArea(new Dimension(10, 10)));

        JLabel m = new JLabel("Mobile - " + a1.getMobile());
        ed.add(m);
        ed.add(Box.createRigidArea(new Dimension(10, 0)));

        JLabel a = new JLabel("Address - " + a1.getAddress());
        ed.add(a);
        ed.add(Box.createRigidArea(new Dimension(10, 0)));

        JLabel h = new JLabel("History - " + a1.getHistory());
        ed.add(h);
        ed.add(Box.createRigidArea(new Dimension(10, 0)));

        ed.add(Box.createRigidArea(new Dimension(640, 10)));
        ed.add(edi);
        ed.add(Box.createRigidArea(new Dimension(640, 10)));

    }

    //EFFECTS - catches any and all button clicks and does the appropriate action
    //MODIFIES - this
    public void editpatop(String s) {

        if (s.equals("Name")) {
            fr.remove(ed);
            change(1);
        }
        if (s.equals("Mobile")) {
            fr.remove(ed);
            change(2);
        }
        if (s.equals("Address")) {
            fr.remove(ed);
            change(3);
        }
        if (s.equals("History")) {
            fr.remove(ed);
            change(4);
        }
        makechng(s);
    }

    //EFFECTS - displays a textbox to enter the change to be done to the patient
    //MODIFIES - this
    public void change(int r) {

        ch = new JPanel();

        JLabel chn = new JLabel("Enter the Change");
        ch.add(Box.createRigidArea(new Dimension(600, 60)));
        ch.add(chn);
        ch.add(Box.createRigidArea(new Dimension(640, 10)));
        chnm = new JTextField("", 16);
        ch.add(chnm);
        ch.add(Box.createRigidArea(new Dimension(640, 10)));
        chkr(r);
        ch.setVisible(true);
        fr.add(ch);
        fr.setVisible(true);
    }

    //EFFECTS - Initialises buttons based on input
    //MODIFIES - this
    public void chkr(int r) {
        if (r == 1) {
            JButton chgnm = new JButton("Change Name!");
            chgnm.addActionListener(this);
            ch.add(chgnm);
        }
        if (r == 2) {
            JButton chgnm = new JButton("Change Mobile!");
            chgnm.addActionListener(this);
            ch.add(chgnm);
        }
        if (r == 3) {
            JButton chgnm = new JButton("Change Address!");
            chgnm.addActionListener(this);
            ch.add(chgnm);
        }
        if (r == 4) {
            JButton chgnm = new JButton("Change History!");
            chgnm.addActionListener(this);
            ch.add(chgnm);
        }
    }

    //EFFECTS - catches any and all button clicks and does the appropriate action
    //MODIFIES - this
    public void makechng(String s) {
        if (s.equals("Change Name!")) {
            a1.setName(chnm.getText());
            ops();
        }
        if (s.equals("Change Mobile!")) {
            a1.setMobile(chnm.getText());
            ops();
        }
        if (s.equals("Change Address!")) {
            a1.setAddress(chnm.getText());
            ops();
        }
        if (s.equals("Change History!")) {
            a1.setHistory(chnm.getText());
            ops();
        }
        editpresop(s);
    }

    //EFFECTS - Removes a panel and displays the patient info again
    //MODIFIES - this
    public void ops() {
        fr.remove(ch);
        displaypatientinfo(a1);
        addoptions(a1);
    }

    //EFFECTS - Displays a frame where the Doctor can edit the prescription
    //MODIFIES - this
    public void editpre() {
        editpres = new JPanel();
        JLabel wh = new JLabel(" what would you like to do with the prescription?");
        editpres.add(Box.createRigidArea(new Dimension(600, 30)));
        editpres.add(wh);
        editpres.add(Box.createRigidArea(new Dimension(600, 10)));
        JButton jb = new JButton("Add new medication");
        jb.addActionListener(this);
        editpres.add(jb);
        editpres.add(Box.createRigidArea(new Dimension(10, 10)));
        JButton pb = new JButton("Create new prescription");
        pb.addActionListener(this);
        editpres.add(pb);
        editpres.setVisible(true);
        fr.add(editpres);
        fr.setVisible(true);
    }

    //EFFECTS - Catches all button clicks and does the appropriate actions
    //MODIFIES - this
    public void editpresop(String s) {
        if (s.equals("Add new medication")) {
            fr.remove(editpres);
            addmed(1);
        }
        if (s.equals("Create new prescription")) {
            fr.remove(editpres);
            addmed(2);
        }
        if (s.equals("Add Medication!")) {
            a1.addmed(new Medication(nm.getText(), dg.getText(), inst.getText()));
            fr.remove(am);
            displaypatientinfo(a1);
        }
        addpatop(s);
    }

    //EFFECTS - Displays a panel to add medications to the patient
    //MODIFIES - this
    public void addmed(int choice) {
        am = new JPanel();
        if (choice == 2) {
            a1.setMeds(new ListOfMedication());
        }
        JLabel jl = new JLabel("Enter the details of the medicine to be added");
        JLabel n = new JLabel("Name of Medication -");
        JLabel d = new JLabel("Dosage -");
        JLabel i = new JLabel("Instructions -");

        nm = new JTextField("",16);
        dg = new JTextField("",16);
        inst = new JTextField("",16);

        JButton a = new JButton("Add Medication!");
        a.addActionListener(this);
        addopp(jl,n,d,i,a);

        fr.add(am);
        fr.setVisible(true);
        am.setVisible(true);
    }

    //EFFECTS - adds elements to a frame
    //MODIFIES - this
    public void addopp(JLabel jl,JLabel n,JLabel d,JLabel i,JButton a) {
        am.add(Box.createRigidArea(new Dimension(600, 30)));
        am.add(jl);
        am.add(Box.createRigidArea(new Dimension(600, 10)));
        am.add(n);
        am.add(Box.createRigidArea(new Dimension(600, 10)));
        am.add(nm);
        am.add(Box.createRigidArea(new Dimension(600, 10)));
        am.add(d);
        am.add(Box.createRigidArea(new Dimension(600, 10)));
        am.add(dg);
        am.add(Box.createRigidArea(new Dimension(600, 10)));
        am.add(i);
        am.add(Box.createRigidArea(new Dimension(600, 10)));
        am.add(inst);
        am.add(Box.createRigidArea(new Dimension(600, 10)));
        am.add(a);
        am.add(Box.createRigidArea(new Dimension(600, 10)));
    }

    //EFFECTS - Displays a frame to add a new patient
    //MODIFIES - this
    public void addpat() {

        JLabel e = new JLabel("Enter the following details about the patient");
        JLabel nm = new JLabel("Name -");
        nam = new JTextField("",16);
        JLabel mb = new JLabel("Mobile -");
        mobi = new JTextField("",16);
        JLabel addr = new JLabel("Address -");
        addre = new JTextField("",16);
        JLabel his = new JLabel("History");
        hist = new JTextField("",16);
        JButton bt = new JButton("Add Patient!");
        bt.addActionListener(this);

        adp = new JPanel();

        adpop(e,nm,mb,addr,his,bt);

        fr.add(adp);
        fr.setVisible(true);
        adp.setVisible(true);
    }

    //EFFECTS - Adds elements to a frame
    //MODIFIES - this
    public void adpop(JLabel e,JLabel nm,JLabel mb,JLabel addr,JLabel his,JButton bt) {
        adp.add(Box.createRigidArea(new Dimension(600, 30)));
        adp.add(e);
        adp.add(Box.createRigidArea(new Dimension(600, 10)));
        adp.add(nm);
        adp.add(Box.createRigidArea(new Dimension(600, 10)));
        adp.add(nam);
        adp.add(Box.createRigidArea(new Dimension(600, 10)));
        adp.add(mb);
        adp.add(Box.createRigidArea(new Dimension(600, 10)));
        adp.add(mobi);
        adp.add(Box.createRigidArea(new Dimension(600, 10)));
        adp.add(addr);
        adp.add(Box.createRigidArea(new Dimension(600, 10)));
        adp.add(addre);
        adp.add(Box.createRigidArea(new Dimension(600, 10)));
        adp.add(his);
        adp.add(Box.createRigidArea(new Dimension(600, 10)));
        adp.add(hist);
        adp.add(Box.createRigidArea(new Dimension(600, 10)));
        adp.add(bt);
        adp.add(Box.createRigidArea(new Dimension(600, 10)));

    }

    //EFFECTS - catches all button clicks and does the appropriate actions
    //MODIFIES - this
    public void addpatop(String s) {
        if (s.equals("Add Patient!"))  {
            ListOfMedication lom = new ListOfMedication();
            a1 = new Patient(nam.getText(),mobi.getText(),addre.getText(),hist.getText(),"password",lom);
            lst.addpatient(a1);
            jwrite(a1);
            fr.setVisible(false);
            disp();
            AuthenticateUI temp = new AuthenticateUI();
            temp.login(lst);
        }
        if (s.equals("Search")) {
            a1 = lst.searchpatient(name.getText());
            fr.remove(pl);
            displaypatientinfo(a1);
        }
        if (s.equals("Go Back")) {
            fr.remove(dispa);
            doctorInterfacemain(lst);
        }
    }

    //EFFECTS - calls the Json Writer class with a try catch block
    public void jwrite(Patient a1) {
        JsonWriter write = new JsonWriter();
        try {
            write.jsonWrite(a1,null);
        } catch (IOException e1) {
            System.out.println(e1);
        }
    }

    //EFFECTS - Displays a panel that shows a list of all the patients
    //MODIFIES - this
    public void dispall() {

        dispa = new JPanel();
        JLabel pat = new JLabel("Patients -");
        dispa.add(pat);
        dispa.add(Box.createRigidArea(new Dimension(600, 5)));

        for (int a = 0;a < lst.getsize();a++) {
            if (!lst.getval(a).getname().equals("test") && !lst.getval(a).getname().equals("null")
                    && !lst.getval(a).getname().equals("Adrian")) {
                dispa.add(Box.createRigidArea(new Dimension(600, 10)));
                dispa.add(new JLabel("Name - " + lst.getval(a).getname()));
                dispa.add(Box.createRigidArea(new Dimension(10, 10)));
                dispa.add(new JLabel("Mobile - " + lst.getval(a).getMobile()));
                dispa.add(Box.createRigidArea(new Dimension(10, 10)));
                dispa.add(new JLabel("Address - " + lst.getval(a).getAddress()));
            }
        }

        JButton bck = new JButton("Go Back");
        bck.addActionListener(this);
        dispa.add(Box.createRigidArea(new Dimension(600, 5)));
        dispa.add(bck);
        fr.add(dispa);
        dispa.setVisible(true);
        fr.setVisible(true);
    }
}
