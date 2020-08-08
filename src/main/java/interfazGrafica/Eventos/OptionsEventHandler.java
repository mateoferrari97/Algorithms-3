package interfazGrafica.Eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import modelo.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class OptionsEventHandler implements EventHandler<ActionEvent> {

    private final Question question;
    private final Player player;
    private List<Option> options;

    public OptionsEventHandler(Option option, Question question) {
        if(this.options == null){
            this.options = new ArrayList<Option>();
        }
        this.options.add(option);
        this.question = question;
        this.player = new Player();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.question.score(this.player,this.options);

    }
}
