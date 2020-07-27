package modelo;
import java.util.ArrayList;
import java.util.List;

public class BooleanQuestion extends Question {
    public BooleanQuestion(String text, List<Option> options, QuestionScorer scorer) {
        super();
        this.options = options;
        this.text = text;
        this.scorer = scorer;
    }

    public void score(Player player, List<Option> options) {
        this.scorer.score(player, options);
    }
}
