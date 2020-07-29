package modelo;

public class CorrectOptionScorer implements IOptionScorer {
    public void score(Player player) {
        player.increasePoints();
    }

    public void calculatePoints(QuestionScorer scorer, Points points) {
        scorer.reward(points);
    }

}
