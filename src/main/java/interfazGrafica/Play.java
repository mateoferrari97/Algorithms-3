package interfazGrafica;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import modelo.Game;

public class Play {

    private final Stage stage;

    public Play(Stage stage) {
        this.stage = stage;
    }

    public void start(Game game){
        Label algo = new Label("cambiaste de escena");
        HBox dasds = new HBox(algo);
        Scene escena = new Scene(dasds);

        this.stage.setScene(escena);
    }
}
