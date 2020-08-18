package interfazGrafica.Draggable;

import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.text.Text;

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
