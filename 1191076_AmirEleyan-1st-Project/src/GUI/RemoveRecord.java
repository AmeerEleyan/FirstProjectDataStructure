package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class RemoveRecord {
    private static Label lblSetNumber;
    private static TextField txtSetNumber;

    public static void removeRecord() {

        String styleLbl = "-fx-text-fill:#000000; -fx-background-color:#ffffff;-fx-font-weight: BOLd; -fx-font-size:14; ";
        String styleTxt = "-fx-background-color: #ffffff; -fx-border-width: 0px0px2px0px; -fx-border-color: #000000; -fx-font-size:15; -fx-text-fill: #000000;";
        String styleBt = "-fx-background-color: #f88f01; -fx-border-radius:25; -fx-background-radius:25; " +
                "-fx-border-width: 1; -fx-border-color: #000000; -fx-font-weight: BOLd;-fx-font-size:15;" + "-fx-text-fill: #000000; -fx-font-family: 'Times New Roman'; ";

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Remove Record");


        lblSetNumber = new Label("Set Number");
        lblSetNumber.setStyle(styleLbl);

        txtSetNumber = new TextField();
        txtSetNumber.setPromptText("Enter the set number");
        txtSetNumber.setMaxWidth(180);
        txtSetNumber.setStyle(styleTxt);

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(5, 5, 5, 5));
        hBox.setStyle("-fx-background-color: #ffffff;");
        hBox.getChildren().addAll(lblSetNumber, txtSetNumber);

        Button btRemove = new Button("Delete");
        btRemove.setStyle(styleBt);
        btRemove.setMinWidth(60);

        VBox vBox = new VBox(15);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(5, 5, 5, 5));
        vBox.setStyle("-fx-background-color: #ffffff;");

        vBox.getChildren().addAll(hBox, btRemove);

        window.setScene(new Scene(vBox));
        window.setMinWidth(300);
        window.setMinHeight(180);
        window.setResizable(false);
        window.showAndWait();

    }
}
