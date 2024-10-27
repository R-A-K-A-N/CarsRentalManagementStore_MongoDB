/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.Customers_BUS;
import DTO.Customers_DTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class Customers_GUI extends javax.swing.JFrame {

    Customers_BUS customerBUS = new Customers_BUS();
    ArrayList<Customers_DTO> customerslist = customerBUS.getAllCustomers();
    int index = 0;
    
    public Customers_GUI() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        populateJTableWithCustomers();
        
        jTable_customers.setRowHeight(25);

    }
    
    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    
    public boolean verify() {
        String name = txt_name.getText();
        String phone = txt_phone.getText();
        String email = txt_email.getText();
        String address = jTextArea_address.getText();
        int customer_id = (int) jSpinner_id.getValue();
        
        if (customer_id == 0) {
            JOptionPane.showMessageDialog(null, "ID cannot be 0", "Invalid Info", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (name.trim().equals("") || phone.trim().equals("") || email.trim().equals("") || address.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter Valid Customer Data", "Invalid Info", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        Date birthDateValue = jDateChooser_birthdate.getDate();
        if (birthDateValue == null) {
            JOptionPane.showMessageDialog(null, "Please select a birth date", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(null, "Enter a valid email address", "Invalid Email", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (!isValidPhoneNumber(phone)) {
            JOptionPane.showMessageDialog(null, "Enter a valid phone number", "Invalid Info", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    
    public boolean isValidPhoneNumber(String phone) {
        return phone.matches("^0\\d{10}$");
    }
    
    public String getFormattedBirthdate() {
        Date birthDateValue = jDateChooser_birthdate.getDate();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        return dateformat.format(birthDateValue);
    }
    
    // Method to populate the JTable with customers data
    public void populateJTableWithCustomers() {
        //clear arraylist
        customerslist.clear();
        //populate arraylist
        customerslist = customerBUS.getAllCustomers();
        
        //jtable columns
        String[] columnsName = {"ID", "Full Name", "Birth Date", "Phone", "Email", "Address"};
        
        // jtable rows
        Object[][] rows = new Object[customerslist.size()][columnsName.length];
        
        for(int i=0; i < customerslist.size(); i++)
        {
            rows[i][0] = customerslist.get(i).getCustomer_id();
            rows[i][1] = customerslist.get(i).getFullname();
            rows[i][2] = customerslist.get(i).getBirthday();
            rows[i][3] = customerslist.get(i).getPhone();
            rows[i][4] = customerslist.get(i).getEmail();
            rows[i][5] = customerslist.get(i).getAddress();
        }
        DefaultTableModel model = new DefaultTableModel(rows,columnsName);
        jTable_customers.setModel(model);
    }
    
    private void displayCustomer(int index) {
        Customers_DTO selected_customer = customerslist.get(index);
        jSpinner_id.setValue(selected_customer.getCustomer_id());
        txt_name.setText(selected_customer.getFullname());
        txt_phone.setText(selected_customer.getPhone());
        txt_email.setText(selected_customer.getEmail());
        jTextArea_address.setText(selected_customer.getAddress());
        
        Date bdate;
        try {
            bdate = new SimpleDateFormat("yyyy-MM-dd").parse(selected_customer.getBirthday());
            jDateChooser_birthdate.setDate(bdate);
        } catch (ParseException ex) {
            Logger.getLogger(Customers_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void selectAndScrollToRow(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < jTable_customers.getRowCount()) {
            jTable_customers.setRowSelectionInterval(rowIndex, rowIndex); 
            jTable_customers.scrollRectToVisible(jTable_customers.getCellRect(rowIndex, 0, true)); 
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel_customers = new javax.swing.JLabel();
        jLabel_close = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_customers = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        int max = Integer.MAX_VALUE;
        int min = 0;
        int step = 1;
        int i = 0;

        SpinnerModel spinner_model_id = new SpinnerNumberModel(i, min, max, step);
        jSpinner_id = new javax.swing.JSpinner(spinner_model_id);
        txt_email = new javax.swing.JTextField();
        jButton_add = new javax.swing.JButton();
        jButton_update = new javax.swing.JButton();
        jButton_delete = new javax.swing.JButton();
        jButton_refresh = new javax.swing.JButton();
        jButton_clear = new javax.swing.JButton();
        jButton_First = new javax.swing.JButton();
        jButton_Next = new javax.swing.JButton();
        jButton_Previous = new javax.swing.JButton();
        jButton_Last = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jDateChooser_birthdate = new com.toedter.calendar.JDateChooser();
        txt_name = new javax.swing.JTextField();
        txt_phone = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_address = new javax.swing.JTextArea();
        btn_search = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        btn_Undo_Customer = new javax.swing.JButton();
        btn_undo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(153, 153, 0));

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Customers");

        jLabel_customers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customers.png"))); // NOI18N

        jLabel_close.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_close.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_close.setText("X");
        jLabel_close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel_customers, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(423, 423, 423)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 517, Short.MAX_VALUE)
                .addComponent(jLabel_close, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel_customers, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel_close, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTable_customers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_customers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_customersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_customers);
        jTable_customers.setCellSelectionEnabled(false);
        jTable_customers.setRowSelectionAllowed(true);
        jTable_customers.setColumnSelectionAllowed(false);
        jTable_customers.setDefaultEditor(Object.class, null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("ID:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Name:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Date Of Birth:");

        jSpinner_id.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txt_email.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_email.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_emailMouseClicked(evt);
            }
        });

        jButton_add.setBackground(new java.awt.Color(255, 0, 204));
        jButton_add.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_add.setForeground(new java.awt.Color(255, 255, 255));
        jButton_add.setText("Add");
        jButton_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addActionPerformed(evt);
            }
        });

        jButton_update.setBackground(new java.awt.Color(51, 255, 0));
        jButton_update.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_update.setForeground(new java.awt.Color(255, 255, 255));
        jButton_update.setText("Edit");
        jButton_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_updateActionPerformed(evt);
            }
        });

        jButton_delete.setBackground(new java.awt.Color(0, 255, 255));
        jButton_delete.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_delete.setForeground(new java.awt.Color(255, 255, 255));
        jButton_delete.setText("Remove");
        jButton_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteActionPerformed(evt);
            }
        });

        jButton_refresh.setBackground(new java.awt.Color(255, 153, 0));
        jButton_refresh.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_refresh.setForeground(new java.awt.Color(255, 255, 255));
        jButton_refresh.setText("Refresh");
        jButton_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_refreshActionPerformed(evt);
            }
        });

        jButton_clear.setBackground(new java.awt.Color(255, 255, 0));
        jButton_clear.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_clear.setText("Clear");
        jButton_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_clearActionPerformed(evt);
            }
        });

        jButton_First.setBackground(new java.awt.Color(34, 47, 62));
        jButton_First.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_First.setForeground(new java.awt.Color(255, 255, 255));
        jButton_First.setText("<<");
        jButton_First.setBorder(null);
        jButton_First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_FirstActionPerformed(evt);
            }
        });

        jButton_Next.setBackground(new java.awt.Color(34, 47, 62));
        jButton_Next.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Next.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Next.setText(">");
        jButton_Next.setBorder(null);
        jButton_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_NextActionPerformed(evt);
            }
        });

        jButton_Previous.setBackground(new java.awt.Color(34, 47, 62));
        jButton_Previous.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Previous.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Previous.setText("<");
        jButton_Previous.setBorder(null);
        jButton_Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PreviousActionPerformed(evt);
            }
        });

        jButton_Last.setBackground(new java.awt.Color(34, 47, 62));
        jButton_Last.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Last.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Last.setText(">>");
        jButton_Last.setBorder(null);
        jButton_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LastActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Phone:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Email:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("Address:");

        jDateChooser_birthdate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txt_name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_nameMouseClicked(evt);
            }
        });

        txt_phone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_phone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_phoneMouseClicked(evt);
            }
        });

        jTextArea_address.setColumns(20);
        jTextArea_address.setRows(5);
        jTextArea_address.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea_addressMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTextArea_address);

        btn_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/research.png"))); // NOI18N
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        btn_Undo_Customer.setBackground(new java.awt.Color(0, 153, 153));
        btn_Undo_Customer.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_Undo_Customer.setForeground(new java.awt.Color(255, 255, 255));
        btn_Undo_Customer.setText("Undo");
        btn_Undo_Customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Undo_CustomerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_First, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(jButton_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(jButton_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(jButton_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(53, 53, 53)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(30, 30, 30)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_email)
                                        .addComponent(jSpinner_id, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jDateChooser_birthdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_name, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txt_phone)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton_update, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_clear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_Undo_Customer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)))
                        .addGap(193, 193, 193)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btn_search)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jSpinner_id, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jDateChooser_birthdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_update, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Undo_Customer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_search)
                            .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_First, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btn_undo.setBackground(new java.awt.Color(255, 255, 0));
        btn_undo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_undo.setText("Undo");
        btn_undo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_undoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(586, 586, 586)
                    .addComponent(btn_undo, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addGap(586, 586, 586)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(284, 284, 284)
                    .addComponent(btn_undo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(285, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseClicked
        this.dispose();

    }//GEN-LAST:event_jLabel_closeMouseClicked

    private void jTable_customersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_customersMouseClicked
        // get the selected customer
        int index = jTable_customers.getSelectedRow();
        int customer_id = Integer.valueOf(jTable_customers.getValueAt(index, 0).toString());
        String fullname = jTable_customers.getValueAt(index, 1).toString();
        String phone = jTable_customers.getValueAt(index, 3).toString();
        String email = jTable_customers.getValueAt(index, 4).toString();
        String address = jTable_customers.getValueAt(index, 5).toString();
        
        jSpinner_id.setValue(customer_id);
        txt_name.setText(fullname);
        txt_phone.setText(phone);
        txt_email.setText(email);
        jTextArea_address.setText(address);
        
        Date bdate;
        try {
            bdate = new SimpleDateFormat("yyyy-MM-dd").parse(jTable_customers.getValueAt(index, 2).toString());
            jDateChooser_birthdate.setDate(bdate);
        } catch (ParseException ex) {
            Logger.getLogger(Customers_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable_customersMouseClicked

    private void jButton_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addActionPerformed
        int customer_id = (int) jSpinner_id.getValue();
        String name = txt_name.getText();
        String phone = txt_phone.getText();
        String email = txt_email.getText();
        String address = jTextArea_address.getText();

        if (verify()) {
            try {
                String birthdate = getFormattedBirthdate();
                if (birthdate == null) {
                    return; 
                }
                customerBUS.addCustomer(customer_id, name, birthdate, phone, email, address);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Enter Valid Customer Info ", "Error", 2);
            }
        }
    }//GEN-LAST:event_jButton_addActionPerformed

    private void jButton_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_updateActionPerformed
        int customer_id = (int) jSpinner_id.getValue();
        String name = txt_name.getText();
        String phone = txt_phone.getText();
        String email = txt_email.getText();
        String address = jTextArea_address.getText();

        if (verify()) {
            try {
                String birthdate = getFormattedBirthdate();
                if (birthdate == null) {
                    return; 
                }
                customerBUS.editCustomer(customer_id, name, birthdate, phone, email, address);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Enter Valid Customer Info", "Error", 2);
            }
        }
    }//GEN-LAST:event_jButton_updateActionPerformed

    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteActionPerformed
        int customer_id = (int) jSpinner_id.getValue();
        
        int confirm = JOptionPane.showConfirmDialog(null, "Are You Sure You Want to Delete this Brand?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                customerBUS.removeCustomer(customer_id);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton_deleteActionPerformed

    private void jButton_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_refreshActionPerformed
        //refresh the table
        populateJTableWithCustomers();
    }//GEN-LAST:event_jButton_refreshActionPerformed

    private void jButton_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_clearActionPerformed
        // clear fields
        jSpinner_id.setValue(0);
        txt_name.setText("");
        txt_email.setText("");
        txt_phone.setText("");
        jTextArea_address.setText("");
        jDateChooser_birthdate.setDate(null);
    }//GEN-LAST:event_jButton_clearActionPerformed

    private void jButton_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_FirstActionPerformed
        // Button First
        if (!customerslist.isEmpty()) {
            index = 0;
            displayCustomer(index);
            selectAndScrollToRow(index);
        }
    }//GEN-LAST:event_jButton_FirstActionPerformed

    private void jButton_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_NextActionPerformed
        // Button Next
        if (index < customerslist.size() - 1) {
            index++;
            displayCustomer(index);
            selectAndScrollToRow(index); 
        }
    }//GEN-LAST:event_jButton_NextActionPerformed

    private void jButton_PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PreviousActionPerformed
        // Button Previous
        if (index > 0) {
            index--;
            displayCustomer(index);
            selectAndScrollToRow(index); 
        }
    }//GEN-LAST:event_jButton_PreviousActionPerformed

    private void jButton_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LastActionPerformed
        // Button Last
        if (!customerslist.isEmpty()) {
            index = customerslist.size() - 1;
            displayCustomer(index);
            selectAndScrollToRow(index); 
        }
    }//GEN-LAST:event_jButton_LastActionPerformed

    private void txt_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_nameMouseClicked

    }//GEN-LAST:event_txt_nameMouseClicked

    private void txt_phoneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_phoneMouseClicked

    }//GEN-LAST:event_txt_phoneMouseClicked

    private void txt_emailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_emailMouseClicked

    }//GEN-LAST:event_txt_emailMouseClicked

    private void jTextArea_addressMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea_addressMouseClicked

    }//GEN-LAST:event_jTextArea_addressMouseClicked

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        String keyword = txt_search.getText().trim().toLowerCase();  
        ArrayList<Customers_DTO> filteredCustomers = new ArrayList<>();    

        for (Customers_DTO customer : customerslist) {
            if (customer.getFullname().toLowerCase().contains(keyword) || 
                customer.getBirthday().toLowerCase().contains(keyword) || 
                customer.getPhone().toLowerCase().contains(keyword) || 
                (customer.getEmail() != null && customer.getEmail().toLowerCase().contains(keyword)) || 
                (customer.getAddress() != null && customer.getAddress().toLowerCase().contains(keyword))) {  
                filteredCustomers.add(customer); 
            }
        }

        updateTable(filteredCustomers);
    }//GEN-LAST:event_btn_searchActionPerformed

    private void btn_undoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_undoActionPerformed

    }//GEN-LAST:event_btn_undoActionPerformed

    private void btn_Undo_CustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Undo_CustomerActionPerformed
        customerBUS.undoCustomer();
        populateJTableWithCustomers();
        JOptionPane.showMessageDialog(null, "Undo action completed!", "Undo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btn_Undo_CustomerActionPerformed

    private void updateTable(ArrayList<Customers_DTO> customerList) {
        DefaultTableModel model = (DefaultTableModel) jTable_customers.getModel();
        model.setRowCount(0);  

        for (Customers_DTO customer : customerList) {
            Object[] rowData = {
                customer.getCustomer_id(),   
                customer.getFullname(),     
                customer.getBirthday(),      
                customer.getPhone(),         
                customer.getEmail() != null ? customer.getEmail() : "No Email", 
                customer.getAddress() != null ? customer.getAddress() : "No Address" 
            };
            model.addRow(rowData);  
        }
    }
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
            java.util.logging.Logger.getLogger(Customers_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Customers_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Customers_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Customers_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Customers_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Undo_Customer;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_undo;
    private javax.swing.JButton jButton_First;
    private javax.swing.JButton jButton_Last;
    private javax.swing.JButton jButton_Next;
    private javax.swing.JButton jButton_Previous;
    private javax.swing.JButton jButton_add;
    private javax.swing.JButton jButton_clear;
    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_refresh;
    private javax.swing.JButton jButton_update;
    private com.toedter.calendar.JDateChooser jDateChooser_birthdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_close;
    private javax.swing.JLabel jLabel_customers;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner_id;
    private javax.swing.JTable jTable_customers;
    private javax.swing.JTextArea jTextArea_address;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_phone;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
