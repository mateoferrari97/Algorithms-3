package interfazGrafica;

import Group.SelectableGroup;
import Group.UnselectableGroup;
import interfazGrafica.Draggable.*;
import interfazGrafica.Eventos.BooleanOptionsEventHandler;
import interfazGrafica.Draggable.GroupChoiceDragDroppedEventHandler;
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
import Group.OptionGroup;

import static constantes.Constantes.GROUP_CHOCIE_QUESTION_TYPE;

public class Play extends VBox {
    Stage stage;
    private int time = 10;

    public Play(Stage stage){
        super();
        this.stage = stage;
    }

    public void start(Game game) {

        Round round = game.getNextRound();
        Turn turn = round.getTurn(game);

        Label tiempo = new Label(String.valueOf(time));
        tiempo.setTextFill(Color.web("#FF0000"));
        tiempo.setStyle("-fx-font: 20 arial;");

        this.setSpacing(100);
        this.setPadding(new Insets(300));

        Scene scene = new Scene(this);

        Timeline timeline = new Timeline();

        Timeline timeline2 = new Timeline();

        Timeline timeline3 = new Timeline();


        createPlayerLabels(turn, tiempo);
        createQuestionLabel(round);

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
                ae -> actualizarEscena(nextScene, game)));
        timeline.play();

        this.setSpacing(30);
        this.stage.setScene(scene);
    }

    public void actualizarEscena(Play nextScene, Game game) {
        if(game.getNextRound() == null){
            EndGame end = new EndGame(stage, game);
        }else{
            nextScene.start(game);
        }
    }

    //----------------------------------------------------------------------
    //--------------------------Buttons creation----------------------------
    //----------------------------------------------------------------------
    private void createAnswerOptionsButtons(Round round, Turn turn, Game game, Timeline timeline, Timeline timeline2, Timeline timeline3) {
        Button[] buttons = getQuestionOptions(round, turn, game, timeline, timeline2, timeline3);
        HBox buttonsContainer = new HBox();
        for(Button aButton : buttons){
            buttonsContainer.getChildren().add(aButton);
        }
        buttonsContainer.setSpacing(150);
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
            handler = new BooleanOptionsEventHandler(option, round, turn, game, nextScene, this.stage, timeline, timeline2, timeline3);
            aButton.setOnAction(handler);
            i++;
        }
    }

    //----------------------------------------------------------------------
    //--------------------------Question text label-------------------------
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
        playerText.setText(player.getName());
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
        Text[] text = getQuestionTextOptions(round, turn, game);
        HBox textContainer = new HBox();
        for(Text aText : text){
            textContainer.getChildren().add(aText);
        }
        textContainer.setAlignment(Pos.CENTER);
        textContainer.setSpacing(100);
        this.getChildren().add(textContainer);
    }

    private Text[] getQuestionTextOptions(Round round, Turn turn, Game game) {
        String[] answerOptions = round.getQuestion().getAnswerOptions();
        Text[] texts = new Text[answerOptions.length];
        int i = 0;
        for(String aString : answerOptions){
            texts[i] = new Text(aString);
            texts[i].setScaleX(2.0);
            texts[i].setScaleY(2.0);
            i++;
        }
        fillTextWithEvents(texts);
        return texts;
    }

    public void fillTextWithEvents(Text[] texts) {
        int i = 0;
        for(Text aText : texts) {
            setAsDraggable(aText);
            i++;
        }
    }

    void setAsDraggable(Text text) {
        text.setOnDragDetected(new DragDetectedEvenHandler(text));
        text.setOnDragDone(new DragDoneEventHandler(text));
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
        groupsContainer.getChildren().add(groupAContainer);
        groupsContainer.getChildren().add(groupBContainer);
        groupAContainer.getChildren().add(groupAText);
        groupAContainer.getChildren().add(groupA);
        groupBContainer.getChildren().add(groupBText);
        groupBContainer.getChildren().add(groupB);
        groupsContainer.setAlignment(Pos.CENTER);
        groupsContainer.setSpacing(200);
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

