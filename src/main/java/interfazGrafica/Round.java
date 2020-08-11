package interfazGrafica;

import interfazGrafica.Eventos.BooleanOptionsEventHandler;
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

import java.util.Arrays;
import java.util.List;

public class Round {


    public Round() {
    }

    public void start(Stage stage, BooleanQuestion question, Player[] players) {
        //----------------------------------------------------------------------
        //--------------------------PlayerOne and points labels-----------------
        //----------------------------------------------------------------------

        Label playerText = new Label();
        playerText.setTextFill(Color.BLACK);
        playerText.setFont(new Font("Arial", 30));

        Label playerPoints = new Label();
        playerPoints.setTextFill(Color.BLACK);
        playerPoints.setFont(new Font("Arial", 30));

        playerText.setText(players[0].getText());
        Integer auxPlayerPoints = players[0].getPoints();
        playerPoints.setText(auxPlayerPoints.toString());

        HBox playerContenedorHorizontal = new HBox(playerText, playerPoints);
        playerContenedorHorizontal.setSpacing(10);


        //----------------------------------------------------------------------
        //--------------------------PlayerTwo and points labels-----------------
        //----------------------------------------------------------------------

        Label playerTextTwo = new Label();
        playerTextTwo.setTextFill(Color.BLACK);
        playerTextTwo.setFont(new Font("Arial", 30));

        Label playerPointsTwo = new Label();
        playerPointsTwo.setTextFill(Color.BLACK);
        playerPointsTwo.setFont(new Font("Arial", 30));

        playerTextTwo.setText(players[1].getText());
        Integer auxPlayerPointsTwo = players[1].getPoints();
        playerPointsTwo.setText(auxPlayerPointsTwo.toString());

        HBox playerContenedorHorizontalTwo = new HBox(playerTextTwo, playerPointsTwo);
        playerContenedorHorizontalTwo.setSpacing(10);

        //----------------------------------------------------------------------
        //--------------------------Question text label-------------------------
        //----------------------------------------------------------------------
        Label questionText = new Label();
        questionText.setTextFill(Color.BLUE);
        questionText.setFont(new Font("Arial", 30));
        questionText.setStyle("-fx-border-color: black;");

        fillQuestionText(question, questionText);

        //----------------------------------------------------------------------
        //--------------------------QuestionTwo text label-------------------------
        //-------------------------------------------------------------------

        Label questionTextTwo = new Label();
        questionTextTwo.setTextFill(Color.BLUE);
        questionTextTwo.setFont(new Font("Arial", 30));
        questionTextTwo.setStyle("-fx-border-color: black;");

        fillQuestionText(question, questionTextTwo);

        //----------------------------------------------------------------------
        //--------------------------Buttons creation----------------------------
        //----------------------------------------------------------------------
        Button[] buttons = getQuestionOptions(question);


        HBox contenedorHorizontal = new HBox();
        fillContainer(contenedorHorizontal, buttons);
        contenedorHorizontal.setSpacing(400);

        //----------------------------------------------------------------------
        //--------------------------ButtonsTwo creation----------------------------
        //----------------------------------------------------------------------
        Button[] buttonsTwo = getQuestionOptions(question);


        HBox contenedorHorizontalTwo = new HBox();
        fillContainer(contenedorHorizontalTwo, buttonsTwo);
        contenedorHorizontalTwo.setSpacing(400);

        //----------------------------------------------------------------------
        //--------------------------PrincipalTwo Container-------------------------
        //----------------------------------------------------------------------
        VBox contenedorPrincipalTwo = new VBox(playerContenedorHorizontalTwo, questionTextTwo, contenedorHorizontalTwo);
        contenedorPrincipalTwo.setSpacing(100);
        contenedorPrincipalTwo.setPadding(new Insets(300));
        Scene escenaTwo = new Scene(contenedorPrincipalTwo);

        fillWithEvents(buttons, question, playerPoints, players[0], stage, escenaTwo);

        //----------------------------------------------------------------------
        //--------------------------Principal Container-------------------------
        //----------------------------------------------------------------------
        VBox contenedorPrincipal = new VBox(playerContenedorHorizontal, questionText, contenedorHorizontal);
        contenedorPrincipal.setSpacing(100);
        contenedorPrincipal.setPadding(new Insets(300));

        Scene escena = new Scene(contenedorPrincipal);
        stage.setScene(escena);

        fillWithEvents(buttonsTwo, question, playerPointsTwo, players[1], stage, escena);


        //fillWithEvents(buttons, question, playerPointsTwo, players[1], playerNumber, stage, escena);

    }

    private void fillWithEvents(Button[] buttons, BooleanQuestion question, Label playerPoints, Player player, Stage stage, Scene escena) {
        int i = 0;
        for(Button aButton : buttons) {
            BooleanOptionsEventHandler booleanOptionsEventHandler = new BooleanOptionsEventHandler(question.getOptions().get(i), playerPoints, player, question, stage, escena);
            aButton.setOnAction(booleanOptionsEventHandler);
            i++;
        }
    }

    private void fillWithEvents(Button[] buttons, MultipleChoiceQuestion question, Label playerPoints, Player player) {
        int i = 0;
        for(Button aButton : buttons) {
            MultipleChoiceOptionsEventHandler multipleChoiceOptionsEventHandler = new MultipleChoiceOptionsEventHandler(question.getOptions().get(i), playerPoints, player, question);
            aButton.setOnAction(multipleChoiceOptionsEventHandler);
            i++;
        }
    }


    private Button[] getQuestionOptions(Question question) {
        String[] answerOptions = question.getAnswerOptions();
        Button[] buttons = new Button[answerOptions.length];
        int i = 0;
        for(String aString : answerOptions){
            buttons[i] = new Button(aString);
            i++;
        }
        return buttons;
    }

    private void fillQuestionText(Question question, Label questionText) {
        questionText.setText(question.getText());
    }

    private void fillContainer(HBox contenedorHorizontal, Button[] buttons) {
        for(Button aButton : buttons){
            contenedorHorizontal.getChildren().add(aButton);
        }
    }

}
