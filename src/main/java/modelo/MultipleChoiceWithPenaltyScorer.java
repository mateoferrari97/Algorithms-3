package modelo;

public class MultipleChoiceWithPenaltyScorer implements QuestionScorer {

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