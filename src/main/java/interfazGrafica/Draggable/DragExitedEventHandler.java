package interfazGrafica.Draggable;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class DragExitedEventHandler  implements EventHandler<DragEvent> {
    private Text target;

    public DragExitedEventHandler(Text target) {
        this.target = target;
    }

    @Override
    public void handle(DragEvent event) {
        target.setFill(Color.BLACK);
    }
}
