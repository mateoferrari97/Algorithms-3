package modelo.options;

import modelo.Points;
import modelo.scorers.QuestionScorer;

public interface IOptionScorer {
    void calculatePoints(QuestionScorer scorer, Points points);

    void changeState(Option option);

    boolean isCorrect();
}
