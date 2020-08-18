package modelo.scorers;

import modelo.game.Player;
import modelo.game.Points;

public class PenaltyScorer implements QuestionScorer {

    public void score(Player player, Points points) {
        points.givePointsToPlayer(player);
    }

    public void reward(Points points) {
        points.increasePoints();
    }

    public void punish(Points points) {
        points.decreasePoints();
    }
}
