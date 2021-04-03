/**
 * @author: Amir Eleyan
 * ID: 1191076
 * At:  30/3/2021  9:23 PM
 */
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
import linkedList.LinkedList;
import linkedList.TRecord;

public abstract class RemoveRecord {
    private static Label lblSetNumber;
    private static TextField txtSetNumber;

    public static void removeRecord(LinkedList<TRecord> list) {


        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Remove Record");
        if (list.isEmpty()) { // list is empty (does not have records)
            Massage.displayMassage("Error", " There are no records to remove from them ");
            window.close();
        } else {

            // style for label
            String styleLbl = "-fx-text-fill:#000000; -fx-background-color:#ffffff;-fx-font-weight: BOLd; -fx-font-size:14; ";

            // style for textField
            String styleTxt = "-fx-background-color: #ffffff; -fx-border-width: 0px0px2px0px; -fx-border-color:" +
                    " #000000; -fx-font-size:17; -fx-text-fill: #000000;";

            // Style for buttons
            String styleBt = "-fx-background-color: #05dfd7; -fx-border-radius:25; -fx-background-radius:25; " +
                    "-fx-border-width: 1; -fx-border-color: #000000; -fx-font-weight: BOLd;-fx-font-size:16;" +
                    "-fx-text-fill: #000000; -fx-font-family: 'Times New Roman'; ";

            // Style for hover buttons
            String styleHoverBt = "-fx-background-color: #ffffff; -fx-border-radius:25; -fx-background-radius:25; " +
                    "-fx-border-width: 1; -fx-border-color: #000000; -fx-font-weight: BOLd;-fx-font-size:16;" +
                    "-fx-text-fill: #000000; -fx-font-family: 'Times New Roman'; ";

            //label for setNumber
            lblSetNumber = new Label(" \nSet Number");
            lblSetNumber.setStyle(styleLbl);

            // txt for get the value of the set number
            txtSetNumber = new TextField();
            txtSetNumber.setPromptText("Enter the set number");
            txtSetNumber.setMaxWidth(180);
            txtSetNumber.setStyle(styleTxt);

            // HBox for label and text
            HBox hBox = new HBox(15);
            hBox.setAlignment(Pos.CENTER);
            hBox.setPadding(new Insets(5, 5, 5, 5));
            hBox.setStyle("-fx-background-color: #ffffff;");
            hBox.getChildren().addAll(lblSetNumber, txtSetNumber);

            // Button for delete record
            Button btRemove = new Button("Delete");
            btRemove.setMinWidth(75);
            btRemove.setStyle(styleBt);
            btRemove.setOnMouseEntered(e -> btRemove.setStyle(styleHoverBt));
            btRemove.setOnMouseExited(e -> btRemove.setStyle(styleBt));

            btRemove.setOnAction(e -> {
                if (!txtSetNumber.getText().trim().isEmpty()) {// the textFiled has data
                    if (CheckTextFiled.isSeatNumber(txtSetNumber)) { // valid setNumber

                        // instance of TRecord to remove it from the list
                        TRecord tRecord = new TRecord(Long.parseLong(txtSetNumber.getText().trim()));

                        if (list.remove(tRecord) != null) { // this record exist in records
                            Massage.displayMassage("Success", txtSetNumber.getText() + " Removed successfully ");
                            txtSetNumber.clear();// clear data from textField
                            TRecordGUI.uploadListToTable(list); // upData the table view

                        } else {// this record does not exist in records
                            Massage.displayMassage("Warning", txtSetNumber.getText() + " Does not exist in records ");
                        }
                    } else {
                        Massage.displayMassage("Error", "The set number is invalid");
                        txtSetNumber.clear();//clear data from textField
                    }
                }

            });

            // button for close the window
            Button btCancel = new Button("Cancel");
            btCancel.setMinWidth(75);
            btCancel.setStyle(styleBt);
            btCancel.setOnMouseEntered(e -> btCancel.setStyle(styleHoverBt));
            btCancel.setOnMouseExited(e -> btCancel.setStyle(styleBt));
            btCancel.setOnAction(e -> window.close());

            // HBox for Button
            HBox hBoxBt = new HBox(55);
            hBoxBt.setAlignment(Pos.CENTER);
            hBoxBt.setPadding(new Insets(5, 5, 5, 5));
            hBoxBt.setStyle("-fx-background-color: #ffffff;");
            hBoxBt.getChildren().addAll(btRemove, btCancel);

            // VBox
            VBox vBox = new VBox(20);
            vBox.setAlignment(Pos.CENTER);
            vBox.setPadding(new Insets(5, 5, 5, 5));
            vBox.setStyle("-fx-background-color: #ffffff;");

            vBox.getChildren().addAll(hBox, hBoxBt);

            window.setScene(new Scene(vBox));
            window.setMinWidth(350);
            window.setMinHeight(200);
            window.setResizable(false);
            window.show();
        }

    }
}
