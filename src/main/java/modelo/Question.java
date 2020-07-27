package modelo;

import java.util.List;

abstract class Question {
    protected String text;
    protected QuestionScorer scorer;
    protected List<Option> options;

    public void score(Player player, List<Option> options) {
        this.scorer.score(player, options);
    }
}
