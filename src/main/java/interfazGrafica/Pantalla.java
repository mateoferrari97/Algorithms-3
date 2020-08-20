package interfazGrafica;

import javafx.application.Application;

import javafx.stage.Stage;

import modelo.game.Game;
import modelo.options.CorrectOptionScorer;
import modelo.options.IncorrectOptionScorer;
import modelo.options.Option;
import modelo.questions.BooleanQuestion;

import modelo.scorers.BooleanScorer;
import modelo.scorers.QuestionScorer;


import java.util.Arrays;
import java.util.List;

import static constantes.Constantes.GAME_TITLE;


public class Pantalla extends Application{
    private Game game = new Game();

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(GAME_TITLE);
        this.game.init();
        Play nextScene = new Play(stage);
        PlayerNames.start(stage, this.game,nextScene);
        //GroupChoice.start(stage, this.game, nextScene);
        stage.show();
    }
}
