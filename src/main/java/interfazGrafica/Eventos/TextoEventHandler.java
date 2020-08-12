package interfazGrafica.Eventos;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TextoEventHandler implements EventHandler<KeyEvent> {

    private Button myButton;

    public TextoEventHandler(Button myButton) {
        this.myButton = myButton;
    }

    @Override
    public void handle(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Event.fireEvent(myButton, new ActionEvent());
        }
    }
}
