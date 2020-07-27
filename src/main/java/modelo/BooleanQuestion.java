package modelo;
import java.util.ArrayList;
import java.util.List;

public class BooleanQuestion extends Question {
    private List<Option> options;

    public BooleanQuestion(String text, List<Option> options) {
        super();
        this.options = options;
        this.text = text;
    }

    public void score(Player player, List<Option> options) {
        for (Option option: options) {
            option.score(player);
        }
    }
}
