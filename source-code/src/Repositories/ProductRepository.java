/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import Database.ConnectDatabase;
import Models.Product;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ThanhNhan
 */
public class ProductRepository {
    private ConnectDatabase db = new ConnectDatabase();
    
    //Lấy tất cả danh sách sản phẩm
    public List<Product> getAllProducts() {
        String query = "SELECT MATD, TENTHUCDON, SOLUONG, DONGIATD, IMAGELINK FROM ThucDon";

        List<Product> products = new ArrayList<Product>();
        try (Connection conn = db.getConnect();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(query)) {

            while (rs.next()) {
                String productID = rs.getString("MATD");
                String productName = rs.getString("TENTHUCDON");
                int productQuantity = rs.getInt("SOLUONG");
                int productPrice = rs.getInt("DONGIATD");  
                String productImage = rs.getString("IMAGELINK");

                products.add(new Product(productID, productName, productQuantity, productPrice, productImage));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Lỗi khi lấy danh sách sản phẩm: " + ex.getMessage());
        }
        return products;
    }
    
    //Thêm sản phẩm vào danh sách sản phẩm
    public boolean addProduct(Product product) {
        String query = "INSERT INTO ThucDon (MATD, TENTHUCDON, SOLUONG, DONGIATD, IMAGELINK) " +
                       "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = db.getConnect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, product.getProductID());
            ps.setString(2, product.getProductName());
            ps.setInt(3, product.getProductQuantity());
            ps.setInt(4, product.getProductPrice()); 
            ps.setString(5, product.getProductImage());
            
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Lỗi khi thêm sản phẩm: " + e.getMessage());
            return false;
        }
    }
    
    //Cập nhật sản phẩm
    public boolean updateProduct(Product product) {
        String query = "UPDATE ThucDon SET TENTHUCDON = ?, SOLUONG = ?, DONGIATD = ?, IMAGELINK = ? " +
                       "WHERE MATD = ?";
        try (Connection conn = db.getConnect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, product.getProductName());
            ps.setInt(2, product.getProductQuantity());
            ps.setInt(3, product.getProductPrice()); 
            ps.setString(4, product.getProductImage());
            ps.setString(5, product.getProductID());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Lỗi khi cập nhật sản phẩm: " + e.getMessage());
            return false;
        }
    }

    //Xóa sản phẩm
    public boolean deleteProduct(String productID) {
        String query = "DELETE FROM ThucDon WHERE MATD = ?";
        try (Connection conn = db.getConnect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, productID);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Lỗi khi xóa sản phẩm: " + e.getMessage());
            return false;
        }
    }
}
