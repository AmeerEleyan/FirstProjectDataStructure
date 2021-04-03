/**
 * @author: Amir Eleyan
 * ID: 1191076
 * At: 31/3/2021  7:45 PM
 */
package GUI;

import linkedList.Calculations;
import linkedList.LinkedList;
import linkedList.Node;
import linkedList.TRecord;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


import java.util.Scanner;

public class TRecordGUI extends Application {
    private File file;
    private Scanner input;
    private Button btAddNewRecord, btRemoveRecord, btStatistic, btTopTen, btPrintReport;
    private Button btSearch;
    private TextField txtSearch;
    private Label lblTotalNumber, lblCalculation;
    private static TextField txtTotalNumber;
    private static TableView<TRecord> recordTableView;
    private static RadioButton rbScience, rbLiterary;
    private ComboBox<String> WestAndGaza, calculation;

    // style for button
    String btStyle = "-fx-background-color:#05dfd7 ; -fx-background-radius:25;" +
            "-fx-border-width: 1; -fx-border-color: 'black'; -fx-font-size:19; -fx-border-radius:25;" +
            " -fx-text-fill: #000000; -fx-font-family:" +
            " 'Calisto MT'; -fx-font-weight: BOLd; ";

    // style for button hover
    String styleHover = "-fx-background-color:#ffffff; -fx-background-radius:25;" +
            "-fx-border-width: 1; -fx-border-color: 'black'; -fx-font-size:19; -fx-border-radius:25;" +
            " -fx-text-fill: #000000; -fx-font-family:" +
            " 'Calisto MT'; -fx-font-weight: BOLd; ";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tawjihi Records");
        primaryStage.setScene(new Scene(AllComponents()));
        primaryStage.setResizable(false);
        uploadDataFromFile();
        Controllers();
        primaryStage.show();
    }

    //************************************************************************************************

    /**
     * Pane for main components
     */
    private BorderPane RightBorderPane() {

        // Pane
        BorderPane RightPane = new BorderPane();

        // icon for region
        ImageView imgLocation = new ImageView(new Image("icons/Location.png"));
        imgLocation.setFitWidth(30);
        imgLocation.setFitHeight(30);

        // compo box
        WestAndGaza = new ComboBox<>();
        WestAndGaza.getItems().addAll("West Bank", "Gaza");
        WestAndGaza.setPromptText("Select Region: ");
        WestAndGaza.setEditable(false);
        WestAndGaza.setPadding(new Insets(0, 0, 5, 0));
        WestAndGaza.setMinWidth(220);
        WestAndGaza.setMinHeight(45);
        WestAndGaza.setStyle("-fx-background-color: #ffffff; -fx-border-width: 0px0px2px0px;" +
                " -fx-border-color: #000000;-fx-font-weight: BOLd;-fx-font-size:16;");

        // HBox for location
        HBox hBoxLocation = new HBox(8);
        hBoxLocation.setPadding(new Insets(0, 5, 5, 10));
        hBoxLocation.setStyle("-fx-background-color: #ffffff;");
        hBoxLocation.getChildren().addAll(imgLocation, WestAndGaza);
        hBoxLocation.setAlignment(Pos.CENTER);
        hBoxLocation.setMargin(imgLocation, new Insets(7, 0, 0, 0));
        hBoxLocation.setMargin(WestAndGaza, new Insets(0, 0, 6, 0));


        // RadioButton
        rbLiterary = new RadioButton("Literary");
        rbLiterary.setStyle("-fx-background-color: #ffffff;" + "-fx-border-radius: 15px;" +
                "-fx-padding: 6px; -fx-font-size:17; -fx-font-weight: BOLd;");

        rbScience = new RadioButton("Science");
        rbScience.setStyle("-fx-background-color: #ffffff;" + "-fx-border-radius: 15px;" +
                "-fx-padding: 6px; -fx-font-size:17; -fx-font-weight: BOLd;");


        // set the radio button as group
        ToggleGroup group = new ToggleGroup();
        rbScience.setToggleGroup(group);
        rbLiterary.setToggleGroup(group);


        // HBox for radioButton
        HBox paneRadio = new HBox(45);
        paneRadio.getChildren().addAll(rbLiterary, rbScience);
        paneRadio.setAlignment(Pos.CENTER);
        paneRadio.setPadding(new Insets(5, 5, 5, 0));
        paneRadio.setStyle("-fx-background-color: #ffffff;");


        // VBox
        VBox rightController = new VBox(10);
        rightController.setAlignment(Pos.TOP_CENTER);
        rightController.setPadding(new Insets(5, 5, 5, 5));
        rightController.setStyle("-fx-background-color: #ffffff;");
        rightController.setMargin(hBoxLocation, new Insets(55, 0, 0, 0));


        rightController.getChildren().addAll(hBoxLocation, paneRadio, leftButtons());

        RightPane.setRight(rightController);
        RightPane.setCenter(centerLeftPane());

        RightPane.setPadding(new Insets(10, 10, 10, 10));
        RightPane.setStyle("-fx-background-color: #ffffff; -fx-border-width: 0px0px0px4px; -fx-border-color: #000000;");

        return RightPane;
    }

    //************************************************************************************************

    /**
     * Table view to display records
     */
    public static TableView<TRecord> tRecordTableView() {

        // Table view to putting the columns in
        recordTableView = new TableView<>();
        recordTableView.setEditable(false);
        recordTableView.setMinWidth(580);
        recordTableView.setMinHeight(487);
        recordTableView.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width:2; -fx-font-family: 'Times New Roman'; -fx-font-size:17; -fx-text-fill: #000000; -fx-font-weight: BOLd; ");

        // first column -> setNumber
        TableColumn<TRecord, Long> setNumber = new TableColumn<>("Set Number");
        setNumber.setMinWidth(280);
        setNumber.setSortable(false);
        setNumber.setResizable(false);
        setNumber.setCellValueFactory(new PropertyValueFactory<>("seatNum"));

        //Second column -> branch
        TableColumn<TRecord, String> branch = new TableColumn<>("Branch");
        branch.setMinWidth(150);
        branch.setSortable(false);
        branch.setResizable(false);
        branch.setCellValueFactory(new PropertyValueFactory<>("branch"));

        //third column -> grade
        TableColumn<TRecord, Float> grade = new TableColumn<>("Grade");
        grade.setMinWidth(150);
        grade.setSortable(false);
        grade.setResizable(false);
        grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        recordTableView.getColumns().addAll(setNumber, branch, grade);

        return recordTableView;
    }


    //************************************************************************************************

    /**
     * Left button
     */
    private VBox leftButtons() {

        // icon add
        ImageView add = new ImageView(new Image("icons/add.png"));
        add.setFitWidth(24);
        add.setFitHeight(24);

        // button for add new record
        btAddNewRecord = new Button("   New Record");
        btAddNewRecord.setGraphic(add);
        btAddNewRecord.setContentDisplay(ContentDisplay.LEFT);
        btAddNewRecord.setMinWidth(250);
        btAddNewRecord.setMinHeight(40);
        btAddNewRecord.setStyle(btStyle);
        btAddNewRecord.setOnMouseEntered(e -> btAddNewRecord.setStyle(styleHover));
        btAddNewRecord.setOnMouseExited(e -> btAddNewRecord.setStyle(btStyle));

        // icon remove
        ImageView remove = new ImageView(new Image("icons/remove.png"));
        remove.setFitHeight(24);
        remove.setFitWidth(24);

        // button for remove record
        btRemoveRecord = new Button("   Delete Record");
        btRemoveRecord.setGraphic(remove);
        btRemoveRecord.setMinWidth(250);
        btRemoveRecord.setMinHeight(40);
        btRemoveRecord.setStyle(btStyle);
        btRemoveRecord.setContentDisplay(ContentDisplay.LEFT);
        btRemoveRecord.setOnMouseEntered(e -> btRemoveRecord.setStyle(styleHover));
        btRemoveRecord.setOnMouseExited(e -> btRemoveRecord.setStyle(btStyle));


        // icon for statistic
        ImageView imgStat = new ImageView(new Image("icons/chart.png"));
        imgStat.setFitHeight(24);
        imgStat.setFitWidth(24);

        //button to get the number of students achieving a specific mark and their percentage
        btStatistic = new Button("     Statistic     ");
        btStatistic.setGraphic(imgStat);
        btStatistic.setMinWidth(250);
        btStatistic.setMinHeight(40);
        btStatistic.setStyle(btStyle);
        btStatistic.setContentDisplay(ContentDisplay.LEFT);
        btStatistic.setOnMouseEntered(e -> btStatistic.setStyle(styleHover));
        btStatistic.setOnMouseExited(e -> btStatistic.setStyle(btStyle));


        // icon topTen
        ImageView imgTopTen = new ImageView(new Image("icons/topTen.png"));
        imgTopTen.setFitHeight(24);
        imgTopTen.setFitWidth(24);

        // button to display topTen grades
        btTopTen = new Button("Top Ten Students");
        btTopTen.setGraphic(imgTopTen);
        btTopTen.setMinWidth(250);
        btTopTen.setMinHeight(42);
        btTopTen.setStyle(btStyle);
        btTopTen.setContentDisplay(ContentDisplay.LEFT);
        btTopTen.setOnMouseEntered(e -> btTopTen.setStyle(styleHover));
        btTopTen.setOnMouseExited(e -> btTopTen.setStyle(btStyle));

        // icon print
        ImageView imgPrint = new ImageView(new Image("icons/print.png"));
        imgPrint.setFitHeight(24);
        imgPrint.setFitWidth(24);

        // button to print report in file
        btPrintReport = new Button("    Print Report");
        btPrintReport.setGraphic(imgPrint);
        btPrintReport.setMaxWidth(250);
        btPrintReport.setMinHeight(40);
        btPrintReport.setStyle(btStyle);
        btPrintReport.setContentDisplay(ContentDisplay.LEFT);
        btPrintReport.setOnMouseEntered(e -> btPrintReport.setStyle(styleHover));
        btPrintReport.setOnMouseExited(e -> btPrintReport.setStyle(btStyle));


        //ComboBox for calculations
        calculation = new ComboBox<>();
        calculation.getItems().addAll("Average", "Mode", "Median", "Standard Deviation", "Variance");
        calculation.setPromptText("Select one to calculate:");
        calculation.setEditable(false);
        calculation.setMinWidth(250);
        calculation.setMinHeight(42);
        calculation.setStyle("-fx-background-color: #05dfd7; -fx-border-radius:25;" +
                " -fx-background-radius:25; -fx-font-weight: BOLd; -fx-font-size:17; -fx-border-color: #000000; " +
                "-fx-border-width: 1;" + " -fx-text-fill:#000000; ");


        // label for display the result
        lblCalculation = new Label();
        lblCalculation.setStyle("-fx-background-color: #ffffff; -fx-border-width: 0px0px3px0px; " +
                " -fx-border-color:#000000; -fx-font-weight: BOLd;-fx-font-size:17; ");
        lblCalculation.setMaxWidth(240);

        // VBox to add all buttons
        VBox vBox = new VBox(14);
        vBox.setPadding(new Insets(5, 5, 5, 5));
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: #ffffff");


        vBox.getChildren().addAll(btAddNewRecord, btRemoveRecord, btPrintReport,
                btStatistic, btTopTen, calculation, lblCalculation);

        return vBox;
    }

    //************************************************************************************************

    /**
     * To display search block
     */
    private HBox search() {

        // HBox to display block search
        HBox hBox = new HBox(15);
        txtSearch = new TextField();
        txtSearch.setPromptText("Enter SetNumber");
        txtSearch.setStyle("-fx-background-color: #ffffff; -fx-border-width: 0px0px2px0px;" +
                " -fx-border-color: #000000; -fx-font-weight: BOLd; -fx-font-size:15;" +
                "-fx-text-fill: #000000;");
        txtSearch.setMaxWidth(170);

        // to display search icon
        ImageView imgSearch = new ImageView(new Image("icons/search.png"));
        imgSearch.setFitHeight(23);
        imgSearch.setFitWidth(23);

        // To process searching for a specific student in the records
        btSearch = new Button("Search");
        btSearch.setMinWidth(45);
        btSearch.setContentDisplay(ContentDisplay.LEFT);
        btSearch.setGraphic(imgSearch);
        btSearch.setStyle(btStyle);

        // To change the design of the button when placing a mouse arrow on it
        btSearch.setOnMouseEntered(e -> {
            btSearch.setStyle(styleHover);
        });

        // To change the design of the button when the mouse arrow is removed from it
        btSearch.setOnMouseExited(e -> {
            btSearch.setStyle(btStyle);
        });


        // Set components of the hBox block
        hBox.getChildren().addAll(txtSearch, btSearch);
        hBox.setPadding(new Insets(5, 5, 5, 0));
        hBox.setStyle("-fx-background-color: #ffffff;");

        return hBox;
    }

    //************************************************************************************************

    /**
     * To display canter components ( search block and table view and total number records block)l
     */
    private VBox centerLeftPane() {

        // Label for total number
        lblTotalNumber = new Label("Total Number");
        lblTotalNumber.setStyle("-fx-text-fill:#000000; -fx-background-color:#ffffff;" +
                "-fx-font-weight: BOLd; -fx-font-size:15;");

        // Text filed to display the total number
        txtTotalNumber = new TextField();
        txtTotalNumber.setMaxWidth(125);
        txtTotalNumber.setEditable(false);
        txtTotalNumber.setStyle("-fx-background-color:#ffffff; -fx-font-size:15;" +
                " -fx-border-width: 0px0px2px0px; -fx-border-color: #000000;" +
                " -fx-text-fill:#000000;  -fx-font-weight: BOLd;");

        // HBox to display total number
        HBox hBox = new HBox(15);
        hBox.setPadding(new Insets(5, 5, 5, 5));
        hBox.setStyle("-fx-background-color: #ffffff;");
        hBox.getChildren().addAll(lblTotalNumber, txtTotalNumber);

        // To Download lblTotalNumber node down
        hBox.setMargin(lblTotalNumber, new Insets(15, 0, 0, 0));

        // To raise the txtTotalNumber node up
        hBox.setMargin(txtTotalNumber, new Insets(0, 0, 10, 0));

        // Vbox to display search block and table view and total number records
        VBox vBox = new VBox(7);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setStyle("-fx-background-color: #ffffff;");

        // to set components of the vBox (search block and table view and totalNumber records
        vBox.getChildren().addAll(search(), tRecordTableView(), hBox);

        return vBox;
    }

    //************************************************************************************************

    /**
     * To set all components
     */
    private BorderPane AllComponents() {

        // Main border pane(contains all components)
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setStyle("-fx-background-color: #ffffff;");

        // icon Palestinian Ministry Of Education Logo
        ImageView logo = new ImageView(new Image("icons/logo.png"));
        logo.setFitHeight(370);
        logo.setFitWidth(450);

        // icon graduate
        ImageView graduate = new ImageView(new Image("icons/graduate.png"));
        graduate.setFitHeight(280);
        graduate.setFitWidth(480);

        // VBox for Main Logo
        VBox VLogo = new VBox();
        VLogo.getChildren().addAll(logo, graduate);
        VLogo.setAlignment(Pos.CENTER);

        // welcome label
        Label lblWelcome = new Label("Welcome To The Academic Record For Tawjihi\n    In The Palestinian Ministry Of Education");
        lblWelcome.setFont(Font.font("Times New Roman", FontWeight.BOLD, 36));
        lblWelcome.setPadding(new Insets(5, 0, 15, 0));
        lblWelcome.setAlignment(Pos.CENTER);
        lblWelcome.setStyle("-fx-background-color: #05dfd7; -fx-border-radius:50;" +
                " -fx-background-radius:50; -fx-text-fill: #000000; ");
        lblWelcome.setMinWidth(1400);

        // HBox for label welcome
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(5, 5, 5, 5));
        hBox.setStyle("-fx-background-color: #ffffff;");

        hBox.getChildren().addAll(lblWelcome);
        hBox.setMargin(lblWelcome, new Insets(2, 0, 10, 0));

        // to set components of pane
        pane.setRight(RightBorderPane());
        pane.setCenter(VLogo);
        pane.setTop(hBox);

        return pane;
    }

    //************************************************************************************************

    /**
     * to view data in table view
     */
    public static void uploadListToTable(LinkedList<TRecord> list) {
        if (!list.isEmpty()) {
            recordTableView.getItems().clear(); // clear data from table
            Node<TRecord> curr = list.getHead();
            int count = 0;
            while (curr != null) {
                recordTableView.getItems().add(curr.getData()); // upload data to the table
                curr = curr.getNext();
                count++;
            }
            txtTotalNumber.setText(count + "");
        } else {
            Massage.displayMassage("Data", " There is no records to display ");
        }
    }

    //************************************************************************************************

    /**
     * Methode to read data from file iteratively
     */
    public void readDataFromFile(String fileName, LinkedList<TRecord> literaryLinkedList,
                                 LinkedList<TRecord> scientificLinkedList) throws IOException {
        file = new File(fileName); // instance of file
        try {
            input = new Scanner(file); // instance of scanner for read data from file
            if (file.length() == 0) {
                throw new IOException("  There's No records in file " + fileName + "  "); // no data in file
            } else {
                TRecord student; // temp of records
                int line = 1; // represent line on the file to display in which line has problem If that happens

                while (input.hasNext()) { // read line of data
                    try {

                        student = new TRecord(input.nextLine()); // create new record

                        if (student.isLiterary())
                            literaryLinkedList.addBySort(student); // this record is from literary branch
                        else if (student.isScientific())
                            scientificLinkedList.addBySort(student); // this record is from scientific branch

                        line++; // increment the line by one

                    } catch (Exception ex) {
                        // the record in the file has a problem
                        // e.g. he does not have a grade or The data arrangement is not in the right place
                        Massage.displayMassage("Warning", " Error reading in student info in line # " + line + " in file " + fileName + "  ");
                    }
                }
                input.close(); // prevent(close) scanner to read data
            }

        } catch (FileNotFoundException e) {
            // The specific file for reading data does not exist
            throw new FileNotFoundException(" The system can NOT find the file " + fileName + "  ");
        }
    }

    //************************************************************************************************

    /**
     * Upload date to table view
     */
    public void uploadDataFromFile() {
        try {
            // Uploading data from Gaza_2019.csv file and add them to the lists
            readDataFromFile("Gaza_2019.csv", Calculations.gazaLiterary, Calculations.gazaScientific);

            // Uploading data from WestBank_2019.csv file and add them to the lists
            readDataFromFile("WestBank_2019.csv", Calculations.westBankLiteraryList, Calculations.westBankScientificList);

        } catch (IOException ex) {
            Massage.displayMassage("Error: \n", ex.getMessage());
        }
    }


    //************************************************************************************************

    /**
     * Actions on all controllers
     */
    private void Controllers() {

        ComboBoxController(); // Actions on ComboBox region

        RadioButtonController(); // Actions on RadioButtons branch

        SearchButtonController(); // Actions on Search button

        NewRecordButton(); // Action on newRecord button

        RemoveRecordButton(); // Actions for remove exist record

        PrintReportButton(); // Actions for print report button

        TopTenButton(); // Actions for top ten button

        StatisticButton();// Actions for statistic button

        ComboBoxCalculations(); // Actions for ComboBox calculations
    }


    //************************************************************************************************

    /**
     * Actions on ComboBox region
     */
    private void ComboBoxController() {
        // Actions on ComboBox region
        WestAndGaza.setOnAction(e -> {
            recordTableView.getItems().clear();  // clear all data in table view
            txtTotalNumber.clear(); // clear data in textFiled totalNumber
            txtSearch.clear(); // clear data in textField search
            lblCalculation.setText(""); // clear data in labelCalculation
            rbScience.setSelected(false); // set radioButton scientific false
            rbLiterary.setSelected(false);// set radioButton literary false
        });
    }

    //************************************************************************************************

    /**
     * Actions on RadioButton branch
     */
    private void RadioButtonController() {
        // Actions on radio button branch

        rbLiterary.setOnAction(e -> {
            if (WestAndGaza.getValue() == null) {// The region has not been selected
                Massage.displayMassage("", "Please select the region");
                rbLiterary.setSelected(false);// set radioButton literary false

            } // Select West Bank and literary
            else if (WestAndGaza.getValue().equals("West Bank")) {

                recordTableView.getItems().clear();// clear all data in table view
                txtTotalNumber.clear();// clear data in textFiled totalNumber
                lblCalculation.setText(""); // clear data from label for calculations
                uploadListToTable(Calculations.westBankLiteraryList); // upload west bank literary list to table view to display

            }// Select Gaza and literary
            else if (WestAndGaza.getValue().equals("Gaza")) {

                recordTableView.getItems().clear();// clear all data in table view
                txtTotalNumber.clear();// clear data in textFiled totalNumber
                lblCalculation.setText(""); // clear data from label for calculations
                uploadListToTable(Calculations.gazaLiterary);// upload gaza literary list to table view to display

            }
        });

        // Scientific radio button
        rbScience.setOnAction(e -> {
            if (WestAndGaza.getValue() == null) { // The region has not been selected
                Massage.displayMassage("", "Please select the region");
                rbScience.setSelected(false);

            }// Select West Bank and scientific
            else if (WestAndGaza.getValue().equals("West Bank")) {

                recordTableView.getItems().clear(); // clear all data in table view
                txtTotalNumber.clear();// clear data in textFiled totalNumber
                lblCalculation.setText(""); // clear data from label for calculations
                uploadListToTable(Calculations.westBankScientificList);// upload gaza scientific list to table view to display

            }// Select Gaza and scientific
            else if (WestAndGaza.getValue().equals("Gaza")) {

                recordTableView.getItems().clear(); // clear all data in table view
                txtTotalNumber.clear();// clear data in textFiled totalNumber
                lblCalculation.setText(""); // clear data from label for calculations
                uploadListToTable(Calculations.gazaScientific);// upload gaza scientific list to table view to display

            }
        });
    }

    //************************************************************************************************

    /**
     * Actions on Search button
     */
    private void SearchButtonController() {
        btSearch.setOnAction(e -> {
            lblCalculation.setText(""); // clear data in labelCalculation
            if (WestAndGaza.getValue() == null) { // The region has not been selected
                Massage.displayMassage("", "Please select the region");

            } else if (!rbScience.isSelected() && !rbLiterary.isSelected()) { //The branch has not been selected
                Massage.displayMassage("", "Please select the the branch");

            } else if (!txtSearch.getText().trim().isEmpty()) { // the text filed has data
                TRecord temp;
                if (CheckTextFiled.isSeatNumber(txtSearch)) { // The seat number is valid
                    // West Bank scientific branch
                    if (WestAndGaza.getValue().equals("West Bank") && rbScience.isSelected()) {
                        // no data in list west bank scientific
                        if (Calculations.westBankScientificList.isEmpty()) {
                            Massage.displayMassage("Error", " There is no records to search from them ");
                        } else {
                            // Search for this records in list scientific in west bank
                            temp = Calculations.westBankScientificList.search(new TRecord(Long.parseLong(txtSearch.getText().trim())));
                            if (temp == null) { // this records does not exist
                                Massage.displayMassage("Warning", txtSearch.getText() + " Does not exist in branch\nscientific on West Bank ");
                            } else {
                                SearchRecord.searchRecord(temp);
                            }
                        }

                    } // West Bank literary branch
                    else if (WestAndGaza.getValue().equals("West Bank") && rbLiterary.isSelected()) {

                        if (Calculations.westBankLiteraryList.isEmpty()) { // no data in list west bank literary
                            Massage.displayMassage("Error", " There is no records to search from them ");
                        } else {
                            // Search for this records in list literary in west bank
                            temp = Calculations.westBankLiteraryList.search(new TRecord(Long.parseLong(txtSearch.getText().trim())));
                            if (temp == null) { // this records does not exist
                                Massage.displayMassage("Warning", txtSearch.getText() + " Does not exist in branch\nliterary on West Bank ");
                            } else {
                                SearchRecord.searchRecord(temp);
                            }
                        }
                    }// Gaza scientific branch
                    else if (WestAndGaza.getValue().equals("Gaza") && rbScience.isSelected()) {

                        if (Calculations.gazaScientific.isEmpty()) { // no data in list gaza scientific
                            Massage.displayMassage("Error", " There is no records to search from them ");
                        } else {
                            // Search for this records in list scientific in gaza
                            temp = Calculations.gazaScientific.search(new TRecord(Long.parseLong(txtSearch.getText().trim())));
                            if (temp == null) { // this records does not exist
                                Massage.displayMassage("Warning", txtSearch.getText() + " Does not exist in branch\nscientific on Gaza ");
                            } else {
                                SearchRecord.searchRecord(temp);
                            }
                        }
                    }// Gaza literary branch
                    else if (WestAndGaza.getValue().equals("Gaza") && rbLiterary.isSelected()) {

                        if (Calculations.gazaLiterary.isEmpty()) { // no data in list gaza literary
                            Massage.displayMassage("Error", " There is no records to search from them ");
                        } else {
                            // Search for this records in list literary in gaza
                            temp = Calculations.gazaLiterary.search(new TRecord(Long.parseLong(txtSearch.getText().trim())));
                            if (temp == null) { // this records does not exist
                                Massage.displayMassage("Warning", txtSearch.getText() + " Does not exist in branch\nliterary on Gaza ");
                            } else {
                                SearchRecord.searchRecord(temp);
                            }
                        }
                    }
                } else {
                    Massage.displayMassage("Error", " The set number is invalid ");
                }
                txtSearch.clear(); // clear data in search textFiled
            }
        });
    }

    //************************************************************************************************

    /**
     * Actions for add new record
     */
    private void NewRecordButton() {
        btAddNewRecord.setOnAction(e -> {
            txtSearch.clear(); // clear data in textField search
            lblCalculation.setText(""); // clear data in labelCalculation
            if (WestAndGaza.getValue() == null) { // The region has not been selected
                Massage.displayMassage("", " Please select the region ");

            } else if (!rbScience.isSelected() && !rbLiterary.isSelected()) { //The branch has not been selected
                Massage.displayMassage("", " Please select the the branch ");
            } else {
                // checking if the  new record exists if not, and add it to the records
                if (WestAndGaza.getValue().equals("West Bank") && rbScience.isSelected()) {
                    // add new record to the west bank scientific list
                    NewRecord.addNewRecord(Calculations.westBankScientificList, "Scientific");

                } else if (WestAndGaza.getValue().equals("West Bank") && rbLiterary.isSelected()) {
                    // add new record to the west bank literary list
                    NewRecord.addNewRecord(Calculations.westBankLiteraryList, "Literary");

                } else if (WestAndGaza.getValue().equals("Gaza") && rbScience.isSelected()) {
                    // add new record to the gaza scientific list
                    NewRecord.addNewRecord(Calculations.gazaScientific, "Scientific");

                } else if (WestAndGaza.getValue().equals("Gaza") && rbLiterary.isSelected()) {
                    // add new record to the gaza literary list
                    NewRecord.addNewRecord(Calculations.gazaLiterary, "Literary");
                }
            }
        });
    }

    //************************************************************************************************

    /**
     * Actions for remove exist record
     */
    private void RemoveRecordButton() {
        btRemoveRecord.setOnAction(e -> {
            txtSearch.clear(); // clear data in textField search
            lblCalculation.setText(""); // clear data in labelCalculation
            if (WestAndGaza.getValue() == null) { // The region has not been selected
                Massage.displayMassage("", " Please select the region ");

            } else if (!rbScience.isSelected() && !rbLiterary.isSelected()) { //The branch has not been selected
                Massage.displayMassage("", " Please select the the branch ");
            } else {
                // Checking whether the record to be deleted exists  or not, and removing it from the records if it exists
                if (WestAndGaza.getValue().equals("West Bank") && rbScience.isSelected()) {
                    // delete this record from the west bank scientific list
                    RemoveRecord.removeRecord(Calculations.westBankScientificList);

                } else if (WestAndGaza.getValue().equals("West Bank") && rbLiterary.isSelected()) {
                    // delete this record from the west bank literary list
                    RemoveRecord.removeRecord(Calculations.westBankLiteraryList);

                } else if (WestAndGaza.getValue().equals("Gaza") && rbScience.isSelected()) {
                    // delete this record from the gaza scientific list
                    RemoveRecord.removeRecord(Calculations.gazaScientific);

                } else if (WestAndGaza.getValue().equals("Gaza") && rbLiterary.isSelected()) {
                    // delete this record from the gaza literary list
                    RemoveRecord.removeRecord(Calculations.gazaLiterary);
                }
            }
        });
    }

    //************************************************************************************************

    /**
     * Actions for print report button
     */
    private void PrintReportButton() {
        btPrintReport.setOnAction(e -> {
            txtSearch.clear(); // clear data in textField search
            lblCalculation.setText(""); // clear data in labelCalculation
            if (WestAndGaza.getValue() == null) { // The region has not been selected
                Massage.displayMassage("", " Please select the region ");

            } else if (!rbScience.isSelected() && !rbLiterary.isSelected()) { //The branch has not been selected
                Massage.displayMassage("", " Please select the the branch ");
            } else {
                if (WestAndGaza.getValue().equals("West Bank") && rbScience.isSelected()) {
                    // print report to the west bank scientific list
                    PrintReport.writeDataInFile(Calculations.westBankScientificList, "West Bank", "Scientific");

                } else if (WestAndGaza.getValue().equals("West Bank") && rbLiterary.isSelected()) {
                    // print report to the west bank literary list
                    PrintReport.writeDataInFile(Calculations.westBankLiteraryList, "West Bank", "Literary");

                } else if (WestAndGaza.getValue().equals("Gaza") && rbScience.isSelected()) {
                    // print report to the gaza scientific list
                    PrintReport.writeDataInFile(Calculations.gazaScientific, "Gaza", "Scientific");

                } else if (WestAndGaza.getValue().equals("Gaza") && rbLiterary.isSelected()) {
                    // print report to the gaza literary list
                    PrintReport.writeDataInFile(Calculations.gazaLiterary, "Gaza", "Literary");
                }
            }
        });
    }

    //************************************************************************************************

    /**
     * Actions for top ten button
     */
    private void TopTenButton() {
        btTopTen.setOnAction(e -> {
            txtSearch.clear(); // clear data in textField search
            lblCalculation.setText(""); // clear data in labelCalculation
            if (WestAndGaza.getValue() == null) { // The region has not been selected
                Massage.displayMassage("", " Please select the region ");

            } else if (!rbScience.isSelected() && !rbLiterary.isSelected()) { //The branch has not been selected
                Massage.displayMassage("", " Please select the the branch ");
            } else {
                if (WestAndGaza.getValue().equals("West Bank") && rbScience.isSelected()) {
                    // view top ten grades to the west bank scientific list
                    ViewTopTen.TopTenRecords(Calculations.westBankScientificList);

                } else if (WestAndGaza.getValue().equals("West Bank") && rbLiterary.isSelected()) {
                    // view top ten grades to the west bank literary list
                    ViewTopTen.TopTenRecords(Calculations.westBankLiteraryList);

                } else if (WestAndGaza.getValue().equals("Gaza") && rbScience.isSelected()) {
                    // view top ten grades to the gaza scientific list
                    ViewTopTen.TopTenRecords(Calculations.gazaScientific);

                } else if (WestAndGaza.getValue().equals("Gaza") && rbLiterary.isSelected()) {
                    // view top ten grades to the gaza literary list
                    ViewTopTen.TopTenRecords(Calculations.gazaLiterary);
                }
            }
        });
    }

    //************************************************************************************************

    /**
     * Actions for statistic button
     */
    private void StatisticButton() {
        btStatistic.setOnAction(e -> {
            txtSearch.clear(); // clear data in textField search
            lblCalculation.setText(""); // clear data in labelCalculation
            if (WestAndGaza.getValue() == null) { // The region has not been selected
                Massage.displayMassage("", " Please select the region ");

            } else if (!rbScience.isSelected() && !rbLiterary.isSelected()) { //The branch has not been selected
                Massage.displayMassage("", " Please select the the branch ");
            } else {
                // get number of student >= specific grade and there percentage
                if (WestAndGaza.getValue().equals("West Bank") && rbScience.isSelected()) {
                    Statistic.statAboveGrade(Calculations.westBankScientificList);

                } else if (WestAndGaza.getValue().equals("West Bank") && rbLiterary.isSelected()) {
                    Statistic.statAboveGrade(Calculations.westBankLiteraryList);

                } else if (WestAndGaza.getValue().equals("Gaza") && rbScience.isSelected()) {
                    Statistic.statAboveGrade(Calculations.gazaScientific);

                } else if (WestAndGaza.getValue().equals("Gaza") && rbLiterary.isSelected()) {
                    Statistic.statAboveGrade(Calculations.gazaLiterary);
                }
            }
        });
    }

    //************************************************************************************************

    /**
     * Actions on CompoBox calculations
     */
    private void ComboBoxCalculations() {
        calculation.setOnAction(e -> {
            txtSearch.clear(); // clear data in textField search
            lblCalculation.setText(""); // clear data in labelCalculation
            if (WestAndGaza.getValue() == null) { // The region has not been selected
                Massage.displayMassage("", " Please select the region ");
                calculation.setPromptText("Select one to calculate:"); // return to the promptText
            } else if (!rbScience.isSelected() && !rbLiterary.isSelected()) { //The branch has not been selected
                Massage.displayMassage("", " Please select the the branch ");
                calculation.setPromptText("Select one to calculate:"); // return to the promptText
            } else {
                // Calculate average for this list
                if (calculation.getValue().equals("Average")) {
                    float avg = 0.0F;
                    if (WestAndGaza.getValue().equals("West Bank") && rbScience.isSelected()) {
                        avg = Calculations.calculateAverage(Calculations.westBankScientificList);

                    } else if (WestAndGaza.getValue().equals("West Bank") && rbLiterary.isSelected()) {
                        avg = Calculations.calculateAverage(Calculations.westBankLiteraryList);

                    } else if (WestAndGaza.getValue().equals("Gaza") && rbScience.isSelected()) {
                        avg = Calculations.calculateAverage(Calculations.gazaScientific);

                    } else if (WestAndGaza.getValue().equals("Gaza") && rbLiterary.isSelected()) {
                        avg = Calculations.calculateAverage(Calculations.gazaLiterary);
                    }
                    if (avg != 0.0F) lblCalculation.setText(" " + String.format("%.2f", avg));

                } // calculate mode for this list
                else if (calculation.getValue().equals("Mode")) {
                    float mode = 0.0F;
                    if (WestAndGaza.getValue().equals("West Bank") && rbScience.isSelected()) {
                        mode = Calculations.calculateMode(Calculations.westBankScientificList);

                    } else if (WestAndGaza.getValue().equals("West Bank") && rbLiterary.isSelected()) {
                        mode = Calculations.calculateMode(Calculations.westBankLiteraryList);

                    } else if (WestAndGaza.getValue().equals("Gaza") && rbScience.isSelected()) {
                        mode = Calculations.calculateMode(Calculations.gazaScientific);

                    } else if (WestAndGaza.getValue().equals("Gaza") && rbLiterary.isSelected()) {
                        mode = Calculations.calculateMode(Calculations.gazaLiterary);
                    }
                    if (mode != 0.0F) lblCalculation.setText(" " + String.format("%.2f", mode));

                } //calculate median for this list
                else if (calculation.getValue().equals("Median")) {
                    float median = 0.0F;
                    if (WestAndGaza.getValue().equals("West Bank") && rbScience.isSelected()) {
                        median = Calculations.calculateMedian(Calculations.westBankScientificList);

                    } else if (WestAndGaza.getValue().equals("West Bank") && rbLiterary.isSelected()) {
                        median = Calculations.calculateMedian(Calculations.westBankLiteraryList);

                    } else if (WestAndGaza.getValue().equals("Gaza") && rbScience.isSelected()) {
                        median = Calculations.calculateMedian(Calculations.gazaScientific);

                    } else if (WestAndGaza.getValue().equals("Gaza") && rbLiterary.isSelected()) {
                        median = Calculations.calculateMedian(Calculations.gazaLiterary);
                    }
                    if (median != 0.0F) lblCalculation.setText(" " + String.format("%.2f", median));

                } // calculate stander deviation fot this list
                else if (calculation.getValue().equals("Standard Deviation")) {
                    float sd = 0.0F;
                    if (WestAndGaza.getValue().equals("West Bank") && rbScience.isSelected()) {
                        sd = Calculations.calculateStandardDeviation(Calculations.westBankScientificList);

                    } else if (WestAndGaza.getValue().equals("West Bank") && rbLiterary.isSelected()) {
                        sd = Calculations.calculateStandardDeviation(Calculations.westBankLiteraryList);

                    } else if (WestAndGaza.getValue().equals("Gaza") && rbScience.isSelected()) {
                        sd = Calculations.calculateStandardDeviation(Calculations.gazaScientific);

                    } else if (WestAndGaza.getValue().equals("Gaza") && rbLiterary.isSelected()) {
                        sd = Calculations.calculateStandardDeviation(Calculations.gazaLiterary);
                    }
                    if (sd != 0.0F) lblCalculation.setText(" " + String.format("%.2f", sd));

                }// calculate variance for this list
                else if (calculation.getValue().equals("Variance")) {
                    float variance = 0.0F;
                    if (WestAndGaza.getValue().equals("West Bank") && rbScience.isSelected()) {
                        variance = Calculations.calculateVariance(Calculations.westBankScientificList);

                    } else if (WestAndGaza.getValue().equals("West Bank") && rbLiterary.isSelected()) {
                        variance = Calculations.calculateVariance(Calculations.westBankLiteraryList);

                    } else if (WestAndGaza.getValue().equals("Gaza") && rbScience.isSelected()) {
                        variance = Calculations.calculateVariance(Calculations.gazaScientific);

                    } else if (WestAndGaza.getValue().equals("Gaza") && rbLiterary.isSelected()) {
                        variance = Calculations.calculateVariance(Calculations.gazaLiterary);
                    }
                    if (variance != 0.0F) lblCalculation.setText(" " + String.format("%.2f", variance));

                }
                calculation.setPromptText("Select one to calculate:"); // return to the promptText
            }
        });
    }
}
