package interfazGrafica.Events;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modelo.game.Round;
import modelo.game.Turn;
import modelo.multiplicators.Multiplicator;

public class MultiplicatorEventHandler implements EventHandler<ActionEvent> {
    private final Multiplicator multiplicator;
    private final Turn turn;
    private final Round round;

    public MultiplicatorEventHandler(Multiplicator aMultiplicator, Turn turn, Round round) {
        this.multiplicator = aMultiplicator;
        this.turn = turn;
        this.round = round;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.round.multiplicate(this.multiplicator, this.turn);
    }
}
