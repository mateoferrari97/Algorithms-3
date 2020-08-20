package interfazGrafica.Events;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class BotonLimpiarEventHandler implements EventHandler<ActionEvent> {

    private TextField text;

    public BotonLimpiarEventHandler(TextField text) {
        this.text = text;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        this.text.setText("");
        this.text.requestFocus();
    }
}
