package interfazGrafica.Draggable;

import modelo.game.OptionGroup;
import interfazGrafica.EndGame;
import interfazGrafica.Play;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.game.Game;
import modelo.game.Round;
import modelo.game.Turn;
import modelo.options.Option;

import java.util.List;

public class GroupChoiceDragDroppedEventHandler implements EventHandler<DragEvent> {

    private Game game;
    private Round round;
    private Turn turn;
    private VBox vBox;
    private Play nextPlay;
    private Stage stage;
    private OptionGroup typeOfGroup;
    private Timeline timeline;
    private Timeline timeline2;
    private Timeline timeline3;


    public GroupChoiceDragDroppedEventHandler(VBox vBox, Game game, Round round, Turn turn, Play nextPlay, Stage stage, OptionGroup typeOfGroup, Timeline timeline, Timeline timeline2, Timeline timeline3){
        this.vBox = vBox;
        this.turn = turn;
        this.game = game;
        this.nextPlay = nextPlay;
        this.stage = stage;
        this.round = round;
        this.typeOfGroup = typeOfGroup;
        this.timeline = timeline;
        this.timeline2 = timeline2;
        this.timeline3 = timeline3;
    }

    @Override
    public void handle(DragEvent event) {

        Dragboard db = event.getDragboard();
        boolean dropped = false;

        if (db.hasString()) {

            List<Option>  options = this.round.getQuestion().getOptions();
            Option selectedOption = null;
            for (Option aOption : options) {
                if (db.getString() == aOption.getText())
                    selectedOption = aOption;
            }
            if (selectedOption != null) {
                this.typeOfGroup.addPlayerAnswer(turn, selectedOption);
                vBox.getChildren().add(new Text(db.getString()));
            }
            dropped = true;
        }
        event.setDropCompleted(dropped);

        if (this.turn.getAmountCurrentOptions() != this.turn.getAmountOfOptions()) {
            return;
        }

        this.timeline.stop();
        this.timeline2.stop();
        this.timeline3.stop();
        this.turn.finish();

        this.turn.finish();

        if(game.getNextRound() == null){
            this.round.finish();
            new EndGame(stage, game);
        }else{
            this.round.finish();
            nextPlay.start(game);
        }
    }
}
