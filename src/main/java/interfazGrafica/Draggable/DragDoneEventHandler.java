package interfazGrafica.Draggable;

import interfazGrafica.EndGame;
import interfazGrafica.Play;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Game;
import modelo.options.Option;

import java.util.List;

public class DragDoneEventHandler implements EventHandler<DragEvent> {

    private Text text;

    public DragDoneEventHandler(Text text) {
        this.text = text;
    }


    @Override
    public void handle(DragEvent event) {
        if (event.getTransferMode() == TransferMode.MOVE) {
            text.setText("");
        }

    }

}
