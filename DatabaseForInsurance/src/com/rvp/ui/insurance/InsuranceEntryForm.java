/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rvp.ui.insurance;

import com.rvp.dao.InsuranceDAO;
import com.rvp.dto.InsuranceDTO;
import com.rvp.dto.StaffDetailsDTO;
import com.rvp.exception.NoBranchNamesFoundException;
import com.rvp.exception.NoCompanyNamesFoundException;
import com.rvp.exception.NoSchemeNamesFoundException;
import com.rvp.service.InsuranceService;
import com.rvp.util.LookAndFeel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author vijay_singh12
 */
public class InsuranceEntryForm extends javax.swing.JFrame {

    /**
     * Creates new form ApplicationEntryForm
     */
    private int userId;
    private Toolkit tk = Toolkit.getDefaultToolkit();
    private Dimension di = tk.getScreenSize();
    private String screenName;
    private String[] companyNames;
    private String[] branchNames;
    private String[] schemeNames;
    private boolean check = true;

    public InsuranceEntryForm(int userId) throws NoCompanyNamesFoundException, NoBranchNamesFoundException, NoSchemeNamesFoundException {

        this.screenName = "newentry";
        this.companyNames = new InsuranceDAO().getCompanyNames();
        this.branchNames = new InsuranceDAO().getBranchName();
        this.schemeNames = new InsuranceDAO().getSchemeNames();
        this.userId = userId;
        dto = new InsuranceDTO();
        LookAndFeel.look();
        initComponents();
        int frame_x = getSize().height;
        int frame_y = getSize().width;
        this.setLocation((di.width - frame_y) / 2, (di.height - frame_x) / 2);

    }

    public InsuranceEntryForm(int userId, InsuranceDTO dto) throws NoCompanyNamesFoundException, NoBranchNamesFoundException, NoSchemeNamesFoundException {
        this.screenName = "newentry";
        this.companyNames = new InsuranceDAO().getCompanyNames();
        this.branchNames = new InsuranceDAO().getBranchName();
        this.schemeNames = new InsuranceDAO().getSchemeNames();
        this.userId = userId;
        this.dto = dto;
        LookAndFeel.look();
        initComponents();
        int frame_x = getSize().height;
        int frame_y = getSize().width;
        this.companyNames = new InsuranceDAO().getCompanyNames();
        this.branchNames = new InsuranceDAO().getBranchName();
        this.schemeNames = new InsuranceDAO().getSchemeNames();

        this.setLocation((di.width - frame_y) / 2, (di.height - frame_x) / 2);
        initializeValues();
    }

    public InsuranceEntryForm(int userId, InsuranceDTO dto,boolean check) throws NoCompanyNamesFoundException, NoBranchNamesFoundException, NoSchemeNamesFoundException {
        this.screenName = "newentry";
        this.companyNames = new InsuranceDAO().getCompanyNames();
        this.branchNames = new InsuranceDAO().getBranchName();
        this.schemeNames = new InsuranceDAO().getSchemeNames();
        this.userId = userId;
        this.dto = dto;
        LookAndFeel.look();
		this.check = false;
        initComponents();
        int frame_x = getSize().height;
        int frame_y = getSize().width;
        this.companyNames = new InsuranceDAO().getCompanyNames();
        this.branchNames = new InsuranceDAO().getBranchName();
        this.schemeNames = new InsuranceDAO().getSchemeNames();

        this.setLocation((di.width - frame_y) / 2, (di.height - frame_x) / 2);

        jButton2.setEnabled(check);
        jButton1.setEnabled(check);
        jButton3.setEnabled(check);
        jButton4.setEnabled(check);
        jButton5.setEnabled(check);
        
        initializeValues();
    }

