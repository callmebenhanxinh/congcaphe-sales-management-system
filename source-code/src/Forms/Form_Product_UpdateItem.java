/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Models.Product;
import Repositories.ProductRepository;
import java.awt.Dimension;
import java.io.File;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ThanhNhan
 */
public class Form_Product_UpdateItem extends javax.swing.JDialog {
    public interface DataChangedListener {
        void onDataChanged();
    }

    private ProductRepository productRepo = new ProductRepository();
    private DataChangedListener dataChangedListener;
    private String currentImagePath = ""; 
    private String selectedImagePath = ""; 
    
    public void setDataChangedListener(DataChangedListener listener) {
        this.dataChangedListener = listener;
    }

    public void setProductID(String itemID) {
        this.txtItemID.setText(itemID);
        this.txtItemID.setEditable(false); 
    }

    public void setProductName(String itemName) {
        this.txtItemName.setText(itemName);
    }

    public void setProductQuantity(int itemQuantity) {
        this.txtItemQuantity.setText(String.valueOf(itemQuantity));
    }

    public void setProductPrice(int itemPrice) {
        this.txtItemPrice.setText(String.valueOf(itemPrice));
    }

    public void setProductImage(String imagePath) {
        this.currentImagePath = imagePath;
        this.lblImagePath.setText(imagePath);
        loadImage(imagePath);
    }
    /**
     * Creates new form Form_Product_UpdateItem
     */
    public Form_Product_UpdateItem(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        loadImage(null);
    }

    
    private void loadImage(String imagePath) {
        int newWidth = 200;
        int newHeight = 200;

        if (imagePath == null || imagePath.trim().isEmpty()) {
            lblItemImage.setIcon(null);
            lblItemImage.setText("Chưa có ảnh");
            lblItemImage.setPreferredSize(new Dimension(newWidth, newHeight));
            lblItemImage.revalidate();
            lblItemImage.repaint();
            return;
        }

        URL imgURL = getClass().getResource(imagePath);
        if (imgURL == null) {
            File file = new File(System.getProperty("user.dir") + imagePath);
            if (file.exists()) {
                ImageIcon icon = new ImageIcon(new ImageIcon(file.getAbsolutePath())
                        .getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
                lblItemImage.setIcon(icon);
                lblItemImage.setText("");
                lblItemImage.setPreferredSize(new Dimension(newWidth, newHeight));
                lblItemImage.revalidate();
                lblItemImage.repaint();
                return;
            }
            lblItemImage.setIcon(null);
            lblItemImage.setText("Không tìm thấy ảnh: " + imagePath);
            System.out.println("Lỗi: Không tìm thấy ảnh " + imagePath);
            return;
        }

        ImageIcon icon = new ImageIcon(imgURL);
        Image img = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        lblItemImage.setIcon(new ImageIcon(img));
        lblItemImage.setText("");
        lblItemImage.setPreferredSize(new Dimension(newWidth, newHeight));
        lblItemImage.revalidate();
        lblItemImage.repaint();
    }

    private void loadImageFromWindows(File imageFile) {
        if (imageFile != null && imageFile.exists()) {
            ImageIcon icon = new ImageIcon(new ImageIcon(imageFile.getAbsolutePath())
                    .getImage().getScaledInstance(lblItemImage.getWidth(), lblItemImage.getHeight(), Image.SCALE_SMOOTH));
            lblItemImage.setIcon(icon);
            lblItemImage.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy ảnh!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            loadImage(currentImagePath);
        }
    }

    public void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn ảnh sản phẩm");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Hình ảnh (JPG, PNG)", "jpg", "png");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            selectedImagePath = saveImageToProjectFolder(selectedFile);
            if (selectedImagePath != null) {
                lblImagePath.setText(selectedImagePath);
                loadImage(selectedImagePath);
            }
        }
    }

    private String saveImageToProjectFolder(File sourceFile) {
        try {
            File destFolder = new File("src/images/");
            if (!destFolder.exists()) {
                destFolder.mkdirs();
            }
            String fileName = sourceFile.getName();
            File destFile = new File(destFolder, fileName);
            Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return "/images/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu ảnh: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtItemID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lblItemImage = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblImagePath = new javax.swing.JLabel();
        btnChooseImage = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtItemPrice = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        txtItemQuantity = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(900, 1000));

        txtItemID.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Mã món:");
        jLabel2.setAlignmentX(0.5F);

        lblItemImage.setText("[productImage]");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("THÔNG TIN THỰC ĐƠN");
        jLabel1.setAlignmentX(0.5F);

        lblImagePath.setText("[imagePath]");

        btnChooseImage.setText("Choose Image");
        btnChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseImageActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Hình ảnh minh họa:");
        jLabel6.setAlignmentX(0.5F);

        txtItemPrice.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Đơn giá");
        jLabel5.setAlignmentX(0.5F);

        txtItemName.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        txtItemQuantity.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Số lượng tồn kho:");
        jLabel4.setAlignmentX(0.5F);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Tên món:");
        jLabel3.setAlignmentX(0.5F);

        btnUpdate.setBackground(new java.awt.Color(204, 255, 204));
        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnUpdate.setText("Cập nhật");
        btnUpdate.setAlignmentX(0.5F);
        btnUpdate.setBorderPainted(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(204, 255, 204));
        btnBack.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnBack.setText("Quay lại");
        btnBack.setAlignmentX(0.5F);
        btnBack.setBorderPainted(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtItemID, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(85, 85, 85)))
                    .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblItemImage, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtItemQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtItemPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblImagePath)
                                    .addComponent(btnChooseImage)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(57, 57, 57))
            .addGroup(layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtItemID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtItemQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtItemName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtItemPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jLabel6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(lblImagePath)
                        .addGap(28, 28, 28)
                        .addComponent(btnChooseImage))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblItemImage, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(566, 566, 566))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBack, btnChooseImage, btnUpdate, jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, lblImagePath, txtItemID, txtItemName, txtItemPrice, txtItemQuantity});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChooseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseImageActionPerformed
        // TODO add your handling code here:
        chooseImage();
    }//GEN-LAST:event_btnChooseImageActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        try {
            String productID = txtItemID.getText().trim();
            String productName = txtItemName.getText().trim();
            String quantityStr = txtItemQuantity.getText().trim();
            String priceStr = txtItemPrice.getText().trim();
            String newImagePath = lblImagePath.getText().trim();

            if (productID.isEmpty() || productName.isEmpty() || quantityStr.isEmpty()
                    || priceStr.isEmpty() || newImagePath.isEmpty() || newImagePath.equals("[Đường dẫn ảnh]")) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin và chọn ảnh!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int quantity = Integer.parseInt(quantityStr);
            int price = Integer.parseInt(priceStr);

            Product updatedProduct = new Product(productID, productName, quantity, price, newImagePath);

            boolean isUpdated = productRepo.updateProduct(updatedProduct);

            if (isUpdated) {
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công!");
                if (dataChangedListener != null) {
                    dataChangedListener.onDataChanged();
                }
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ cho số lượng và đơn giá!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form_Product_UpdateItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Product_UpdateItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Product_UpdateItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Product_UpdateItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Form_Product_UpdateItem dialog = new Form_Product_UpdateItem(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnChooseImage;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblImagePath;
    private javax.swing.JLabel lblItemImage;
    private javax.swing.JTextField txtItemID;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtItemPrice;
    private javax.swing.JTextField txtItemQuantity;
    // End of variables declaration//GEN-END:variables
}
