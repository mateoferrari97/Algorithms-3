package interfazGrafica.Events;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import modelo.game.Round;
import modelo.game.Turn;
import modelo.multiplicators.Multiplicator;

import java.util.List;

public class MultiplicatorEventHandler implements EventHandler<ActionEvent> {
    private final Multiplicator multiplicator;
    private final Turn turn;
    private final Round round;
    private final Button button;
    private final HBox multiplicatorBox;

    public MultiplicatorEventHandler(Multiplicator aMultiplicator, Turn turn, Round round, Button button, HBox multiplicatorBox) {
        this.multiplicator = aMultiplicator;
        this.turn = turn;
        this.round = round;
        this.button = button;
        this.multiplicatorBox = multiplicatorBox;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.multiplicatorBox.getChildren().clear();
        this.multiplicatorBox.getChildren().add(this.button);
        this.button.setDisable(true);
        this.round.multiplicate(this.multiplicator, this.turn);
    }
}
