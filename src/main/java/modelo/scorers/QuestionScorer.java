package modelo.scorers;


import modelo.game.Points;
import modelo.multiplicators.Multiplicator;

import java.util.List;

public interface QuestionScorer {

    void reward(Points points);

    void punish(Points points);

    void getMultiplicator(List<Multiplicator> multiplicator);
}
