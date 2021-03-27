package linkedList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class Statistic {
    private static Label lblGrade;
    private static TextField txtGrade;
    private static Text txtStat;

    public static void statAboveGrade() {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Statistic");

        String styleTxt = "-fx-background-color: #ffffff; -fx-border-width: 0px0px2px0px; -fx-border-color: #000000; -fx-font-size:15; -fx-text-fill: #000000;";
        String styleLbl = "-fx-text-fill:#000000; -fx-background-color:#ffffff;-fx-font-weight: BOLd; -fx-font-size:14; ";
        String styleBt = "-fx-background-color: #f88f01; -fx-border-radius:25; -fx-background-radius:25; " +
                "-fx-border-width: 1; -fx-border-color: #000000; -fx-font-weight: BOLd;-fx-font-size:15;" + "-fx-text-fill: #000000; -fx-font-family: 'Times New Roman'; ";

        GridPane pane = new GridPane();
        pane.setStyle("-fx-background-color: #ffffff;");
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(15);
        pane.setHgap(8);
        pane.setPadding(new Insets(5, 5, 5, 5));

        lblGrade = new Label("Grade");
        lblGrade.setStyle(styleLbl);

        txtGrade = new TextField();
        txtGrade = new TextField();
        txtGrade.setPromptText("Enter a specific grade");
        txtGrade.setMaxWidth(180);
        txtGrade.setStyle(styleTxt);

        txtStat = new Text();
        txtStat.setStyle(styleTxt);

        pane.add(lblGrade, 0, 0);
        pane.add(txtGrade, 1, 0);

        ImageView img = new ImageView(new Image("calculate.png"));
        img.setFitWidth(22);
        img.setFitHeight(22);


        Button btCalculate = new Button("Calculate");
        btCalculate.setMinWidth(60);
        btCalculate.setStyle(styleBt);
        btCalculate.setOnAction(e -> txtStat.setText("Number of students achieving a mark 50 or more: 500 students\nPercentage: 85%"));

        btCalculate.setContentDisplay(ContentDisplay.LEFT);
        btCalculate.setGraphic(img);

        VBox vBox = new VBox(25);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(5, 5, 5, 5));
        vBox.setStyle("-fx-background-color: #ffffff;");
        vBox.getChildren().addAll(pane, txtStat, btCalculate);


        window.setScene(new Scene(vBox));
        window.setMinWidth(500);
        window.setMinHeight(280);
        window.setResizable(false);
        window.showAndWait();
    }
}
