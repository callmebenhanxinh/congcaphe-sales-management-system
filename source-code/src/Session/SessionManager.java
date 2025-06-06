/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Models.Employee;

/**
 *
 * @author Thanhhan
 */
public class SessionManager {
    private static Employee currentEmployee = null;

    public static void login(Employee emp) {
        currentEmployee = emp;
    }

    public static void logout() {
        currentEmployee = null;
    }

    public static Employee getCurrentEmployee() {
        return currentEmployee;
    }

    public static boolean isLoggedIn() {
        return currentEmployee != null;
    }
}
