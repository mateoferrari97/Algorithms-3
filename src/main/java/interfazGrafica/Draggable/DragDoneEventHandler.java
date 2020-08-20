package interfazGrafica.Draggable;

import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
