package interfazGrafica.Draggable;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

public class DragOverEventHandler implements EventHandler<DragEvent>{

    @Override
    public void handle(DragEvent event) {
        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
    }
}
