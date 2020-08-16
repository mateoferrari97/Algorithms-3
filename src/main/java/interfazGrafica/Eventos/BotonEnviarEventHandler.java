package interfazGrafica.Eventos;

import interfazGrafica.Play;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import modelo.Game;
import modelo.Player;

public class BotonEnviarEventHandler implements EventHandler<ActionEvent> {

    private Game game;
    private Play nextScene;
    private TextField textField;
    private Label label;
    private Integer amountOfPlayers = 0;

    public BotonEnviarEventHandler(TextField textField, Label label, Play nextScene, Game game) {
        this.textField = textField;
        this.label = label;
        this.nextScene = nextScene;
        this.game = game;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        if (this.textField.getText().trim().equals("")) {

            this.label.setText("Debe ingresar un texto");
            this.label.setTextFill(Color.web("#FF0000"));
            this.textField.requestFocus();

        } else {
            Player player = this.game.getNextPlayer();
            if (amountOfPlayers < this.game.getPlayers().length) {
                amountOfPlayers++;
                player.setName(textField.getText());
                this.textField.clear();
                this.textField.requestFocus();
                if(amountOfPlayers == this.game.getPlayers().length){
                    this.nextScene.start(this.game);
                }
            }

        }

    }
}
