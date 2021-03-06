package interfazGrafica;


import modelo.game.SelectableGroup;
import modelo.game.UnselectableGroup;
import interfazGrafica.Draggable.*;
import interfazGrafica.Events.BooleanOptionsEventHandler;
import interfazGrafica.Draggable.GroupChoiceDragDroppedEventHandler;
import interfazGrafica.Events.MultiplicatorEventHandler;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelo.game.Game;
import modelo.game.Player;
import modelo.game.Round;
import modelo.game.Turn;
import modelo.options.Option;

import modelo.game.OptionGroup;

import modelo.multiplicators.Multiplicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Play extends VBox {
    Stage stage;
    List<String> backgrounds;
    private int time = 10;

    public Play(Stage stage){
        super();
        this.stage = stage;
        this.backgrounds = Arrays.asList("-fx-background-color: lightblue;", "-fx-background-color: lightgreen;" );
    }

    public void start(Game game) {

        Round round = game.getNextRound();
        Turn turn = round.getTurn(game);
        this.setPadding(new Insets(10));

        Label tiempo = new Label(String.valueOf(time));
        tiempo.setTextFill(Color.web("#FF0000"));
        tiempo.setStyle("-fx-font: 20 arial;");

        Scene scene = new Scene(this, 900, 600);
        Random random = new Random();
        this.setStyle(this.backgrounds.get(random.nextInt(backgrounds.size())));


        Timeline timeline = new Timeline();

        Timeline timeline2 = new Timeline();

        Timeline timeline3 = new Timeline();

        createPlayerLabels(turn, tiempo);

        createQuestionLabel(round);

        createMultiplicatorButton(round, turn);

        switch (round.getQuestion().getType()) {
            case "GroupChoiceQuestion":
                createAnswerDraggableOptions(round, turn, game);
                createTargeteableGroups(round, turn, game, timeline, timeline2, timeline3);
                break;
            default:
                createAnswerOptionsButtons(round, turn, game, timeline, timeline2, timeline3);
        }

        Play nextScene = new Play(this.stage);

        timeline3.getKeyFrames().add(new KeyFrame(Duration.millis(1000),
                ae -> time--));
        timeline3.setCycleCount(10);
        timeline3.play();

        timeline2.getKeyFrames().add(new KeyFrame(Duration.millis(1000),
                ae -> tiempo.setText(String.valueOf(time))));
        timeline2.setCycleCount(10);
        timeline2.play();


        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10000),
                ae -> actualizarEscena(nextScene, game, turn)));
        timeline.play();

        this.setSpacing(30);
        this.stage.setScene(scene);
    }

    public void actualizarEscena(Play nextScene, Game game, Turn turn) {
        turn.finish();
        if(game.getNextRound() == null){
            EndGame end = new EndGame(stage, game);
        }else{
            nextScene.start(game);
        }
    }

    //----------------------------------------------------------------------
    //-------------------Multiplicator Buttons creation---------------------
    //----------------------------------------------------------------------
    private void createMultiplicatorButton(Round round, Turn turn) {
        List<Button> buttons = new ArrayList<>();

        HBox multiplicatorsContainer = new HBox();
        for(Multiplicator aMultiplicator : round.getMultiplicator()){
            Button button = new Button(aMultiplicator.getText());
            multiplicatorsContainer.getChildren().add(button);
            MultiplicatorEventHandler multiplicatorEventHandler = new MultiplicatorEventHandler(aMultiplicator, turn, round, button, multiplicatorsContainer);
            button.setOnAction(multiplicatorEventHandler);
            buttons.add(button);
        }


        for(Button aButton : buttons){
            aButton.setStyle("-fx-background-radius: 30; -fx-background-color: #896bab; -fx-border-color: white; -fx-border-width: 2px; -fx-border-radius: 30; -fx-text-fill: white;");
            aButton.setScaleX(1.5);
            aButton.setScaleY(1.5);
        }
        multiplicatorsContainer.setAlignment(Pos.CENTER);
        multiplicatorsContainer.setSpacing(20);

        this.getChildren().add(multiplicatorsContainer);
    }

    //----------------------------------------------------------------------
    //--------------------------Buttons creation----------------------------
    //----------------------------------------------------------------------
    private void createAnswerOptionsButtons(Round round, Turn turn, Game game, Timeline timeline, Timeline timeline2, Timeline timeline3) {
        Button[] buttons = getQuestionOptions(round, turn, game, timeline, timeline2, timeline3);
        HBox buttonsContainer = new HBox();
        for(Button aButton : buttons){
            aButton.setPrefSize(100,100);
            aButton.setFont(Font.font("Impact", 18));
            aButton.setStyle("-fx-background-color: #fa8072; -fx-border-color: white; -fx-border-width: 3px; -fx-text-fill: white;");
            buttonsContainer.getChildren().add(aButton);
        }
        buttonsContainer.setSpacing(20);
        buttonsContainer.setAlignment(Pos.CENTER);
        this.getChildren().add(buttonsContainer);
    }

    private Button[] getQuestionOptions(Round round, Turn turn, Game game, Timeline timeline, Timeline timeline2, Timeline timeline3) {
        String[] answerOptions = round.getQuestion().getAnswerOptions();
        Button[] buttons = new Button[answerOptions.length];
        int i = 0;
        for(String aString : answerOptions){
            buttons[i] = new Button(aString);
            i++;
        }
        fillWithEvents(buttons, round, turn, game, timeline, timeline2, timeline3);
        return buttons;
    }

    public void fillWithEvents(Button[] buttons, Round round, Turn turn, Game game, Timeline timeline, Timeline timeline2, Timeline timeline3) {
        int i = 0;
        Play nextScene = new Play(this.stage);
        for(Button aButton : buttons) {
            Option option = round.getQuestion().getOptions().get(i);
            EventHandler<ActionEvent> handler;
            handler = new BooleanOptionsEventHandler(option, round, turn, game, nextScene, this.stage, aButton, timeline, timeline2, timeline3);
            aButton.setOnAction(handler);
            i++;
        }
    }

    //----------------------------------------------------------------------
    //--------------------------Question text label-------------------------
    //----------------------------------------------------------------------
    private void createQuestionLabel(Round round) {
        HBox questionContainer = new HBox();
        Label questionText = new Label(round.getQuestion().getText());
        questionText.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-color: black; -fx-border-width: 3px");
        questionText.setFont(new Font("Arial", 30));


        questionContainer.getChildren().add(questionText);

        questionContainer.setAlignment(Pos.CENTER);

        this.getChildren().add(questionContainer);
    }

    //----------------------------------------------------------------------
    //--------------------------Player and points labels--------------------
    //----------------------------------------------------------------------
    private void createPlayerLabels(Turn turn, Label tiempo) {
        Label playerText = new Label();
        playerText.setTextFill(Color.BLACK);
        playerText.setFont(new Font("Arial", 30));

        Label playerPoints = new Label();
        playerPoints.setTextFill(Color.BLACK);
        playerPoints.setFont(new Font("Arial", 30));


        Player player = turn.getPlayer();
        playerText.setText(player.getName() + ":");
        playerPoints.setText(player.getPoints().toString());

        HBox playerContenedorHorizontal = new HBox(playerText, playerPoints, tiempo);
        playerContenedorHorizontal.setSpacing(10);

        this.getChildren().add(playerContenedorHorizontal);
    }

    public void update() {
    }

    //----------------------------------------------------------------------
    //--------------------------Draggable Text Options----------------------
    //----------------------------------------------------------------------

    private void createAnswerDraggableOptions(Round round, Turn turn, Game game) {
        HBox textContainer = new HBox();

        Text[] text = getQuestionTextOptions(round, turn, game, textContainer);
        for(Text aText : text){
            textContainer.getChildren().add(aText);
        }
        textContainer.setStyle("-fx-background-color: white");
        textContainer.setMinHeight(50);
        textContainer.setAlignment(Pos.CENTER);
        textContainer.setSpacing(100);
        this.getChildren().add(textContainer);
    }

    private Text[] getQuestionTextOptions(Round round, Turn turn, Game game, HBox hBox) {
        String[] answerOptions = round.getQuestion().getAnswerOptions();
        Text[] texts = new Text[answerOptions.length];
        int i = 0;
        for(String aString : answerOptions){
            texts[i] = new Text(aString);
            texts[i].setScaleX(2.0);
            texts[i].setScaleY(2.0);
            i++;
        }
        fillTextWithEvents(texts, hBox);
        return texts;
    }

    public void fillTextWithEvents(Text[] texts, HBox hBox) {
        int i = 0;
        for(Text aText : texts) {
            setAsDraggable(aText, hBox);
            i++;
        }
    }

    void setAsDraggable(Text text, HBox hBox) {
        text.setOnDragDetected(new DragDetectedEvenHandler(text));
        text.setOnDragDone(new DragDoneEventHandler(text, hBox));
    }

    //----------------------------------------------------------------------
    //--------------------------Targeteable Groups--------------------------
    //----------------------------------------------------------------------

    private void createTargeteableGroups(Round round, Turn turn, Game game, Timeline timeline, Timeline timeline2, Timeline timeline3){
        Text groupAText = new Text("Grupo A");
        groupAText.setScaleX(2.0);
        groupAText.setScaleY(2.0);

        Text groupBText = new Text("Grupo B");
        groupBText.setScaleX(2.0);
        groupBText.setScaleY(2.0);

        VBox groupA = new VBox();
        VBox groupB = new VBox();

        setAsTarget(groupAText, groupA, game, round, turn, new SelectableGroup(), timeline, timeline2, timeline3);
        setAsTarget(groupBText, groupB, game, round, turn, new UnselectableGroup(), timeline, timeline2, timeline3);



        HBox groupsContainer = new HBox();
        VBox groupAContainer = new VBox();
        VBox groupBContainer = new VBox();

        groupAContainer.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2px");
        groupAContainer.setMinWidth(150);
        groupAContainer.setMinHeight(40);

        groupBContainer.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2px");
        groupBContainer.setMinWidth(150);
        groupBContainer.setMinHeight(40);

        groupsContainer.getChildren().add(groupAContainer);
        groupsContainer.getChildren().add(groupBContainer);
        groupAContainer.getChildren().add(groupAText);
        groupAContainer.getChildren().add(groupA);
        groupBContainer.getChildren().add(groupBText);
        groupBContainer.getChildren().add(groupB);
        groupsContainer.setAlignment(Pos.CENTER);
        groupsContainer.setSpacing(200);
        groupAContainer.setAlignment(Pos.CENTER);
        groupA.setAlignment(Pos.CENTER);
        groupBContainer.setAlignment(Pos.CENTER);
        groupB.setAlignment(Pos.CENTER);
        groupAContainer.setSpacing(15);
        groupBContainer.setSpacing(15);
        groupA.setSpacing(10);
        groupB.setSpacing(10);

        this.getChildren().add(groupsContainer);
    }

    void setAsTarget(Text target, VBox vBox, Game game, Round round, Turn turn, OptionGroup typeOfGroup, Timeline timeline, Timeline timeline2, Timeline timeline3) {
        Play nextScene = new Play(this.stage);
        target.setOnDragOver(new DragOverEventHandler());
        target.setOnDragEntered(new DragEnteredEventHandler(target));
        target.setOnDragExited(new DragExitedEventHandler(target));
        target.setOnDragDropped(new GroupChoiceDragDroppedEventHandler(vBox, game, round, turn, nextScene, this.stage, typeOfGroup, timeline, timeline2, timeline3));
    }
}