    public void initializeValues() {
        addressField.setText(dto.getAddress());
        depositorField.setText(dto.getDepositorName());
        policyTermField.setText(dto.getPolicyTerm());
        policyAmtField.setText("" + dto.getPolicyAmt());
        PremiumAmtField.setText("" + dto.getPremiumAmt());
        serviceTaxField.setText("" + dto.getServiceTax());
        /*DateFormat userDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date sqlDate = userDateFormat.parse(dto.getPremCheqDate().toString());
            dto.setPremCheqDate(new java.sql.Date(sqlDate.getTime()));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }*/
        Calendar cal = new GregorianCalendar();
        cal.setTime(dto.getPremCheqDate());
        if((cal.get(Calendar.MONTH)+1)<10){
             if(cal.get(Calendar.DATE)<10){
                premCheqDateField.setText("0"+cal.get(Calendar.DATE)+"-0"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR));
            }else{
                premCheqDateField.setText(cal.get(Calendar.DATE)+"-0"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR));
            }
        }else{
            if(cal.get(Calendar.DATE)<10){
                premCheqDateField.setText("0"+cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR));
            }else{
                premCheqDateField.setText(cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR));
            }
        }
        
        //premCheqDateField.setText(dto.getPremCheqDate().toString());
        premCheqAmtField.setText("" + dto.getPremCheqAmt());
        premCheqNoField.setText(dto.getPremCheqNum());
        bankMicrNoField.setText(dto.getMicrNo());
        lifeAssuredField.setText(dto.getLifeAssured());
        bankNameField.setText(dto.getBankName());
        bankBranchField.setText(dto.getBankBranch());
        existPolicyNoField.setText(dto.getExistPolicyNo());
        nomineeField.setText(dto.getNomineeName());
        cityField.setText(dto.getCity());
        phoneNoField.setText(dto.getPhNumber());
        mobileNoField.setText(dto.getMobileNo());
        emailIdField.setText(dto.getEmailId());
        pinCodeField.setText(dto.getPinCode());
        /*try {
            java.util.Date sqlDate = userDateFormat.parse(dto.getDob().toString());
            dto.setDob(new java.sql.Date(sqlDate.getTime()));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }*/
        cal.setTime(dto.getDob());
        if((cal.get(Calendar.MONTH)+1)<10){
             if(cal.get(Calendar.DATE)<10){
                dobField.setText("0"+cal.get(Calendar.DATE)+"-0"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR));
              }else{
                dobField.setText(cal.get(Calendar.DATE)+"-0"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR));
              }
        }else{
              if(cal.get(Calendar.DATE)<10){
                dobField.setText("0"+cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR));
              }else{
                dobField.setText(cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR));
              }
        }
        
        //dobField.setText(dto.getDob().toString());
        panNoField.setText(dto.getPanNumber());
        panNoField.setEditable(false);
        ackNoField.setText(dto.getAckNo());
        ackNoField.setEditable(false);
        /*try {
            java.util.Date sqlDate = userDateFormat.parse(dto.getAckDate().toString());
            dto.setAckDate(new java.sql.Date(sqlDate.getTime()));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }*/
        cal.setTime(dto.getAckDate());
        if((cal.get(Calendar.MONTH)+1)<10){
            if(cal.get(Calendar.DATE)<10){
                ackDateField.setText("0"+cal.get(Calendar.DATE)+"-0"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR));
            }else{
                ackDateField.setText(cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR));
            }
        }else{
            if(cal.get(Calendar.DATE)<10){
                ackDateField.setText("0"+cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR));
            }else{
                ackDateField.setText(cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR));
            }
        }
        //ackDateField.setText(dto.getAckDate().toString());
        ackDateField.setEditable(false);
        appNoField.setText(dto.getAppNo());
        appNoField.setEditable(false);
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        companyName = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        schemeName = new javax.swing.JComboBox();
        jLabel41 = new javax.swing.JLabel();
        branchField = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        gender = new javax.swing.JComboBox();
        maritalStatus = new javax.swing.JComboBox();
        residentStatus = new javax.swing.JComboBox();
        depositorField = new javax.swing.JTextField();
        lifeAssuredField = new javax.swing.JTextField();
        nomineeField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        addressField = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cityField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        dobField = new javax.swing.JTextField();
        phoneNoField = new javax.swing.JTextField();
        mobileNoField = new javax.swing.JTextField();
        emailIdField = new javax.swing.JTextField();
        pinCodeField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        yesRadio = new javax.swing.JRadioButton();
        noRadio = new javax.swing.JRadioButton();
        jLabel38 = new javax.swing.JLabel();
        ackNoField = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        ackDateField = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        appNoField = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        policyTermField = new javax.swing.JTextField();
        policyAmtField = new javax.swing.JTextField();
        PremiumAmtField = new javax.swing.JTextField();
        serviceTaxField = new javax.swing.JTextField();
        premCheqAmtField = new javax.swing.JTextField();
        premCheqNoField = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        bankMicrNoField = new javax.swing.JTextField();
        bankNameField = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        bankBranchField = new javax.swing.JTextField();
        existPolicyNoField = new javax.swing.JTextField();
        premCheqDateField = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        panNoField = new javax.swing.JTextField();
        idProofField = new javax.swing.JComboBox();
        incomeProofField = new javax.swing.JComboBox();
        ageProofField = new javax.swing.JComboBox();
        addressProofField = new javax.swing.JComboBox();
        payModeField = new javax.swing.JComboBox();
        payTermField = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        jRadioButton1.setText("jRadioButton1");
		System.out.println("check "+check);
		if(check)
			setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		else
			setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Insurance");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Company Name");

        companyName.setModel(new javax.swing.DefaultComboBoxModel(companyNames));
        companyName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                companyNameActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel2.setText("Scheme Name");

        schemeName.setModel(new javax.swing.DefaultComboBoxModel(schemeNames));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel41.setText("Branch");

        branchField.setModel(new javax.swing.DefaultComboBoxModel(branchNames));
        branchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                branchFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(companyName, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(jLabel41)
                .addGap(18, 18, 18)
                .addComponent(branchField, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(schemeName, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(companyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schemeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel41)
                    .addComponent(branchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Depositor Details");

        jLabel5.setText("Gender");

        jLabel6.setText("Marital Status");

        jLabel7.setText("Resident Status");

        jLabel8.setText("Depositor");

        jLabel9.setText("Life Assured");

        jLabel10.setText("Nominee");

        jLabel11.setText("Address");

        jLabel12.setText("City");

        gender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Gender", "Male", "Female" }));

        maritalStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Marital Status", "Single", "Married", "Divorced", "Widow" }));

        residentStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Resident", "Indian", "NRI", "Other", " " }));

        addressField.setColumns(20);
        addressField.setLineWrap(true);
        addressField.setRows(5);
        addressField.setToolTipText("Address");
        addressField.setWrapStyleWord(true);
        jScrollPane1.setViewportView(addressField);

        jLabel15.setText("Pin Code");

        jLabel16.setText("Email ID");

        jLabel13.setText("Phone No");

        jLabel14.setText("Mobile No");

        jLabel17.setText("Date Of Birth");

        dobField.setEnabled(false);
        dobField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dobFieldActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon("img\\calendar.gif")); // NOI18N
        jButton1.setToolTipText("Date Of Birth");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel37.setText("ECS Given?");

        buttonGroup1.add(yesRadio);
        yesRadio.setText("Yes");

        buttonGroup1.add(noRadio);
        noRadio.setText("No");
        noRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noRadioActionPerformed(evt);
            }
        });

        jLabel38.setText("Ack No");

        jLabel39.setText("Ack Date");

        ackDateField.setEnabled(false);
        ackDateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ackDateFieldActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon("img\\calendar.gif")); // NOI18N
        jButton5.setToolTipText("Date Of Birth");
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel40.setText("App. No");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(maritalStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cityField)
                                    .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                                    .addComponent(nomineeField)
                                    .addComponent(lifeAssuredField)
                                    .addComponent(depositorField, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                    .addComponent(residentStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dobField, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(phoneNoField, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(mobileNoField, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel15))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pinCodeField, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                    .addComponent(emailIdField, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(yesRadio)
                                .addGap(18, 18, 18)
                                .addComponent(noRadio)
                                .addGap(12, 12, 12))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel38)
                                    .addComponent(jLabel39))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(ackDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(ackNoField, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addGap(25, 25, 25)
                                .addComponent(appNoField, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))))
                    .addComponent(jLabel3))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(pinCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(maritalStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(emailIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(residentStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(phoneNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(depositorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(mobileNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(lifeAssuredField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(dobField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton1, 0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(nomineeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(noRadio)
                    .addComponent(yesRadio))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel38)
                                    .addComponent(ackNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel39)
                                        .addComponent(ackDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel40)
                                .addComponent(appNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(40, 40, 40))
        );

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel4.setText("Payment Details");

        jLabel18.setText("Pay Mode");

        jLabel19.setText("Policy Term");

        jLabel20.setText("Pay Term");

        jLabel21.setText("Policy Amt");

        jLabel22.setText("Premium Amt");

        jLabel23.setText("Service Tax");

        jLabel24.setText("Prem Cheq Date");

        jLabel25.setText("Prem Cheq Amt");

        jLabel26.setText("Prem Cheq No");

        jLabel28.setText("Bank Name");

        premCheqAmtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                premCheqAmtFieldActionPerformed(evt);
            }
        });

        jLabel29.setText("Bank Micr No");

        jLabel27.setText("Bank Branch");

        jLabel30.setText("Exist Policy No.");

        premCheqDateField.setEnabled(false);
        premCheqDateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                premCheqDateFieldActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon("img\\calendar.gif")); // NOI18N
        jButton4.setToolTipText("Date Of Birth");
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel31.setText("Proof Details");

        jLabel32.setText("Id Proof");

        jLabel33.setText("Income Proof");

        jLabel34.setText("Age Proof");

        jLabel35.setText("Address Proof");

        jLabel36.setText("Pan No.");

        idProofField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Id Proof", "Passport", "Driving License", "Voter ID", "Pan No." }));

        incomeProofField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Income Proof", "ITR Copy", "Form 16", "Salary Slip", "6 Month Bank Statement" }));

        ageProofField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Age Proof", "Passport", "School L C", "Birth Certificate", "D.O.B" }));

        addressProofField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Address Proof", "Passport", "Ration Card", "Telephone Bill", "Electricity Bill" }));

        payModeField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Payment Mode", "Yearly", "Half Yearly", "Quaterly", "Bi-Monthly", "Monthly", "Single" }));

        payTermField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(18, 18, 18)
                        .addComponent(premCheqNoField, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel24)
                            .addComponent(jLabel23)
                            .addComponent(jLabel22)
                            .addComponent(jLabel21)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(policyTermField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(policyAmtField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(PremiumAmtField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(serviceTaxField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(premCheqDateField, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(premCheqAmtField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(payModeField, javax.swing.GroupLayout.Alignment.LEADING, 0, 143, Short.MAX_VALUE)
                            .addComponent(payTermField, 0, 143, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bankMicrNoField, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(bankNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(bankBranchField, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(existPolicyNoField, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)))
                    .addComponent(jLabel31)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34)
                            .addComponent(jLabel36)
                            .addComponent(jLabel32)
                            .addComponent(jLabel35))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addressProofField, 0, 140, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(panNoField, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
                            .addComponent(idProofField, 0, 140, Short.MAX_VALUE)
                            .addComponent(incomeProofField, 0, 140, Short.MAX_VALUE)
                            .addComponent(ageProofField, 0, 140, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel29)
                    .addComponent(bankMicrNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(payModeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(policyTermField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(bankNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(bankBranchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(payTermField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(policyAmtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(existPolicyNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(PremiumAmtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(serviceTaxField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(premCheqDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton4, 0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(premCheqAmtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26)
                            .addComponent(premCheqNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(idProofField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(incomeProofField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(ageProofField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(addressProofField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(panNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jButton2.setText("Submit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jMenu1.setText("Home");

        jMenuItem6.setText("Go Home");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Data Entry");

		jMenu5.setText("Entry");
        jMenu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu5ActionPerformed(evt);
            }
        });

        jMenuItem3.setText("Edit Entry");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem3);

        jMenu2.add(jMenu5);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Query");

        jMenuItem5.setText("Document Query");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        InsuranceHome mf = new InsuranceHome(userId);
        mf.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        InsuranceHome mf = new InsuranceHome(userId);
        mf.setEnabled(true); // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new InsuranceSearchScreen(userId).setVisible(true);
            }
        });
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String date = new DatePicker(this).setPickedDate();
        dobField.setText(date);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void dobFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dobFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dobFieldActionPerformed

    private void premCheqDateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_premCheqDateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_premCheqDateFieldActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String date = new DatePicker(this).setPickedDate();
        premCheqDateField.setText(date);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        addressField.setText("");
        depositorField.setText("");
        policyTermField.setText("");
        policyAmtField.setText("");
        PremiumAmtField.setText("");
        serviceTaxField.setText("");
        premCheqDateField.setText("");
        premCheqAmtField.setText("");
        premCheqNoField.setText("");
        bankMicrNoField.setText("");
        lifeAssuredField.setText("");
        bankNameField.setText("");
        bankBranchField.setText("");
        existPolicyNoField.setText("");
        nomineeField.setText("");
        cityField.setText("");
        phoneNoField.setText("");
        mobileNoField.setText("");
        emailIdField.setText("");
        pinCodeField.setText("");
        dobField.setText("");
        ackNoField.setText("");
        ackDateField.setText("");
        appNoField.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String address = addressField.getText();
        String depositor = depositorField.getText();
        String payMode = payModeField.getSelectedItem().toString();
        String policyTerm = policyTermField.getText();
        String payTerm = payTermField.getSelectedItem().toString();
        String policyAmt = policyAmtField.getText();
        String premiumAmt = PremiumAmtField.getText();
        String serviceTax = serviceTaxField.getText();
        String premCheqDate = premCheqDateField.getText();
        String premCheqAmt = premCheqAmtField.getText();
        String premCheqNo = premCheqNoField.getText();
        String bankMicrNo = bankMicrNoField.getText();
        String lifeAssured = lifeAssuredField.getText();
        String bankName = bankNameField.getText();
        String bankBranch = bankBranchField.getText();
        String existPolicyNo = existPolicyNoField.getText();
        String nominee = nomineeField.getText();
        String city = cityField.getText();
        String phoneNo = phoneNoField.getText();
        String mobileNo = mobileNoField.getText();
        String email = emailIdField.getText();
        String pinCode = pinCodeField.getText();
        String dob = dobField.getText();
        String residentStatusValue = residentStatus.getSelectedItem().toString();
        String maritalStatusValue = maritalStatus.getSelectedItem().toString();
        String genderValue = gender.getSelectedItem().toString();
        String companyNameValue = companyName.getSelectedItem().toString();
        String schemeNameValue = schemeName.getSelectedItem().toString();
        String panNo = panNoField.getText();
        String addressProof = addressProofField.getSelectedItem().toString();
        String ageProof = ageProofField.getSelectedItem().toString();
        String idProof = idProofField.getSelectedItem().toString();
        String incomeProof = incomeProofField.getSelectedItem().toString();
        String ackNo = ackNoField.getText().trim();
        String ackDate = ackDateField.getText().trim();
        String appNo = appNoField.getText().trim();
        String branchName = branchField.getSelectedItem().toString();


        if (!yesRadio.isSelected() && !noRadio.isSelected()) {
            JOptionPane.showMessageDialog(null, "Please choose option for ECS", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (ackNo == null || ackNo.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Ack. No.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (ackDate == null || ackDate.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Ack. Date", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (appNo == null || appNo.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Application No.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (address == null || address.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Adress", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (addressProof.trim().equalsIgnoreCase("Select Address Proof")) {
            JOptionPane.showMessageDialog(null, "Please select the value for Address Proof", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (ageProof.trim().equalsIgnoreCase("Select Age Proof")) {
            JOptionPane.showMessageDialog(null, "Please select the value for Age Proof", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (idProof.trim().equalsIgnoreCase("Select Id Proof")) {
            JOptionPane.showMessageDialog(null, "Please select the value for Id Proof", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (incomeProof.trim().equalsIgnoreCase("Select Income Proof")) {
            JOptionPane.showMessageDialog(null, "Please select the value for Income Proof", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (panNo == null || panNo.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Pan No.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (policyTerm == null || policyTerm.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Poliy Term", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (depositor == null || depositor.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Depositor", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (payMode.trim().equalsIgnoreCase("Select Payment Mode")) {
            JOptionPane.showMessageDialog(null, "Please select the value for Pay Mode", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (payTerm.trim().equalsIgnoreCase("Item 1")) {
            JOptionPane.showMessageDialog(null, "Please select the value for Pay Term", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (policyAmt == null || policyAmt.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Policy Amount", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (premiumAmt == null || premiumAmt.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Premium Amount", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (serviceTax == null || serviceTax.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Service Tax", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (premCheqDate == null || premCheqDate.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Premium Cheq. Date", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (premCheqAmt == null || premCheqAmt.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Premium Cheq. Amount", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (premCheqNo == null || premCheqNo.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Premium Cheque No.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (bankMicrNo == null || bankMicrNo.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Bank MICR No.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (lifeAssured == null || lifeAssured.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Life Assured", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (bankName == null || bankName.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Bank Name", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (bankBranch == null || bankBranch.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Bank Branch", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (existPolicyNo == null || existPolicyNo.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Existing Policy No.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (nominee == null || nominee.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Nominee", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (city == null || city.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for City", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (phoneNo == null || phoneNo.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Phone", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (mobileNo == null || mobileNo.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Mobile No.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (email == null || email.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Email", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (pinCode == null || pinCode.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Pin Code", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (dob == null || dob.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the value for Date Of Birth", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (residentStatusValue.trim().equalsIgnoreCase("Select Resident")) {
            JOptionPane.showMessageDialog(null, "Please select the value for Resident Status", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (maritalStatusValue.trim().equalsIgnoreCase("Select Marital Status")) {
            JOptionPane.showMessageDialog(null, "Please select the value for Marital Status", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (genderValue.trim().equalsIgnoreCase("Select Gender")) {
            JOptionPane.showMessageDialog(null, "Please select the value for Gender", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (companyNameValue.trim().equalsIgnoreCase("Select Company")) {
            JOptionPane.showMessageDialog(null, "Please select the value for Company", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (schemeNameValue.trim().equalsIgnoreCase("Select Scheme")) {
            JOptionPane.showMessageDialog(null, "Please select the value for Scheme", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (branchName.trim().equalsIgnoreCase("Select Branch")) {
            JOptionPane.showMessageDialog(null, "Please select the value for Branch", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                dto.setBranchName(branchName);
                dto.setAckNo(ackNo);
                dto.setAppNo(appNo);
                dto.setUserId(userId);
                dto.setEcsGiven("" + yesRadio.isSelected());
                dto.setPhNumber(phoneNo);
                dto.setCompanyName(companyNameValue);
                dto.setSchemeName(schemeNameValue);
                dto.setAddress(address);
                dto.setAddressProof(addressProof);
                dto.setAgeProof(ageProof);
                dto.setBankBranch(bankBranch);
                dto.setBankName(bankName);
                dto.setCity(city);
                dto.setDepositorName(depositor);
                DateFormat userDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    java.util.Date sqlDate = userDateFormat.parse(dob);
                    dto.setDob(new java.sql.Date(sqlDate.getTime()));
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Please select correct date for DOB", "Error", JOptionPane.ERROR_MESSAGE);
                    throw ex;
                }
                try {
                    java.util.Date sqlDate = userDateFormat.parse(ackDate);
                    dto.setAckDate(new java.sql.Date(sqlDate.getTime()));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Please select correct date for Ack. Date", "Error", JOptionPane.ERROR_MESSAGE);
                    throw e;
                }
                dto.setEmailId(email);
                dto.setExistPolicyNo(existPolicyNo);
                dto.setGender(genderValue);
                dto.setIdProof(idProof);
                dto.setIncomeProof(incomeProof);
                dto.setLifeAssured(lifeAssured);
                dto.setMaritalStatus(maritalStatusValue);
                dto.setMicrNo(bankMicrNo);
                dto.setMobileNo(mobileNo);
                dto.setNomineeName(nominee);
                dto.setPanNumber(panNo);
                dto.setPayTerm(payTerm);
                dto.setPaymentMode(payMode);
                dto.setPinCode(pinCode);
                try {
                    dto.setPolicyAmt(Float.parseFloat(policyAmt));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid policy amount", "Error", JOptionPane.ERROR_MESSAGE);
                    throw e;
                }
                dto.setPolicyTerm(policyTerm);
                try {
                    dto.setPremCheqAmt(Float.parseFloat(premCheqAmt));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Premium Cheq. Amount", "Error", JOptionPane.ERROR_MESSAGE);
                    throw e;
                }
                try {
                    java.util.Date sqlDate = userDateFormat.parse(premCheqDate);
                    dto.setPremCheqDate(new java.sql.Date(sqlDate.getTime()));
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Please select correct date for Premium Cheq Date", "Error", JOptionPane.ERROR_MESSAGE);
                    throw ex;
                }
                dto.setPremCheqNum(premCheqNo);
                try {
                    dto.setPremiumAmt(Float.parseFloat(premiumAmt));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Premium Amount", "Error", JOptionPane.ERROR_MESSAGE);
                    throw e;
                }
                dto.setResidentialStatus(residentStatusValue);
                try {
                    dto.setServiceTax(Float.parseFloat(serviceTax));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Service Tax", "Error", JOptionPane.ERROR_MESSAGE);
                    throw e;
                }
                d = new JDialog();
                d.setModal(true);
                d.add(new StaffInfoInsurancePanel(), BorderLayout.CENTER);
                d.pack();
                d.setLocationRelativeTo(this);
                d.setVisible(true);
            } catch (Exception e) {
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void noRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noRadioActionPerformed

    private void companyNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_companyNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_companyNameActionPerformed

    private void ackDateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ackDateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ackDateFieldActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String date = new DatePicker(this).setPickedDate();
        ackDateField.setText(date);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void branchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_branchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_branchFieldActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        this.dispose();
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new InsuranceHome(userId).setVisible(true);
            }
        });
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new InsuranceEditEntry(userId).setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void premCheqAmtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_premCheqAmtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_premCheqAmtFieldActionPerformed

    private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu5ActionPerformed

    public class StaffInfoInsurancePanel extends javax.swing.JPanel {

        /** Creates new form StaffInfoInsurancePanel */
        public StaffInfoInsurancePanel() {
            initComponents();
        }

        private void initComponents() {

            jLabel1 = new javax.swing.JLabel();
            jTextField1 = new javax.swing.JTextField();
            jLabel2 = new javax.swing.JLabel();
            jTextField2 = new javax.swing.JTextField();
            jLabel3 = new javax.swing.JLabel();
            jTextField3 = new javax.swing.JTextField();
            jButton1 = new javax.swing.JButton();
            jButton2 = new javax.swing.JButton();

            setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

            jLabel1.setText("Branch Mgr");

            jLabel2.setText("Staff Code");

            jLabel3.setText("Branch Asst");

            jButton1.setText("Save");

            jButton2.setText("Cancel");

            jButton2.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    d.dispose();
                }
            });

            jButton1.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    String branchMgr = jTextField1.getText().trim();
                    String staffCode = jTextField2.getText().trim();
                    String branchAsst = jTextField3.getText().trim();

                    if (branchMgr == null || branchMgr.trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Please enter the value for Branch Manager", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (staffCode == null || staffCode.trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Please enter the value for Staff Code", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (branchAsst == null || branchAsst.trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Please enter the value for Branch Asst.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        StaffDetailsDTO staff = new StaffDetailsDTO();
                        staff.setBankMgr(branchMgr);
                        staff.setBranchAsst(branchAsst);
                        try {
                            staff.setStaffCode(Integer.parseInt(staffCode));
                            dto.setStaffDetails(staff);
                            d.dispose();
                            service = new InsuranceService();
                            java.awt.EventQueue.invokeLater(new Runnable() {

                                public void run() {
                                    if (service.processNewEntry(dto)) {
                                        JOptionPane.showMessageDialog(null, "Details are saved!!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Oops!! Some Internal Error Occured While Processing", "Error", JOptionPane.ERROR_MESSAGE);
                                    }

                                }
                            });

                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(null, "Please enter the valid Staff Code", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }

                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jLabel2).addGap(2, 2, 2).addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jLabel3).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(jButton2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 431, Short.MAX_VALUE).addComponent(jButton1))).addContainerGap()));
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel2).addComponent(jLabel3).addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jButton1).addComponent(jButton2)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        }// </editor-fold>
        // Variables declaration - do not modify
        private javax.swing.JButton jButton1;
        private javax.swing.JButton jButton2;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JTextField jTextField1;
        private javax.swing.JTextField jTextField2;
        private javax.swing.JTextField jTextField3;
        // End of variables declaration
    }

    public void onClose() {
        this.dispose();
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new InsuranceHome(userId).setVisible(true);
            }
        });
    }
    private InsuranceService service;
    private InsuranceDTO dto;
    private JDialog d;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField PremiumAmtField;
    private javax.swing.JTextField ackDateField;
    private javax.swing.JTextField ackNoField;
    private javax.swing.JTextArea addressField;
    private javax.swing.JComboBox addressProofField;
    private javax.swing.JComboBox ageProofField;
    private javax.swing.JTextField appNoField;
    private javax.swing.JTextField bankBranchField;
    private javax.swing.JTextField bankMicrNoField;
    private javax.swing.JTextField bankNameField;
    private javax.swing.JComboBox branchField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JTextField cityField;
    private javax.swing.JComboBox companyName;
    private javax.swing.JTextField depositorField;
    private javax.swing.JTextField dobField;
    private javax.swing.JTextField emailIdField;
    private javax.swing.JTextField existPolicyNoField;
    private javax.swing.JComboBox gender;
    private javax.swing.JComboBox idProofField;
    private javax.swing.JComboBox incomeProofField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lifeAssuredField;
    private javax.swing.JComboBox maritalStatus;
    private javax.swing.JTextField mobileNoField;
    private javax.swing.JRadioButton noRadio;
    private javax.swing.JTextField nomineeField;
    private javax.swing.JTextField panNoField;
    private javax.swing.JComboBox payModeField;
    private javax.swing.JComboBox payTermField;
    private javax.swing.JTextField phoneNoField;
    private javax.swing.JTextField pinCodeField;
    private javax.swing.JTextField policyAmtField;
    private javax.swing.JTextField policyTermField;
    private javax.swing.JTextField premCheqAmtField;
    private javax.swing.JTextField premCheqDateField;
    private javax.swing.JTextField premCheqNoField;
    private javax.swing.JComboBox residentStatus;
    private javax.swing.JComboBox schemeName;
    private javax.swing.JTextField serviceTaxField;
    private javax.swing.JRadioButton yesRadio;
    // End of variables declaration//GEN-END:variables
}
