/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.*;
/**
 *
 * @author ThanhNhan
 */
public class Employee {
    private String employeeID;
    private String employeeName;
    private String employeeGender;
    private Date employeeDOB;
    private String employeeAddress;
    private String employeePhone;
    private Date employeeDOW;
    private String employeeUsername;
    private String employeePassword;
    private String positionID; 
    private String positionName;
    
    public Employee() {
        this.employeeID = null;
        this.employeeName = null;
        this.employeeGender = null;
        this.employeeDOB = null;
        this.employeeAddress = null;
        this.employeePhone = null;
        this.employeeDOW = null;
        this.employeeUsername = null;
        this.employeePassword = null;
        this.positionID = null;
        this.positionName = null;
    }

    public Employee(String id, String name, String gender, Date dob, String address, String phone, Date dow, 
                    String username, String password, String positionID, String positionName) {
        this.employeeID = id;
        this.employeeName = name;
        this.employeeGender = gender;
        this.employeeDOB = dob;
        this.employeeAddress = address;
        this.employeePhone = phone;
        this.employeeDOW = dow;
        this.employeeUsername = username;
        this.employeePassword = password;
        this.positionID = positionID;
        this.positionName = positionName;
    }
    
    public Employee(String id, String name, String gender, Date dob, String address, String phone, Date dow, 
                    String username, String password, String positionID) {
        this.employeeID = id;
        this.employeeName = name;
        this.employeeGender = gender;
        this.employeeDOB = dob;
        this.employeeAddress = address;
        this.employeePhone = phone;
        this.employeeDOW = dow;
        this.employeeUsername = username;
        this.employeePassword = password;
        this.positionID = positionID;
    }

    //Getter và Setter
    public String getEmployeeID() { return employeeID; }
    public void setEmployeeID(String employeeID) { this.employeeID = employeeID; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getEmployeeGender() { return employeeGender; }
    public void setEmployeeGender(String employeeGender) { this.employeeGender = employeeGender; }

    public Date getEmployeeDOB() { return employeeDOB; }
    public void setEmployeeDOB(Date employeeDOB) { this.employeeDOB = employeeDOB; }

    public String getEmployeeAddress() { return employeeAddress; }
    public void setEmployeeAddress(String employeeAddress) { this.employeeAddress = employeeAddress; }

    public String getEmployeePhone() { return employeePhone; }
    public void setEmployeePhone(String employeePhone) { this.employeePhone = employeePhone; }

    public Date getEmployeeDOW() { return employeeDOW; }
    public void setEmployeeDOW(Date employeeDOW) { this.employeeDOW = employeeDOW; }

    public String getEmployeeUsername() { return employeeUsername; }
    public void setEmployeeUsername(String employeeUsername) { this.employeeUsername = employeeUsername; }

    public String getEmployeePassword() { return employeePassword; }
    public void setEmployeePassword(String employeePassword) { this.employeePassword = employeePassword; }

    public String getPositionID() { return positionID; }
    public void setPositionID(String positionID) { this.positionID = positionID; }

    public String getPositionName() { return positionName; }
    public void setPositionName(String positionName) { this.positionName = positionName; }
}
