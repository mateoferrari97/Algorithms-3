package modelo.scorers;

import modelo.game.Points;
import modelo.multiplicators.Multiplicator;
import modelo.multiplicators.ScoreExclusivity;

import java.util.List;

public class OrderedScorer implements QuestionScorer {

    public void reward(Points points) {
        points.gainAPoint();
    }

    public void punish(Points points) {
        points.changeScoreToZero();
    }

    public void getMultiplicator(List<Multiplicator> multiplicator) {
        multiplicator.add(new ScoreExclusivity());
    }
}
