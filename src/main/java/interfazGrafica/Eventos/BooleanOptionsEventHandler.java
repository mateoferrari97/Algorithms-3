package interfazGrafica.Eventos;

import interfazGrafica.Turn;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import modelo.*;

public class BooleanOptionsEventHandler implements EventHandler<ActionEvent> {
    private Turn turn;
    private Option answer;

    public BooleanOptionsEventHandler(Option answer, Turn turn) {
        this.answer = answer;
        this.turn = turn;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
/*        this.turn.addAnswer(this.answer);
        this.turn.finish();
        this.round.next();*/
    }
}
