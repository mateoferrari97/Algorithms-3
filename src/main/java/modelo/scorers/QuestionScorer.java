package modelo.scorers;


import modelo.game.Player;
import modelo.game.Points;
import modelo.multiplicators.Multiplicator;

import java.util.List;

public interface QuestionScorer {
    void score(List<Player> player, Points playerOne, Points points);

    void reward(Points points);

    void punish(Points points);

    void getMultiplicator(List<Multiplicator> multiplicator);
}
