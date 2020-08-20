package interfazGrafica.Draggable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.text.Text;

public class DragDetectedEvenHandler implements EventHandler<MouseEvent> {

    private Text text;

    public DragDetectedEvenHandler(Text text) {
        this.text = text;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
            Dragboard db = text.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString(text.getText());
            db.setContent(content);
    }
}
