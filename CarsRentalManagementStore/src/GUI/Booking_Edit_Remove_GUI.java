/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.Brands_BUS;
import BUS.Cars_BUS;
import BUS.Locations_BUS;
import BUS.Booking_BUS;
import BUS.Customers_BUS;
import DTO.Locations_DTO;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
/**
 *
 * @author ADMIN
 */
public class Booking_Edit_Remove_GUI extends javax.swing.JFrame {

    /**
     * Creates new form Booking_Edit_Remove_GUI
     */
    Border border = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 0, 153));
    Border panel_border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.darkGray);
    Brands_BUS brandBus = new Brands_BUS();
    Cars_BUS carsBus = new Cars_BUS();
    Locations_BUS locationBus = new Locations_BUS();
    Booking_BUS bookingBus = new Booking_BUS();
    Customers_BUS customerBus = new Customers_BUS();
    HashMap<Integer, String> map = brandBus.getBrandsHashMap();
    
    public Booking_Edit_Remove_GUI() {
        initComponents();
        
        //center the form
        this.setLocationRelativeTo(null);
        
        jLabel_selectcar.setBorder(border);
        jLabel_pickup.setBorder(border);
        jLabel_selectcustomer.setBorder(border);
        jLabel_dropoff.setBorder(border);
        
        jPanel_selectcar.setBorder(panel_border);
        jPanel_pickup.setBorder(panel_border);
        jPanel_selectcustomer.setBorder(panel_border);
        jPanel_dropoff.setBorder(panel_border);
        
        populateComboboxBrands();
    }

    public void populateComboboxAddress(String pickup_or_dropoff, String city) {
        if (pickup_or_dropoff.equals("pickup")){
                jComboBox_PickUp_Address.removeAllItems();
            for (Locations_DTO l : locationBus.locationsListByCity(city))
            {
                jComboBox_PickUp_Address.addItem(l.getAddress());
            }
        } 
        else if (pickup_or_dropoff.equals("dropoff")){
                jComboBox_DropOff_Address.removeAllItems();
            for (Locations_DTO l : locationBus.locationsListByCity(city))
            {
                jComboBox_DropOff_Address.addItem(l.getAddress());
            }
        } 
    }
    
    public void populateComboboxBrands() {
        for (String s : map.values()) {
            jComboBox_Brands.addItem(s);
        }
    }
    
    public static void displayCustomer (String id, String name)
    {
        jTextField_customer.setText(name);
        jLabel_customer_id.setText(id);
    }
    
    public static void displayCarInfo(String id, String model, String price)
    {
        jLabel_car_id.setText(id);
        jLabel_car_model.setText(model);
        jLabel_price_car.setText(price);
    }
    
    public static void displayBooking(String book_id, String car_id, String customer_id, String pickup_city, 
                                    String pickup_address, String pickup_date, String pickup_time, String dropoff_city, 
                                    String dropoff_address, String dropoff_date, String dropoff_time, String total_price)
    {
        jLabel_booking_id.setText(book_id);
        jLabel_car_id.setText(car_id);
        
        try {
            jLabel_car_model.setText(new Cars_BUS().getCarById(Integer.valueOf(car_id)).getModel());
            jLabel_price_car.setText(String.valueOf(new Cars_BUS().getCarById(Integer.valueOf(car_id)).getPrice()));
            jLabel_brand_id.setText(String.valueOf(new Cars_BUS().getCarById(Integer.valueOf(car_id)).getBrand()));
            jComboBox_Brands.setSelectedItem(new Brands_BUS().getBrandById(new Cars_BUS().getCarById(Integer.valueOf(car_id)).getBrand()).getName());
            jTextField_customer.setText(new Customers_BUS().getCustomerById(Integer.valueOf(customer_id)).getFullname());
        } catch (Exception ex) {
            Logger.getLogger(Booking_Edit_Remove_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        jLabel_customer_id.setText(customer_id);
        jLabel_total_price.setText(total_price);
        
        jComboBox_PickUp_City.setSelectedItem(pickup_city); 
        jComboBox_PickUp_Address.setSelectedItem(pickup_address);
        jComboBox_DropOff_City.setSelectedItem(dropoff_city); 
        jComboBox_DropOff_Address.setSelectedItem (dropoff_address);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            jDateChooser_PickUp_Date.setDate(dateFormat.parse(pickup_date));
            jDateChooser_DropOff_Date.setDate(dateFormat.parse(dropoff_date));
            
            timePicker_PickUp_Time.setText(pickup_time);
            timePicker_DropOff_Time.setText(dropoff_time);
        } catch (ParseException ex) {
            Logger.getLogger(Booking_Edit_Remove_GUI.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel_timetable = new javax.swing.JLabel();
        jLabel_close = new javax.swing.JLabel();
        jPanel_selectcar = new javax.swing.JPanel();
        jLabel_selectcar = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox_Brands = new javax.swing.JComboBox<>();
        jButton_selectcar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel_brand_id = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel_car_id = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel_car_model = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel_price_car = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel_selectcustomer = new javax.swing.JPanel();
        jLabel_selectcustomer = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField_customer = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel_customer_id = new javax.swing.JLabel();
        jButton_selectcustomer = new javax.swing.JButton();
        jPanel_pickup = new javax.swing.JPanel();
        jLabel_pickup = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox_PickUp_City = new javax.swing.JComboBox<>();
        jComboBox_PickUp_Address = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jDateChooser_PickUp_Date = new com.toedter.calendar.JDateChooser();
        timePicker_PickUp_Time = new com.github.lgooddatepicker.components.TimePicker();
        jPanel_dropoff = new javax.swing.JPanel();
        jLabel_dropoff = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jComboBox_DropOff_City = new javax.swing.JComboBox<>();
        jComboBox_DropOff_Address = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jDateChooser_DropOff_Date = new com.toedter.calendar.JDateChooser();
        timePicker_DropOff_Time = new com.github.lgooddatepicker.components.TimePicker();
        jButton_editbooking = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel_price_text = new javax.swing.JLabel();
        jLabel_total_price = new javax.swing.JLabel();
        jButton_calculate = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel_price_text1 = new javax.swing.JLabel();
        jButton_bookinglist = new javax.swing.JButton();
        jLabel_booking_id = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jButton_remove_cancel = new javax.swing.JButton();
        btn_undo_book = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(0, 51, 102));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Booking Edit / Remove");
        jLabel4.setFont(new java.awt.Font("Verdana", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel_timetable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/timetable.png"))); // NOI18N

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
                .addComponent(jLabel_timetable, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(238, 238, 238)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_close, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel_timetable, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel_close, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel_selectcar.setBackground(new java.awt.Color(72, 219, 251));

        jLabel_selectcar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_selectcar.setText("Select a Car");
        jLabel_selectcar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_selectcar.setForeground(new java.awt.Color(51, 51, 255));

        jLabel6.setText("Brand:");
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel7.setText("Car:");
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jComboBox_Brands.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        jComboBox_Brands.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox_Brands.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_BrandsActionPerformed(evt);
            }
        });

        jButton_selectcar.setText("Select Car");
        jButton_selectcar.setBackground(new java.awt.Color(0, 0, 153));
        jButton_selectcar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_selectcar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_selectcar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_selectcarActionPerformed(evt);
            }
        });

        jLabel11.setText("ID:");
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel_brand_id.setText("000");
        jLabel_brand_id.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel16.setText("ID:");
        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel_car_id.setText("000");
        jLabel_car_id.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel21.setText("Model:");
        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel_car_model.setText("###");
        jLabel_car_model.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel22.setText("Price:");
        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel_price_car.setText("000");
        jLabel_price_car.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel23.setText("|");
        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel_selectcarLayout = new javax.swing.GroupLayout(jPanel_selectcar);
        jPanel_selectcar.setLayout(jPanel_selectcarLayout);
        jPanel_selectcarLayout.setHorizontalGroup(
            jPanel_selectcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_selectcar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_selectcarLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel_selectcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel_selectcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_selectcarLayout.createSequentialGroup()
                        .addComponent(jButton_selectcar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(jPanel_selectcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_selectcarLayout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_car_model))
                            .addGroup(jPanel_selectcarLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_car_id)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_price_car))))
                    .addGroup(jPanel_selectcarLayout.createSequentialGroup()
                        .addComponent(jComboBox_Brands, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_brand_id)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_selectcarLayout.setVerticalGroup(
            jPanel_selectcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_selectcarLayout.createSequentialGroup()
                .addComponent(jLabel_selectcar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_selectcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel_brand_id)
                    .addComponent(jComboBox_Brands, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel_selectcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_selectcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(jLabel_price_car))
                    .addGroup(jPanel_selectcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(jLabel_car_id)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_selectcar)
                        .addComponent(jLabel7)))
                .addGap(18, 18, 18)
                .addGroup(jPanel_selectcarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel_car_model))
                .addGap(0, 25, Short.MAX_VALUE))
        );

        jPanel_selectcustomer.setBackground(new java.awt.Color(72, 219, 251));

        jLabel_selectcustomer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_selectcustomer.setText("Select Customer");
        jLabel_selectcustomer.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_selectcustomer.setForeground(new java.awt.Color(51, 51, 255));

        jLabel9.setText("ID:");
        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField_customer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_customer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_customerMouseClicked(evt);
            }
        });

        jLabel10.setText("Customer:");
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel_customer_id.setText("000");
        jLabel_customer_id.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton_selectcustomer.setText("Select Customer");
        jButton_selectcustomer.setBackground(new java.awt.Color(0, 0, 153));
        jButton_selectcustomer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_selectcustomer.setForeground(new java.awt.Color(255, 255, 255));
        jButton_selectcustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_selectcustomerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_selectcustomerLayout = new javax.swing.GroupLayout(jPanel_selectcustomer);
        jPanel_selectcustomer.setLayout(jPanel_selectcustomerLayout);
        jPanel_selectcustomerLayout.setHorizontalGroup(
            jPanel_selectcustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_selectcustomer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_selectcustomerLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_selectcustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_selectcustomerLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_customer_id)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel_selectcustomerLayout.createSequentialGroup()
                        .addComponent(jTextField_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_selectcustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_selectcustomerLayout.setVerticalGroup(
            jPanel_selectcustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_selectcustomerLayout.createSequentialGroup()
                .addComponent(jLabel_selectcustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(jPanel_selectcustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_customer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_selectcustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jButton_selectcustomer)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel_selectcustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel_customer_id))
                .addGap(21, 21, 21))
        );

        jPanel_pickup.setBackground(new java.awt.Color(72, 219, 251));

        jLabel_pickup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_pickup.setText("Pick Up Location & Date");
        jLabel_pickup.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_pickup.setForeground(new java.awt.Color(51, 51, 255));

        jLabel12.setText("City:");
        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel13.setText("Address:");
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jComboBox_PickUp_City.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ho Chi Minh City", "Buon Ma Thuot City", "Da Lat City", "Dien Bien Phu City", "Dong Ha City", "Dong Hoi City", "Cao Lanh City", "Cao Bang City", "Bac Lieu City", "Ba Ria City", "Bao Loc City", "Bac Giang City", "Bac Kan City", "Bac Ninh City", "Ben Tre City", "Bien Hoa City", "Ca Mau City", "Cam Ranh City", "Cam Pha City", "Chau Đoc City", "Dong Xoai City", "Ha Giang City", "Ha Long City", "Ha Tien City", "Ha Tinh City", "Hai Duong City", "Hoa Binh City", "Hoi An City", "Hue City ", "Hung Yen City", "Kon Tum City", "Lai Chau City", "Lang Son City", "Lao Cai City", "Long Xuyen City", "Mong Cai City", "My Tho City", "Nam Dinh City", "Nha Trang City", "Ninh Binh City", "Phan Rang - Thap Cham City", "Phan Thiet City", "Phu Ly City", "Phuc Yen City", "Pleiku City", "Quang Ngai City", "Quy Nhon City", "Rach Gia City", "Sa Dec City", "Sam Son City", "Soc Trang City", "Son La City", "Song Cong City", "Tam Diep City", "Tam Ky City", "Tan An City", "Tay Ninh City", "Thai Binh City", "Thai Nguyen City", "Thanh Hoa City", "Thu Dau Mot City", "Tra Vinh City", "Tuy Hoa City", "Tuyen Quang City", "Thuan An City", "Uong Bi City", "Vi Thanh City", "Viet Tri City", "Vinh City", "Vinh Long City", "Vinh Yen City", "Vung Tau City", "Yen Bai City", "Di An City", "Phu Quoc City", "Nga Bay City", "Long Khanh City", "Hong Ngu City", "Gia Nghia City", "Ha Noi City", "Hai Phong City", "Can Tho City", "Da Nang City", "Thu Duc City" }));
        jComboBox_PickUp_City.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_PickUp_CityActionPerformed(evt);
            }
        });

        jComboBox_PickUp_Address.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel14.setText("Date:");
        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel15.setText("Time:");
        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel_pickupLayout = new javax.swing.GroupLayout(jPanel_pickup);
        jPanel_pickup.setLayout(jPanel_pickupLayout);
        jPanel_pickupLayout.setHorizontalGroup(
            jPanel_pickupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_pickup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_pickupLayout.createSequentialGroup()
                .addGroup(jPanel_pickupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addGroup(jPanel_pickupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel_pickupLayout.createSequentialGroup()
                            .addGap(39, 39, 39)
                            .addComponent(jLabel12))
                        .addGroup(jPanel_pickupLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel13))))
                .addGap(28, 28, 28)
                .addGroup(jPanel_pickupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox_PickUp_City, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox_PickUp_Address, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser_PickUp_Date, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .addComponent(timePicker_PickUp_Time, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_pickupLayout.setVerticalGroup(
            jPanel_pickupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_pickupLayout.createSequentialGroup()
                .addComponent(jLabel_pickup, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_pickupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jComboBox_PickUp_City, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_pickupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_PickUp_Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel_pickupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jDateChooser_PickUp_Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_pickupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(timePicker_PickUp_Time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel_dropoff.setBackground(new java.awt.Color(72, 219, 251));

        jLabel_dropoff.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_dropoff.setText("Drop Off Location & Date");
        jLabel_dropoff.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_dropoff.setForeground(new java.awt.Color(51, 51, 255));

        jLabel17.setText("City:");
        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel18.setText("Address:");
        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jComboBox_DropOff_City.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ho Chi Minh City", "Buon Ma Thuot City", "Da Lat City", "Dien Bien Phu City", "Dong Ha City", "Dong Hoi City", "Cao Lanh City", "Cao Bang City", "Bac Lieu City", "Ba Ria City", "Bao Loc City", "Bac Giang City", "Bac Kan City", "Bac Ninh City", "Ben Tre City", "Bien Hoa City", "Ca Mau City", "Cam Ranh City", "Cam Pha City", "Chau Đoc City", "Dong Xoai City", "Ha Giang City", "Ha Long City", "Ha Tien City", "Ha Tinh City", "Hai Duong City", "Hoa Binh City", "Hoi An City", "Hue City ", "Hung Yen City", "Kon Tum City", "Lai Chau City", "Lang Son City", "Lao Cai City", "Long Xuyen City", "Mong Cai City", "My Tho City", "Nam Dinh City", "Nha Trang City", "Ninh Binh City", "Phan Rang - Thap Cham City", "Phan Thiet City", "Phu Ly City", "Phuc Yen City", "Pleiku City", "Quang Ngai City", "Quy Nhon City", "Rach Gia City", "Sa Dec City", "Sam Son City", "Soc Trang City", "Son La City", "Song Cong City", "Tam Diep City", "Tam Ky City", "Tan An City", "Tay Ninh City", "Thai Binh City", "Thai Nguyen City", "Thanh Hoa City", "Thu Dau Mot City", "Tra Vinh City", "Tuy Hoa City", "Tuyen Quang City", "Thuan An City", "Uong Bi City", "Vi Thanh City", "Viet Tri City", "Vinh City", "Vinh Long City", "Vinh Yen City", "Vung Tau City", "Yen Bai City", "Di An City", "Phu Quoc City", "Nga Bay City", "Long Khanh City", "Hong Ngu City", "Gia Nghia City", "Ha Noi City", "Hai Phong City", "Can Tho City", "Da Nang City", "Thu Duc City" }));
        jComboBox_DropOff_City.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_DropOff_CityActionPerformed(evt);
            }
        });

        jComboBox_DropOff_Address.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel19.setText("Date:");
        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel20.setText("Time:");
        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel_dropoffLayout = new javax.swing.GroupLayout(jPanel_dropoff);
        jPanel_dropoff.setLayout(jPanel_dropoffLayout);
        jPanel_dropoffLayout.setHorizontalGroup(
            jPanel_dropoffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_dropoff, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
            .addGroup(jPanel_dropoffLayout.createSequentialGroup()
                .addGroup(jPanel_dropoffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19)
                    .addGroup(jPanel_dropoffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel_dropoffLayout.createSequentialGroup()
                            .addGap(39, 39, 39)
                            .addComponent(jLabel17))
                        .addGroup(jPanel_dropoffLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel18))))
                .addGap(28, 28, 28)
                .addGroup(jPanel_dropoffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox_DropOff_City, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox_DropOff_Address, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser_DropOff_Date, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .addComponent(timePicker_DropOff_Time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_dropoffLayout.setVerticalGroup(
            jPanel_dropoffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_dropoffLayout.createSequentialGroup()
                .addComponent(jLabel_dropoff, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_dropoffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jComboBox_DropOff_City, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_dropoffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_DropOff_Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel_dropoffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jDateChooser_DropOff_Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_dropoffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(timePicker_DropOff_Time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jButton_editbooking.setText("Edit Booking");
        jButton_editbooking.setBackground(new java.awt.Color(0, 0, 153));
        jButton_editbooking.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_editbooking.setForeground(new java.awt.Color(255, 255, 255));
        jButton_editbooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_editbookingActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));

        jLabel_price_text.setText("Total Price:");
        jLabel_price_text.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel_total_price.setText("0000");
        jLabel_total_price.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton_calculate.setText("calculate");
        jButton_calculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_calculateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel_price_text)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_total_price, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_calculate)
                .addGap(19, 19, 19))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_price_text)
                    .addComponent(jLabel_total_price)
                    .addComponent(jButton_calculate))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(72, 219, 251));

        jLabel_price_text1.setText("Select Booking:");
        jLabel_price_text1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton_bookinglist.setText("Booking List");
        jButton_bookinglist.setBackground(new java.awt.Color(0, 0, 153));
        jButton_bookinglist.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_bookinglist.setForeground(new java.awt.Color(255, 255, 255));
        jButton_bookinglist.setPreferredSize(new java.awt.Dimension(112, 29));
        jButton_bookinglist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_bookinglistActionPerformed(evt);
            }
        });

        jLabel_booking_id.setText("00");
        jLabel_booking_id.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel24.setText("ID:");
        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel_price_text1)
                .addGap(18, 18, 18)
                .addComponent(jButton_bookinglist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_booking_id)
                .addGap(47, 47, 47))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(jLabel_booking_id))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_price_text1)
                        .addComponent(jButton_bookinglist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jButton_remove_cancel.setText("Remove/Cancel Booking");
        jButton_remove_cancel.setBackground(new java.awt.Color(255, 0, 255));
        jButton_remove_cancel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_remove_cancel.setForeground(new java.awt.Color(255, 255, 255));
        jButton_remove_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_remove_cancelActionPerformed(evt);
            }
        });

        btn_undo_book.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/undo.png"))); // NOI18N
        btn_undo_book.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_undo_bookActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel_selectcar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel_selectcustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel_dropoff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel_pickup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton_remove_cancel)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_editbooking, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_undo_book, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(563, 563, 563))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel_pickup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel_dropoff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_editbooking, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_remove_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel_selectcar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel_selectcustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btn_undo_book, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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

    private void jComboBox_BrandsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_BrandsActionPerformed
        int brand_id = 0;
        for (Map.Entry<Integer, String> entry : map.entrySet())
        {
            if (entry.getValue().equals(jComboBox_Brands.getSelectedItem().toString()))
            {
                brand_id = entry.getKey();
            }
        }
        jLabel_brand_id.setText(String.valueOf(brand_id));
    }//GEN-LAST:event_jComboBox_BrandsActionPerformed

    private void jButton_selectcarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_selectcarActionPerformed
        //get the brand id
        int brand_id = Integer.valueOf(jLabel_brand_id.getText());

        CarsListByBrand_GUI carslbr_gui = new CarsListByBrand_GUI(brand_id, "edit");
        carslbr_gui.setVisible(true);
    }//GEN-LAST:event_jButton_selectcarActionPerformed

    private void jButton_selectcustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_selectcustomerActionPerformed
        //show the customer list
        CustomersList_GUI cstl_gui = new CustomersList_GUI("edit");
        cstl_gui.setVisible(true);
    }//GEN-LAST:event_jButton_selectcustomerActionPerformed

    private void jComboBox_PickUp_CityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_PickUp_CityActionPerformed
        // populate jComboBox address depending on the city combobox
        populateComboboxAddress("pickup", jComboBox_PickUp_City.getSelectedItem().toString());
    }//GEN-LAST:event_jComboBox_PickUp_CityActionPerformed

    private void jComboBox_DropOff_CityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_DropOff_CityActionPerformed
        // populate jComboBox address depending on the city combobox
        populateComboboxAddress("dropoff", jComboBox_DropOff_City.getSelectedItem().toString());
    }//GEN-LAST:event_jComboBox_DropOff_CityActionPerformed

    private void jButton_bookinglistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_bookinglistActionPerformed
        BookingList_GUI bkl_gui = new BookingList_GUI("edit");
        bkl_gui.setVisible(true);
    }//GEN-LAST:event_jButton_bookinglistActionPerformed

    private void jButton_editbookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_editbookingActionPerformed
        try{
            int book_id = Integer.valueOf(jLabel_booking_id.getText());
            // get the car info
            int car_id = Integer.valueOf(jLabel_car_id.getText());
            // get the customer info
            int customer_id = Integer.valueOf(jLabel_customer_id.getText());
            // get the pick up info
            String pickup_city = jComboBox_PickUp_City.getSelectedItem().toString();
            String pickup_address = jComboBox_PickUp_Address.getSelectedItem().toString();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String pickup_date = dateFormat.format(jDateChooser_PickUp_Date.getDate());
            Date pickup_date_ = dateFormat.parse(pickup_date);
            String pickup_time = timePicker_PickUp_Time.getText();

            // get the dropoff info
            String dropoff_city = jComboBox_DropOff_City.getSelectedItem().toString();
            String dropoff_address = jComboBox_DropOff_Address.getSelectedItem().toString();

            String dropoff_date = dateFormat.format(jDateChooser_DropOff_Date.getDate());
            Date dropoff_date_ = dateFormat.parse(dropoff_date);
            String dropoff_time = timePicker_DropOff_Time.getText();

            // get date difference in days
            long diff = dropoff_date_.getTime() - pickup_date_.getTime();
            //System.out.println("diff - " + diff);

            int diff_days = (int) (diff / 1000 / 60 / 60 / 24);
            //System.out.println("diff_days - " + diff_days);

            int price = Integer.valueOf(jLabel_price_car.getText());
            int total_price = diff_days * price;

            jLabel_price_text.setText("Total Price (" + String.valueOf(price) + " x " + String.valueOf(diff_days) +"):");
            jLabel_total_price.setText(String.valueOf(total_price));
            //check if the dropoff date is before the pickup date
            if(dropoff_date_.before(pickup_date_))
            {
                JOptionPane.showMessageDialog(null, "The Drop Off Date Must Be After The Pickup Date", "Invalid Date", 2);
            }
            //check if the dropoff date is equal to the pickup date
            else if(dropoff_date_.equals(pickup_date_))
            {
                //check if the dropoff date is before the pickup time
                if(timePicker_DropOff_Time.getTime().isBefore(timePicker_PickUp_Time.getTime()))
                {
                    JOptionPane.showMessageDialog(null, "The Drop Off Time Must Be After The Pickup Time", "Invalid Time", 2);
                }
                else
                {
                    // if it's the same day in different pickup and drop off time
                    // we will count it as 1H
                    total_price = 1 * price;
                    jLabel_price_text.setText("Total Price (" + String.valueOf(price) + " x " + String.valueOf(1) +"):");
                    jLabel_total_price.setText(String.valueOf(total_price));
                    bookingBus.editBooking(book_id, car_id, customer_id, pickup_city, pickup_address, pickup_date, pickup_time, dropoff_city, dropoff_address, dropoff_date, dropoff_time, total_price);
                }
            }
            else
            {
                bookingBus.editBooking(book_id, car_id, customer_id, pickup_city, pickup_address, pickup_date, pickup_time, dropoff_city, dropoff_address, dropoff_date, dropoff_time, total_price);
            }
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Enter Valid Data - Make Sure To Enter All The Information", "Invalid Info", 2);
        }
    }//GEN-LAST:event_jButton_editbookingActionPerformed

    private void jButton_remove_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_remove_cancelActionPerformed
        int book_id = Integer.valueOf(jLabel_booking_id.getText());
        int confirm = JOptionPane.showConfirmDialog(null, "Are You Sure You Want to Delete this Reservation?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                bookingBus.removeBooking(book_id);
            } catch (Exception ex) {
                Logger.getLogger(Booking_Edit_Remove_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_jButton_remove_cancelActionPerformed

    private void jButton_calculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_calculateActionPerformed
        try {
            // calculate the total price
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String pickup_date = dateFormat.format(jDateChooser_PickUp_Date.getDate());
            Date pickup_date_ = dateFormat.parse(pickup_date);
            //String pickup_time = timePicker_PickUp_Time.getText();

            String dropoff_date = dateFormat.format(jDateChooser_DropOff_Date.getDate());
            Date dropoff_date_ = dateFormat.parse(dropoff_date);
            //String dropoff_time = timePicker_DropOff_Time.getText();

            long diff = dropoff_date_.getTime() - pickup_date_.getTime();
            //System.out.println("diff - " + diff);

            int diff_days = (int) (diff / 1000/ 60/ 60/ 24);
            //System.out.println("diff_days - " + diff_days);

            int price = Integer.valueOf(jLabel_price_car.getText());
            int total_price = diff_days * price;

            jLabel_price_text.setText("Total Price (" + String.valueOf(price) + " x " + String.valueOf(diff_days) +"):");
            jLabel_total_price.setText(String.valueOf(total_price));
            //check if the dropoff date is before the pickup date
            if(dropoff_date_.before(pickup_date_))
            {
                JOptionPane.showMessageDialog(null, "The Drop Off Date Must Be After The Pickup Date", "Invalid Date", 2);
            }
            //check if the dropoff date is equal to the pickup date
            else if(dropoff_date_.equals(pickup_date_))
            {
                //check if the dropoff date is before the pickup time
                if(timePicker_DropOff_Time.getTime().isBefore(timePicker_PickUp_Time.getTime()))
                {
                    JOptionPane.showMessageDialog(null, "The Drop Off Time Must Be After The Pickup Time", "Invalid Time", 2);
                }
                else
                {
                    // if it's the same day in different pickup and drop off time
                    // we will count it as 1H
                    total_price = 1 * price;
                    jLabel_price_text.setText("Total Price (" + String.valueOf(price) + " x " + String.valueOf(1) +"):");
                    jLabel_total_price.setText(String.valueOf(total_price));
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "You Need To Select a Car and Pickup/Drop Date", "Invalid Info", 2);
        }
    }//GEN-LAST:event_jButton_calculateActionPerformed

    private void jTextField_customerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_customerMouseClicked

    }//GEN-LAST:event_jTextField_customerMouseClicked

    private void btn_undo_bookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_undo_bookActionPerformed
        try {
            int book_id = Integer.valueOf(jLabel_booking_id.getText());

            bookingBus.undoRemoveBooking(book_id);

            JOptionPane.showMessageDialog(this, "Booking has been restored successfully.", "Undo", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid booking ID format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error undoing booking removal: " + ex.getMessage(), "Undo Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_undo_bookActionPerformed

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
            java.util.logging.Logger.getLogger(Booking_Edit_Remove_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Booking_Edit_Remove_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Booking_Edit_Remove_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Booking_Edit_Remove_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Booking_Edit_Remove_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_undo_book;
    private javax.swing.JButton jButton_bookinglist;
    private javax.swing.JButton jButton_calculate;
    private javax.swing.JButton jButton_editbooking;
    private javax.swing.JButton jButton_remove_cancel;
    private javax.swing.JButton jButton_selectcar;
    private javax.swing.JButton jButton_selectcustomer;
    public static javax.swing.JComboBox<String> jComboBox_Brands;
    public static javax.swing.JComboBox<String> jComboBox_DropOff_Address;
    public static javax.swing.JComboBox<String> jComboBox_DropOff_City;
    public static javax.swing.JComboBox<String> jComboBox_PickUp_Address;
    public static javax.swing.JComboBox<String> jComboBox_PickUp_City;
    public static com.toedter.calendar.JDateChooser jDateChooser_DropOff_Date;
    public static com.toedter.calendar.JDateChooser jDateChooser_PickUp_Date;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private static javax.swing.JLabel jLabel_booking_id;
    private static javax.swing.JLabel jLabel_brand_id;
    private static javax.swing.JLabel jLabel_car_id;
    private static javax.swing.JLabel jLabel_car_model;
    private javax.swing.JLabel jLabel_close;
    private static javax.swing.JLabel jLabel_customer_id;
    private javax.swing.JLabel jLabel_dropoff;
    private javax.swing.JLabel jLabel_pickup;
    private static javax.swing.JLabel jLabel_price_car;
    private javax.swing.JLabel jLabel_price_text;
    private javax.swing.JLabel jLabel_price_text1;
    private javax.swing.JLabel jLabel_selectcar;
    private javax.swing.JLabel jLabel_selectcustomer;
    private javax.swing.JLabel jLabel_timetable;
    private static javax.swing.JLabel jLabel_total_price;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel_dropoff;
    private javax.swing.JPanel jPanel_pickup;
    private javax.swing.JPanel jPanel_selectcar;
    private javax.swing.JPanel jPanel_selectcustomer;
    private static javax.swing.JTextField jTextField_customer;
    public static com.github.lgooddatepicker.components.TimePicker timePicker_DropOff_Time;
    public static com.github.lgooddatepicker.components.TimePicker timePicker_PickUp_Time;
    // End of variables declaration//GEN-END:variables
}
