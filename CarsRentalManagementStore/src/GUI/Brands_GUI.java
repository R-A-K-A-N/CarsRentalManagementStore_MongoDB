/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DAO.Brands_DAO;
import BUS.Brands_BUS;
import DTO.Brands_DTO;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author ADMIN
 */
public class Brands_GUI extends javax.swing.JFrame {

    
    Brands_BUS brandsBUS = new Brands_BUS();
    ArrayList<Brands_DTO> brands_list = new ArrayList<>();
    private int currentIndex = 0;
    public Brands_GUI() {
        initComponents();
        //center the form
        this.setLocationRelativeTo(null);
        // Populate the JTable with brands data
        populateJTableWithBrands();
        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1); // start at 1, min 1, max Integer.MAX_VALUE, step 1
        jSpinner_id.setModel(model);
        
        jLabel_logo.setPreferredSize(new Dimension(205, 152));
        jLabel_logo.setMinimumSize(new Dimension(205, 152));
        jLabel_logo.setMaximumSize(new Dimension(205, 152));
    }

    public boolean verify() {
        String name = txt_name.getText().trim();
        int id = (int) jSpinner_id.getValue();

        if (id == 0) {
            JOptionPane.showMessageDialog(null, "ID cannot be 0", "Invalid Info", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!name.isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Enter a Valid Brand", "Invalid Info", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    
    public void populateJTableWithBrands() {
        brands_list.clear();
        brands_list = brandsBUS.getAllBrands();
        
        String[] columnsName = {"ID", "Name"};
        
        Object[][] rows = new Object[brands_list.size()][columnsName.length];
        
        for(int i=0; i < brands_list.size(); i++)
        {
            rows[i][0] = brands_list.get(i).getBrand_id();
            rows[i][1] = brands_list.get(i).getName();
        }
        DefaultTableModel model = new DefaultTableModel(rows,columnsName);
        jTable_brands.setModel(model);
    }
    
    public String selectImage()
    {
        JFileChooser fileChooser = new JFileChooser(); 
        fileChooser.setDialogTitle("Select Pricture");
        
        fileChooser.setCurrentDirectory (new File("D:\\Nosql\\CarsRentalManagementStore\\CarsRentalManagementStore\\src\\images\\brand"));
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
        ImageIcon imageIco = new ImageIcon(image_path);
        Image image = imageIco.getImage().getScaledInstance(134, 116, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(image));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
    }
    
    public void displayByteImage (int width, int height, byte[] image_byte, JLabel label)
    {
        ImageIcon imageIco = new ImageIcon(image_byte);
        Image image = imageIco.getImage().getScaledInstance(134, 116, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(image));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
    }
    
    private void displayBrand(int index) {
        Brands_DTO selected_brand = brands_list.get(index); 
        jSpinner_id.setValue(selected_brand.getBrand_id());
        txt_name.setText(selected_brand.getName());
        displayByteImage (jLabel_logo.getWidth(), jLabel_logo.getHeight(), selected_brand.getLogo(), jLabel_logo);
    }
    
    private void selectAndScrollToRow(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < jTable_brands.getRowCount()) {
            jTable_brands.setRowSelectionInterval(rowIndex, rowIndex);
            jTable_brands.scrollRectToVisible(new Rectangle(jTable_brands.getCellRect(rowIndex, 0, true)));
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
        jLabel_brands_logo = new javax.swing.JLabel();
        jLabel_close = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_brands = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSpinner_id = new javax.swing.JSpinner();
        txt_name = new javax.swing.JTextField();
        jLabel_logo = new javax.swing.JLabel();
        jButton_browse = new javax.swing.JButton();
        jButton_add = new javax.swing.JButton();
        jButton_update = new javax.swing.JButton();
        jButton_delete = new javax.swing.JButton();
        jLabel_imagePath = new javax.swing.JLabel();
        jButton_refresh = new javax.swing.JButton();
        jButton_clear = new javax.swing.JButton();
        jButton_First = new javax.swing.JButton();
        jButton_Next = new javax.swing.JButton();
        jButton_Previous = new javax.swing.JButton();
        jButton_Last = new javax.swing.JButton();
        btn_search = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        btn_undo_brand = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Brands");

        jLabel_brands_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/brand.jpg"))); // NOI18N

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
                .addComponent(jLabel_brands_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(264, 264, 264)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_close, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel_brands_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel_close, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTable_brands.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_brands.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_brandsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_brands);
        jTable_brands.setCellSelectionEnabled(false);
        jTable_brands.setRowSelectionAllowed(true);
        jTable_brands.setColumnSelectionAllowed(false);
        jTable_brands.setDefaultEditor(Object.class, null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("ID:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Name:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Logo:");

        jSpinner_id.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txt_name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_nameFocusLost(evt);
            }
        });
        txt_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_nameMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_nameMousePressed(evt);
            }
        });

        jLabel_logo.setBackground(new java.awt.Color(102, 255, 102));
        jLabel_logo.setOpaque(true);

        jButton_browse.setText("Browse");
        jButton_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_browseActionPerformed(evt);
            }
        });

        jButton_add.setBackground(new java.awt.Color(51, 255, 51));
        jButton_add.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_add.setForeground(new java.awt.Color(255, 255, 255));
        jButton_add.setText("Add");
        jButton_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addActionPerformed(evt);
            }
        });

        jButton_update.setBackground(new java.awt.Color(51, 51, 255));
        jButton_update.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_update.setForeground(new java.awt.Color(255, 255, 255));
        jButton_update.setText("Update");
        jButton_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_updateActionPerformed(evt);
            }
        });

        jButton_delete.setBackground(new java.awt.Color(255, 0, 0));
        jButton_delete.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_delete.setForeground(new java.awt.Color(255, 255, 255));
        jButton_delete.setText("Delete");
        jButton_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteActionPerformed(evt);
            }
        });

        jLabel_imagePath.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_imagePath.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton_refresh.setBackground(new java.awt.Color(230, 126, 34));
        jButton_refresh.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_refresh.setForeground(new java.awt.Color(255, 255, 255));
        jButton_refresh.setText("Refresh");
        jButton_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_refreshActionPerformed(evt);
            }
        });

        jButton_clear.setBackground(new java.awt.Color(153, 153, 0));
        jButton_clear.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_clear.setForeground(new java.awt.Color(255, 255, 255));
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

        btn_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/research.png"))); // NOI18N
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        btn_undo_brand.setBackground(new java.awt.Color(255, 255, 0));
        btn_undo_brand.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_undo_brand.setText("Undo");
        btn_undo_brand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_undo_brandActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel_imagePath, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_browse, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner_id, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_update, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_delete, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(btn_undo_brand, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_First, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jButton_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jButton_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_search)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jSpinner_id, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_search, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_search, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(jLabel_imagePath, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_browse))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_update)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_First, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_delete)
                        .addComponent(jButton_add)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton_refresh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_undo_brand, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseClicked
        this.dispose();
        
    }//GEN-LAST:event_jLabel_closeMouseClicked

    private void jButton_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addActionPerformed
        int brand_id = (int) jSpinner_id.getValue();
        String name = txt_name.getText().trim();
        byte[] logo = null;

        try {
            if (!jLabel_imagePath.getText().trim().isEmpty()) {
                logo = Files.readAllBytes(Paths.get(jLabel_imagePath.getText()));
            } else {
                JOptionPane.showMessageDialog(null, "Please select a logo image", "Invalid Info", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (IOException ex) {
            Logger.getLogger(Brands_GUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error reading image file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (verify()) {
            boolean nameExists = brands_list.stream().anyMatch(brand -> brand.getName().equalsIgnoreCase(name));
            if (nameExists) {
                JOptionPane.showMessageDialog(null, "Brand name already exists!", "Duplicate Brand", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                brandsBUS.addBrand(brand_id, name, logo);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Use a Smaller Size Image", "Brand Logo", 2);
            }
        }
    }//GEN-LAST:event_jButton_addActionPerformed

    private void jButton_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_browseActionPerformed
        String imagePath = selectImage();
        displayImage(jLabel_logo.getWidth(), jLabel_logo.getHeight(), imagePath, jLabel_logo);

        jLabel_imagePath.setText(imagePath);
    }//GEN-LAST:event_jButton_browseActionPerformed

    private void jButton_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_updateActionPerformed
        int brand_id = (int) jSpinner_id.getValue();
        String name = txt_name.getText().trim();
        byte[] logo = null;

        try {
            if (!jLabel_imagePath.getText().trim().isEmpty()) {
                logo = Files.readAllBytes(Paths.get(jLabel_imagePath.getText()));
            } else {
                logo = brandsBUS.getBrandById(brand_id).getLogo();
            }
        } catch (IOException ex) {
            Logger.getLogger(Brands_GUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error reading image file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (verify()) {
            boolean nameExists = brands_list.stream().anyMatch(brand -> 
                brand.getName().equalsIgnoreCase(name) && brand.getBrand_id() != brand_id
            );
            if (nameExists) {
                JOptionPane.showMessageDialog(null, "Brand name already exists!", "Duplicate Brand", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                brandsBUS.updateBrand(brand_id, name, logo);
            } catch (Exception ex) {
                Logger.getLogger(Brands_GUI.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Failed to update brand. Please check the image size or format.", "Update Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton_updateActionPerformed

    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteActionPerformed
        int brand_id = (int) jSpinner_id.getValue();
        
        int confirm = JOptionPane.showConfirmDialog(null, "Are You Sure You Want to Delete this Brand?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                brandsBUS.deleteBrand(brand_id);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton_deleteActionPerformed

    private void jTable_brandsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_brandsMouseClicked
        int index = jTable_brands.getSelectedRow();
        if (index >= 0) {
            int id = Integer.valueOf(jTable_brands.getValueAt(index, 0).toString());
            String name = jTable_brands.getValueAt(index, 1).toString();

            Brands_DTO selectedBrand = null;
            for (Brands_DTO brand : brands_list) {
                if (brand.getBrand_id() == id) {
                    selectedBrand = brand;
                    break;
                }
            }

            if (selectedBrand != null) {
                jLabel_imagePath.setText("");
                jSpinner_id.setValue(selectedBrand.getBrand_id());
                txt_name.setText(selectedBrand.getName());
                displayByteImage(jLabel_logo.getWidth(), jLabel_logo.getHeight(), selectedBrand.getLogo(), jLabel_logo);
            }
        }
    }//GEN-LAST:event_jTable_brandsMouseClicked

    private void jButton_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_refreshActionPerformed
        populateJTableWithBrands();
    }//GEN-LAST:event_jButton_refreshActionPerformed

    private void jButton_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_clearActionPerformed
        jSpinner_id.setValue(0);
        txt_name.setText("");
        jLabel_logo.setIcon(null);
        jLabel_imagePath.setText("");
    }//GEN-LAST:event_jButton_clearActionPerformed

    private void jButton_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_FirstActionPerformed
        if (!brands_list.isEmpty()) {
            currentIndex = 0;
            displayBrand(currentIndex);
            selectAndScrollToRow(currentIndex);
        }
    }//GEN-LAST:event_jButton_FirstActionPerformed

    private void jButton_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_NextActionPerformed
        if (currentIndex < brands_list.size() - 1) {
            currentIndex++;
            displayBrand(currentIndex);
            selectAndScrollToRow(currentIndex); 
        }
    }//GEN-LAST:event_jButton_NextActionPerformed

    private void jButton_PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PreviousActionPerformed
        if (currentIndex > 0) {
            currentIndex--;
            displayBrand(currentIndex);
            selectAndScrollToRow(currentIndex); 
        }
    }//GEN-LAST:event_jButton_PreviousActionPerformed

    private void jButton_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LastActionPerformed
        if (!brands_list.isEmpty()) {
            currentIndex = brands_list.size() - 1;
            displayBrand(currentIndex);
            selectAndScrollToRow(currentIndex); 
        }
    }//GEN-LAST:event_jButton_LastActionPerformed

    private void txt_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_nameMouseClicked

    }//GEN-LAST:event_txt_nameMouseClicked

    private void txt_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nameFocusGained

    }//GEN-LAST:event_txt_nameFocusGained

    private void txt_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nameFocusLost
        
    }//GEN-LAST:event_txt_nameFocusLost

    private void txt_nameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_nameMousePressed

    }//GEN-LAST:event_txt_nameMousePressed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        String keyword = txt_search.getText().trim().toLowerCase();  
        ArrayList<Brands_DTO> filteredBrands = new ArrayList<>();    

        for (Brands_DTO brand : brands_list) {
            if (brand.getName().toLowerCase().contains(keyword)) {  
                filteredBrands.add(brand); 
            }
        }

        updateTable(filteredBrands);
    }//GEN-LAST:event_btn_searchActionPerformed

    private void btn_undo_brandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_undo_brandActionPerformed
        brandsBUS.undoDeleteBrand();
        populateJTableWithBrands();
    }//GEN-LAST:event_btn_undo_brandActionPerformed

    private void updateTable(ArrayList<Brands_DTO> brandList) {
        DefaultTableModel model = (DefaultTableModel) jTable_brands.getModel();
        model.setRowCount(0);  

        for (Brands_DTO brand : brandList) {
            Object[] rowData = {
                brand.getBrand_id(),
                brand.getName(),
                brand.getLogo() != null ? "Has Logo" : "No Logo"  
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
            java.util.logging.Logger.getLogger(Brands_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Brands_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Brands_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Brands_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Brands_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_undo_brand;
    private javax.swing.JButton jButton_First;
    private javax.swing.JButton jButton_Last;
    private javax.swing.JButton jButton_Next;
    private javax.swing.JButton jButton_Previous;
    private javax.swing.JButton jButton_add;
    private javax.swing.JButton jButton_browse;
    private javax.swing.JButton jButton_clear;
    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_refresh;
    private javax.swing.JButton jButton_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_brands_logo;
    private javax.swing.JLabel jLabel_close;
    private javax.swing.JLabel jLabel_imagePath;
    private javax.swing.JLabel jLabel_logo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner_id;
    private javax.swing.JTable jTable_brands;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
