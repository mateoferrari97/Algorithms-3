package modelo.scorers;


import modelo.game.Player;
import modelo.game.Points;

public interface QuestionScorer {
    void score(Player player, Points points);

    void reward(Points points);

    void punish(Points points);
}
