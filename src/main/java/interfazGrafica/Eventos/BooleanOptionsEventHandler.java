package interfazGrafica.Eventos;

import interfazGrafica.EndGame;
import interfazGrafica.Play;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.stage.Stage;
import modelo.*;

import modelo.options.Option;


public class BooleanOptionsEventHandler implements EventHandler<ActionEvent> {
    private final Play nextScene;
    private final Stage stage;
    private Round round;
    private Option option;
    private Game game;
    private Turn turn;

    public BooleanOptionsEventHandler(Option option, Round round, Turn turn, Game game, Play nextScene, Stage stage) {
        this.round = round;
        this.turn = turn;
        this.option = option;
        this.game = game;
        this.nextScene = nextScene;
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.turn.addPlayerAnswer(this.option);
        this.turn.finish();

        //this.round = this.game.getNextRound();
        if(game.getNextRound() == null){
            EndGame end = new EndGame(stage);
        }else{
            nextScene.start(game);
        }


        //this.turn = this.round.getTurn();
    }
}
