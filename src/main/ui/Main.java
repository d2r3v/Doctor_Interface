package ui;

import model.ListOfPatients;

import javax.swing.*;

public class Main {

    //EFFECTS - calls the login method
    public static void main(String[] args) {

        AuthenticateUI temp = new AuthenticateUI();
        temp.login(new ListOfPatients());
    }
}
