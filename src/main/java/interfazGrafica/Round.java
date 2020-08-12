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

import java.util.List;

public class Round {
    public static void start(Stage stage, List<Player> players) {

        //----------------------------------------------------------------------
        //--------------------------Principal Container-------------------------
        //----------------------------------------------------------------------
        VBox contenedorPrincipal = new VBox();
        contenedorPrincipal.setSpacing(100);
        contenedorPrincipal.setPadding(new Insets(300));


        Scene escena = new Scene(contenedorPrincipal);

        //----------------------------------------------------------------------
        //--------------------------PlayerOne and points labels-----------------
        //----------------------------------------------------------------------

        //----------------------------------------------------------------------
        //--------------------------QuestionTwo text label----------------------
        //----------------------------------------------------------------------
        Label questionText = new Label();
        questionText.setTextFill(Color.BLUE);
        questionText.setFont(new Font("Arial", 30));
        questionText.setStyle("-fx-border-color: black;");

/*        fillQuestionText(questionText);*/


        //----------------------------------------------------------------------
        //--------------------------Buttons creation----------------------------
        //----------------------------------------------------------------------

        stage.setScene(escena);
        stage.setFullScreen(true);


    }

    private void fillWithEvents(Button[] buttons, MultipleChoiceQuestion question, Label playerPoints, Player player) {
        int i = 0;
        for(Button aButton : buttons) {
            MultipleChoiceOptionsEventHandler multipleChoiceOptionsEventHandler = new MultipleChoiceOptionsEventHandler(question.getOptions().get(i), playerPoints, player, question);
            aButton.setOnAction(multipleChoiceOptionsEventHandler);
            i++;
        }
    }


/*    private void fillQuestionText(Label questionText) {
        questionText.setText(question.getText());
    }*/
}
