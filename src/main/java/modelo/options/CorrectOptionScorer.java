package modelo.options;

import modelo.game.Points;
import modelo.scorers.QuestionScorer;

public class CorrectOptionScorer implements IOptionScorer {

    public void calculatePoints(QuestionScorer scorer, Points points) {
        scorer.reward(points);
    }

    public void changeState(Option option) {
        option.changeToIncorrect();
    }

    public boolean isCorrect() {
        return true;
    }
}
