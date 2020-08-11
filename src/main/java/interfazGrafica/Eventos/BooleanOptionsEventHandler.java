package interfazGrafica.Eventos;

import interfazGrafica.Turn;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modelo.*;

import java.util.ArrayList;
import java.util.List;


public class BooleanOptionsEventHandler implements EventHandler<ActionEvent> {
    private Turn turn;
    private Question question;
    private Player player;
    private Option chosenOption;
    private Label playerPoints;


    public BooleanOptionsEventHandler(Option option, Label playerPoints, Player player, BooleanQuestion question, Turn turn) {
        this.chosenOption = option;
        this.playerPoints = playerPoints;
        this.player = player;
        this.question = question;
        this.turn = turn;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        List<Option> options = new ArrayList();
        options.add(this.chosenOption);
        this.question.score(this.player, options);
        playerPoints.setText(player.getPoints().toString());
        turn.startScene();

    }
}
