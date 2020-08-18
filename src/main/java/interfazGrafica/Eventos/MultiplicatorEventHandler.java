package interfazGrafica.Eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modelo.game.Turn;
import modelo.multiplicators.Multiplicator;

public class MultiplicatorEventHandler implements EventHandler<ActionEvent> {
    private final Multiplicator multiplicator;
    private final Turn turn;

    public MultiplicatorEventHandler(Multiplicator aMultiplicator, Turn turn) {
        this.multiplicator = aMultiplicator;
        this.turn = turn;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.turn.multiplicate(this.multiplicator);
    }
}
