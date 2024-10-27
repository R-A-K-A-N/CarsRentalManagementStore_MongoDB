/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.Booking_BUS;
import BUS.Cars_BUS;
import BUS.Customers_BUS;
import DTO.Booking_DTO;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.DocumentException;
import org.apache.poi.ss.usermodel.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;
import static org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory.model;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.*;
import org.bson.Document;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.Vector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
/**
 *
 * @author ADMIN
 */
public class BookingList_GUI extends javax.swing.JFrame {

    /**
     * Creates new form BookingList_GUI
     */
    Booking_BUS bookingBUS = new Booking_BUS();
    Customers_BUS customerBUS = new Customers_BUS();
    Cars_BUS carBUS = new Cars_BUS();
    String add_edit;
    public BookingList_GUI(String add_or_edit) {
        initComponents();
        
        add_edit = add_or_edit;
        
        this.setLocationRelativeTo(null);
        
        jTable_bookinglist.setRowHeight(50);
        
        if(add_edit.equals("add"))
        {
            jButton_selectbooking.setEnabled(false);
        }
        
        populateJTableWithBookings();
    }
    
    public void populateJTableWithBookings() {

        ArrayList<Booking_DTO> booking_list = bookingBUS.bookingList();
        
        String[] columnsName = {"ID" , "Car ID", "Customer ID", "Pick Up City", "Pick Up Address", "Pick Up Date", "Pick Up Time", "Drop Off City", "Drop Off Address", "Drop Off Date", "Drop Off Time", "Total Price"};
        
        // jtable rows
        Object[][] rows = new Object[booking_list.size()][columnsName.length];
        
        for(int i=0; i < booking_list.size(); i++)
        {
            rows[i][0] = booking_list.get(i).getBook_id();
            rows[i][1] = booking_list.get(i).getCar_id();
            rows[i][2] = booking_list.get(i).getCustomer_id();
            rows[i][3] = booking_list.get(i).getPickup_city();
            rows[i][4] = booking_list.get(i).getPickup_address();
            rows[i][5] = booking_list.get(i).getPickup_date();
            rows[i][6] = booking_list.get(i).getPickup_time();
            rows[i][7] = booking_list.get(i).getDropoff_city();
            rows[i][8] = booking_list.get(i).getDropoff_address();
            rows[i][9] = booking_list.get(i).getDropoff_date();
            rows[i][10] = booking_list.get(i).getDropoff_time();
            rows[i][11] = booking_list.get(i).getTotal_price();
            
        }
        DefaultTableModel model = new DefaultTableModel(rows,columnsName);
        jTable_bookinglist.setModel(model);
    }
    
