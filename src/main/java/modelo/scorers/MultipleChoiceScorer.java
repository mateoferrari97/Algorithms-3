package modelo.scorers;


import modelo.game.Player;
import modelo.game.Points;
import modelo.multiplicators.Multiplicator;
import modelo.multiplicators.ScoreExclusivity;

import java.util.List;

public class MultipleChoiceScorer implements QuestionScorer {

    public void score(List<Player> players, Points playerOnePoints, Points playerTwoPoints) {
        playerOnePoints.comparatePoints(players.get(0), playerTwoPoints);
        playerOnePoints.givePointsToPlayer(players.get(0));
        playerTwoPoints.givePointsToPlayer(players.get(1));
    }


    public void reward(Points points) {
        points.gainAPoint();
    }

    public void punish(Points points) {
        points.changeScoreToZero();
    }

    public void getMultiplicator(List<Multiplicator> multiplicator) {
        multiplicator.add(new ScoreExclusivity());
    }


}
