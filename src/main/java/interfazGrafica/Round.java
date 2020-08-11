package interfazGrafica;

import interfazGrafica.Eventos.MultipleChoiceOptionsEventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modelo.*;

public class Round {
    Turn[] turns = new Turn[2];


    public Round() {
    }

    public void start(Stage stage, BooleanQuestion question, Player[] players) {

        //----------------------------------------------------------------------
        //--------------------------Principal Container-------------------------
        //----------------------------------------------------------------------
        VBox contenedorPrincipal = new VBox();
        contenedorPrincipal.setSpacing(100);
        contenedorPrincipal.setPadding(new Insets(300));

        for(int i = 0; i <= 1; i++) {
            turns[i] = new Turn(players[i], contenedorPrincipal);
        }

        Scene escena = new Scene(contenedorPrincipal);

        //----------------------------------------------------------------------
        //--------------------------PlayerOne and points labels-----------------
        //----------------------------------------------------------------------


        turns[0].createPlayerLabels();
        turns[1].createPlayerLabels();

        //----------------------------------------------------------------------
        //--------------------------QuestionTwo text label----------------------
        //----------------------------------------------------------------------
        Label questionText = new Label();
        questionText.setTextFill(Color.BLUE);
        questionText.setFont(new Font("Arial", 30));
        questionText.setStyle("-fx-border-color: black;");

        fillQuestionText(question, questionText);

        turns[0].setQuestionLabel(questionText);
        turns[1].setQuestionLabel(questionText);


        //----------------------------------------------------------------------
        //--------------------------Buttons creation----------------------------
        //----------------------------------------------------------------------
        turns[0].setButtons(question);
        turns[1].setButtons(question);

        turns[0].fillWithEvents(question, turns[1]);
        turns[1].fillWithEvents(question, turns[0]);


        turns[0].startScene();
        stage.setScene(escena);


    }

    private void fillWithEvents(Button[] buttons, MultipleChoiceQuestion question, Label playerPoints, Player player) {
        int i = 0;
        for(Button aButton : buttons) {
            MultipleChoiceOptionsEventHandler multipleChoiceOptionsEventHandler = new MultipleChoiceOptionsEventHandler(question.getOptions().get(i), playerPoints, player, question);
            aButton.setOnAction(multipleChoiceOptionsEventHandler);
            i++;
        }
    }


    private void fillQuestionText(Question question, Label questionText) {
        questionText.setText(question.getText());
    }


}
