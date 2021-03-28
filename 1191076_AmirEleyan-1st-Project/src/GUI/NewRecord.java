package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

    private static TextField txtSetNumber, txtGrade;
    private static Label lblSetNumber, lblBranch, lblGrade;

    public static void addNewRecord(LinkedList<TRecord> list) {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("New Record");

        String styleLbl = "-fx-text-fill:#000000; -fx-background-color:#ffffff;-fx-font-weight: BOLd; -fx-font-size:14; ";
        String styleTxt = "-fx-background-color: #ffffff; -fx-border-width: 0px0px2px0px; -fx-border-color: #000000; -fx-font-size:15; -fx-text-fill: #000000;";
        String styleBt = "-fx-background-color: #f88f01; -fx-border-radius:25; -fx-background-radius:25; " +
                "-fx-border-width: 1; -fx-border-color: #000000; -fx-font-weight: BOLd;-fx-font-size:15;" + "-fx-text-fill: #000000; -fx-font-family: 'Times New Roman'; ";

        GridPane pane = new GridPane();
        pane.setStyle("-fx-background-color: #ffffff;");
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(15);
        pane.setHgap(8);
        pane.setPadding(new Insets(5, 5, 5, 5));

        lblSetNumber = new Label("\nSet Number");
        lblSetNumber.setStyle(styleLbl);


        txtSetNumber = new TextField();
        txtSetNumber.setPromptText("Enter the set number");
        txtSetNumber.setMaxWidth(180);
        txtSetNumber.setStyle(styleTxt);


        lblBranch = new Label("\nBranch");
        lblBranch.setStyle(styleLbl);

        ComboBox<String> bxBranch = new ComboBox<>();
        bxBranch.getItems().addAll("Scientific", "Literary");
        bxBranch.setPromptText("Branch?");
        bxBranch.setEditable(false);
        bxBranch.setPadding(new Insets(0, 0, 2, 0));
        bxBranch.setMinWidth(180);
        bxBranch.setStyle("-fx-background-color: #ffffff; -fx-border-width: 0px0px2px0px; -fx-border-color: #000000;-fx-font-weight: BOLd;-fx-font-size:14;");


        lblGrade = new Label("\nGrade");
        lblGrade.setStyle(styleLbl);

        txtGrade = new TextField();
        txtGrade.setPromptText("Enter the grade");
        txtGrade.setMaxWidth(180);
        txtGrade.setStyle(styleTxt);

        pane.add(lblSetNumber, 0, 0);
        pane.add(txtSetNumber, 1, 0);

        pane.add(lblBranch, 0, 1);
        pane.add(bxBranch, 1, 1);

        pane.add(lblGrade, 0, 2);
        pane.add(txtGrade, 1, 2);

        HBox hBox = new HBox(50);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(5, 5, 5, 5));
        hBox.setStyle("-fx-background-color: #ffffff;");

        Button btAdd = new Button("Add");
        btAdd.setMinWidth(60);
        btAdd.setStyle(styleBt);

        btAdd.setOnAction(e -> {
            if (!txtSetNumber.getText().isEmpty() && !txtGrade.getText().isEmpty() && !bxBranch.getValue().isEmpty()) {
                if (list.isEmpty()) {
                    Massage.displayMassage("Error", "There is no data to add this record to them");
                } else {
                    if (CheckTextFiled.isGrade(txtSetNumber)) {
                        if (CheckTextFiled.isGrade(txtGrade)) {
                            TRecord record = new TRecord();
                            record.setSeatNum(Long.parseLong(txtSetNumber.getText()));
                            if (list.search(record) != null)
                                Massage.displayMassage("Warning", txtSetNumber.getText() + " is exists in records. Try again");
                            else {
                                record.setBranch(bxBranch.getValue());
                                record.setGrade(Float.parseFloat(txtGrade.getText()));
                                list.addBySort(record);
                            }

                        } else {
                            Massage.displayMassage("Warning", "Invalid Grade");
                        }

                    } else {
                        Massage.displayMassage("Warning", "Invalid Set Number");
                    }
                }

            } else {
                Massage.displayMassage("Warning", "   There is a field that is empty " +
                        "please fill it   ");
            }
        });

        Button btCancel = new Button("Cancel");
        btCancel.setMinWidth(60);
        btCancel.setStyle(styleBt);
        btCancel.setOnAction(e -> window.close());

        hBox.getChildren().addAll(btAdd, btCancel);

        VBox vBox = new VBox(25);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(5, 5, 5, 5));
        vBox.setStyle("-fx-background-color: #ffffff;");

        vBox.getChildren().addAll(pane, hBox);

        window.setScene(new Scene(vBox));
        window.setMinWidth(360);
        window.setMinHeight(280);
        window.setResizable(false);
        window.show();

    }


}
