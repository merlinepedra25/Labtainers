/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labtainers.mainui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import labtainers.mainui.LabData.ContainerData;
import labtainers.mainui.LabData.NetworkData;
import java.util.ArrayList;
import java.util.Arrays;


/**
 *
 * @author Daniel Liao
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    LabData labData;
    String labtainerPath;
    File labsPath;
    File currentLab;
    File iniFile;
    Properties pathProperties;
    String[] bases;
    public MainWindow() throws IOException {
        initComponents();
        containerScrollPaneBar = ContainerScrollPane.getVerticalScrollBar();
        networkScrollPaneBar = NetworkScrollPane.getVerticalScrollBar();
        LabExistLabel.setVisible(false);
        
        labData = new LabData();       
        parseINI();
        getBaseImageDockerfiles();   
    }
    
    // checks out the ini file to set the labtainers path and also checks if we load a previous lab
    private void parseINI() throws IOException{
        // Load .ini file information
        try {
            iniFile = new File("/home/student/dev/Labtainers/UI/bin/mainUI.ini"); //location will need to be updated in final
            pathProperties  = new Properties();
            pathProperties.load(new FileInputStream(iniFile)); 
            
            
            //If the labtainers path has not been set in the config 
            if(pathProperties.getProperty("labtainerPath").isEmpty()){
                System.out.println("No labtainer path set yet");
                
                // update the labtainerPath
                pathProperties.put("labtainerPath", System.getenv("LABTAINER_DIR"));
                FileOutputStream out = new FileOutputStream(iniFile);
                
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                pathProperties.store(out, "Updated: "+ formatter.format(date));
            } 
            
            labtainerPath = pathProperties.getProperty("labtainerPath");
            labsPath = new File(labtainerPath + File.separator + "labs");
            labChooser.setCurrentDirectory(labsPath);
            
            
                        //if a lab has been loaded before then load that lab initially
            //If a lab has been opened before
            String iniPrevLab = pathProperties.getProperty("prevLab").trim();
            System.out.println("iniPrevlab: "+iniPrevLab);
            System.out.println();
            File prevLab = new File(iniPrevLab);
            if(!iniPrevLab.isEmpty() && prevLab.isDirectory()){
                System.out.println(prevLab+" is lab!");
                openLab(prevLab);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (NullPointerException ex) {
            System.out.println(ex);
            //resetINIFile();
        }
    }

    // get list of base images ready for when player wants to make a new lab
    private void getBaseImageDockerfiles(){
        File dockerfileBasesPath = new File(labtainerPath + File.separator +"scripts"+ File.separator+"designer"+File.separator+"base_dockerfiles");
        
        File[] baseFiles = dockerfileBasesPath.listFiles(new FilenameFilter(){
            public boolean accept(File dockerfileBasesPath, String filename)
                {return filename.startsWith("Dockerfile.labtainer."); }
        } );
        
        bases = new String[baseFiles.length];
        for(int i = 0;i<baseFiles.length;i++){
            bases[i] = baseFiles[i].getName().split("Dockerfile.labtainer.")[1];
        }
        
//        String x;
//        for(String i : bases){
//            x = i;
//            System.out.println(x);
//        }
        
        //Set the base image combobox options for making new labs and adding containers
        for(String baseImage : bases){
            NewLabBaseImageComboBox.addItem(baseImage);
        }
        
        for(String baseImage : bases){
            ContainerAddDialogBaseImageCombobox.addItem(baseImage);
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

        ContainerAddDialog = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ContainerAddDialogNameTextfield = new javax.swing.JTextField();
        ContainerAddDialogCreateButton = new javax.swing.JButton();
        ContainerAddDialogCancelButton = new javax.swing.JButton();
        ContainerAddDialogBaseImageCombobox = new javax.swing.JComboBox<>();
        NetworkAddDialog = new javax.swing.JDialog();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        NetworkAddDialogNameTextfield = new javax.swing.JTextField();
        NetworkAddDialogMaskTextfield = new javax.swing.JTextField();
        NetworkAddDialogGatewayTextfield = new javax.swing.JTextField();
        NetworkAddDialogIPRangeTextfield = new javax.swing.JTextField();
        NetworkAddDialogCreateButton = new javax.swing.JButton();
        NetworkAddDialogCancelButton = new javax.swing.JButton();
        NetworkAddDialogMacVLanExtSpinner = new javax.swing.JSpinner();
        NetworkAddDialogMacVLanSpinner = new javax.swing.JSpinner();
        labChooser = new javax.swing.JFileChooser();
        NewLabDialog = new javax.swing.JDialog();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        NewLabNameTextfield = new javax.swing.JTextField();
        NewLabBaseImageComboBox = new javax.swing.JComboBox<>();
        NewLabCreateButton = new javax.swing.JButton();
        NewLabCancelButton = new javax.swing.JButton();
        LabExistLabel = new javax.swing.JLabel();
        Header = new javax.swing.JPanel();
        AssessmentButton = new javax.swing.JButton();
        LabnameLabel = new javax.swing.JLabel();
        ContainerPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ContainerScrollPane = new javax.swing.JScrollPane();
        ContainerPanePanel = new javax.swing.JPanel();
        addContainerButton = new javax.swing.JButton();
        NetworkPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        NetworkScrollPane = new javax.swing.JScrollPane();
        NetworkPanePanel = new javax.swing.JPanel();
        addNetworkButton = new javax.swing.JButton();
        MainMenuBar = new javax.swing.JMenuBar();
        FileMenuBar = new javax.swing.JMenu();
        NewLabMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        OpenLabMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();

        ContainerAddDialog.setTitle("Adding New Container");
        ContainerAddDialog.setMinimumSize(new java.awt.Dimension(433, 220));
        ContainerAddDialog.setResizable(false);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Provide container name and the docker base image used:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Name: ");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Base Image:");

        ContainerAddDialogNameTextfield.setMinimumSize(new java.awt.Dimension(300, 20));
        ContainerAddDialogNameTextfield.setPreferredSize(new java.awt.Dimension(300, 20));

        ContainerAddDialogCreateButton.setText("Create");
        ContainerAddDialogCreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContainerAddDialogCreateButtonActionPerformed(evt);
            }
        });

        ContainerAddDialogCancelButton.setText("Cancel");

        javax.swing.GroupLayout ContainerAddDialogLayout = new javax.swing.GroupLayout(ContainerAddDialog.getContentPane());
        ContainerAddDialog.getContentPane().setLayout(ContainerAddDialogLayout);
        ContainerAddDialogLayout.setHorizontalGroup(
            ContainerAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContainerAddDialogLayout.createSequentialGroup()
                .addGroup(ContainerAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ContainerAddDialogLayout.createSequentialGroup()
                        .addGap(0, 285, Short.MAX_VALUE)
                        .addComponent(ContainerAddDialogCreateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ContainerAddDialogCancelButton))
                    .addGroup(ContainerAddDialogLayout.createSequentialGroup()
                        .addGroup(ContainerAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ContainerAddDialogLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3))
                            .addGroup(ContainerAddDialogLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(ContainerAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(ContainerAddDialogLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ContainerAddDialogNameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ContainerAddDialogLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(4, 4, 4)
                                        .addComponent(ContainerAddDialogBaseImageCombobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(2, 2, 2)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        ContainerAddDialogLayout.setVerticalGroup(
            ContainerAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContainerAddDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(ContainerAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ContainerAddDialogNameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(ContainerAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ContainerAddDialogBaseImageCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(ContainerAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ContainerAddDialogCreateButton)
                    .addComponent(ContainerAddDialogCancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        NetworkAddDialog.setTitle("Adding New Network");
        NetworkAddDialog.setMinimumSize(new java.awt.Dimension(400, 380));
        NetworkAddDialog.setResizable(false);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Please fill the sections below to create a new network:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Gateway:");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Mask:");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("IP_Range:");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("MACVLAN:");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setText("MACVLAN_EXT:");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setText("Name:");

        NetworkAddDialogNameTextfield.setMinimumSize(new java.awt.Dimension(300, 20));
        NetworkAddDialogNameTextfield.setName(""); // NOI18N
        NetworkAddDialogNameTextfield.setPreferredSize(new java.awt.Dimension(300, 20));

        NetworkAddDialogMaskTextfield.setMinimumSize(new java.awt.Dimension(300, 20));
        NetworkAddDialogMaskTextfield.setName(""); // NOI18N
        NetworkAddDialogMaskTextfield.setPreferredSize(new java.awt.Dimension(300, 20));

        NetworkAddDialogGatewayTextfield.setMinimumSize(new java.awt.Dimension(300, 20));
        NetworkAddDialogGatewayTextfield.setName(""); // NOI18N
        NetworkAddDialogGatewayTextfield.setPreferredSize(new java.awt.Dimension(300, 20));

        NetworkAddDialogIPRangeTextfield.setMinimumSize(new java.awt.Dimension(300, 20));
        NetworkAddDialogIPRangeTextfield.setName(""); // NOI18N
        NetworkAddDialogIPRangeTextfield.setPreferredSize(new java.awt.Dimension(300, 20));

        NetworkAddDialogCreateButton.setText("Create");
        NetworkAddDialogCreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NetworkAddDialogCreateButtonActionPerformed(evt);
            }
        });

        NetworkAddDialogCancelButton.setText("Cancel");
        NetworkAddDialogCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NetworkAddDialogCancelButtonActionPerformed(evt);
            }
        });

        NetworkAddDialogMacVLanExtSpinner.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        NetworkAddDialogMacVLanSpinner.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout NetworkAddDialogLayout = new javax.swing.GroupLayout(NetworkAddDialog.getContentPane());
        NetworkAddDialog.getContentPane().setLayout(NetworkAddDialogLayout);
        NetworkAddDialogLayout.setHorizontalGroup(
            NetworkAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NetworkAddDialogLayout.createSequentialGroup()
                .addGroup(NetworkAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NetworkAddDialogLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7))
                    .addGroup(NetworkAddDialogLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(NetworkAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(NetworkAddDialogLayout.createSequentialGroup()
                                .addGroup(NetworkAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(NetworkAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NetworkAddDialogMaskTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NetworkAddDialogNameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(NetworkAddDialogLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(NetworkAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(NetworkAddDialogLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(NetworkAddDialogGatewayTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(NetworkAddDialogLayout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(NetworkAddDialogMacVLanExtSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(NetworkAddDialogLayout.createSequentialGroup()
                                        .addGroup(NetworkAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel10))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(NetworkAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(NetworkAddDialogIPRangeTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(NetworkAddDialogMacVLanSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NetworkAddDialogLayout.createSequentialGroup()
                                        .addComponent(NetworkAddDialogCreateButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(NetworkAddDialogCancelButton)))))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        NetworkAddDialogLayout.setVerticalGroup(
            NetworkAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NetworkAddDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(NetworkAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(NetworkAddDialogNameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(NetworkAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NetworkAddDialogMaskTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(NetworkAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(NetworkAddDialogGatewayTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(NetworkAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NetworkAddDialogMacVLanExtSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(NetworkAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(NetworkAddDialogMacVLanSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(NetworkAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NetworkAddDialogIPRangeTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(NetworkAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NetworkAddDialogCreateButton)
                    .addComponent(NetworkAddDialogCancelButton))
                .addGap(68, 68, 68))
        );

        labChooser.setCurrentDirectory(new java.io.File("/var/lib/snapd/void/C:/Users/Daniel Liao/Desktop/Labtainers/labs"));
        labChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        NewLabDialog.setTitle("Creating New Lab");
        NewLabDialog.setMaximumSize(new java.awt.Dimension(469, 200));
        NewLabDialog.setMinimumSize(new java.awt.Dimension(469, 200));
        NewLabDialog.setPreferredSize(new java.awt.Dimension(469, 200));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel6.setText("Name");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel14.setText("Base Image");

        NewLabNameTextfield.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N

        NewLabCreateButton.setText("Create");
        NewLabCreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewLabCreateButtonActionPerformed(evt);
            }
        });

        NewLabCancelButton.setText("Cancel");
        NewLabCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewLabCancelButtonActionPerformed(evt);
            }
        });

        LabExistLabel.setText("Lab already exists!");

        javax.swing.GroupLayout NewLabDialogLayout = new javax.swing.GroupLayout(NewLabDialog.getContentPane());
        NewLabDialog.getContentPane().setLayout(NewLabDialogLayout);
        NewLabDialogLayout.setHorizontalGroup(
            NewLabDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewLabDialogLayout.createSequentialGroup()
                .addGroup(NewLabDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NewLabDialogLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NewLabNameTextfield))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NewLabDialogLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(NewLabDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(NewLabDialogLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(4, 4, 4)
                                .addComponent(NewLabBaseImageComboBox, 0, 344, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NewLabDialogLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(NewLabCreateButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NewLabCancelButton)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NewLabDialogLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(LabExistLabel)
                .addGap(158, 158, 158))
        );
        NewLabDialogLayout.setVerticalGroup(
            NewLabDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewLabDialogLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(NewLabDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(NewLabNameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabExistLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(NewLabDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(NewLabBaseImageComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(NewLabDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NewLabCreateButton)
                    .addComponent(NewLabCancelButton))
                .addGap(23, 23, 23))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                MainWindow.this.windowClosing(evt);
            }
        });

        AssessmentButton.setText("ASSESSMENT");
        AssessmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AssessmentButtonActionPerformed(evt);
            }
        });

        LabnameLabel.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        LabnameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabnameLabel.setText("Lab:");

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(LabnameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AssessmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabnameLabel)
                    .addComponent(AssessmentButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ContainerPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ContainerPanel.setMaximumSize(new java.awt.Dimension(384, 400));
        ContainerPanel.setMinimumSize(new java.awt.Dimension(384, 400));
        ContainerPanel.setPreferredSize(new java.awt.Dimension(384, 400));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("Containers");

        ContainerScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ContainerScrollPane.setAutoscrolls(true);

        ContainerPanePanel.setMaximumSize(new java.awt.Dimension(0, 0));
        ContainerPanePanel.setMinimumSize(new java.awt.Dimension(0, 0));
        ContainerPanePanel.setPreferredSize(new java.awt.Dimension(0, 0));
        ContainerPanePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        ContainerScrollPane.setViewportView(ContainerPanePanel);

        addContainerButton.setText("Add");
        addContainerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addContainerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ContainerPanelLayout = new javax.swing.GroupLayout(ContainerPanel);
        ContainerPanel.setLayout(ContainerPanelLayout);
        ContainerPanelLayout.setHorizontalGroup(
            ContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContainerPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(ContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ContainerScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addGroup(ContainerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(38, 38, 38)
                        .addComponent(addContainerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        ContainerPanelLayout.setVerticalGroup(
            ContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContainerPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(ContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addContainerButton))
                .addGap(5, 5, 5)
                .addComponent(ContainerScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        NetworkPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        NetworkPanel.setMaximumSize(new java.awt.Dimension(384, 400));
        NetworkPanel.setMinimumSize(new java.awt.Dimension(384, 400));
        NetworkPanel.setPreferredSize(new java.awt.Dimension(384, 400));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setText("Networks");

        NetworkScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        NetworkScrollPane.setAutoscrolls(true);

        NetworkPanePanel.setMaximumSize(new java.awt.Dimension(0, 0));
        NetworkPanePanel.setMinimumSize(new java.awt.Dimension(0, 0));
        NetworkPanePanel.setPreferredSize(new java.awt.Dimension(0, 0));
        NetworkPanePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        NetworkScrollPane.setViewportView(NetworkPanePanel);

        addNetworkButton.setText("Add");
        addNetworkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNetworkButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NetworkPanelLayout = new javax.swing.GroupLayout(NetworkPanel);
        NetworkPanel.setLayout(NetworkPanelLayout);
        NetworkPanelLayout.setHorizontalGroup(
            NetworkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NetworkPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(NetworkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(NetworkPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(41, 41, 41)
                        .addComponent(addNetworkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(NetworkScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        NetworkPanelLayout.setVerticalGroup(
            NetworkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NetworkPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(NetworkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(addNetworkButton))
                .addGap(5, 5, 5)
                .addComponent(NetworkScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        FileMenuBar.setText("File");

        NewLabMenuItem.setText("New Lab");
        NewLabMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewLabMenuItemActionPerformed(evt);
            }
        });
        FileMenuBar.add(NewLabMenuItem);
        FileMenuBar.add(jSeparator1);

        OpenLabMenuItem.setText("Open Lab");
        OpenLabMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenLabMenuItemActionPerformed(evt);
            }
        });
        FileMenuBar.add(OpenLabMenuItem);
        FileMenuBar.add(jSeparator2);

        jMenuItem1.setText("Edit Labtainers Directory");
        FileMenuBar.add(jMenuItem1);

        MainMenuBar.add(FileMenuBar);

        setJMenuBar(MainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(ContainerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NetworkPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ContainerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NetworkPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AssessmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AssessmentButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AssessmentButtonActionPerformed

    
   
    private void addContainerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addContainerButtonActionPerformed
        ContainerAddDialogNameTextfield.setText("");
        ContainerAddDialog.setVisible(true);
    }//GEN-LAST:event_addContainerButtonActionPerformed

    
    private void addNetworkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNetworkButtonActionPerformed
        NetworkAddDialogGatewayTextfield.setText("");
        NetworkAddDialogIPRangeTextfield.setText("");
        NetworkAddDialogMacVLanExtSpinner.setValue(0);
        NetworkAddDialogMacVLanSpinner.setValue(0);
        NetworkAddDialogMaskTextfield.setText("");
        NetworkAddDialogNameTextfield.setText("");
        NetworkAddDialog.setVisible(true);
    }//GEN-LAST:event_addNetworkButtonActionPerformed


    private void ContainerAddDialogCreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContainerAddDialogCreateButtonActionPerformed
        addContainerPanel(null);
    }//GEN-LAST:event_ContainerAddDialogCreateButtonActionPerformed
    public int containerPanePanelLength = 0;
    private final JScrollBar containerScrollPaneBar;
    private void addContainerPanel(ContainerData data){
        //Resize the JPanel Holding all the ContainerObjPanels to fit another ContainerObjPanel 
        //(makes the scroll bar resize and should show all objects listed)
        containerPanePanelLength+=50;
        ContainerPanePanel.setPreferredSize(new Dimension(0,containerPanePanelLength));
        
        // Create the Container Obj Panel and add it
        ContainerObjPanel newContainer;
        if(data == null){
            newContainer = new ContainerObjPanel(this, ContainerAddDialogNameTextfield.getText());
        }
        else {
            newContainer = new ContainerObjPanel(this, data);
        }

        ContainerPanePanel.add(newContainer);
        
        // Redraw GUI with the new Panel
        ContainerPanePanel.revalidate();
        ContainerPanePanel.repaint(); 
        
        //Lower the Scroll Bar to show the newly added container (BUG[6/25/20]: still always off by a single panel)
        //System.out.println("Panel Length: "+containerPanePanelLength);
        //System.out.println("Max: "+(50+containerScrollPaneBar.getMaximum()));
        containerScrollPaneBar.setValue(50+containerScrollPaneBar.getMaximum());
        ContainerAddDialog.setVisible(false);
    }

    private void NetworkAddDialogCreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NetworkAddDialogCreateButtonActionPerformed
        addNetworkPanel(null);
    }//GEN-LAST:event_NetworkAddDialogCreateButtonActionPerformed
   
    //[BUG: 6/25/2020] Not sure Why but the network obj panel needs to be 1 px taller than the container panel to be the same size
    public int networkPanePanelLength = 0;
    private JScrollBar networkScrollPaneBar;
    private void addNetworkPanel(NetworkData data){
        //Resize the JPanel Holding all the NetworkObjPanels to fit another NetworkObjPanel 
        //(makes the scroll bar resize and should show all objects listed)
        networkPanePanelLength+=51;
        NetworkPanePanel.setPreferredSize(new Dimension(0,networkPanePanelLength));
        
        // Create the Network Obj Panel and add it
        NetworkObjPanel newNetwork;
        if(data == null){
            newNetwork = new NetworkObjPanel(this, NetworkAddDialogNameTextfield.getText()); 
        }
        else {
            newNetwork = new NetworkObjPanel(this, data); 
        } 
            
        
        NetworkPanePanel.add(newNetwork);
        
        // Redraw GUI with the new Panel
        NetworkPanePanel.revalidate();
        NetworkPanePanel.repaint(); 
        
        //Lower the Scroll Bar to show the newly added container (BUG[6/25/20]: still always off by a single panel)
        //System.out.println("Panel Length: "+networkPanePanelLength);
        //System.out.println("Max: "+(51+networkScrollPaneBar.getMaximum()));
        networkScrollPaneBar.setValue(networkScrollPaneBar.getMaximum());
        NetworkAddDialog.setVisible(false);
    }
    
    
    
    private void OpenLabMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenLabMenuItemActionPerformed
            int returnVal = labChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File lab = labChooser.getSelectedFile();
                openLab(lab);
            } 
            else {
                System.out.println("File access cancelled by user.");
            }
    }//GEN-LAST:event_OpenLabMenuItemActionPerformed

    
private void openLab(File lab){
    currentLab = lab;
    String labname = lab.toString().substring(lab.toString().lastIndexOf(File.separator)+1);
           
    labData = new LabData(lab, labname); //initialize all data for the lab

    // Visual load of lab
    resetWindow();
    loadLab();
    //labData.printData();    
}    

    private void NetworkAddDialogCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NetworkAddDialogCancelButtonActionPerformed
        NetworkAddDialog.setVisible(false);
    }//GEN-LAST:event_NetworkAddDialogCancelButtonActionPerformed

    private void windowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_windowClosing
        //Write the current lab to the ini so that when the app opens again it opens to this lab
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(iniFile);
            if(out != null){
                //String tmp = File.toString(currentLab);
                pathProperties.put("prevLab", currentLab.getPath());

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();

                pathProperties.store(out, "Updated: "+ formatter.format(date));
            }
            
        } 
        catch (FileNotFoundException ex) { System.out.println(ex);} 
        catch (IOException ex) { System.out.println(ex);} 
        catch (NullPointerException ex) {
            System.out.println(ex);
            resetINIFile();
            
        }
        
        finally {
            try { if(out != null){out.close();}} 
            catch (IOException ex) { System.out.println(ex);}
        }
    }//GEN-LAST:event_windowClosing

    
    // Code taken from Beginners Book: https://beginnersbook.com/2014/05/how-to-copy-a-file-to-another-file-in-java/
    private void resetINIFile(){
        FileInputStream instream = null;
	FileOutputStream outstream = null;
 
    	try{
    	    File infile = new File("/home/student/dev/Labtainers/UI/bin/mainUI.ini.backup"); //location will need to be updated in final;
    	    File outfile = iniFile;
 
    	    instream = new FileInputStream(infile);
    	    outstream = new FileOutputStream(outfile);
 
    	    byte[] buffer = new byte[1024];
 
    	    int length;
    	    /*copying the contents from input stream to
    	     * output stream using read and write methods
    	     */
    	    while ((length = instream.read(buffer)) > 0){
    	    	outstream.write(buffer, 0, length);
    	    }

    	    //Closing the input/output file streams
    	    instream.close();
    	    outstream.close();

    	    System.out.println("File copied successfully!!");
 
    	}catch(IOException ioe){
    		ioe.printStackTrace();
    	 }
    }
    
    private void NewLabMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewLabMenuItemActionPerformed
        NewLabNameTextfield.setText("");
        NewLabDialog.setVisible(true);
    }//GEN-LAST:event_NewLabMenuItemActionPerformed

    private void NewLabCreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewLabCreateButtonActionPerformed
        LabExistLabel.setVisible(false);
        
        //mkdir newlab (in labs dir if )
        String newLabName = NewLabNameTextfield.getText();
        if(!Arrays.asList(labsPath.list()).contains(newLabName)){ // If lab doesn't exist
            try{
                LabExistLabel.setVisible(false);
                NewLabDialog.revalidate();
                System.out.println("made new lab");
                //call python new_lab_script: new_lab_setup.py -b basename              
                String cmd = "./callNewLab.sh "+labsPath+" "+newLabName+" "+NewLabBaseImageComboBox.getSelectedItem();
                System.out.println(cmd);
                Process pr = Runtime.getRuntime().exec(cmd);
            
                BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                String line;
                while((line = reader.readLine()) != null){
                    System.out.println(line);
                }
                reader.close();
                NewLabDialog.setVisible(false);
                
                //open the new lab
                openLab(new File(labsPath+File.separator+newLabName));
            } 
            catch (IOException e){
                System.out.println(e);
            }
           
        }
        else{
            System.out.println("Lab already exists. Make the lab with a different name other than:");
            LabExistLabel.setVisible(true);
            NewLabDialog.revalidate();
            int labCount = 1;
            for(String lab : labsPath.list()){
                System.out.print(lab + ", ");
                if(labCount % 5 == 0){
                    System.out.println();
                }
                labCount++;
            }
        }
    }//GEN-LAST:event_NewLabCreateButtonActionPerformed

    private void NewLabCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewLabCancelButtonActionPerformed
        NewLabDialog.setVisible(false);
    }//GEN-LAST:event_NewLabCancelButtonActionPerformed
    
    
    
    private void resetWindow(){
        // Clear Container Panel
        Component[] componentList = ContainerPanePanel.getComponents();
        for(Component c: componentList)
            ContainerPanePanel.remove(c);
        
        containerPanePanelLength=0;
        ContainerPanePanel.setPreferredSize(new Dimension(0,containerPanePanelLength));
        
        // Clear Network Panel
        componentList = NetworkPanePanel.getComponents();
        for(Component c: componentList)
            NetworkPanePanel.remove(c);
        
        networkPanePanelLength=0;
        NetworkPanePanel.setPreferredSize(new Dimension(0,networkPanePanelLength));
        
        this.revalidate();
        this.repaint();
    }
    
    // Load the data visually
    private void loadLab(){
        LabnameLabel.setText("Lab: "+labData.getName());
        
        // Load the networks 
        for(int i = 0;i<labData.getNetworks().size();i++){
            addNetworkPanel(labData.getNetworks().get(i));
        }
        
        //Load the containers 
        for(int i = 0;i<labData.getContainers().size();i++){
            addContainerPanel(labData.getContainers().get(i));
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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainWindow().setVisible(true);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AssessmentButton;
    private javax.swing.JDialog ContainerAddDialog;
    private javax.swing.JComboBox<String> ContainerAddDialogBaseImageCombobox;
    private javax.swing.JButton ContainerAddDialogCancelButton;
    private javax.swing.JButton ContainerAddDialogCreateButton;
    private javax.swing.JTextField ContainerAddDialogNameTextfield;
    private javax.swing.JPanel ContainerPanePanel;
    private javax.swing.JPanel ContainerPanel;
    private javax.swing.JScrollPane ContainerScrollPane;
    private javax.swing.JMenu FileMenuBar;
    private javax.swing.JPanel Header;
    private javax.swing.JLabel LabExistLabel;
    private javax.swing.JLabel LabnameLabel;
    private javax.swing.JMenuBar MainMenuBar;
    private javax.swing.JDialog NetworkAddDialog;
    private javax.swing.JButton NetworkAddDialogCancelButton;
    private javax.swing.JButton NetworkAddDialogCreateButton;
    private javax.swing.JTextField NetworkAddDialogGatewayTextfield;
    private javax.swing.JTextField NetworkAddDialogIPRangeTextfield;
    private javax.swing.JSpinner NetworkAddDialogMacVLanExtSpinner;
    private javax.swing.JSpinner NetworkAddDialogMacVLanSpinner;
    private javax.swing.JTextField NetworkAddDialogMaskTextfield;
    private javax.swing.JTextField NetworkAddDialogNameTextfield;
    private javax.swing.JPanel NetworkPanePanel;
    private javax.swing.JPanel NetworkPanel;
    private javax.swing.JScrollPane NetworkScrollPane;
    private javax.swing.JComboBox<String> NewLabBaseImageComboBox;
    private javax.swing.JButton NewLabCancelButton;
    private javax.swing.JButton NewLabCreateButton;
    private javax.swing.JDialog NewLabDialog;
    private javax.swing.JMenuItem NewLabMenuItem;
    private javax.swing.JTextField NewLabNameTextfield;
    private javax.swing.JMenuItem OpenLabMenuItem;
    private javax.swing.JButton addContainerButton;
    private javax.swing.JButton addNetworkButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JFileChooser labChooser;
    // End of variables declaration//GEN-END:variables
}
