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
    private Stage window;
    private Scene MainScene;
    private static File file;
    private static Scanner input;
    private Button btAddNewRecord, btRemoveRecord, btStatistic, btTopTen, btPrintReport;
    private Button btSearch;
    private TextField txtSearch;
    private Label lblTotalNumber, lblCalculation;
    private TextField txtTotalNumber;
    private TableView<TRecord> recordTableView;
    private RadioButton rbScience, rbLiterary;
    private ComboBox<String> WestAndGaza, calculation;
    private LinkedList<TRecord> lit = new LinkedList<>(), sec = new LinkedList<>();

    String btStyle = "-fx-background-color:#a4ebf3; -fx-background-radius:25;" +
            "-fx-border-width: 1; -fx-border-color: 'black'; -fx-font-size:19; -fx-border-radius:25;" +
            " -fx-text-fill: #000000; -fx-font-family:" +
            " 'Calisto MT'; -fx-font-weight: BOLd; ";

    String styleHover = "-fx-background-color:#ffffff; -fx-background-radius:25;" +
            "-fx-border-width: 1; -fx-border-color: 'black'; -fx-font-size:19; -fx-border-radius:25;" +
            " -fx-text-fill: #000000; -fx-font-family:" +
            " 'Calisto MT'; -fx-font-weight: BOLd; ";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Tawjihi Records");
        MainScene = new Scene(AllComponents());
        Controller();
        window.setScene(MainScene);
        window.setResizable(false);
        window.show();
    }

    private BorderPane RightBorderPane() {
        BorderPane RightPane = new BorderPane();

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


        ToggleGroup group = new ToggleGroup();
        rbScience.setToggleGroup(group);
        rbLiterary.setToggleGroup(group);


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


        rightController.getChildren().addAll(hBoxLocation, paneRadio, addAndDelete());

        RightPane.setRight(rightController);
        RightPane.setCenter(centerLeftPane());

        RightPane.setPadding(new Insets(10, 10, 10, 10));
        RightPane.setStyle("-fx-background-color: #ffffff; -fx-border-width: 0px0px0px4px; -fx-border-color: #000000;");

        return RightPane;
    }


    private TableView<TRecord> tRecordTableView() {
        recordTableView = new TableView<>();
        recordTableView.setEditable(false);
        recordTableView.setMinWidth(580);
        recordTableView.setMinHeight(487);
        recordTableView.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width:2; -fx-font-family: 'Times New Roman'; -fx-font-size:17; -fx-text-fill: #000000; -fx-font-weight: BOLd; ");
        TableColumn<TRecord, Long> setNumber = new TableColumn<>("Set Number");
        setNumber.setMinWidth(280);
        setNumber.setEditable(false);
        setNumber.setSortable(true);
        setNumber.setResizable(false);
        setNumber.setCellValueFactory(new PropertyValueFactory<>("seatNum"));

        TableColumn<TRecord, String> branch = new TableColumn<>("Branch");
        branch.setMinWidth(150);
        branch.setEditable(false);
        branch.setSortable(false);
        branch.setResizable(false);
        branch.setCellValueFactory(new PropertyValueFactory<>("branch"));

        TableColumn<TRecord, Float> grade = new TableColumn<>("Grade");
        grade.setMinWidth(150);
        grade.setEditable(false);
        grade.setSortable(true);
        grade.setResizable(false);
        grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        recordTableView.getColumns().addAll(setNumber, branch, grade);


        return recordTableView;
    }


    private VBox addAndDelete() {

        ImageView add = new ImageView(new Image("icons/add.png"));
        add.setFitWidth(24);
        add.setFitHeight(24);

        btAddNewRecord = new Button("   New Record");
        btAddNewRecord.setGraphic(add);
        btAddNewRecord.setContentDisplay(ContentDisplay.LEFT);
        btAddNewRecord.setMinWidth(250);
        btAddNewRecord.setMinHeight(40);
        btAddNewRecord.setStyle(btStyle);
        btAddNewRecord.setOnMouseEntered(e -> btAddNewRecord.setStyle(styleHover));
        btAddNewRecord.setOnMouseExited(e -> btAddNewRecord.setStyle(btStyle));

        ImageView remove = new ImageView(new Image("icons/remove.png"));
        remove.setFitHeight(24);
        remove.setFitWidth(24);
        btRemoveRecord = new Button("   Delete Record");
        btRemoveRecord.setGraphic(remove);
        btRemoveRecord.setMinWidth(250);
        btRemoveRecord.setMinHeight(40);
        btRemoveRecord.setStyle(btStyle);
        btRemoveRecord.setContentDisplay(ContentDisplay.LEFT);
        btRemoveRecord.setOnMouseEntered(e -> btRemoveRecord.setStyle(styleHover));
        btRemoveRecord.setOnMouseExited(e -> btRemoveRecord.setStyle(btStyle));


        ImageView imgStat = new ImageView(new Image("icons/chart.png"));
        imgStat.setFitHeight(24);
        imgStat.setFitWidth(24);

        btStatistic = new Button("     Statistic     ");
        btStatistic.setGraphic(imgStat);
        btStatistic.setMinWidth(250);
        btStatistic.setMinHeight(40);
        btStatistic.setStyle(btStyle);
        btStatistic.setContentDisplay(ContentDisplay.LEFT);
        btStatistic.setOnMouseEntered(e -> btStatistic.setStyle(styleHover));
        btStatistic.setOnMouseExited(e -> btStatistic.setStyle(btStyle));


        ImageView imgTopTen = new ImageView(new Image("icons/topTen.png"));
        imgTopTen.setFitHeight(24);
        imgTopTen.setFitWidth(24);

        btTopTen = new Button("Top Ten Students");
        btTopTen.setGraphic(imgTopTen);
        btTopTen.setMinWidth(250);
        btTopTen.setMinHeight(42);
        btTopTen.setStyle(btStyle);
        btTopTen.setContentDisplay(ContentDisplay.LEFT);
        btTopTen.setOnMouseEntered(e -> btTopTen.setStyle(styleHover));
        btTopTen.setOnMouseExited(e -> btTopTen.setStyle(btStyle));

        ImageView imgPrint = new ImageView(new Image("icons/print.png"));
        imgPrint.setFitHeight(24);
        imgPrint.setFitWidth(24);

        btPrintReport = new Button("    Print Report");
        btPrintReport.setGraphic(imgPrint);
        btPrintReport.setMaxWidth(250);
        btPrintReport.setMinHeight(40);
        btPrintReport.setStyle(btStyle);
        btPrintReport.setContentDisplay(ContentDisplay.LEFT);
        btPrintReport.setOnMouseEntered(e -> btPrintReport.setStyle(styleHover));
        btPrintReport.setOnMouseExited(e -> btPrintReport.setStyle(btStyle));


        VBox vBox = new VBox(14);
        vBox.setPadding(new Insets(5, 5, 5, 5));
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: #ffffff");


        calculation = new ComboBox<>();
        calculation.getItems().addAll("Average", "Mode", "Median", "Standard Deviation", "Variance");
        calculation.setPromptText("Select one to calculate:");
        calculation.setEditable(false);
        calculation.setMinWidth(250);
        calculation.setMinHeight(42);
        calculation.setStyle("-fx-background-color: #a4ebf3; -fx-border-radius:25;" +
                " -fx-background-radius:25; -fx-font-weight: BOLd; -fx-font-size:17; -fx-border-color: #000000; " +
                "-fx-border-width: 1;" + " -fx-text-fill:#000000; ");


        lblCalculation = new Label();
        lblCalculation.setStyle("-fx-background-color: #ffffff; -fx-border-width: 0px0px3px0px; " +
                " -fx-border-color:#000000; -fx-font-weight: BOLd;-fx-font-size:16; ");
        lblCalculation.setMaxWidth(250);

        vBox.getChildren().addAll(btAddNewRecord, btRemoveRecord, btPrintReport,
                btStatistic, btTopTen, calculation, lblCalculation);

        return vBox;
    }

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
        //   btSearch.setOnAction(e -> SearchRecord.searchRecord());
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

        // to set components of the vBox
        vBox.getChildren().addAll(search(), tRecordTableView(), hBox);

        return vBox;
    }

    /**
     * To set all components
     */
    private BorderPane AllComponents() {

        // Main border pane(contains all components)
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setStyle("-fx-background-color: #ffffff;");

        // Palestinian Ministry Of Education Logo
        ImageView logo = new ImageView(new Image("icons/logo.jpg"));
        logo.setFitHeight(400);
        logo.setFitWidth(400);

        // VBox for Main Logo
        VBox VLogo = new VBox();
        VLogo.getChildren().addAll(logo);
        VLogo.setMargin(logo, new Insets(125, 0, 0, 0));

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(5, 5, 5, 5));
        hBox.setStyle("-fx-background-color: #ffffff;");


        // welcome label
        Label lblWelcome = new Label("Welcome To The Academic Record For Tawjihi\n    In The Palestinian Ministry Of Education");
        lblWelcome.setFont(Font.font("Times New Roman", FontWeight.BOLD, 36));
        lblWelcome.setPadding(new Insets(5, 0, 15, 0));
        lblWelcome.setAlignment(Pos.CENTER);
        lblWelcome.setStyle("-fx-background-color: #a4ebf3; -fx-border-radius:50;" +
                " -fx-background-radius:50; -fx-text-fill: #000000; ");
        lblWelcome.setMinWidth(1300);

        hBox.getChildren().addAll(lblWelcome);
        hBox.setMargin(lblWelcome, new Insets(2, 0, 10, 0));

        // to set components of pane
        pane.setRight(RightBorderPane());
        pane.setCenter(VLogo);
        pane.setTop(hBox);

        return pane;
    }


    /**
     * to view data in table view
     */
    private void uploadListToTable(LinkedList<TRecord> list) {
        if (!list.isEmpty()) {
            recordTableView.getItems().clear();
            Node<TRecord> curr = list.getHead();
            int count = 0;
            while (curr != null) {
                recordTableView.getItems().add(curr.getData()); // upload data to the table
                curr = curr.getNext();
                count++;
            }
            txtTotalNumber.setText(count + "");
        } else {
            Massage.displayMassage("Data", "There is no data to display");
        }
    }

    /**
     * Methode to read data from file iteratively
     */
    public static void readDataFromFile(String fileName, LinkedList<TRecord> literaryLinkedList,
                                        LinkedList<TRecord> scientificLinkedList) throws IOException {
        file = new File(fileName);
        try {
            input = new Scanner(file);
            if (file.length() == 0) {
                throw new IOException("There's No data in file " + fileName);
            } else {
                TRecord student;
                int line = 1;
                while (input.hasNext()) { // read line of data
                    try {
                        student = new TRecord(input.nextLine());
                        if (student.isLiterary()) literaryLinkedList.addBySort(student);
                        else if (student.isScientific()) scientificLinkedList.addBySort(student);
                        line++;
                    } catch (Exception ex) {
                        Massage.displayMassage("Warning", "Error reading in student info in line # " + line + " in file " + fileName);
                    }
                }
                input.close();
            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("The system can not find the file " + fileName);
        }
    }

    private void Controller() {

        // Actions on ComboBox region
        WestAndGaza.setOnAction(e -> {
            recordTableView.getItems().clear();
            rbScience.setSelected(false);
            rbLiterary.setSelected(false);
            txtTotalNumber.setText("");

            // Read data from westBank file
            if (WestAndGaza.getValue().equals("West Bank")) {
                Calculations.literaryList = new LinkedList<>();
                Calculations.scientificList = new LinkedList<>();
                try {
                    readDataFromFile("WestBank_2019.txt", Calculations.literaryList, Calculations.scientificList);
                } catch (IOException ex) {
                    Massage.displayMassage("Error", ex.getMessage());
                }

            }// Read data from Gaza file
            else if (WestAndGaza.getValue().equals("Gaza")) {
                Calculations.literaryList = new LinkedList<>();
                Calculations.scientificList = new LinkedList<>();
                try {
                    readDataFromFile("Gaza_2019.txt", Calculations.literaryList, Calculations.scientificList);
                } catch (IOException ex) {
                    Massage.displayMassage("Error", ex.getMessage());
                }
            }
        });

        // Actions on radio button branch

        rbLiterary.setOnAction(e -> {
            if (!WestAndGaza.getValue().isEmpty()) {
                uploadListToTable(Calculations.literaryList);
                System.out.println(Calculations.literaryList.length());
            }
        });

        rbScience.setOnAction(e -> {
            if (!WestAndGaza.getValue().isEmpty()) {
                uploadListToTable(Calculations.scientificList);
            }
        });


    }
}
