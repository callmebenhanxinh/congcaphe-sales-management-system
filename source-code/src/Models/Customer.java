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
public class Customer {
    private String customerID;
    private String customerName;
    private String customerGender;
    private Date customerDOB;
    private String customerAddress;
    private String customerPhone;
    private String customerEmail;
    
    public Customer()
    {
        this.customerID = null;
        this.customerName = null;
        this.customerGender = null;
        this.customerDOB = new Date();
        this.customerAddress = null;
        this.customerPhone = null;
        this.customerEmail = null;
    }
    
    public Customer(String id, String name, String gender, Date dob, String address, String phone, String email) {
        this.customerID = id;
        this.customerName = name;
        this.customerGender = gender;
        this.customerDOB = dob;
        this.customerAddress = address;
        this.customerPhone = phone;
        this.customerEmail = email;
    }
    
    public void InKhachHang() {
        System.out.println("----------" + "Khach Hang" + "----------");
        System.out.println("Ma khach hang: " + this.customerID );
        System.out.println("Ho ten khach hang: " + this.customerName);
        System.out.println("Gioi tinh: " + this.customerGender);
        System.out.println("Ngay sinh: " + this.customerDOB.toString());
        System.out.println("Dia chi: " + this.customerAddress);
        System.out.println("Dien thoai: " + this.customerPhone);
    }
    
    public String getID() {return this.customerID; };
    public String getName() {return this.customerName; };
    public String getGender() {return this.customerGender;};
    public Date getDOB() {return this.customerDOB;};
    public String getAddress() {return this.customerAddress;};
    public String getPhone() {return this.customerPhone;};
    public String getEmail() {return this.customerEmail;};
    
}
