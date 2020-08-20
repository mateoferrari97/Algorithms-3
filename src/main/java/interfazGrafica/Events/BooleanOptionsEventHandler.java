package interfazGrafica.Events;

import interfazGrafica.EndGame;
import interfazGrafica.Play;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Button;
import javafx.stage.Stage;

import modelo.game.Game;
import modelo.game.Round;
import modelo.game.Turn;
import modelo.options.Option;


public class BooleanOptionsEventHandler implements EventHandler<ActionEvent> {
    private final Play nextPlay;
    private final Stage stage;
    private Round round;
    private Option option;
    private Game game;
    private Turn turn;
    private Button button;

    public BooleanOptionsEventHandler(Option option, Round round, Turn turn, Game game, Play nextPlay, Stage stage, Button button) {
        this.round = round;
        this.turn = turn;
        this.option = option;
        this.game = game;
        this.nextPlay = nextPlay;
        this.stage = stage;
        this.button = button;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.turn.addPlayerAnswer(this.option);

        this.button.setDisable(true);

        if (this.turn.getAmountChosenOptions() != this.turn.getAmountCurrentOptions()) {
            return;
        }

        this.turn.finish();

        if(game.getNextRound() == null){
            this.round.finish();
            new EndGame(stage, game);
        }else{
            this.round.finish();
            nextPlay.start(game);
        }
    }
}


