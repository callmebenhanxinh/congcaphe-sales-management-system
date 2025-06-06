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
public class Product {
    private String productID;
    private String productName;
    private int productQuantity;
    private int productPrice;
    private String productImage;

    public Product()
    {
        this.productID = null;
        this.productName = null;
        this.productQuantity = 0;
        this.productPrice = 0;
        this.productImage = null;
    }
    
    public Product(String id, String name, int quantity, int price, String img)
    {
        this.productID = id;
        this.productName = name;
        this.productQuantity = quantity;
        this.productPrice = price;
        this.productImage = img;
    }
    
    //Getter và Setter
    public String getProductID() { return productID; }
    public void setProductID(String productID) { this.productID = productID; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getProductQuantity() { return productQuantity; }
    public void setProductQuantity(int productQuantity) { this.productQuantity = productQuantity; }

    public int getProductPrice() { return productPrice; }
    public void setProductPrice(int productPrice) { this.productPrice = productPrice; }

    public String getProductImage() { return productImage; }
    public void setProductImage(String productImage) { this.productImage = productImage; }
}
