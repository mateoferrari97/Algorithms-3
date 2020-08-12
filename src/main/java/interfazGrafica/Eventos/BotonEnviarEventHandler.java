package interfazGrafica.Eventos;

import interfazGrafica.Play;
import interfazGrafica.Round;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Game;
import modelo.Player;

import java.util.List;

public class BotonEnviarEventHandler implements EventHandler<ActionEvent> {

    private Game game;
    private Play nextScene;
    private TextField textField;
    private Label label;
    private Player player;
    private Stage stage;
    private Integer playersSize = 0;

    public BotonEnviarEventHandler(TextField textField, Label label, Player player, Play nextScene, Stage stage, Game game) {
        this.textField = textField;
        this.label = label;
        this.player = player;
        this.nextScene = nextScene;
        this.stage = stage;
        this.game = game;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        if (this.textField.getText().trim().equals("")) {

            this.label.setText("Debe ingresar un texto");
            this.label.setTextFill(Color.web("#FF0000"));
            this.textField.requestFocus();

        } else {

            if (playersSize < 2) {
                this.player.setText(textField.getText());
                this.textField.clear();
                this.playersSize++;
            }

            if(playersSize == 2){
                this.nextScene.start(this.game);
            }


        }

    }
}
