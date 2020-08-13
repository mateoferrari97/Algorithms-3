package modelo.options;

import modelo.Points;
import modelo.scorers.QuestionScorer;

public class IncorrectOptionScorer implements IOptionScorer {

    public void calculatePoints(QuestionScorer scorer, Points points) {
        scorer.punish(points);
    }

    public void changeState(Option option) {
        option.changeToCorrect();
    }
}