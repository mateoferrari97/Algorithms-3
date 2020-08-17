package interfazGrafica;

import Group.SelectableGroup;
import Group.UnselectableGroup;
import interfazGrafica.Draggable.*;
import interfazGrafica.Eventos.BooleanOptionsEventHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.*;
import modelo.Round;
import modelo.Turn;
import modelo.options.Option;
import Group.OptionGroup;

public class Play extends VBox {
    Stage stage;

    public Play(Stage stage){
        super();
        this.stage = stage;
    }

    public void start(Game game) {

        Round round = game.getNextRound();
        Turn turn = round.getTurn(game);

        this.setSpacing(100);
        this.setPadding(new Insets(300));

        Scene scene = new Scene(this);

        createPlayerLabels(turn);
        createQuestionLabel(round);

        switch (round.getQuestion().getType()) {
            case "group choice":
                createAnswerDraggableOptions(round, turn, game);
                createTargeteableGroups(round, turn, game);
                break;
            default:
                createAnswerOptionsButtons(round, turn, game);
        }




        this.setSpacing(30);
        this.stage.setScene(scene);
    }

    //----------------------------------------------------------------------
    //--------------------------Buttons creation----------------------------
    //----------------------------------------------------------------------

    private void createTargeteableGroups(Round round, Turn turn, Game game){
        Text grupoATexto = new Text("Grupo A");
        grupoATexto.setScaleX(2.0);
        grupoATexto.setScaleY(2.0);

        Text grupoBTexto = new Text("Grupo B");
        grupoBTexto.setScaleX(2.0);
        grupoBTexto.setScaleY(2.0);

        VBox grupoA = new VBox();
        VBox grupoB = new VBox();

        setAsTarget(grupoATexto, grupoA, game, round, turn, new SelectableGroup());
        setAsTarget(grupoBTexto, grupoB, game, round, turn, new UnselectableGroup());

        HBox contenedorGrupos = new HBox();
        VBox contenedorGrupoA = new VBox();
        VBox contenedorGrupoB = new VBox();
        contenedorGrupos.getChildren().add(contenedorGrupoA);
        contenedorGrupos.getChildren().add(contenedorGrupoB);
        contenedorGrupoA.getChildren().add(grupoATexto);
        contenedorGrupoA.getChildren().add(grupoA);
        contenedorGrupoB.getChildren().add(grupoBTexto);
        contenedorGrupoB.getChildren().add(grupoB);
        contenedorGrupos.setAlignment(Pos.CENTER);
        contenedorGrupos.setSpacing(200);
        contenedorGrupoA.setSpacing(15);
        contenedorGrupoB.setSpacing(15);
        grupoA.setSpacing(10);
        grupoB.setSpacing(10);

        this.getChildren().add(contenedorGrupos);
    }

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

    private void createAnswerOptionsButtons(Round round, Turn turn, Game game) {
        Button[] buttons = getQuestionOptions(round, turn, game);
        HBox buttonsContainer = new HBox();
        for(Button aButton : buttons){
            buttonsContainer.getChildren().add(aButton);
        }
        buttonsContainer.setAlignment(Pos.CENTER);
        buttonsContainer.setSpacing(300);
        this.getChildren().add(buttonsContainer);
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

    public void fillTextWithEvents(Text[] texts) {
        int i = 0;
        //Play nextScene = new Play(this.stage);
        for(Text aText : texts) {
            setAsDraggable(aText);
            i++;
        }
    }

    public void fillWithEvents(Button[] buttons, Round round, Turn turn, Game game) {
        int i = 0;
        Play nextScene = new Play(this.stage);
        for(Button aButton : buttons) {
            Option option = round.getQuestion().getOptions().get(i);

            EventHandler<ActionEvent> handler;
            switch (round.getQuestion().getType()) {
                case "group choice":
                    //handler = new GroupChoiceEventHandler(option, round, turn, game, nextScene, this.stage);
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
        playerText.setText(player.getText());
        playerPoints.setText(player.getPoints().toString());

        HBox playerContenedorHorizontal = new HBox(playerText, playerPoints);
        playerContenedorHorizontal.setSpacing(10);

        this.getChildren().add(playerContenedorHorizontal);
    }

    public void update() {
        
    }


    void setAsDraggable(Text text) {
        Play nextScene = new Play(this.stage);
        text.setOnDragDetected(new DragDetectedEvenHandler(text));
        text.setOnDragDone(new DragDoneEventHandler(text));
    }

    void setAsTarget(Text target, VBox vBox, Game game, Round round, Turn turn, OptionGroup typeOfGroup) {
        Play nextScene = new Play(this.stage);
        target.setOnDragOver(new DragOverEventHandler());
        target.setOnDragEntered(new DragEnteredEventHandler(target));
        target.setOnDragExited(new DragExitedEventHandler(target));
        target.setOnDragDropped(new DragDroppedEventHandler(vBox, game, round, turn, nextScene, this.stage, typeOfGroup));
    }


}

