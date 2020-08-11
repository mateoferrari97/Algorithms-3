package interfazGrafica;
import interfazGrafica.Eventos.BooleanOptionsEventHandler;
import interfazGrafica.Eventos.MultipleChoiceOptionsEventHandler;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import modelo.*;

import java.util.Arrays;
import java.util.List;


public class Pantalla extends Application{
    Round round;
    Player[] players = new Player[2];


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Kahoot");

        players[0] = new Player();
        players[1] = new Player();
        players[0].setText("PlayerOne:");
        players[1].setText("PlayerTwo:");

        BooleanQuestion question = getBooleanQuesiton();

        this.round = new Round();
        this.round.start(stage, question, this.players);


        stage.show();
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
