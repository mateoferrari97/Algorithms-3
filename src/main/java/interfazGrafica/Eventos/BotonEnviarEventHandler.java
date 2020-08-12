package interfazGrafica.Eventos;

import interfazGrafica.Round;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Player;

import java.util.List;

public class BotonEnviarEventHandler implements EventHandler<ActionEvent> {

    private Text mensaje;
    private TextField textField;
    private Label label;
    private List<Player> players;
    private Stage stage;
    private Round round;

    public BotonEnviarEventHandler(Text mensaje, TextField textField, Label label, List<Player> players, Stage stage, Round round) {
        this.mensaje = mensaje;
        this.textField = textField;
        this.label = label;
        this.players = players;
        this.stage = stage;
        this.round = round;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        if (this.textField.getText().trim().equals("")) {

            this.label.setText("Debe ingresar un texto");
            this.label.setTextFill(Color.web("#FF0000"));
            this.textField.requestFocus();

        } else {

            if (players.size() < 2) {
                players.add(new Player(textField.getText()));
                mensaje.setText("Jugador " + String.valueOf(players.size() + 1) + " ingrese su nombre");
                this.textField.clear();
                if (players.size() == 2) {
                    this.round.start(stage, this.players);
                }
            }

        }

    }
}
