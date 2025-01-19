package barcodescanner;

import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class javaattendance extends javax.swing.JFrame {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public javaattendance() {
        initComponents();
        Connect();
        LoadAttendance();
    }

    // Initialize GUI components
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        barcodeTxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Increase the size of the frame here (larger than before)
        setSize(800, 600); // Example size: width=800px, height=600px

        jLabel1.setText("Enter Barcode:");

        barcodeTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                barcodeTxtKeyPressed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] { "Attendance ID", "First Name", "Last Name", "Barcode", "Log Date", "Time" }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Attendance System");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(barcodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)) // Increased field width
                        .addGroup(layout.createSequentialGroup()
                            .addGap(300, 300, 300)
                            .addComponent(jLabel2)) // Centered label
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))) // Increased table width
                    .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel2)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(barcodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE) // Increased table height
                    .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

    // Establishing connection to the database
    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance_barcode", "root", "Saurabh@890");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(javaattendance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Loading attendance into the table
    public void LoadAttendance() {
        try {
            String query = "SELECT a.attid, e.namex, e.lastname, a.barcodenumber, a.logdate, a.time "
                         + "FROM attendance a "
                         + "INNER JOIN student e ON a.barcodenumber = e.barcodenumber";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            DefaultTableModel df = (DefaultTableModel) jTable1.getModel();
            df.setRowCount(0); // Clear existing rows

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("attid"));
                row.add(rs.getString("namex"));
                row.add(rs.getString("lastname"));
                row.add(rs.getString("barcodenumber"));
                row.add(rs.getString("logdate"));
                row.add(rs.getString("time"));
                df.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(javaattendance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void barcodeTxtKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String Barcode = barcodeTxt.getText();

            try {
                // Check if barcode exists
                String checkQuery = "SELECT * FROM student WHERE barcodenumber = ?";
                pst = con.prepareStatement(checkQuery);
                pst.setString(1, Barcode);
                rs = pst.executeQuery();

                if (rs.next()) {
                    // Insert attendance
                    String insertQuery = "INSERT INTO attendance (barcodenumber, logdate, time) VALUES (?, ?, ?)";
                    pst = con.prepareStatement(insertQuery);

                    java.util.Date currentDate = new java.util.Date();
                    pst.setString(1, Barcode);
                    pst.setDate(2, new java.sql.Date(currentDate.getTime()));
                    pst.setTimestamp(3, new java.sql.Timestamp(currentDate.getTime()));

                    int result = pst.executeUpdate();

                    if (result == 1) {
                        JOptionPane.showMessageDialog(this, "Attendance Successfully Inserted!");
                        barcodeTxt.setText("");
                        LoadAttendance();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error inserting attendance.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Barcode not found!");
                }
            } catch (SQLException ex) {
                Logger.getLogger(javaattendance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new javaattendance().setVisible(true);
        });
    }

    // Variables declaration
    private javax.swing.JTextField barcodeTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration
}
