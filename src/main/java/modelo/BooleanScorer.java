package modelo;


public class BooleanScorer implements QuestionScorer {

    public void score(Player player, Points points) {
        points.givePointsToPlayer(player);
    }

    public void reward(Points points) {
        points.increasePoints();
    }

    public void punish(Points points) {

    }

    @Override
    public void multiplicate(Points points, Integer multiplicator) {

    }

}
