package interfazGrafica;
import interfazGrafica.Eventos.BooleanOptionsEventHandler;
import interfazGrafica.Eventos.MultipleChoiceOptionsEventHandler;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import modelo.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Pantalla extends Application{
    private Game game = new Game();

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.game.init();
        this.init();
        PlayerNames.start(stage, this.game);
        stage.show();
    }

    private void init(Stage stage) {
        stage.setTitle("Kahoot");
    }


    private BooleanQuestion getBooleanQuesiton() {
        List<Option> options = Arrays.asList(
                new Option("SI", new CorrectOptionScorer()),
                new Option("NO", new IncorrectOptionScorer()));

        QuestionScorer scorer = new BooleanScorer();
        BooleanQuestion question = new BooleanQuestion("Vamos a aprobar algoritmos 3?", options, scorer);

        return question;
    }

}
