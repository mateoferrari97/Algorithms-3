package modelo.scorers;


import modelo.game.Player;
import modelo.game.Points;

public class MultipleChoiceScorer implements QuestionScorer {

    public void score(Player player, Points points) {
        points.givePointsToPlayer(player);
    }


    public void reward(Points points) {
        points.gainAPoint();
    }

    public void punish(Points points) {
        points.changeScoreToZero();
    }



}
