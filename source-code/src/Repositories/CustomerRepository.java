/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import Database.ConnectDatabase;
import java.util.*;
import Models.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author ThanhNhan
 */
public class CustomerRepository {
    private ConnectDatabase db = new ConnectDatabase();
    
    //Lấy tất cả danh sách khách hàng
    public List<Customer> getAllCustomers() {
        String query = "SELECT * FROM KhachHang";
        List<Customer> customers = new ArrayList<Customer>();
        try {
            Connection conn = db.getConnect();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("MAKH");
                String name = rs.getString("HOTENKH");
                String gender = rs.getString("GIOITINHKH");
                Date dob = rs.getDate("NGAYSINHKH");
                String address = rs.getString("DIACHIKH");
                String phone = rs.getString("SDTKH");
                String email = rs.getString("EMAIL");
                
                customers.add(new Customer(id, name, gender, dob, address, phone, email));
            }
        }
        catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Lỗi khi lấy danh sách khách hàng: " + ex.getMessage());
        }
        return customers;
    }
    
    //Thêm khách hàng vào danh sách khách hàng
    public boolean addCustomer(Customer customer) {
        String query = "INSERT INTO KhachHang (MAKH, HOTENKH, GIOITINHKH, NGAYSINHKH, DIACHIKH, SDTKH, EMAIL) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = db.getConnect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, customer.getID());           
            ps.setString(2, customer.getName());      
            ps.setString(3, customer.getGender());  
            ps.setDate(4, (java.sql.Date) customer.getDOB());         
            ps.setString(5, customer.getAddress());  
            ps.setString(6, customer.getPhone());    
            ps.setString(7, customer.getEmail());    

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0; // Trả về true nếu thêm thành công
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Lỗi khi thêm khách hàng: " + e.getMessage());
            return false;
        }
    }
    
    //Chỉnh sửa thông tin khách hàng
    public boolean updateCustomer(Customer customer) {
        String query = "UPDATE KhachHang SET HOTENKH = ?, GIOITINHKH = ?, NGAYSINHKH = ?, DIACHIKH = ?, SDTKH = ?, EMAIL = ? "  
                     + "WHERE MAKH = ?";
        try (Connection conn = db.getConnect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, customer.getName());      
            ps.setString(2, customer.getGender());  
            ps.setDate(3, (java.sql.Date) customer.getDOB());         
            ps.setString(4, customer.getAddress());  
            ps.setString(5, customer.getPhone());    
            ps.setString(6, customer.getEmail()); 
            ps.setString(7, customer.getID());           

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; 
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Lỗi khi sửa thông tin khách hàng: " + e.getMessage());
            return false;
        }    
    }
    
    //Xóa thông tin khách hàng
    public boolean deleteCustomer(String customerID) {
        String query = "DELETE FROM KhachHang WHERE MAKH = ?";
        try (Connection conn = db.getConnect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, customerID);
            int rowsDeleted = ps.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Xóa khách hàng thành công!");
            } else {
                System.out.println("Không tìm thấy khách hàng để xóa.");
            }
            return rowsDeleted > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Lỗi khi xóa khách hàng: " + e.getMessage());
            return false;
        }
    }
}
