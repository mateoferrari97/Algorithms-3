package interfazGrafica;

import interfazGrafica.Eventos.BooleanOptionsEventHandler;
import interfazGrafica.Eventos.GroupChoiceEventHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modelo.game.Game;
import modelo.game.Player;
import modelo.game.Round;
import modelo.game.Turn;
import modelo.options.Option;

public class Play extends VBox {
    Stage stage;

    public Play(Stage stage){
        super();
        this.stage = stage;
    }

    public void start(Game game){

        Round round = game.getNextRound();
        Turn turn = round.getTurn(game);

        this.setSpacing(100);
        this.setPadding(new Insets(300));


        Scene scene = new Scene(this);


        createPlayerLabels(turn);

        createQuestionLabel(round);

        createAnswerOptionsButtons(round, turn, game);


        this.stage.setScene(scene);
    }

    //----------------------------------------------------------------------
    //--------------------------Buttons creation----------------------------
    //----------------------------------------------------------------------
    private void createAnswerOptionsButtons(Round round, Turn turn, Game game) {
        Button[] buttons = getQuestionOptions(round, turn, game);
        HBox buttonsContainer = new HBox();
        for(Button aButton : buttons){
            buttonsContainer.getChildren().add(aButton);
        }
        buttonsContainer.setSpacing(300);
        this.getChildren().add(buttonsContainer);
    }

    private Button[] getQuestionOptions(Round round, Turn turn, Game game) {
        String[] answerOptions = round.getQuestion().getAnswerOptions();
        Button[] buttons = new Button[answerOptions.length];
        int i = 0;
        for(String aString : answerOptions){
            buttons[i] = new Button(aString);
            i++;
        }
        fillWithEvents(buttons, round, turn, game);
        return buttons;
    }

    public void fillWithEvents(Button[] buttons, Round round, Turn turn, Game game) {
        int i = 0;
        Play nextScene = new Play(this.stage);
        for(Button aButton : buttons) {
            Option option = round.getQuestion().getOptions().get(i);

            EventHandler<ActionEvent> handler;
            switch (round.getQuestion().getType()) {
                case "group choice":
                    handler = new GroupChoiceEventHandler(option, round, turn, game, nextScene, this.stage);
                default:
                    handler = new BooleanOptionsEventHandler(option, round, turn, game, nextScene, this.stage);
            }

            EventHandler<ActionEvent> optionsEventHandler = handler;
            aButton.setOnAction(optionsEventHandler);
            i++;
        }
    }

    //----------------------------------------------------------------------
    //--------------------------QuestionTwo text label----------------------
    //----------------------------------------------------------------------
    private void createQuestionLabel(Round round) {
        Label questionText = new Label();
        questionText.setTextFill(Color.BLUE);
        questionText.setFont(new Font("Arial", 30));
        questionText.setStyle("-fx-border-color: black;");

        questionText.setText(round.getQuestion().getText());

        this.getChildren().add(questionText);
    }

    //----------------------------------------------------------------------
    //--------------------------PlayerOne and points labels-----------------
    //----------------------------------------------------------------------
    private void createPlayerLabels(Turn turn) {
        Label playerText = new Label();
        playerText.setTextFill(Color.BLACK);
        playerText.setFont(new Font("Arial", 30));

        Label playerPoints = new Label();
        playerPoints.setTextFill(Color.BLACK);
        playerPoints.setFont(new Font("Arial", 30));


        Player player = turn.getPlayer();
        playerText.setText(player.getName());
        playerPoints.setText(player.getPoints().toString());

        HBox playerContenedorHorizontal = new HBox(playerText, playerPoints);
        playerContenedorHorizontal.setSpacing(10);

        this.getChildren().add(playerContenedorHorizontal);
    }

    public void update() {
        
    }
}
