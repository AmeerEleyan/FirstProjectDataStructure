package GUI;

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
    private Button btAvg, btMode, btMedian, btVar, btSD, btPercentage;
    private TextField txtAvg, txtMode, txtMedian, txtVar, txtSD, txtPercentage;
    private Label lblAvg, lblMod, lblMedian, lblVar, lblSD, lblPercentage;
    private Button btAddNewRecord, btRemoveRecord, btStatistic, btTopTen, btPrintReport;
    private Button btSearch;
    private TextField txtSearch;
    private Label lblTotalNumber, lblCalculation;
    private TextField txtTotalNumber;
    private TableView<TRecord> recordTableView;
    private RadioButton rbScience, rbLiterary;
    private ComboBox<String> calculation;
    private LinkedList<TRecord> lit = new LinkedList<>(), sec = new LinkedList<>();

    String btStyle = "-fx-background-color:f88f01; -fx-background-radius:25;" +
            "-fx-font-size:18; -fx-border-radius:25;" +
            " -fx-border-width: 3; -fx-text-fill: #000000; -fx-font-family:" +
            " 'Calisto MT'; -fx-font-weight: BOLd; ";

    String styleHover = "-fx-background-color:#ffffff; -fx-background-radius:25;" +
            "-fx-font-size:18; -fx-border-radius:25;-fx-border-color: #000000;" +
            " -fx-border-width: 1; -fx-text-fill: #000000; -fx-font-family: " +
            "'Calisto MT'; -fx-font-weight: BOLd;";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Tawjihi Records");
        MainScene = new Scene(AllComponents());
        MainScene.getStylesheets().add(getClass().getResource("styel.css").toExternalForm());

        window.setScene(MainScene);
        window.setResizable(false);
        window.show();
    }

    private BorderPane RightBorderPane() {
        BorderPane RightPane = new BorderPane();

        ImageView imgLocation = new ImageView(new Image("icons/Location.png"));
        imgLocation.setFitWidth(20);
        imgLocation.setFitHeight(20);

        // compo box
        ComboBox<String> WestAndGaza = new ComboBox<>();
        WestAndGaza.getItems().addAll("West Bank", "Gaza");
        WestAndGaza.setPromptText("Which Area?");
        WestAndGaza.setEditable(false);
        WestAndGaza.setPadding(new Insets(0, 0, 2, 0));
        WestAndGaza.setMinWidth(130);
        WestAndGaza.setMinHeight(20);
        WestAndGaza.setStyle("-fx-background-color: #ffffff; -fx-border-width: 0px0px2px0px; -fx-border-color: #000000;-fx-font-weight: BOLd;-fx-font-size:14;");

        HBox hBoxLocation = new HBox(8);
        hBoxLocation.setPadding(new Insets(0, 5, 5, 5));
        hBoxLocation.setStyle("-fx-background-color: #ffffff;");
        hBoxLocation.getChildren().addAll(imgLocation, WestAndGaza);
        hBoxLocation.setAlignment(Pos.CENTER);
        hBoxLocation.setMargin(imgLocation, new Insets(6, 0, 0, 0));
        hBoxLocation.setMargin(WestAndGaza, new Insets(0, 0, 5, 0));


        // RadioButton
        rbLiterary = new RadioButton("Literary");
        rbLiterary.setStyle("fx-border-width: 1px;-fx-border-color: #000000;-fx-background-color: transparent;" +
                "-fx-border-radius: 15px;-fx-padding: 4px; -fx-font-size:12; -fx-font-weight: BOLd;");

        rbScience = new RadioButton("Science");
        rbScience.setStyle("fx-border-width: 1px;-fx-border-color: #000000;-fx-background-color: transparent;" +
                "-fx-border-radius: 15px;-fx-padding: 4px; -fx-font-size:12; -fx-font-weight: BOLd;");

        rbLiterary.setOnAction(e -> {
            if (rbLiterary.isSelected()) {
                uploadListToTable(lit);
            }
        });
        rbScience.setOnAction(e -> {
            if (rbScience.isSelected()) {
                uploadListToTable(sec);
            }
        });

        WestAndGaza.setOnAction(e -> {
            if (WestAndGaza.getValue().equals("West Bank")) {
                rbScience.setSelected(false);
                rbLiterary.setSelected(false);
                try {
                    System.out.println("Start");
                    readDataFromFile("WestBank_2019.txt", lit, sec);
                    System.out.println("West upoded");
                } catch (Exception ex) {

                }
            } else if (WestAndGaza.getValue().equals("Gaza")) {
                rbScience.setSelected(false);
                rbLiterary.setSelected(false);
                try {
                    System.out.println("Start");
                    readDataFromFile("Gaza_2019.txt", lit, sec);
                    System.out.println("Gaza");
                } catch (Exception ex) {

                }
            }
        });




        ToggleGroup group = new ToggleGroup();
        rbScience.setToggleGroup(group);
        rbLiterary.setToggleGroup(group);


        HBox paneRadio = new HBox(15);
        paneRadio.getChildren().addAll(rbLiterary, rbScience);
        paneRadio.setAlignment(Pos.CENTER);
        paneRadio.setPadding(new Insets(5, 5, 5, 0));
        paneRadio.setStyle("-fx-background-color: #ffffff;");
        // end RadioButton


        // VBox
        VBox rightController = new VBox(10);
        rightController.setAlignment(Pos.TOP_CENTER);
        rightController.setPadding(new Insets(5, 5, 5, 5));
        rightController.setStyle("-fx-background-color: #ffffff;");
        rightController.setMargin(hBoxLocation, new Insets(55, 0, 0, 0));
       /* btUpload.setOnAction(e -> {
            if ((t.getText() != null && !t.getText().isEmpty())){
                System.out.println("Done");
            }
        });*/
        rightController.getChildren().addAll(hBoxLocation, paneRadio, addAndDelete());
        // end VBox

        RightPane.setRight(rightController);


        RightPane.setCenter(centerLeftPane());

        RightPane.setPadding(new Insets(10, 10, 10, 10));
        RightPane.setStyle("-fx-background-color: #ffffff; -fx-border-width: 0px0px0px4px; -fx-border-color: #000000;");
        return RightPane;
    }


    /**
     * Methode to read data from file iteratively
     */
    public static void readDataFromFile(String fileName, LinkedList<TRecord> literaryLinkedList, LinkedList<TRecord> scientificLinkedList) throws IOException {
        file = new File(fileName);
        try {
            input = new Scanner(file);
            if (file.length() == 0) {
                throw new IOException("There's No data in file ");
            } else {
                TRecord student;
                int line = 1;
                while (input.hasNext()) { // read line of data
                    try {
                        student = new TRecord(input.nextLine());
                        // if (student.isLiterary()) literaryLinkedList.addWithSort(student);
                        //else if (student.isScientific()) scientificLinkedList.addWithSort(student);
                        line++;
                    } catch (Exception ex) {
                        System.out.println("Error reading in student info in line # " + line + " in file ");
                    }
                }
                input.close();
            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("The system can not find the file ");
        }
    }

    private TableView<TRecord> tRecordTableView(LinkedList<TRecord> s, LinkedList<TRecord> l) {
        recordTableView = new TableView<>();
        recordTableView.setEditable(false);
        recordTableView.setMinWidth(500);
        recordTableView.setMinHeight(400);
        recordTableView.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width:2; -fx-font-family: 'Times New Roman'; -fx-font-size:17; -fx-text-fill: #000000; -fx-font-weight: BOLd; ");
        TableColumn<TRecord, Long> setNumber = new TableColumn<>("Set Number");
        setNumber.setMinWidth(200);
        setNumber.setEditable(false);
        setNumber.setSortable(true);
        setNumber.setResizable(false);
        setNumber.setCellValueFactory(new PropertyValueFactory<>("seatNum"));

        TableColumn<TRecord, String> branch = new TableColumn<>("Branch");
        branch.setMinWidth(100);
        branch.setEditable(false);
        branch.setSortable(false);
        branch.setResizable(false);
        branch.setCellValueFactory(new PropertyValueFactory<>("branch"));

        TableColumn<TRecord, Float> grade = new TableColumn<>("Grade");
        grade.setMinWidth(100);
        grade.setEditable(false);
        grade.setSortable(true);
        grade.setResizable(false);
        grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        recordTableView.getColumns().addAll(setNumber, branch, grade);


        return recordTableView;
    }


    private VBox buttons() {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(5, 5, 5, 5));
        vBox.setStyle("-fx-background-color: #ffffff;");

        GridPane calculation = new GridPane();
        calculation.setStyle("-fx-background-color: #ffffff;");
        calculation.setVgap(20);
        calculation.setHgap(15);
        calculation.setPadding(new Insets(5, 5, 5, 5));
        vBox.getChildren().addAll(calculation);
        vBox.setMargin(calculation, new Insets(50, 0, 0, 0));

        //***************************
        ImageView imgAvg = new ImageView(new Image("icons/calculate.png"));
        imgAvg.setFitWidth(22);
        imgAvg.setFitHeight(22);
        btAvg = new Button("Calculate");
        btAvg.setGraphic(imgAvg);
        btAvg.setStyle("-fx-background-color:f88f01; -fx-background-radius:25;" +
                "-fx-font-size:15; -fx-border-radius:25;-fx-border-color: #000000; -fx-border-width: 1; -fx-text-fill: #000000; -fx-font-family: Times New Roman; -fx-font-weight: BOLd; ");
        btAvg.setContentDisplay(ContentDisplay.LEFT);
        btAvg.setMinWidth(50);

        txtAvg = new TextField();
        txtAvg.setPromptText("Average");
        txtAvg.setMaxWidth(120);
        txtAvg.setEditable(false);
        txtAvg.setStyle("-fx-background-color: #ffffff; -fx-border-width: 0px0px2px0px; -fx-border-color: #000000; -fx-font-weight: BOLd; -fx-font-size:14;");

        lblAvg = new Label("Average");
        lblAvg.setStyle("-fx-text-fill:#000000; -fx-background-color:#ffffff;-fx-font-weight: BOLd; -fx-font-size:16; ");
        //**************************************************


        //*******************************************
        ImageView imgMode = new ImageView(new Image("icons/calculate.png"));
        imgMode.setFitWidth(22);
        imgMode.setFitHeight(22);
        btMode = new Button("Calculate");
        btMode.setGraphic(imgMode);
        btMode.setStyle("-fx-background-color:f88f01; -fx-background-radius:25;" +
                "-fx-font-size:15; -fx-border-radius:25;-fx-border-color: #000000; -fx-border-width: 1; -fx-text-fill: #000000; -fx-font-family: Times New Roman; -fx-font-weight: BOLd; ");
        btMode.setContentDisplay(ContentDisplay.LEFT);
        btMode.setMinWidth(50);

        txtMode = new TextField();
        txtMode.setPromptText("Mode");
        txtMode.setMaxWidth(120);
        txtMode.setEditable(false);
        txtMode.setStyle("-fx-background-color: #ffffff; -fx-border-width: 0px0px2px0px; -fx-border-color: #000000; -fx-font-weight: BOLd; -fx-font-size:14;");

        lblMod = new Label("Mode");
        lblMod.setStyle("-fx-text-fill:#000000; -fx-background-color:#ffffff;-fx-font-weight: BOLd; -fx-font-size:16; ");
        //**************************************************

        //**************************************************
        ImageView imgMedian = new ImageView(new Image("icons/calculate.png"));
        imgMedian.setFitWidth(22);
        imgMedian.setFitHeight(22);
        btMedian = new Button("Calculate");
        btMedian.setGraphic(imgMedian);
        btMedian.setStyle("-fx-background-color:f88f01; -fx-background-radius:25;" +
                "-fx-font-size:15; -fx-border-radius:25;-fx-border-color: #000000; -fx-border-width: 1; -fx-text-fill: #000000; -fx-font-family: Times New Roman; -fx-font-weight: BOLd; ");
        btMedian.setContentDisplay(ContentDisplay.LEFT);
        btMedian.setMinWidth(50);

        txtMedian = new TextField();
        txtMedian.setPromptText("Median");
        txtMedian.setMaxWidth(120);
        txtMedian.setEditable(false);
        txtMedian.setStyle("-fx-background-color: #ffffff; -fx-border-width: 0px0px2px0px; -fx-border-color: #000000; -fx-font-weight: BOLd; -fx-font-size:14;");

        lblMedian = new Label("Median");
        lblMedian.setStyle("-fx-text-fill:#000000; -fx-background-color:#ffffff;-fx-font-weight: BOLd; -fx-font-size:16; ");
        //**************************************************

        //***************************************************
        ImageView imgSD = new ImageView(new Image("icons/calculate.png"));
        imgSD.setFitWidth(22);
        imgSD.setFitHeight(22);
        btSD = new Button("Calculate");
        btSD.setGraphic(imgSD);
        btSD.setStyle("-fx-background-color:f88f01; -fx-background-radius:25;" +
                "-fx-font-size:15; -fx-border-radius:25;-fx-border-color: #000000; -fx-border-width: 1; -fx-text-fill: #000000; -fx-font-family: Times New Roman; -fx-font-weight: BOLd; ");
        btSD.setContentDisplay(ContentDisplay.LEFT);
        btSD.setMinWidth(50);

        txtSD = new TextField();
        txtSD.setPromptText("Standard Deviation");
        txtSD.setMaxWidth(120);
        txtSD.setEditable(false);
        txtSD.setStyle("-fx-background-color: #ffffff; -fx-border-width: 0px0px2px0px; -fx-border-color: #000000; -fx-font-weight: BOLd; -fx-font-size:14;");

        lblSD = new Label("Standard Deviation");
        lblSD.setStyle("-fx-text-fill:#000000; -fx-background-color:#ffffff;-fx-font-weight: BOLd; -fx-font-size:16; ");
        //**************************************************

        //**************************************************
        ImageView imgVar = new ImageView(new Image("icons/calculate.png"));
        imgVar.setFitWidth(22);
        imgVar.setFitHeight(22);
        btVar = new Button("Calculate");
        btVar.setGraphic(imgVar);
        btVar.setStyle("-fx-background-color:f88f01; -fx-background-radius:25;" +
                "-fx-font-size:15; -fx-border-radius:25;-fx-border-color: #000000; -fx-border-width: 1; -fx-text-fill: #000000; -fx-font-family: Times New Roman; -fx-font-weight: BOLd; ");
        btVar.setContentDisplay(ContentDisplay.LEFT);
        btVar.setMinWidth(50);

        txtVar = new TextField();
        txtVar.setPromptText("Variance");
        txtVar.setMaxWidth(120);
        txtVar.setEditable(false);
        txtVar.setStyle("-fx-background-color: #ffffff; -fx-border-width: 0px0px2px0px; -fx-border-color: #000000; -fx-font-weight: BOLd; -fx-font-size:14;");

        lblVar = new Label("Variance");
        lblVar.setStyle("-fx-text-fill:#000000; -fx-background-color:#ffffff;-fx-font-weight: BOLd; -fx-font-size:16; ");
        //**************************************************

        //**************************************************
        ImageView imgPer = new ImageView(new Image("icons/calculate.png"));
        imgPer.setFitWidth(22);
        imgPer.setFitHeight(22);
        btPercentage = new Button("Calculate");
        btPercentage.setGraphic(imgPer);
        btPercentage.setStyle("-fx-background-color:f88f01; -fx-background-radius:25;" +
                "-fx-font-size:15; -fx-border-radius:25;-fx-border-color: #000000; -fx-border-width: 1; -fx-text-fill: #000000; -fx-font-family: Times New Roman; -fx-font-weight: BOLd; ");
        btPercentage.setContentDisplay(ContentDisplay.LEFT);
        btPercentage.setMinWidth(50);

        txtPercentage = new TextField();
        txtPercentage.setPromptText("Enter the grade");
        txtPercentage.setMaxWidth(120);
        txtPercentage.setStyle("-fx-background-color: #ffffff; -fx-border-width: 0px0px2px0px; -fx-border-color: #000000; -fx-font-weight: BOLd; -fx-font-size:14;");

        lblPercentage = new Label("Percentage Above\nSpecific Grade");
        lblPercentage.setStyle("-fx-text-fill:#000000; -fx-background-color:#ffffff;-fx-font-weight: BOLd; -fx-font-size:16; ");
        //**************************************************

        calculation.add(lblAvg, 0, 0);
        calculation.add(txtAvg, 1, 0);
        calculation.add(btAvg, 2, 0);
        calculation.add(lblMod, 0, 1);
        calculation.add(txtMode, 1, 1);
        calculation.add(btMode, 2, 1);
        calculation.add(lblMedian, 0, 2);
        calculation.add(txtMedian, 1, 2);
        calculation.add(btMedian, 2, 2);
        calculation.add(lblSD, 0, 3);
        calculation.add(txtSD, 1, 3);
        calculation.add(btSD, 2, 3);
        calculation.add(lblVar, 0, 4);
        calculation.add(txtVar, 1, 4);
        calculation.add(btVar, 2, 4);
        calculation.add(lblPercentage, 0, 5);
        calculation.add(txtPercentage, 1, 5);
        calculation.add(btPercentage, 2, 5);

        return vBox;
    }

    private VBox addAndDelete() {
        ImageView add = new ImageView(new Image("icons/add.png"));
        add.setFitWidth(22);
        add.setFitHeight(22);
        btAddNewRecord = new Button("   New Record");
        btAddNewRecord.setGraphic(add);
        btAddNewRecord.setStyle(btStyle);
        btAddNewRecord.setContentDisplay(ContentDisplay.LEFT);
        btAddNewRecord.setMinWidth(170);
        btAddNewRecord.setOnAction(e -> NewRecord.addNewRecord());

        ImageView remove = new ImageView(new Image("icons/remove.png"));
        remove.setFitHeight(22);
        remove.setFitWidth(22);
        btRemoveRecord = new Button("   Delete Record");
        btRemoveRecord.setGraphic(remove);
        btRemoveRecord.setMinWidth(170);
        btRemoveRecord.setStyle(btStyle);

        btRemoveRecord.setContentDisplay(ContentDisplay.LEFT);
        btRemoveRecord.setOnAction(e -> RemoveRecord.removeRecord());


        ImageView imgStat = new ImageView(new Image("icons/chart.png"));
        imgStat.setFitHeight(24);
        imgStat.setFitWidth(24);
        btStatistic = new Button("     Statistic     ");
        btStatistic.setGraphic(imgStat);
        btStatistic.setMinWidth(170);
        btStatistic.setStyle(btStyle);
        btStatistic.setContentDisplay(ContentDisplay.LEFT);
        btStatistic.setOnAction(e -> Statistic.statAboveGrade());


        ImageView imgTopTen = new ImageView(new Image("icons/topTen.png"));
        imgTopTen.setFitHeight(24);
        imgTopTen.setFitWidth(24);
        btTopTen = new Button("Top Ten Students");
        btTopTen.setGraphic(imgTopTen);
        btTopTen.setMinWidth(170);
        btTopTen.setStyle(btStyle);
        btTopTen.setContentDisplay(ContentDisplay.LEFT);

        ImageView imgPrint = new ImageView(new Image("icons/print.png"));
        imgPrint.setFitHeight(22);
        imgPrint.setFitWidth(22);
        btPrintReport = new Button("    Print Report");
        btPrintReport.setGraphic(imgPrint);
        btPrintReport.setMinWidth(170);
        btPrintReport.setStyle(btStyle);
        btPrintReport.setContentDisplay(ContentDisplay.LEFT);
        btPrintReport.setOnAction(e -> {
            if (!CheckTextFiled.isSeatNumber(txtSearch))
                Massage.displayMassage("Error", "Set Number Invalid");
        });


        VBox vBox = new VBox(15);
        vBox.setPadding(new Insets(5, 5, 5, 5));
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: #ffffff");


        ImageView imgPer = new ImageView(new Image("icons/calculate.png"));
        imgPer.setFitWidth(25);
        imgPer.setFitHeight(25);

        calculation = new ComboBox<>();
        calculation.getItems().addAll("Average", "Mode", "Median", "Standard Deviation", "Variance");
        calculation.setPromptText("Select one");
        calculation.setEditable(false);
        calculation.setPadding(new Insets(0, 0, 2, 0));
        calculation.setMaxWidth(185);
        calculation.setMinHeight(25);
        calculation.setStyle("-fx-background-color: #f88f01; -fx-border-radius:25; -fx-background-radius:25; -fx-border-width: 1; -fx-font-weight: BOLd;-fx-font-size:14;");

        HBox hBoxCalculation = new HBox(8);
        hBoxCalculation.setPadding(new Insets(0, 5, 5, 5));
        hBoxCalculation.setStyle("-fx-background-color: #ffffff;");
        hBoxCalculation.getChildren().addAll(imgPer, calculation);
        hBoxCalculation.setAlignment(Pos.CENTER);
        hBoxCalculation.setMargin(imgPer, new Insets(6, 0, 0, 0));
        hBoxCalculation.setMargin(calculation, new Insets(0, 0, 5, 0));
        lblCalculation = new Label();
        lblCalculation.setStyle("-fx-background-color: #ffffff; -fx-border-width: 0px0px2px0px;  -fx-border-color:#000000; -fx-font-weight: BOLd;-fx-font-size:14; ");
        lblCalculation.setMaxWidth(200);

        vBox.getChildren().addAll(btAddNewRecord, btRemoveRecord, btPrintReport, btStatistic, btTopTen, hBoxCalculation, lblCalculation);

        return vBox;
    }

    /**
     * To display search block
     */
    private HBox search() {

        // HBox to display block search
        HBox hBox = new HBox(10);
        txtSearch = new TextField();
        txtSearch.setPromptText("Enter SetNumber");

        txtSearch.setStyle("-fx-background-color: #ffffff; -fx-border-width: 0px0px2px0px;" +
                " -fx-border-color: #000000; -fx-font-weight: BOLd; -fx-font-size:16; -fx-text-fill: #000000;");

        // to display search icon
        ImageView imgSearch = new ImageView(new Image("icons/search.png"));
        imgSearch.setFitHeight(22);
        imgSearch.setFitWidth(22);

        // To process searching for a specific student in the records
        btSearch = new Button("Search");
        btSearch.setMinWidth(50);
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

        // Actions whe button search clicked
        btSearch.setOnAction(e -> {
            if (!txtSearch.getText().isEmpty()) {
                if (CheckTextFiled.isSeatNumber(txtSearch)) {
                //    SearchRecord.searchRecord();
                    Massage.displayMassage("", txtSearch + " Not exist in records");
                } else {
                    Massage.displayMassage("Error Input", "Invalid Set Number");
                }
            }
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
                " -fx-border-width: 0px0px2px0px; -fx-border-color: #000000;");

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
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setStyle("-fx-background-color: #ffffff;");

        // to set components of the vBox
        vBox.getChildren().addAll(search(), tRecordTableView(lit, sec), hBox);

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

        // welcome label
        Label lblWelcome = new Label("Welcome To The Academic Record For Tawjihi\n    In The Palestinian Ministry Of Education");
        lblWelcome.setFont(Font.font("Times New Roman", FontWeight.BOLD, 32));
        lblWelcome.setPadding(new Insets(5, 0, 15, 0));
        lblWelcome.setAlignment(Pos.CENTER);
        lblWelcome.setStyle("-fx-background-color: #f88f01; -fx-border-radius:35;" +
                " -fx-background-radius:35; -fx-text-fill: #000000; ");
        lblWelcome.setMinWidth(1200);

        // to set components of pane
        pane.setRight(RightBorderPane());
        pane.setCenter(VLogo);
        pane.setTop(lblWelcome);

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
}
