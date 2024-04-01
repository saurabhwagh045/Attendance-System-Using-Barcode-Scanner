
package barcodescanner;

import java.awt.event.*;
//import java.lang.System.Logger.Level;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Saurabh
 */
public class javaattendance extends javax.swing.JFrame {
                  
    /**
     * Creates new form javaattendance
     */
    public javaattendance() {
        initComponents();
        Connect();
        LoadAttendance();
    }

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public void Connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/simplejavaattendance","root","");
        } catch (ClassNotFoundException ex){
            Logger.getLogger(javaattendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(javaattendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public void LoadAttendance(){
        
        try {
             int q;
             
            pst = con.prepareStatement("SELECT * FROM attendance a INNER JOIN student e ON a.barcodenumber = e.barcodenumber");
            rs = pst.executeQuery();
            
            ResultSetMetaData rss = rs.getMetaData();
            q = rss.getColumnCount();
            
            DefaultTableModel df = (DefaultTableModel)jTable1.getModel();
            df.setRowCount(0);
             
            while(rs.next()){
                
                Vector v2 = new Vector();
                
                for(int a=1;a<=q;a++){
                    v2.add(rs.getString("attid"));
                    v2.add(rs.getString("namex"));
                    v2.add(rs.getString("lastname"));
                    v2.add(rs.getString("barcodenumber"));
                    v2.add(rs.getString("logdate"));
                    v2.add(rs.getString("time"));
                }
                df.addRow(v2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(javaattendance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        barcodeTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        barcodeTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                barcodeTxtKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Scan Barcode Here");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Scan Your Barcode Number For Attendance");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "First Name", "Last Name", "Verification Code", "Log Date", "Time"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 141, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barcodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(barcodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void barcodeTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barcodeTxtKeyPressed
        // TODO add your handling code here:
        String Barcode = barcodeTxt.getText();
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            try{
            //  JOptionPane.showMessageDialog(this,"Your Barcode number is:"+Barcode);
            
            java.util.Date date = new java.util.Date();
            java.sql.Date sqldate = new java.sql.Date(date.getTime());
            java.sql.Timestamp sqltime = new java.sql.Timestamp(date.getTime());
           
            pst = con.prepareStatement("SELECT * FROM student WHERE barcodenumber=?");
           // pst.setString(0, attid);
           pst.setString(1, Barcode);
            rs = pst.executeQuery();
            
            if(rs.next()==true){
                
                pst = con.prepareStatement("INSERT INTO attendance (barcodenumber,logdate,time)VALUES(?,?,?)");
                    pst.setString(1, Barcode);
                    pst.setDate(2, sqldate);
                    pst.setTimestamp(3, sqltime);
                
                int q = pst.executeUpdate();
                
                if(q == 1){
                    JOptionPane.showMessageDialog(this,"Attendance SuccessFully Inserted ! ");
                    barcodeTxt.setText("");
                    barcodeTxt.requestFocus();
                    LoadAttendance();
                    
                }else{
                    JOptionPane.showMessageDialog(this,"Attendance Error to Insert !!!");
                }    
            }else{
                JOptionPane.showMessageDialog(this,"Barcode Number is not found !!! ");
                   //  barcodeTxt.requestFocus();
                     barcodeTxt.setText("");
                   
            }
                
            }catch(SQLException ex){
                Logger.getLogger(javaattendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
           
            
            
        }
    }//GEN-LAST:event_barcodeTxtKeyPressed

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
            java.util.logging.Logger.getLogger(javaattendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(javaattendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(javaattendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(javaattendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new javaattendance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barcodeTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

package barcodescanner;

import java.awt.event.*;
//import java.lang.System.Logger.Level;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Saurabh
 */
public class javaattendance extends javax.swing.JFrame {
                  
    /**
     * Creates new form javaattendance
     */
    public javaattendance() {
        initComponents();
        Connect();
        LoadAttendance();
    }

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public void Connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/simplejavaattendance","root","");
        } catch (ClassNotFoundException ex){
            Logger.getLogger(javaattendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(javaattendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public void LoadAttendance(){
        
        try {
             int q;
             
            pst = con.prepareStatement("SELECT * FROM attendance a INNER JOIN student e ON a.barcodenumber = e.barcodenumber");
            rs = pst.executeQuery();
            
            ResultSetMetaData rss = rs.getMetaData();
            q = rss.getColumnCount();
            
            DefaultTableModel df = (DefaultTableModel)jTable1.getModel();
            df.setRowCount(0);
             
            while(rs.next()){
                
                Vector v2 = new Vector();
                
                for(int a=1;a<=q;a++){
                    v2.add(rs.getString("attid"));
                    v2.add(rs.getString("namex"));
                    v2.add(rs.getString("lastname"));
                    v2.add(rs.getString("barcodenumber"));
                    v2.add(rs.getString("logdate"));
                    v2.add(rs.getString("time"));
                }
                df.addRow(v2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(javaattendance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        barcodeTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        barcodeTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                barcodeTxtKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Scan Barcode Here");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Scan Your Barcode Number For Attendance");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "First Name", "Last Name", "Verification Code", "Log Date", "Time"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 141, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barcodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(barcodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void barcodeTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barcodeTxtKeyPressed
        // TODO add your handling code here:
        String Barcode = barcodeTxt.getText();
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            try{
            //  JOptionPane.showMessageDialog(this,"Your Barcode number is:"+Barcode);
            
            java.util.Date date = new java.util.Date();
            java.sql.Date sqldate = new java.sql.Date(date.getTime());
            java.sql.Timestamp sqltime = new java.sql.Timestamp(date.getTime());
           
            pst = con.prepareStatement("SELECT * FROM student WHERE barcodenumber=?");
           // pst.setString(0, attid);
           pst.setString(1, Barcode);
            rs = pst.executeQuery();
            
            if(rs.next()==true){
                
                pst = con.prepareStatement("INSERT INTO attendance (barcodenumber,logdate,time)VALUES(?,?,?)");
                    pst.setString(1, Barcode);
                    pst.setDate(2, sqldate);
                    pst.setTimestamp(3, sqltime);
                
                int q = pst.executeUpdate();
                
                if(q == 1){
                    JOptionPane.showMessageDialog(this,"Attendance SuccessFully Inserted ! ");
                    barcodeTxt.setText("");
                    barcodeTxt.requestFocus();
                    LoadAttendance();
                    
                }else{
                    JOptionPane.showMessageDialog(this,"Attendance Error to Insert !!!");
                }    
            }else{
                JOptionPane.showMessageDialog(this,"Barcode Number is not found !!! ");
                   //  barcodeTxt.requestFocus();
                     barcodeTxt.setText("");
                   
            }
                
            }catch(SQLException ex){
                Logger.getLogger(javaattendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
           
            
            
        }
    }//GEN-LAST:event_barcodeTxtKeyPressed

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
            java.util.logging.Logger.getLogger(javaattendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(javaattendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(javaattendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(javaattendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new javaattendance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barcodeTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
