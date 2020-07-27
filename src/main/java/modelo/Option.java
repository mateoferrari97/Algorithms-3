package modelo;

import java.util.List;

public class Option {
    private String text;
    private IScoreBehavior scorer;

    public Option(String text, IScoreBehavior scorer) {
        this.text = text;
        this.scorer = scorer;
    }

    public void score(Player player) {
        scorer.score(player);
    }
}
