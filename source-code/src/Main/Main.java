/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Forms.Form_Home;
import Forms.Form_LogIn;
import Session.SessionManager;

/**
 *
 * @author ThanhNhan
 */
public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (SessionManager.isLoggedIn()) {
                    new Form_Home(SessionManager.getCurrentEmployee()).setVisible(true);
                } else {
                    new Form_LogIn().setVisible(true);
                }
            }
        });
    }
}
