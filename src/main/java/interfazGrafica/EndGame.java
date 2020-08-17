package interfazGrafica;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import static constantes.Constantes.END_GAME_LABEL;

public class EndGame extends HBox {
    Stage stage;


    public EndGame(Stage stage) {
        super();

        this.stage = stage;

        Label finish = new Label(END_GAME_LABEL);
        this.getChildren().add(finish);
        Scene end = new Scene(this);
        stage.setScene(end);
    }
}
