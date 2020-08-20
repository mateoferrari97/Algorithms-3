package interfazGrafica.Eventos;

import interfazGrafica.EndGame;
import interfazGrafica.Play;
import javafx.animation.Timeline;
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
    private Timeline timeline;
    private Timeline timeline2;
    private Timeline timeline3;

    public BooleanOptionsEventHandler(Option option, Round round, Turn turn, Game game, Play nextPlay, Stage stage, Button button, Timeline timeline, Timeline timeline2, Timeline timeline3) {
        this.round = round;
        this.turn = turn;
        this.option = option;
        this.game = game;
        this.nextPlay = nextPlay;
        this.stage = stage;
        this.button = button;
        this.timeline = timeline;
        this.timeline2 = timeline2;
        this.timeline3 = timeline3;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.turn.addPlayerAnswer(this.option);

        this.button.setDisable(true);

        if (this.turn.getAmountChosenOptions() != this.turn.getAmountCurrentOptions()) {
            return;
        }

        this.timeline.stop();
        this.timeline2.stop();
        this.timeline3.stop();
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


