package interfazGrafica.Eventos;

import interfazGrafica.EndGame;
import interfazGrafica.Play;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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

    public BooleanOptionsEventHandler(Option option, Round round, Turn turn, Game game, Play nextPlay, Stage stage) {
        this.round = round;
        this.turn = turn;
        this.option = option;
        this.game = game;
        this.nextPlay = nextPlay;
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.turn.addPlayerAnswer(this.option);

        if (this.turn.getAmountChosenOptions() != this.turn.getAmountCurrentOptions()) {
            return;
        }

        this.turn.finish();

        if(game.getNextRound() == null){
            EndGame end = new EndGame(stage);
        }else{
            nextPlay.start(game);
        }
    }
}


