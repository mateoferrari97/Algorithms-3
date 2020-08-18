package interfazGrafica.Draggable;

import Group.OptionGroup;
import interfazGrafica.EndGame;
import interfazGrafica.Play;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Game;
import modelo.Round;
import modelo.Turn;
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


    public GroupChoiceDragDroppedEventHandler(VBox vBox, Game game, Round round, Turn turn, Play nextPlay, Stage stage, OptionGroup typeOfGroup){
        this.vBox = vBox;
        this.turn = turn;
        this.game = game;
        this.nextPlay = nextPlay;
        this.stage = stage;
        this.round = round;
        this.typeOfGroup = typeOfGroup;
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

        this.turn.finish();

        if(game.getNextRound() == null){
            EndGame end = new EndGame(stage);
        }else{
            nextPlay.start(game);
        }
    }
}
