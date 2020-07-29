package modelo;

public class IncorrectOptionScorer implements IOptionScorer {

    public void calculatePoints(QuestionScorer scorer, Points points) {
        scorer.punish(points);
    }
}