    private void exportPDF(File file, JTable table) throws DocumentException, IOException {
        com.itextpdf.text.Document pdfDocument = new com.itextpdf.text.Document();
        PdfWriter.getInstance(pdfDocument, new FileOutputStream(file));
        pdfDocument.open();

        Image carIcon = Image.getInstance("D:\\Nosql\\CarsRentalManagementStore\\CarsRentalManagementStore\\src\\images\\car_bill.png");
        carIcon.scaleToFit(50, 50);
        carIcon.setAlignment(Element.ALIGN_CENTER);
        pdfDocument.add(carIcon);

        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Paragraph title = new Paragraph("Booking List", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        pdfDocument.add(title);
        pdfDocument.add(new Paragraph(" ")); // Add a blank line

        Font contentFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
        for (int i = 0; i < table.getRowCount(); i++) {
            // Create a table for each booking
            PdfPTable bookingTable = new PdfPTable(2);
            bookingTable.setWidthPercentage(100);
            bookingTable.setSpacingBefore(10f);
            bookingTable.setSpacingAfter(10f);

            // Add cells to the booking table
            addBookingCell(bookingTable, "Booking ID: ", table.getValueAt(i, 0).toString(), contentFont);
            addBookingCell(bookingTable, "Car ID: ", table.getValueAt(i, 1).toString(), contentFont);
            addBookingCell(bookingTable, "Customer ID: ", table.getValueAt(i, 2).toString(), contentFont);
            addBookingCell(bookingTable, "Pick Up City: ", table.getValueAt(i, 3).toString(), contentFont);
            addBookingCell(bookingTable, "Pick Up Address: ", table.getValueAt(i, 4).toString(), contentFont);
            addBookingCell(bookingTable, "Pick Up Date: ", table.getValueAt(i, 5).toString(), contentFont);
            addBookingCell(bookingTable, "Pick Up Time: ", table.getValueAt(i, 6).toString(), contentFont);
            addBookingCell(bookingTable, "Drop Off City: ", table.getValueAt(i, 7).toString(), contentFont);
            addBookingCell(bookingTable, "Drop Off Address: ", table.getValueAt(i, 8).toString(), contentFont);
            addBookingCell(bookingTable, "Drop Off Date: ", table.getValueAt(i, 9).toString(), contentFont);
            addBookingCell(bookingTable, "Drop Off Time: ", table.getValueAt(i, 10).toString(), contentFont);
            addBookingCell(bookingTable, "Total Price: ", table.getValueAt(i, 11).toString(), contentFont);

            pdfDocument.add(bookingTable);
        }

        pdfDocument.close();
    }
    
    private void addBookingCell(PdfPTable table, String label, String value, Font font) {
        PdfPCell cellLabel = new PdfPCell(new Phrase(label, font));
        cellLabel.setBorder(Rectangle.NO_BORDER);
        table.addCell(cellLabel);

        PdfPCell cellValue = new PdfPCell(new Phrase(value, font));
        cellValue.setBorder(Rectangle.NO_BORDER);
        table.addCell(cellValue);
    }
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
        jTable_bookinglist = new javax.swing.JTable();
        jButton_selectbooking = new javax.swing.JButton();
        jButton_printTXT = new javax.swing.JButton();
        jButton_printPDF = new javax.swing.JButton();
        jButton_importexcel = new javax.swing.JButton();
        jButton_exportexcel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Booking List");

        jLabel_customers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/list.png"))); // NOI18N

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
                .addGap(30, 30, 30)
                .addComponent(jLabel_customers, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(273, 273, 273)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jTable_bookinglist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_bookinglist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_bookinglistMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_bookinglist);
        jTable_bookinglist.setCellSelectionEnabled(false);
        jTable_bookinglist.setRowSelectionAllowed(true);
        jTable_bookinglist.setColumnSelectionAllowed(false);
        jTable_bookinglist.setDefaultEditor(Object.class, null);

        jButton_selectbooking.setBackground(new java.awt.Color(0, 0, 153));
        jButton_selectbooking.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_selectbooking.setForeground(new java.awt.Color(255, 255, 255));
        jButton_selectbooking.setText("Select Booking");
        jButton_selectbooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_selectbookingActionPerformed(evt);
            }
        });

        jButton_printTXT.setBackground(new java.awt.Color(255, 51, 204));
        jButton_printTXT.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_printTXT.setForeground(new java.awt.Color(255, 255, 255));
        jButton_printTXT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Office/txt-file.png"))); // NOI18N
        jButton_printTXT.setText("Print Bill By .TXT");
        jButton_printTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_printTXTActionPerformed(evt);
            }
        });

        jButton_printPDF.setBackground(new java.awt.Color(51, 255, 51));
        jButton_printPDF.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_printPDF.setForeground(new java.awt.Color(255, 255, 255));
        jButton_printPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Office/pdf.png"))); // NOI18N
        jButton_printPDF.setText("Print Bill By .PDF");
        jButton_printPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_printPDFActionPerformed(evt);
            }
        });

        jButton_importexcel.setBackground(new java.awt.Color(51, 204, 0));
        jButton_importexcel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_importexcel.setForeground(new java.awt.Color(255, 255, 255));
        jButton_importexcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Office/office365.png"))); // NOI18N
        jButton_importexcel.setText("Import Excel");
        jButton_importexcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_importexcelActionPerformed(evt);
            }
        });

        jButton_exportexcel.setBackground(new java.awt.Color(51, 204, 0));
        jButton_exportexcel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_exportexcel.setForeground(new java.awt.Color(255, 255, 255));
        jButton_exportexcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Office/office365.png"))); // NOI18N
        jButton_exportexcel.setText("Export Excel");
        jButton_exportexcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_exportexcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_selectbooking, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jButton_printTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jButton_printPDF)
                        .addGap(47, 47, 47)
                        .addComponent(jButton_importexcel)
                        .addGap(40, 40, 40)
                        .addComponent(jButton_exportexcel)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_selectbooking, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_printTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_printPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_importexcel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_exportexcel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jTable_bookinglistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_bookinglistMouseClicked

    }//GEN-LAST:event_jTable_bookinglistMouseClicked

    private void jButton_selectbookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_selectbookingActionPerformed
        // get the selected customer id and fullname
        try {
            int index = jTable_bookinglist.getSelectedRow();
            String book_id = jTable_bookinglist.getValueAt(index, 0).toString();
            String car_id = jTable_bookinglist.getValueAt(index, 1).toString();
            String customer_id = jTable_bookinglist.getValueAt(index, 2).toString();
            String pickup_city = jTable_bookinglist.getValueAt(index, 3).toString();
            String pickup_address = jTable_bookinglist.getValueAt(index, 4).toString();
            String pickup_date = jTable_bookinglist.getValueAt(index, 5).toString();
            String pickup_time = jTable_bookinglist.getValueAt(index, 6).toString();
            String dropoff_city = jTable_bookinglist.getValueAt(index, 7).toString();
            String dropoff_address = jTable_bookinglist.getValueAt(index, 8).toString();
            String dropoff_date = jTable_bookinglist.getValueAt(index, 9).toString();
            String dropoff_time = jTable_bookinglist.getValueAt(index, 10).toString();
            String total_price = jTable_bookinglist.getValueAt(index, 11).toString();

            Booking_Edit_Remove_GUI.displayBooking(book_id, car_id, customer_id, pickup_city, pickup_address, pickup_date, pickup_time, dropoff_city, dropoff_address, dropoff_date, dropoff_time, total_price);

            //Close this form
            this.dispose();
        } catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "No Booking Selected", "Error", 2);
        }
    }//GEN-LAST:event_jButton_selectbookingActionPerformed

    private void jButton_printTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_printTXTActionPerformed
        // print the selected booking
        try {
            File file = new File("D:\\Nosql\\CarsRentalManagementStore\\CarsRentalManagementStore\\booking.txt"); 
            if(!file.exists())
            {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsolutePath());
            BufferedWriter bw = new BufferedWriter(fw);

            int index = jTable_bookinglist.getSelectedRow();
            String book_id = jTable_bookinglist.getValueAt(index, 0).toString();
            String car_id = jTable_bookinglist.getValueAt(index, 1).toString();
            String customer_id = jTable_bookinglist.getValueAt(index, 2).toString();
            String pickup_city = jTable_bookinglist.getValueAt(index, 3).toString();
            String pickup_address = jTable_bookinglist.getValueAt(index, 4).toString();
            String pickup_date = jTable_bookinglist.getValueAt(index, 5).toString();
            String pickup_time = jTable_bookinglist.getValueAt(index, 6).toString();
            String dropoff_city = jTable_bookinglist.getValueAt(index, 7).toString();
            String dropoff_address = jTable_bookinglist.getValueAt(index, 8).toString();
            String dropoff_date = jTable_bookinglist.getValueAt(index, 9).toString();
            String dropoff_time = jTable_bookinglist.getValueAt(index, 10).toString();
            String total_price = jTable_bookinglist.getValueAt(index, 11).toString();
            String customer_fullname = new Customers_BUS().getCustomerById(Integer.valueOf(customer_id)).getFullname();
            String car_model = new Cars_BUS().getCarById(Integer.valueOf(car_id)).getModel();
            
            bw.write("Booking ID: " + book_id); 
            bw.newLine();
            bw.write("-------------------------------------");// break the line at the beginning and the end
            bw.newLine();
            bw.write("Customer ID: " + customer_id + "| Customer Fullname: " + customer_fullname);
            bw.newLine();
            bw.write("-------------------------------------");// break the line at the beginning and the end
            bw.newLine();
            bw.write("Car ID: " + car_id + "| Car Model: " + car_model);
            bw.newLine();
            bw.write("-------------------------------------");// break the line at the beginning and the end
            bw.newLine();
            bw.write("Pickup City: " + pickup_city);
            bw.newLine();
            bw.write("-------------------------------------");// break the line at the beginning and the end
            bw.newLine();
            bw.write("Pickup Address: " + pickup_address);
            bw.newLine();
            bw.write("-------------------------------------");// break the line at the beginning and the end
            bw.newLine();
            bw.write("Pickup Date: " + pickup_date + " | Time: " + pickup_time);
            bw.newLine();
            bw.write("-------------------------------------");// break the line at the beginning and the end
            bw.newLine();
            bw.write("Dropoff City: " + dropoff_city);
            bw.newLine();
            bw.write("-------------------------------------");// break the line at the beginning and the end
            bw.newLine();
            bw.write("Dropoff Address: " + dropoff_address);
            bw.newLine();
            bw.write("-------------------------------------");// break the line at the beginning and the end
            bw.newLine();
            bw.write("Dropoff Date: " + dropoff_date + " | Time: " + dropoff_time);
            bw.newLine();
            bw.write("-------------------------------------");// break the line at the beginning and the end
            bw.newLine();
            bw.write("Dropoff Time: " + dropoff_time);
            bw.newLine();
            bw.write("-------------------------------------");// break the line at the beginning and the end
            bw.newLine();
            bw.write("Total Price: " + total_price);
            bw.newLine();
            bw.write("-------------------------------------");// break the line at the beginning and the end
            
            bw.close();
            fw.close();
            
            JOptionPane.showMessageDialog(null, "Data Has Been Exported", "Export", 1);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR -> " + ex.getMessage(), "ERROR", 2);
            System.out.println("ERROR ->" + ex.getMessage());
        }
        
    }//GEN-LAST:event_jButton_printTXTActionPerformed

    private void jButton_printPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_printPDFActionPerformed
        try {
            File fileToSave = new File("D:\\Nosql\\CarsRentalManagementStore\\CarsRentalManagementStore\\booking_list.pdf");
            exportPDF(fileToSave, jTable_bookinglist);
            JOptionPane.showMessageDialog(null, "PDF created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error creating PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_printPDFActionPerformed

    private void jButton_importexcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_importexcelActionPerformed
        File excelFile = new File("D:\\Nosql\\CarsRentalManagementStore\\CarsRentalManagementStore\\your_excel_file.xlsx"); // Thay đổi tên file cho phù hợp
        if (!excelFile.exists()) {
            JOptionPane.showMessageDialog(null, "File không tồn tại tại đường dẫn: " + excelFile.getAbsolutePath());
            return;
        }

        try (FileInputStream fis = new FileInputStream(excelFile); 
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet excelSheet = workbook.getSheetAt(0);
            MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
            MongoDatabase database = mongoClient.getDatabase("car_rental_db");
            MongoCollection<org.bson.Document> collection = database.getCollection("reservations");

            // Duyệt qua các hàng trong file Excel và thêm vào MongoDB
            for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
                XSSFRow excelRow = excelSheet.getRow(row);
                org.bson.Document reservationDoc = new org.bson.Document();

                for (int col = 0; col < excelRow.getLastCellNum(); col++) {
                    XSSFCell cell = excelRow.getCell(col);
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case STRING:
                                reservationDoc.append(excelSheet.getRow(0).getCell(col).getStringCellValue(), cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    reservationDoc.append(excelSheet.getRow(0).getCell(col).getStringCellValue(), cell.getDateCellValue());
                                } else {
                                    reservationDoc.append(excelSheet.getRow(0).getCell(col).getStringCellValue(), cell.getNumericCellValue());
                                }
                                break;
                            case BOOLEAN:
                                reservationDoc.append(excelSheet.getRow(0).getCell(col).getStringCellValue(), cell.getBooleanCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }
                collection.insertOne(reservationDoc);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy file: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi đọc file: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton_importexcelActionPerformed
    
    private void jButton_exportexcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_exportexcelActionPerformed
        FileOutputStream excelFos = null;
        XSSFWorkbook excelJTableExporter = null;
        BufferedOutputStream excelBos = null;
        try {
            JFileChooser excelFileChooser = new JFileChooser("D:\\Nosql\\CarsRentalManagementStore\\CarsRentalManagementStore");
            excelFileChooser.setDialogTitle("Save As ..");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Files", "xls", "xlsx", "xlsm");
            excelFileChooser.setFileFilter(fnef);
            int excelchooser = excelFileChooser.showSaveDialog(null);

            if (excelchooser == JFileChooser.APPROVE_OPTION) {
                excelJTableExporter = new XSSFWorkbook();
                XSSFSheet excelSheet = excelJTableExporter.createSheet("JTable Sheet");

                // Lấy đối tượng TableModel từ bảng của bạn
                TableModel model = jTable_bookinglist.getModel();

                // Xuất tiêu đề của bảng
                XSSFRow excelHeaderRow = excelSheet.createRow(0);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    XSSFCell excelHeaderCell = excelHeaderRow.createCell(j);
                    excelHeaderCell.setCellValue(model.getColumnName(j));
                }

                // Xuất dữ liệu của bảng
                for (int i = 0; i < model.getRowCount(); i++) {
                    XSSFRow excelRow = excelSheet.createRow(i + 1);
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        XSSFCell excelCell = excelRow.createCell(j);
                        Object value = model.getValueAt(i, j);
                        if (value != null) {
                            excelCell.setCellValue(value.toString());
                        }
                    }
                }

                File selectedFile = excelFileChooser.getSelectedFile();
                if (!selectedFile.getAbsolutePath().endsWith(".xlsx")) {
                    selectedFile = new File(selectedFile + ".xlsx");
                }

                excelFos = new FileOutputStream(selectedFile);
                excelBos = new BufferedOutputStream(excelFos);
                excelJTableExporter.write(excelBos);
                JOptionPane.showMessageDialog(null, "Exported Successfully");
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            try {
                if (excelBos != null) {
                    excelBos.close();
                }
                if (excelFos != null) {
                    excelFos.close();
                }
                if (excelJTableExporter != null) {
                    excelJTableExporter.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }//GEN-LAST:event_jButton_exportexcelActionPerformed

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
            java.util.logging.Logger.getLogger(BookingList_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookingList_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookingList_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookingList_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookingList_GUI("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_exportexcel;
    private javax.swing.JButton jButton_importexcel;
    private javax.swing.JButton jButton_printPDF;
    private javax.swing.JButton jButton_printTXT;
    private javax.swing.JButton jButton_selectbooking;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_close;
    private javax.swing.JLabel jLabel_customers;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_bookinglist;
    // End of variables declaration//GEN-END:variables
}
