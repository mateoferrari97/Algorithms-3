package interfazGrafica.Draggable;

import interfazGrafica.EndGame;
import interfazGrafica.Play;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Game;
import modelo.options.Option;

import java.util.List;

public class DragDoneEventHandler implements EventHandler<DragEvent> {

    private Text text;
    private HBox hBox;

    public DragDoneEventHandler(Text text, HBox hBox) {
        this.text = text;
        this.hBox = hBox;
    }


    @Override
    public void handle(DragEvent event) {
        if (event.getTransferMode() == TransferMode.MOVE) {
            this.hBox.getChildren().remove(this.text);
        }

    }

}
