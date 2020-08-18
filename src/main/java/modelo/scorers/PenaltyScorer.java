package modelo.scorers;

import modelo.game.Player;
import modelo.game.Points;
import modelo.multiplicators.Multiplicate;
import modelo.multiplicators.Multiplicator;

import java.util.List;

public class PenaltyScorer implements QuestionScorer {

    public void score(List<Player> players, Points playerOnePoints, Points playerTwoPoints) {
        playerOnePoints.comparatePoints(players.get(0), playerTwoPoints);
        playerOnePoints.givePointsToPlayer(players.get(0));
        playerTwoPoints.givePointsToPlayer(players.get(1));
    }

    public void reward(Points points) {
        points.increasePoints();
    }

    public void punish(Points points) {
        points.decreasePoints();
    }

    public void getMultiplicator(List<Multiplicator> multiplicator) {
        multiplicator.add(new Multiplicate("X2", 2));
        multiplicator.add(new Multiplicate("X3", 3));
    }
}
