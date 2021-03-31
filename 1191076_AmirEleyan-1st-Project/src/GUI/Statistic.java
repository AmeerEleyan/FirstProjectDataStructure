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
    protected static int totalGrade;

    public static void statAboveGrade(LinkedList<TRecord> list) {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Statistic");

        // style for textFiled
        String styleTxt = "-fx-background-color: #ffffff; -fx-border-width: 0px0px2px0px;" +
                " -fx-border-color: #000000; -fx-font-size:17; -fx-text-fill: #000000;";

        // style for label
        String styleLbl = "-fx-text-fill:#000000; -fx-background-color:#ffffff;-fx-font-weight:" +
                " BOLd; -fx-font-size:16; ";

        // Style for buttons
        String styleBt = "-fx-background-color: #05dfd7; -fx-border-radius:25; -fx-background-radius:25; " +
                "-fx-border-width: 1; -fx-border-color: #000000; -fx-font-weight: BOLd;-fx-font-size:17;" +
                "-fx-text-fill: #000000; -fx-font-family: 'Times New Roman'; ";

        // Style for hover buttons
        String styleHoverBt = "-fx-background-color: #ffffff; -fx-border-radius:25; -fx-background-radius:25; " +
                "-fx-border-width: 1; -fx-border-color: #000000; -fx-font-weight: BOLd;-fx-font-size:17;" +
                "-fx-text-fill: #000000; -fx-font-family: 'Times New Roman'; ";

        // Pane for arrange label and text filed
        GridPane pane = new GridPane();
        pane.setStyle("-fx-background-color: #ffffff;");
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(15);
        pane.setHgap(8);
        pane.setPadding(new Insets(5, 5, 5, 5));

        lblGrade = new Label("\nGrade ");
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


        // HBox
        HBox hBox = new HBox(50);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(5, 5, 5, 5));
        hBox.setStyle("-fx-background-color: #ffffff;");

        // Button fot close window
        Button btCancel = new Button("Cancel");
        btCancel.setMinWidth(90);
        btCancel.setStyle(styleBt);
        btCancel.setOnMouseEntered(e -> btCancel.setStyle(styleHoverBt));
        btCancel.setOnMouseExited(e -> btCancel.setStyle(styleBt));
        btCancel.setOnAction(e -> window.close());

        // Button for calculate
        Button btCalculate = new Button("Calculate");
        btCalculate.setMinWidth(90);
        btCalculate.setStyle(styleBt);
        btCalculate.setOnMouseEntered(e -> btCalculate.setStyle(styleHoverBt));
        btCalculate.setOnMouseExited(e -> btCalculate.setStyle(styleBt));

        btCalculate.setOnAction(e -> {
            if (!txtGrade.getText().trim().isEmpty()) { // the testField has data
                if (CheckTextFiled.isGrade(txtGrade)) { // valid grade
                    if (list.isEmpty()) { // no data in list
                        Massage.displayMassage("Warning", " There's no data to calculate ");
                    } else {
                        // the calculations
                        int total = Calculations.numberOfRecordAboveAGrade(list, Float.parseFloat(txtGrade.getText().trim()));
                        totalGrade = total;
                        txtStat.setText("Number of students achieving\na mark " + txtGrade.getText() +
                                " or more: " + total + "\nAnd their percentage: "
                                + String.format("%.2f", (((float) total / list.length()) * 100)) + "%");
                    }
                } else {
                    Massage.displayMassage("Warning", " The grade is invalid ");
                }
            }
        });


        // add the components to the HBox
        hBox.getChildren().addAll(btCalculate, btCancel);

        // VBox
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
