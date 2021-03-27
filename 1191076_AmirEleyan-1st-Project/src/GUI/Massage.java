/**
 * @author: Amir Eleyan
 * ID: 1191076
 * Time: 27/3/2021   7:35 PM
 */
package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class Massage {

    public static void displayMassage(String title, String massage) {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        String btStyle = "-fx-background-color:f88f01; -fx-background-radius:25;" +
                "-fx-font-size:15; -fx-border-radius:25;" +
                " -fx-border-width: 1; -fx-border-color: 'black'; -fx-text-fill: #000000; -fx-font-family:" +
                " 'Calisto MT'; -fx-font-weight: BOLd; ";

        String styleHover = "-fx-background-color:#ffffff; -fx-background-radius:25;" +
                "-fx-font-size:15; -fx-border-radius:25;-fx-border-color: #000000;" +
                " -fx-border-width: 1; -fx-text-fill: #000000; -fx-font-family: " +
                "'Calisto MT'; -fx-font-weight: BOLd;";


        Label lbl = new Label(massage);
        lbl.setStyle("-fx-text-fill:#000000; -fx-background-color:#ffffff; -fx-font-size:15; -fx-font-family: 'Arial';");
        lbl.setAlignment(Pos.CENTER);

        ImageView imgWarning = new ImageView(new Image("icons/warning.png"));
        imgWarning.setFitWidth(32);
        imgWarning.setFitHeight(32);

        HBox hBox = new HBox(8);
        hBox.setPadding(new Insets(5, 5, 5, 5));
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-background-color: #ffffff;");
        hBox.getChildren().addAll(imgWarning, lbl);

        Button closeButton = new Button("OK");
        closeButton.setStyle(btStyle);

        // To change the design of the button when placing a mouse arrow on it
        closeButton.setOnMouseEntered(e -> {
            closeButton.setStyle(styleHover);
        });
        // To change the design of the button when the mouse arrow is removed from it
        closeButton.setOnMouseExited(e -> {
            closeButton.setStyle(btStyle);
        });

        closeButton.setMinWidth(75);
        closeButton.setOnAction(e -> window.close());

        VBox vBox = new VBox(12);
        vBox.getChildren().addAll(hBox, closeButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: #ffffff;");
        vBox.setMinWidth(400);
        vBox.setMinHeight(100);

        window.setScene(new Scene(vBox));
        window.setResizable(false);
        window.show();
    }

}
