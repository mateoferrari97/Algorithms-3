package modelo;

import java.util.List;

public class BooleanWithPenaltyScorer implements QuestionScorer {
    public void score(Player player, List<Option> options) {
        for (Option option : options) {
            option.score(player);
        }
    }
}
