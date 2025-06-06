/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import Database.ConnectDatabase;
import java.util.*;
import Models.Employee;
import Utils.HashUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.sql.Date;

/**
 *
 * @author ThanhNhan
 */
public class EmployeeRepository {
    private ConnectDatabase db = new ConnectDatabase();
    
    //Lấy tất cả danh sách nhân viên
    public List<Employee> getAllEmployees() {
        String query = "SELECT nv.MANV, nv.HOTENNV, nv.GIOITINHNV, nv.NGAYSINH, nv.DIACHINV, nv.SDTNV, " +
                       "nv.NGAYVAOLAM, nv.TENDANGNHAP, nv.MATKHAU, cv.MACV, cv.TENCV " +
                       "FROM NhanVien nv " +
                       "JOIN ChucVu cv ON nv.MACV = cv.MACV";  

        List<Employee> employees = new ArrayList<>();
        try (Connection conn = db.getConnect();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(query)) {

            while (rs.next()) {
                String id = rs.getString("MANV");
                String name = rs.getString("HOTENNV");
                String gender = rs.getString("GIOITINHNV");
                Date dob = rs.getDate("NGAYSINH");
                String address = rs.getString("DIACHINV");
                String phone = rs.getString("SDTNV");
                Date dow = rs.getDate("NGAYVAOLAM");
                String username = rs.getString("TENDANGNHAP");
                String password = rs.getString("MATKHAU");
                String positionID = rs.getString("MACV");
                String positionName = rs.getString("TENCV");  

                employees.add(new Employee(id, name, gender, dob, address, phone, dow, username, password, positionID, positionName));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Lỗi khi lấy danh sách nhân viên: " + ex.getMessage());
        }
        return employees;
    }
    
    //Thêm nhân viên vào danh sách nhân viên
    public boolean addEmployee(Employee employee) {
        String query = "INSERT INTO NhanVien (MANV, HOTENNV, GIOITINHNV, NGAYSINH, DIACHINV, SDTNV, NGAYVAOLAM, TENDANGNHAP, MATKHAU, MACV) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = db.getConnect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, employee.getEmployeeID());
            ps.setString(2, employee.getEmployeeName());
            ps.setString(3, employee.getEmployeeGender());
            ps.setDate(4, new java.sql.Date(employee.getEmployeeDOB().getTime())); 
            ps.setString(5, employee.getEmployeeAddress());
            ps.setString(6, employee.getEmployeePhone());
            ps.setDate(7, new java.sql.Date(employee.getEmployeeDOW().getTime()));
            ps.setString(8, employee.getEmployeeUsername());
            ps.setString(9, employee.getEmployeePassword());
            ps.setString(10, employee.getPositionID());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Lỗi khi thêm nhân viên: " + e.getMessage());
            return false;
        }
    }
    
    //Cập nhật nhân viên
    public boolean updateEmployee(Employee employee) {
        String query = "UPDATE NhanVien SET HOTENNV = ?, GIOITINHNV = ?, NGAYSINH = ?, DIACHINV = ?, " +
                       "SDTNV = ?, NGAYVAOLAM = ?, TENDANGNHAP = ?, MATKHAU = ?, MACV = ? " +
                       "WHERE MANV = ?";
        try (Connection conn = db.getConnect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, employee.getEmployeeName());
            ps.setString(2, employee.getEmployeeGender());
            ps.setDate(3, new java.sql.Date(employee.getEmployeeDOB().getTime())); 
            ps.setString(4, employee.getEmployeeAddress());
            ps.setString(5, employee.getEmployeePhone());
            ps.setDate(6, new java.sql.Date(employee.getEmployeeDOW().getTime()));
            ps.setString(7, employee.getEmployeeUsername());
            ps.setString(8, employee.getEmployeePassword());
            ps.setString(9, employee.getPositionID());
            ps.setString(10, employee.getEmployeeID());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Lỗi khi cập nhật nhân viên: " + e.getMessage());
            return false;
        }
    }

    //Xóa nhân viên
    public boolean deleteEmployee(String employeeID) {
        String query = "DELETE FROM NhanVien WHERE MANV = ?";
        try (Connection conn = db.getConnect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, employeeID);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Lỗi khi xóa nhân viên: " + e.getMessage());
            return false;
        }
    }
    
    public Employee getUserByUsernameandPassword (String username, String password) {
        Employee emp = null;
        String query = "SELECT nv.MANV, nv.HOTENNV, nv.GIOITINHNV, nv.NGAYSINH, nv.DIACHINV, nv.SDTNV, " +
               "nv.NGAYVAOLAM, nv.TENDANGNHAP, nv.MATKHAU, cv.MACV, cv.TENCV " +
               "FROM NhanVien nv " +
               "JOIN ChucVu cv ON nv.MACV = cv.MACV " +
               "WHERE nv.TENDANGNHAP = ? AND nv.MATKHAU = ?";
        try (Connection conn = db.getConnect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            String hashedPassword = HashUtils.hashPassword(password);

            ps.setString(1, username);
            ps.setString(2, hashedPassword);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                emp = new Employee(
                    rs.getString("MANV"),
                    rs.getString("HOTENNV"),
                    rs.getString("GIOITINHNV"),
                    rs.getDate("NGAYSINH"),
                    rs.getString("DIACHINV"),
                    rs.getString("SDTNV"),
                    rs.getDate("NGAYVAOLAM"),
                    rs.getString("TENDANGNHAP"),
                    rs.getString("MATKHAU"),
                    rs.getString("MACV"),
                    rs.getString("TENCV")
                );
            }
        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException e) {
            System.out.println("Lỗi khi kiểm tra đăng nhập: " + e.getMessage());
        }
        return emp;
    }
}


