/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import Database.ConnectDatabase;
import java.util.*;
import Models.Invoice;
import java.sql.*;
import java.sql.Date;
/**
 *
 * @author ThanhNhan
 */
public class InvoiceRepository {
    private ConnectDatabase db = new ConnectDatabase();
    
    //Lấy tất cả danh sách hóa đơn
    public List<Invoice> getAllInvoices() {
        String query = "SELECT MAHD, NGAYLAP, PTTT, THANHTIEN, MANV FROM HoaDon ";
                       
        List<Invoice> invoices = new ArrayList<>();
        try (Connection conn = db.getConnect();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(query)) {

            while (rs.next()) {
                String invoiceID = rs.getString("MAHD");
                Date createDate = rs.getDate("NGAYLAP");
                String paymentMethod = rs.getString("PTTT");
                int total = rs.getInt("THANHTIEN");  
                String employeeID = rs.getString("MANV");

                invoices.add(new Invoice(invoiceID, createDate, paymentMethod, total, employeeID));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Lỗi khi lấy danh sách hóa đơn: " + ex.getMessage());
        }
        return invoices;
    }
    
    //Thêm hóa đơn
    public boolean addInvoice (Invoice invoice) {
        String query = "INSERT INTO HoaDon (MAHD, NGAYLAP, PTTT, THANHTIEN, MANV) " +
                       "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = db.getConnect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, invoice.getInvoiceID());           
            ps.setDate(2, (java.sql.Date)invoice.getCreateDate());      
            ps.setString(3, invoice.getPaymentMethod());  
            ps.setInt(4, invoice.getTotal());  
            ps.setString(5, invoice.getEmployeeID());    

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0; 
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Lỗi khi thêm hóa đơn: " + e.getMessage());
            return false;
        }
    }
    
    //Chỉnh sửa thông tin hóa đơn
    public boolean updateInvoice(Invoice invoice) {
        String query = "UPDATE HoaDon SET NGAYLAP = ?, PTTT = ?, THANHTIEN = ?, MANV = ? "  
                     + "WHERE MAHD = ?";
        try (Connection conn = db.getConnect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setDate(1, (java.sql.Date)invoice.getCreateDate());      
            ps.setString(2, invoice.getPaymentMethod());  
            ps.setInt(3, invoice.getTotal());  
            ps.setString(4, invoice.getEmployeeID());
            ps.setString(5, invoice.getInvoiceID()); 

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; 
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Lỗi khi sửa thông tin hóa đơn: " + e.getMessage());
            return false;
        }    
    }
    
    //Xóa thông tin hóa đơn
    public boolean deleteInvoice(String invoiceID) {
        String query = "DELETE FROM HoaDon WHERE MAHD = ?";
        try (Connection conn = db.getConnect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, invoiceID);  
            int rowsDeleted = ps.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Xóa hóa đơn thành công!");
            } else {
                System.out.println("Không tìm thấy hóa đơn để xóa.");
            }
            return rowsDeleted > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Lỗi khi xóa hóa đơn: " + e.getMessage());
            return false;
        }
    }
}
