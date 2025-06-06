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
public class Invoice {
    private String invoiceID;
    private Date createDate;
    private String paymentMethod;
    private int Total;
    private String employeeID;
    
    public Invoice() {
        this.invoiceID = null;
        this.createDate = new Date();
        this.paymentMethod = null;
        this.Total = 0;
        this.employeeID = null;
    }
    
    public Invoice(String id, Date date, String method, int total, String employeeID) {
        this.invoiceID = id;
        this.createDate = date;
        this.paymentMethod = method;
        this.Total = total;
        this.employeeID = employeeID;
    }
    
    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
}
