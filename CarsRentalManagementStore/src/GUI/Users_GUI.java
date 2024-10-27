/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.Users_BUS;
import DTO.Users_DTO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class Users_GUI extends javax.swing.JFrame {

    /**
     * Creates new form Users_GUI
     */
    Users_BUS userBUS = new Users_BUS();
    ArrayList<Users_DTO> userlist = new ArrayList<>();
    public Users_GUI() {
        initComponents();
        
        //center the form
        this.setLocationRelativeTo(null);
        
        ButtonGroup btn_group = new ButtonGroup();
        btn_group.add(jRadioButton_user);
        btn_group.add(jRadioButton_admin);
        
        populateJTableWithUsers();
        
        jTable_users.setRowHeight(40);
    }

    //create a function to check empty fields
    public boolean verify() {
        int user_id = (int) jSpinner_id.getValue();
        String fullname = txt_fullname.getText();
        String username = txt_username.getText();
        String phone = txt_phone.getText();
        String email = txt_email.getText();
        String password = String.valueOf(jPasswordField_password.getPassword());
        String confirmPassword = String.valueOf(jPasswordField_confirmpassword.getPassword());
        String imagePath = jLabel_imagePath.getText().trim();

        if (user_id == 0) {
            JOptionPane.showMessageDialog(null, "ID cannot be 0", "Invalid Info", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (fullname.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid full name", "Invalid Info", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid username", "Invalid Info", JOptionPane.WARNING_MESSAGE);
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

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid password", "Invalid Info", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Password and Confirm Password do not match", "Invalid Info", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (imagePath.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select an image", "Invalid Info", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }
    
    // Method to populate the JTable with user data
    public void populateJTableWithUsers() {
        // Clear arraylist
        userlist.clear();
        // Populate arraylist
        userlist = userBUS.getAllUsers();

        // jtable columns
        String[] columnsName = {"ID", "Full Name", "Username", "User Type", "Phone", "Email"};

        // jtable rows
        Object[][] rows = new Object[userlist.size()][columnsName.length];

        for (int i = 0; i < userlist.size(); i++) {
            rows[i][0] = userlist.get(i).getUser_id();
            rows[i][1] = userlist.get(i).getFullname();
            rows[i][2] = userlist.get(i).getUsername();
            rows[i][3] = userlist.get(i).getUser_type();
            rows[i][4] = userlist.get(i).getPhone();
            rows[i][5] = userlist.get(i).getEmail();
        }
        DefaultTableModel model = new DefaultTableModel(rows, columnsName);
        jTable_users.setModel(model);
    }
    // create a function to select an image
    // the function will return the image path 
    public String selectImage()
    {
        JFileChooser fileChooser = new JFileChooser(); 
        fileChooser.setDialogTitle("Select Pricture");
        
        fileChooser.setCurrentDirectory (new File("D:\\Java\\DoAnJava\\CarsRentalManagementStore\\CarsRentalManagementStore\\src\\images\\user"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("image", ".png",".jpg"); 
        fileChooser.addChoosableFileFilter(filter);
        
        int state = fileChooser.showSaveDialog (null);
        String path = ""; 
        if (state == JFileChooser.APPROVE_OPTION)
        {
            path = fileChooser.getSelectedFile().getAbsolutePath();
        }
        return path;
    }
    
    public void displayImage (int width, int height, String image_path, JLabel label)
    {
        // get the image
        ImageIcon imageIco = new ImageIcon(image_path);
        // resize the image
        Image image = imageIco.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // set the image into the jlabel
        label.setIcon(new ImageIcon(image));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
    }
    
    public void displayByteImage(int width, int height, byte[] image_byte, JLabel label) {
        ImageIcon imageIco = new ImageIcon(image_byte);
        // resize the image
        Image image = imageIco.getImage().getScaledInstance(134, 116, Image.SCALE_SMOOTH);
        // set the image into the jlabel
        label.setIcon(new ImageIcon(image));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
    }
    
    public boolean isValidPhoneNumber(String phone) {
        return phone.matches("^0\\d{9}$");
    }
    
    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel_users = new javax.swing.JLabel();
        jLabel_close1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_users = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        int max = Integer.MAX_VALUE;
        int min = 0;
        int step = 1;
        int i = 0;

        SpinnerModel spinner_model_id = new SpinnerNumberModel(i, min, max, step);
        jSpinner_id = new javax.swing.JSpinner(spinner_model_id);
        jButton_add = new javax.swing.JButton();
        jButton_update = new javax.swing.JButton();
        jButton_delete = new javax.swing.JButton();
        jButton_refresh = new javax.swing.JButton();
        jButton_clear = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_fullname = new javax.swing.JTextField();
        txt_username = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_phone = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jRadioButton_user = new javax.swing.JRadioButton();
        jRadioButton_admin = new javax.swing.JRadioButton();
        jPasswordField_password = new javax.swing.JPasswordField();
        jPasswordField_confirmpassword = new javax.swing.JPasswordField();
        jCheckBox_showpassword = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel_image = new javax.swing.JLabel();
        jButton_browse = new javax.swing.JButton();
        jLabel_imagePath = new javax.swing.JLabel();
        btn_search = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        btn_Undo_Users = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 0, 255));

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Users");

        jLabel_users.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profile.png"))); // NOI18N

        jLabel_close1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_close1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_close1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_close1.setText("X");
        jLabel_close1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_close1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_close1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel_users, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(423, 423, 423)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_close1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel_users, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel_close1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTable_users.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_users.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_usersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_users);
        jTable_users.setCellSelectionEnabled(false);
        jTable_users.setRowSelectionAllowed(true);
        jTable_users.setColumnSelectionAllowed(false);
        jTable_users.setDefaultEditor(Object.class, null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("ID:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Full Name:");

        jSpinner_id.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

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

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Password:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("Confirm Password:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("User Type:");

        txt_fullname.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txt_username.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Username:");

        txt_phone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txt_email.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setText("Phone:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setText("Email:");

        jRadioButton_user.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton_user.setForeground(new java.awt.Color(102, 0, 0));
        jRadioButton_user.setText("User");

        jRadioButton_admin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton_admin.setForeground(new java.awt.Color(102, 0, 0));
        jRadioButton_admin.setText("Admin");

        jPasswordField_password.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jPasswordField_confirmpassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jCheckBox_showpassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBox_showpassword.setText("Show password");
        jCheckBox_showpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_showpasswordActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setText("Image:");

        jLabel_image.setBackground(new java.awt.Color(0, 0, 255));
        jLabel_image.setOpaque(true);

        jButton_browse.setText("Browse");
        jButton_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_browseActionPerformed(evt);
            }
        });

        jLabel_imagePath.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_imagePath.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btn_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/research.png"))); // NOI18N
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        btn_Undo_Users.setBackground(new java.awt.Color(0, 153, 153));
        btn_Undo_Users.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_Undo_Users.setForeground(new java.awt.Color(255, 255, 255));
        btn_Undo_Users.setText("Undo");
        btn_Undo_Users.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Undo_UsersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jSpinner_id, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_username)
                                        .addComponent(txt_fullname, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPasswordField_password, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPasswordField_confirmpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jRadioButton_user, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButton_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox_showpassword)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addComponent(jLabel_image, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton_browse, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel_imagePath, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txt_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(btn_Undo_Users, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jButton_update, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jSpinner_id, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_fullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jPasswordField_password, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jPasswordField_confirmpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox_showpassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jRadioButton_user)
                            .addComponent(jRadioButton_admin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel_image, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_browse)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_update, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8))
                    .addComponent(jLabel_imagePath, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btn_Undo_Users, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_close1MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel_close1MouseClicked

    private void jTable_usersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_usersMouseClicked
        jButton_clearActionPerformed(null);

        int index = jTable_users.getSelectedRow();
        int user_id = Integer.valueOf(jTable_users.getValueAt(index, 0).toString());

        Users_DTO selected_user = null;
        try {
            selected_user = userBUS.getUserById(user_id);
        } catch (Exception ex) { 
            Logger.getLogger(Users_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (selected_user != null) {
            jSpinner_id.setValue(user_id);
            txt_fullname.setText(selected_user.getFullname());
            txt_username.setText(selected_user.getUsername());
            txt_email.setText(selected_user.getEmail());
            txt_phone.setText(selected_user.getPhone());
            if (selected_user.getUser_type().equals("user")) {
                jRadioButton_user.setSelected(true);
            } else if (selected_user.getUser_type().equals("admin")) {
                jRadioButton_admin.setSelected(true);
            }
            displayByteImage(jLabel_image.getWidth(), jLabel_image.getHeight(), selected_user.getPicture(), jLabel_image);
        } else {
            JOptionPane.showMessageDialog(null, "User not found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTable_usersMouseClicked

    private void jButton_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addActionPerformed
        int user_id = (int) jSpinner_id.getValue();
        String fullname = txt_fullname.getText();
        String username = txt_username.getText();
        String phone = txt_phone.getText();
        String email = txt_email.getText();
        String password = String.valueOf(jPasswordField_password.getPassword());
        String user_type = "user";
        if(jRadioButton_admin.isSelected()) { user_type = "admin"; }
        byte[] picture = null;

        try {
            if (!jLabel_imagePath.getText().trim().isEmpty()) {
                picture = Files.readAllBytes(Paths.get(jLabel_imagePath.getText()));
            } else {
                JOptionPane.showMessageDialog(null, "Please select a image", "Invalid Info", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (IOException ex) {
            Logger.getLogger(Users_GUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error reading image file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (verify()) {
            try {
                userBUS.addUser(user_id, fullname, username, password, user_type, picture, phone, email);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Add a User Profile Picture", "Error", 2);
            }
        }
    }//GEN-LAST:event_jButton_addActionPerformed

    private void jButton_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_updateActionPerformed
        int user_id = (int) jSpinner_id.getValue();
        String fullname = txt_fullname.getText();
        String username = txt_username.getText();
        String phone = txt_phone.getText();
        String email = txt_email.getText();
        String password = String.valueOf(jPasswordField_password.getPassword());
        String user_type = "user";
        if(jRadioButton_admin.isSelected()) { user_type = "admin"; }
        byte[] picture = null;

        try {
            if (!jLabel_imagePath.getText().trim().isEmpty()) {
                picture = Files.readAllBytes(Paths.get(jLabel_imagePath.getText()));
            } else {
                try {
                    picture = userBUS.getUserById(user_id).getPicture();
                } catch (Exception ex) {
                    Logger.getLogger(Users_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Users_GUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error reading image file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (verify()) {
            try {
                userBUS.updateUser(user_id, fullname, username, password, user_type, picture, phone, email);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Use a Smaller Size Image", "Image User", 2);
            }
        }
    }//GEN-LAST:event_jButton_updateActionPerformed

    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteActionPerformed
        // delete a user
        int id = (int) jSpinner_id.getValue();
        
        int confirm = JOptionPane.showConfirmDialog(null, "Are You Sure You Want to Delete this User?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                userBUS.deleteUser(id);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton_deleteActionPerformed

    private void jButton_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_refreshActionPerformed
        //refresh the table
        populateJTableWithUsers();
    }//GEN-LAST:event_jButton_refreshActionPerformed

    private void jButton_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_clearActionPerformed
        // clear fields
        jSpinner_id.setValue(0);
        txt_fullname.setText("");
        txt_username.setText("");
        jPasswordField_password.setText("");
        jPasswordField_confirmpassword.setText("");
        txt_email.setText("");
        txt_phone.setText("");
        jRadioButton_user.setSelected(true);
        jLabel_imagePath.setText("              ");
        jLabel_image.setIcon(null);
    }//GEN-LAST:event_jButton_clearActionPerformed

    private void jButton_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_browseActionPerformed
        // browse and display the image
        String imagePath = selectImage();
        displayImage(jLabel_image.getWidth(), jLabel_image.getHeight(), imagePath, jLabel_image);
        //display image path
        jLabel_imagePath.setText(imagePath);       
    }//GEN-LAST:event_jButton_browseActionPerformed

    private void jCheckBox_showpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_showpasswordActionPerformed
        // show and hide password
        if (jCheckBox_showpassword.isSelected())
        {
            jPasswordField_password.setEchoChar((char)0);
            jPasswordField_confirmpassword.setEchoChar((char)0);
        }
        else
        {
            jPasswordField_password.setEchoChar('*');
            jPasswordField_confirmpassword.setEchoChar('*');
        }
    }//GEN-LAST:event_jCheckBox_showpasswordActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        int user_id = (int) jSpinner_id.getValue();

        Users_DTO user = userBUS.getUserById(user_id);
    
        if (user != null) {
        txt_fullname.setText(user.getFullname());
        txt_username.setText(user.getUsername());
        jPasswordField_password.setText(user.getPassword());
        if (user.getUser_type().equals("User")) {
            jRadioButton_user.setSelected(true);
        } else if (user.getUser_type().equals("Admin")) {
            jRadioButton_admin.setSelected(true);
        }
        txt_phone.setText(user.getPhone());
        txt_email.setText(user.getEmail());
        if (user.getPicture() != null) {
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(user.getPicture()).getImage().getScaledInstance(jLabel_image.getWidth(), jLabel_image.getHeight(), Image.SCALE_SMOOTH));
            jLabel_image.setIcon(imageIcon);
        }
        } else {
            // Nếu không tìm thấy, hiển thị thông báo lỗi
            JOptionPane.showMessageDialog(null, "User Not Found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_searchActionPerformed

    private void btn_Undo_UsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Undo_UsersActionPerformed
        userBUS.undoUser();
        populateJTableWithUsers();
        JOptionPane.showMessageDialog(null, "Undo action completed!", "Undo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btn_Undo_UsersActionPerformed

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
            java.util.logging.Logger.getLogger(Users_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Users_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Users_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Users_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Users_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Undo_Users;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton jButton_add;
    private javax.swing.JButton jButton_browse;
    private javax.swing.JButton jButton_clear;
    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_refresh;
    private javax.swing.JButton jButton_update;
    private javax.swing.JCheckBox jCheckBox_showpassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_close1;
    private javax.swing.JLabel jLabel_image;
    private javax.swing.JLabel jLabel_imagePath;
    private javax.swing.JLabel jLabel_users;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPasswordField jPasswordField_confirmpassword;
    private javax.swing.JPasswordField jPasswordField_password;
    private javax.swing.JRadioButton jRadioButton_admin;
    private javax.swing.JRadioButton jRadioButton_user;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner_id;
    private javax.swing.JTable jTable_users;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_fullname;
    private javax.swing.JTextField txt_phone;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
