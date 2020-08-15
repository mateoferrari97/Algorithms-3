package interfazGrafica;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class EndGame extends HBox {
    Stage stage;


    public EndGame(Stage stage) {
        super();

        this.stage = stage;

        Label finish = new Label("END OF GAME");
        this.getChildren().add(finish);
        Scene end = new Scene(this);
        stage.setScene(end);
    }
}
