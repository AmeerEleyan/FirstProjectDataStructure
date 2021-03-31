/**
 * @author: Amir Eleyan
 * ID: 1191076
 * At: 28/3/2021  2:55 AM
 */
package GUI;

import linkedList.TRecord;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class SearchRecord {

    private static TextField txtSetNumber, txtBranch, txtGrade;
    private static Label lblSetNumber, lblBranch, lblGrade;

    public static void searchRecord(TRecord record) {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Student Info");

        // Style for labels
        String styleLbl = "-fx-text-fill:#000000; -fx-background-color:#ffffff;-fx-font-weight: BOLd; -fx-font-size:14; ";

        // Style for textFields
        String styleTxt = "-fx-background-color: #ffffff; -fx-border-width: 0px0px2px0px; -fx-border-color: #000000;" +
                " -fx-font-size:14;  -fx-text-fill: #000000; -fx-font-weight: BOLd;";

        // Style for buttons
        String styleBt = "-fx-background-color: #05dfd7; -fx-border-radius:25; -fx-background-radius:25; " +
                "-fx-border-width: 1; -fx-border-color: #000000; -fx-font-weight: BOLd;-fx-font-size:15;" +
                "-fx-text-fill: #000000; -fx-font-family: 'Times New Roman'; ";

        // Style for hover buttons
        String styleHoverBt = "-fx-background-color: #ffffff; -fx-border-radius:25; -fx-background-radius:25; " +
                "-fx-border-width: 1; -fx-border-color: #000000; -fx-font-weight: BOLd;-fx-font-size:15;" +
                "-fx-text-fill: #000000; -fx-font-family: 'Times New Roman'; ";

        // pane for display record info
        GridPane pane = new GridPane();
        pane.setStyle("-fx-background-color: #ffffff;");
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(15);
        pane.setHgap(8);
        pane.setPadding(new Insets(5, 5, 5, 5));

        // label for set number
        lblSetNumber = new Label("Set Number");
        lblSetNumber.setStyle(styleLbl);

        // text for display setNumber
        txtSetNumber = new TextField(record.getSeatNum() + "");
        txtSetNumber.setMaxWidth(150);
        txtSetNumber.setStyle(styleTxt);
        txtSetNumber.setEditable(false);

        // label for branch
        lblBranch = new Label("Branch");
        lblBranch.setStyle(styleLbl);

        // text for display branch
        txtBranch = new TextField(record.getBranch());
        txtBranch.setMaxWidth(150);
        txtBranch.setStyle(styleTxt);
        txtBranch.setEditable(false);

        // label for  grade
        lblGrade = new Label("Grade");
        lblGrade.setStyle(styleLbl);

        // text for display grade
        txtGrade = new TextField(record.getGrade() + "");
        txtGrade.setMaxWidth(150);
        txtGrade.setStyle(styleTxt);
        txtGrade.setEditable(false);

        // arrange labels and textFields in the gridPane
        pane.add(lblSetNumber, 0, 0);
        pane.add(txtSetNumber, 1, 0);

        pane.add(lblBranch, 0, 1);
        pane.add(txtBranch, 1, 1);

        pane.add(lblGrade, 0, 2);
        pane.add(txtGrade, 1, 2);


        // button for close window
        Button btOk = new Button("OK");
        btOk.setMinWidth(80);
        btOk.setStyle(styleBt);
        btOk.setOnMouseEntered(e -> btOk.setStyle(styleBt));
        btOk.setOnMouseExited(e -> btOk.setStyle(styleHoverBt));
        btOk.setOnAction(e -> window.close());


        VBox vBox = new VBox(25);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(5, 5, 5, 5));
        vBox.setStyle("-fx-background-color: #ffffff;");
        vBox.getChildren().addAll(pane, btOk);

        window.setScene(new Scene(vBox));
        window.setMinWidth(330);
        window.setMinHeight(295);
        window.setResizable(false);
        window.show();

    }
}
