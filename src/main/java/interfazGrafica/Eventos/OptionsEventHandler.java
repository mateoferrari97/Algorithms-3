package interfazGrafica.Eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import modelo.BooleanQuestion;
import modelo.Option;
import modelo.Player;

import java.util.List;

public class OptionsEventHandler implements EventHandler<ActionEvent> {

    private final BooleanQuestion question;
    private List<Option> options;

    public OptionsEventHandler(Option option, BooleanQuestion question) {
        this.options.add(option);
        this.question = question;

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Player player = new Player();
        this.question.score(player,this.options);

    }
}
