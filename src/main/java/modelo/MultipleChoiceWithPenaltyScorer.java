package modelo;

import java.util.List;

public class MultipleChoiceWithPenaltyScorer implements QuestionScorer {
    public void score(Player player, List<Option> options) {
        for (Option option : options) {
            option.score(player);
        }
    }
}
