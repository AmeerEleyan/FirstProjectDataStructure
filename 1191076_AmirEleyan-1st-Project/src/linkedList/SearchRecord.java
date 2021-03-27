package linkedList;

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

    public static void searchRecord(LinkedList<TRecord> list) {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Student Info");

        String styleLbl = "-fx-text-fill:#000000; -fx-background-color:#ffffff;-fx-font-weight: BOLd; -fx-font-size:14; ";
        String styleTxt = "-fx-background-color: #ffffff; -fx-border-width: 0px0px2px0px; -fx-border-color: #000000; -fx-font-size:15;  -fx-text-fill: #000000;";
        String styleBt = "-fx-background-color: #f88f01; -fx-border-radius:25; -fx-background-radius:25; " +
                "-fx-border-width: 1; -fx-border-color: #000000; -fx-font-weight: BOLd;-fx-font-size:15;" + "-fx-text-fill: #000000; -fx-font-family: 'Times New Roman'; ";

        GridPane pane = new GridPane();
        pane.setStyle("-fx-background-color: #ffffff;");
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(15);
        pane.setHgap(8);
        pane.setPadding(new Insets(5, 5, 5, 5));

        lblSetNumber = new Label("Set Number");
        lblSetNumber.setStyle(styleLbl);


        txtSetNumber = new TextField();
        txtSetNumber.setMaxWidth(150);
        txtSetNumber.setStyle(styleTxt);
        txtSetNumber.setEditable(false);


        lblBranch = new Label("Branch");
        lblBranch.setStyle(styleLbl);

        txtBranch = new TextField();
        txtBranch.setMaxWidth(150);
        txtBranch.setStyle(styleTxt);
        txtBranch.setEditable(false);


        lblGrade = new Label("Grade");
        lblGrade.setStyle(styleLbl);

        txtGrade = new TextField();
        txtGrade.setMaxWidth(150);
        txtGrade.setStyle(styleTxt);
        txtGrade.setEditable(false);

        pane.add(lblSetNumber, 0, 0);
        pane.add(txtSetNumber, 1, 0);

        pane.add(lblBranch, 0, 1);
        pane.add(txtBranch, 1, 1);

        pane.add(lblGrade, 0, 2);
        pane.add(txtGrade, 1, 2);


        Button btOk = new Button("OK");
        btOk.setMinWidth(80);
        btOk.setStyle(styleBt);
        btOk.setOnAction(e -> window.close());

        btOk.setOnAction(e -> {
            if(CheckTextFiled.isSeatNumber(txtSetNumber)){
                if(CheckTextFiled.isGrade(txtGrade)){
                   // if(list.search())
                }else{
                    Massage.displayMassage("Error Input","The  Set Number Invalid");
                }
            }else{
                Massage.displayMassage("Error Input","The Grade Invalid ");
            }
        });


        VBox vBox = new VBox(25);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(5, 5, 5, 5));
        vBox.setStyle("-fx-background-color: #ffffff;");

        vBox.getChildren().addAll(pane, btOk);

        window.setScene(new Scene(vBox));
        window.setMinWidth(300);
        window.setMinHeight(280);
        window.setResizable(false);
        window.showAndWait();

    }
}
