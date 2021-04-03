/**
 * @author: Amir Eleyan
 * ID: 1191076
 * At: 31/3/2021  3:20 AM
 */
package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import linkedList.Calculations;
import linkedList.LinkedList;
import linkedList.Node;
import linkedList.TRecord;

public abstract class ViewTopTen {
    private static TableView<TRecord> recordTableView;

    public static void TopTenRecords(LinkedList<TRecord> list) {

        if (list.isEmpty()) { // no data in the list
            Massage.displayMassage("Error", " There are no records to display Top Ten Grads ");
        } else {

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("TOP TEN STUDENTS");

            // Style for buttons
            String styleBt = "-fx-background-color: #05dfd7; -fx-border-radius:25; -fx-background-radius:25; " +
                    "-fx-border-width: 1; -fx-border-color: #000000; -fx-font-weight: BOLd;-fx-font-size:16;" +
                    "-fx-text-fill: #000000; -fx-font-family: 'Times New Roman'; ";

            // Style for hover buttons
            String styleHoverBt = "-fx-background-color: #ffffff; -fx-border-radius:25; -fx-background-radius:25; " +
                    "-fx-border-width: 1; -fx-border-color: #000000; -fx-font-weight: BOLd;-fx-font-size:16;" +
                    "-fx-text-fill: #000000; -fx-font-family: 'Times New Roman'; ";


            //VBox
            VBox vBox = new VBox(15);
            vBox.setPadding(new Insets(5, 5, 10, 5));
            vBox.setAlignment(Pos.CENTER);
            vBox.setStyle("-fx-background-color: #ffffff;");

            // Button for close the window
            Button btClose = new Button("Close");
            btClose.setMinWidth(90);
            btClose.setStyle(styleBt);
            btClose.setOnMouseEntered(e -> btClose.setStyle(styleHoverBt));
            btClose.setOnMouseExited(e -> btClose.setStyle(styleBt));
            btClose.setOnAction(e -> window.close());

            // Label for top ten student
            Label lblTopTen = new Label("Top Ten Students");
            lblTopTen.setFont(Font.font("Times New Roman", FontWeight.BOLD, 36));
            lblTopTen.setPadding(new Insets(5, 0, 15, 0));
            lblTopTen.setAlignment(Pos.CENTER);
            lblTopTen.setStyle("-fx-background-color: #05dfd7; -fx-border-radius:50;" +
                    " -fx-background-radius:50; -fx-text-fill: #000000; ");
            lblTopTen.setMinWidth(600);


            // get the top ten students for this list
            Calculations.topTenGrads(list, Calculations.topTen);
            vBox.getChildren().addAll(lblTopTen, tRecordTableView(Calculations.topTen), btClose);

            window.setScene(new Scene(vBox));
            window.setMinWidth(450);
            window.setMinHeight(250);
            window.setResizable(false);
            window.show();
        }

    }

    /**
     * Table view to display top ten students
     */
    public static TableView<TRecord> tRecordTableView(LinkedList<TRecord> list) {

        recordTableView = new TableView<>();
        recordTableView.setEditable(false);
        recordTableView.setMinWidth(580);
        recordTableView.setMinHeight(485);
        recordTableView.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000; " +
                "-fx-border-width:2; -fx-font-family: 'Times New Roman'; -fx-font-size:17;" +
                " -fx-text-fill: #000000; -fx-font-weight: BOLd; ");

        TableColumn<TRecord, Long> setNumber = new TableColumn<>("Set Number"); // first column (setNumber)
        setNumber.setMinWidth(280);
        setNumber.setSortable(false);
        setNumber.setResizable(false);
        setNumber.setCellValueFactory(new PropertyValueFactory<>("seatNum"));

        TableColumn<TRecord, String> branch = new TableColumn<>("Branch");// second column (branch)
        branch.setMinWidth(150);
        branch.setSortable(false);
        branch.setResizable(false);
        branch.setCellValueFactory(new PropertyValueFactory<>("branch"));

        TableColumn<TRecord, Float> grade = new TableColumn<>("Grade");
        grade.setMinWidth(150);
        grade.setSortable(false);
        grade.setResizable(false);
        grade.setCellValueFactory(new PropertyValueFactory<>("grade")); // third  column(grade)
        recordTableView.getColumns().addAll(setNumber, branch, grade);

        // upload data to the table view
        if (!list.isEmpty()) {
            recordTableView.getItems().clear(); // clear data in the table view
            Node<TRecord> curr = list.getHead();
            while (curr != null) {
                recordTableView.getItems().add(curr.getData()); // upload data to the table
                curr = curr.getNext();
            }
        }

        return recordTableView;
    }
}
