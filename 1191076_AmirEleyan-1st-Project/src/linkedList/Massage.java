package linkedList;

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

        Label lbl = new Label(massage);
        lbl.setStyle("-fx-text-fill:#000000; -fx-background-color:#ffffff; -fx-font-size:15; -fx-font-family: 'Arial';");

        ImageView imgWarning = new ImageView(new Image("warning.png"));
        imgWarning.setFitWidth(30);
        imgWarning.setFitHeight(30);

        HBox hBox = new HBox(5);
        hBox.setPadding(new Insets(5, 5, 5, 5));
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-background-color: #ffffff;");
        hBox.getChildren().addAll(imgWarning, lbl);

        Button closeButton = new Button("OK");
        closeButton.setStyle("-fx-background-color: #f88f01; -fx-border-radius:25; -fx-background-radius:25; " +
                "-fx-border-width: 1; -fx-border-color: #000000; -fx-font-weight: BOLd;-fx-font-size:15;" + "-fx-text-fill: #000000; -fx-font-family: 'Times New Roman'; ");
        closeButton.setMinWidth(70);
        closeButton.setOnAction(e -> window.close());

        VBox vBox = new VBox(15);
        vBox.getChildren().addAll(hBox, closeButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: #ffffff;");
        vBox.setMinWidth(400);
        vBox.setMinHeight(100);

        window.setScene(new Scene(vBox));
        window.setResizable(false);
        window.showAndWait();
    }

}
