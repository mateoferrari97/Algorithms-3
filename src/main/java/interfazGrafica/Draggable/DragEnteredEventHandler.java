package interfazGrafica.Draggable;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class DragEnteredEventHandler implements EventHandler<DragEvent> {

    private Text target;

    public DragEnteredEventHandler(Text target) {
        this.target = target;
    }

    @Override
    public void handle(DragEvent event) {
        target.setFill(Color.BLUE);
    }
}
