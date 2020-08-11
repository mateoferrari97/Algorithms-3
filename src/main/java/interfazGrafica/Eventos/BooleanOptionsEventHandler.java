package interfazGrafica.Eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modelo.*;

import java.util.ArrayList;
import java.util.List;


public class BooleanOptionsEventHandler implements EventHandler<ActionEvent> {
    private final Scene escena;
    private final Stage stage;
    private Question question;
    private Player player;
    private Option chosenOption;
    private Label playerPoints;


    public BooleanOptionsEventHandler(Option option, Label playerPoints, Player player, BooleanQuestion question, Stage stage, Scene escena) {
        this.chosenOption = option;
        this.playerPoints = playerPoints;
        this.player = player;
        this.question = question;
        this.stage = stage;
        this.escena = escena;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        List<Option> options = new ArrayList();
        options.add(this.chosenOption);
        this.question.score(this.player, options);
        Integer auxPlayerPoints = player.getPoints();
        playerPoints.setText(auxPlayerPoints.toString());
        this.stage.setScene(this.escena);

    }
}
