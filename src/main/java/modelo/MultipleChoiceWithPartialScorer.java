package modelo;

import java.util.List;

public class MultipleChoiceWithPartialScorer implements QuestionScorer {
    public void score(Player player, List<Option> options) {
        Integer currentPlayerScorer = player.getPoints();

        for (Option option : options) {
            option.score(player);
            if (player.getPoints() < currentPlayerScorer) {
                player.setPoints(currentPlayerScorer);
                return;
            }
        }
    }
}