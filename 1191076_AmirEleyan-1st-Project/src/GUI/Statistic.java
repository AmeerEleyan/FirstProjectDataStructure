/**
 * @author: Amir Eleyan
 * ID: 1191076
 * Time: 28/3/2021  2:55 AM
 */
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import linkedList.Calculations;
import linkedList.LinkedList;
import linkedList.TRecord;

public abstract class Statistic {
    private static Label lblGrade;
    private static TextField txtGrade;
    private static Text txtStat;
    protected static float grade;

    public static void statAboveGrade(LinkedList<TRecord> list) {

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


        HBox hBox = new HBox(30);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(5, 5, 5, 5));
        hBox.setStyle("-fx-background-color: #ffffff;");

        Button btCancel = new Button("Cancel");
        btCancel.setMinWidth(90);
        btCancel.setStyle(styleBt);
        btCancel.setOnAction(e -> window.close());

        Button btCalculate = new Button("Calculate");
        btCalculate.setMinWidth(90);
        btCalculate.setStyle(styleBt);

        hBox.getChildren().addAll(btCalculate, btCancel);


        btCalculate.setOnAction(e -> {
            if (!txtStat.getText().isEmpty()) {
                if (CheckTextFiled.isGrade(txtGrade)) {
                    if (list.isEmpty()) {
                        Massage.displayMassage("Warning", " There's no data to calculate ");
                    } else {
                        float total = Calculations.numberOfRecordAboveAGrade(list, Float.parseFloat(txtGrade.getText()));
                        grade = total;
                        txtStat.setText(" Number of students achieving a mark " + txtGrade.getText() +
                                " or more: " + total + "And their percentage: "
                                + String.format("%.2f", ((total / list.length()) * 100)) + "%");
                    }
                } else {
                    Massage.displayMassage("Warning", " Invalid Grade");
                }
            }

        });


        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(5, 5, 5, 5));
        vBox.setStyle("-fx-background-color: #ffffff;");
        vBox.getChildren().addAll(pane, txtStat, hBox);


        window.setScene(new Scene(vBox));
        window.setMinWidth(500);
        window.setMinHeight(280);
        window.setResizable(false);
        window.show();
    }
}
