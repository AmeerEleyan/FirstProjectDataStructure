package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import linkedList.LinkedList;
import linkedList.TRecord;

public abstract class NewRecord {

    private static TextField txtSetNumber, txtGrade, txtBranch;
    private static Label lblSetNumber, lblBranch, lblGrade;

    public static void addNewRecord(LinkedList<TRecord> list, String branch) {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("New Record");

        // style for labels
        String styleLbl = "-fx-text-fill:#000000; -fx-background-color:#ffffff;-fx-font-weight: BOLd; -fx-font-size:14; ";

        //style for textFields
        String styleTxt = "-fx-background-color: #ffffff; -fx-border-width: 0px0px2px0px; -fx-border-color: #000000; -fx-font-size:15; -fx-text-fill: #000000;";

        // Style for buttons
        String styleBt = "-fx-background-color: #05dfd7; -fx-border-radius:25; -fx-background-radius:25; " +
                "-fx-border-width: 1; -fx-border-color: #000000; -fx-font-weight: BOLd;-fx-font-size:15;" +
                "-fx-text-fill: #000000; -fx-font-family: 'Times New Roman'; ";

        // Style for hover buttons
        String styleHoverBt = "-fx-background-color: #ffffff; -fx-border-radius:25; -fx-background-radius:25; " +
                "-fx-border-width: 1; -fx-border-color: #000000; -fx-font-weight: BOLd;-fx-font-size:15;" +
                "-fx-text-fill: #000000; -fx-font-family: 'Times New Roman'; ";

        // gridPane for arrange labels and testFields
        GridPane pane = new GridPane();
        pane.setStyle("-fx-background-color: #ffffff;");
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(15);
        pane.setHgap(8);
        pane.setPadding(new Insets(5, 5, 5, 5));

        // label for setNumber
        lblSetNumber = new Label("\nSet Number");
        lblSetNumber.setStyle(styleLbl);

        // text filed to get the value of the setNumber
        txtSetNumber = new TextField();
        txtSetNumber.setPromptText("Enter the set number");
        txtSetNumber.setMaxWidth(180);
        txtSetNumber.setStyle(styleTxt);

        // label for branch
        lblBranch = new Label("\nBranch");
        lblBranch.setStyle(styleLbl);

        // text field for branch
        txtBranch = new TextField("\n" + branch);
        txtBranch.setMaxWidth(180);
        txtBranch.setStyle(styleTxt);
        txtBranch.setEditable(false);

        // label for grade
        lblGrade = new Label("\nGrade");
        lblGrade.setStyle(styleLbl);

        // text field to get the value of the grade
        txtGrade = new TextField();
        txtGrade.setPromptText("Enter the grade");
        txtGrade.setMaxWidth(180);
        txtGrade.setStyle(styleTxt);


        pane.add(lblSetNumber, 0, 0);
        pane.add(txtSetNumber, 1, 0);

        pane.add(lblBranch, 0, 1);
        pane.add(txtBranch, 1, 1);

        pane.add(lblGrade, 0, 2);
        pane.add(txtGrade, 1, 2);

        HBox hBox = new HBox(50);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(5, 5, 5, 5));
        hBox.setStyle("-fx-background-color: #ffffff;");

        // button for add new record
        Button btAdd = new Button("Add");
        btAdd.setMinWidth(65);
        btAdd.setStyle(styleBt);
        btAdd.setOnMouseEntered(e -> btAdd.setStyle(styleHoverBt));
        btAdd.setOnMouseExited(e -> btAdd.setStyle(styleBt));


        //  Actions on add button
        btAdd.setOnAction(e -> {
            // There is an empty text between the textFields
            if (!txtSetNumber.getText().isEmpty() && !txtGrade.getText().isEmpty() && !txtBranch.getText().isEmpty()) {

                if (CheckTextFiled.isGrade(txtSetNumber)) { // setNumber is valid
                    if (CheckTextFiled.isGrade(txtGrade)) { // grade is valid

                        TRecord record = new TRecord(); // instance of TRecord to add the list
                        // set the setNumber for this record
                        record.setSeatNum(Long.parseLong(txtSetNumber.getText().trim()));
                        // set the branch for this record
                        record.setBranch(branch);
                        // set the grade for this record
                        record.setGrade(Float.parseFloat(txtGrade.getText().trim()));

                        if (list.search(record) != null) // means that this set number is exist in records
                            Massage.displayMassage("Warning", txtSetNumber.getText() + " is already exists in records. Try again");
                        else {
                            list.addBySort(record); //  This record does not exist in the records and has been added to the records
                            Massage.displayMassage("Success", txtSetNumber.getText() + " Added successfully");
                            txtSetNumber.clear();// clear data from setNumber textField
                            txtGrade.clear();// clear data from grade textField
                        }
                    } else {
                        txtGrade.clear(); // clear data from grade textField
                        Massage.displayMassage("Warning", " The grade is invalid ");
                    }
                } else {
                    txtSetNumber.clear();// clear data from setNumber textField
                    Massage.displayMassage("Warning", " The set number is invalid ");
                }

            } else {
                Massage.displayMassage("Warning", "   There is a field that is empty " +
                        "please fill it   ");
            }
        });

        // button for close the window
        Button btCancel = new Button("Cancel");
        btCancel.setMinWidth(65);
        btCancel.setStyle(styleBt);
        btCancel.setOnMouseEntered(e -> btCancel.setStyle(styleHoverBt));
        btCancel.setOnMouseExited(e -> btCancel.setStyle(styleBt));
        btCancel.setOnAction(e -> window.close());

        hBox.getChildren().addAll(btAdd, btCancel);

        VBox vBox = new VBox(25);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(5, 5, 5, 5));
        vBox.setStyle("-fx-background-color: #ffffff;");

        vBox.getChildren().addAll(pane, hBox);

        window.setScene(new Scene(vBox));
        window.setMinWidth(380);
        window.setMinHeight(290);
        window.setResizable(false);
        window.show();
    }

}
