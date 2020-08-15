package interfazGrafica.Eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import modelo.Player;
import modelo.options.Option;
import modelo.questions.MultipleChoiceQuestion;
import modelo.questions.Question;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceOptionsEventHandler implements EventHandler<ActionEvent> {
    private Question question;
    private Player player;
    private Option chosenOption;
    private Label playerPoints;

    public MultipleChoiceOptionsEventHandler(Option option, Label playerPoints, Player player, MultipleChoiceQuestion question) {
        this.chosenOption = option;
        this.playerPoints = playerPoints;
        this.player = player;
        this.question = question;

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        List<Option> options = new ArrayList();
        options.add(this.chosenOption);
        this.question.selectOptions(options);
        this.question.score(this.player);
        Integer auxPlayerPoints = player.getPoints();
        playerPoints.setText(auxPlayerPoints.toString());
    }
}