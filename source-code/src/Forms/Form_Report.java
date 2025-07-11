/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Database.ConnectDatabase;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ThanhNhan
 */
public class Form_Report extends javax.swing.JPanel {
    private javax.swing.JPanel mainContent;
    /**
     * Creates new form Form_Report
     */
    public Form_Report() {
        initComponents();
        this.mainContent = mainContent;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnReportCustomer = new javax.swing.JButton();

        btnReportCustomer.setText("Tạo báo cáo thông tin Nhân viên");
        btnReportCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportCustomerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(btnReportCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnReportCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnReportCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportCustomerActionPerformed
        // TODO add your handling code here:
        try {
            ConnectDatabase db = new ConnectDatabase();
            db.getConnect();
            String reportPath = "C:\\Program Files (x86)\\Jaspersoft\\iReport-5.6.0\\ireport\\samples\\Basic_report\\simple.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(reportPath);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, db.getConnect());
            JRViewer viewer = new JRViewer(jp);
            viewer.setOpaque(true);
            viewer.setVisible(true);

            // Xóa nội dung hiện tại của mainContent
            mainContent.removeAll();
            mainContent.setLayout(new java.awt.BorderLayout());

            // Thêm JRViewer vào mainContent
            mainContent.add(viewer, java.awt.BorderLayout.CENTER);

            // Cập nhật giao diện
            SwingUtilities.invokeLater(() -> {
                mainContent.revalidate();
                mainContent.repaint();
            });

        } catch (Exception ex) {
            System.out.println("Lỗi tạo báo cáo: " + ex.getMessage());
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnReportCustomerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReportCustomer;
    // End of variables declaration//GEN-END:variables
}
