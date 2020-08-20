package modelo.scorers;

import modelo.game.Points;
import modelo.multiplicators.Multiplicator;
import modelo.multiplicators.ScoreExclusivity;

import java.util.List;

public class BooleanScorer implements QuestionScorer {

    public void reward(Points points) {
        points.increasePoints();
    }

    public void punish(Points points) {

    }

    public void getMultiplicator(List<Multiplicator> multiplicator) {
        multiplicator.add(new ScoreExclusivity());
    }

}
