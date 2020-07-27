package modelo;

import java.util.List;

public class BooleanScorer implements QuestionScorer {
    public void score(Player player, List<Option> options) {
        Integer currentPlayerPoints = player.getPoints();
        for (Option option : options) {
            option.score(player);
            if (player.getPoints() < currentPlayerPoints) {
                player.increasePoints();
            }
        }
    }
}
