package modelo;

public class IncorrectOptionScorer implements IOptionScorer {
    public void score(Player player) {
        player.decreasePoints();
    }

    public void calculatePoints(QuestionScorer scorer, Points points) {
        scorer.punish(points);
    }
}
