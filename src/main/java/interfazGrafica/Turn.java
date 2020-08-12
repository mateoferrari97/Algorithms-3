package interfazGrafica;

import interfazGrafica.Eventos.BooleanOptionsEventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import modelo.BooleanQuestion;
import modelo.Player;
import modelo.Question;

public class Turn {
    private Label playerPoints;
    private Player player;
    private VBox contenedorPrincipal;
    private Label questionText;
    private HBox buttonsContainer;
    private HBox playerContenedorHorizontal;
    private Button[] buttons;

    public Turn(Player player, VBox contenedorPrincipal) {
        this.contenedorPrincipal = contenedorPrincipal;
        this.player = player;

    }


    public void createPlayerLabels() {
        Label playerText = new Label();
        playerText.setTextFill(Color.BLACK);
        playerText.setFont(new Font("Arial", 30));

        this.playerPoints = new Label();
        this.playerPoints.setTextFill(Color.BLACK);
        this.playerPoints.setFont(new Font("Arial", 30));

        playerText.setText(player.getText());
        this.playerPoints.setText(player.getPoints().toString());

        this.playerContenedorHorizontal = new HBox(playerText, this.playerPoints);
        playerContenedorHorizontal.setSpacing(10);

    }

    public void fillWithEvents(BooleanQuestion question, Turn turn) {
        int i = 0;
        for(Button aButton : this.buttons) {
            BooleanOptionsEventHandler booleanOptionsEventHandler = new BooleanOptionsEventHandler(question.getOptions().get(i), this.playerPoints, this.player, question, turn);
            aButton.setOnAction(booleanOptionsEventHandler);
            i++;
        }
    }

    public void setQuestionLabel(Label questionText) {
        this.questionText = questionText;
    }

    public void startScene() {
        this.contenedorPrincipal.getChildren().clear();
        this.contenedorPrincipal.getChildren().add(this.playerContenedorHorizontal);
        this.contenedorPrincipal.getChildren().add(this.questionText);
        this.contenedorPrincipal.getChildren().add(this.buttonsContainer);

    }

    public void setButtons(BooleanQuestion question) {
        getQuestionOptions(question);
        this.buttonsContainer = new HBox();
        for(Button aButton : buttons){
            this.buttonsContainer.getChildren().add(aButton);
        }
        this.buttonsContainer.setSpacing(300);
    }

    private void getQuestionOptions(Question question) {
        String[] answerOptions = question.getAnswerOptions();
        this.buttons = new Button[answerOptions.length];
        int i = 0;
        for(String aString : answerOptions){
            this.buttons[i] = new Button(aString);
            i++;
        }
    }

}
