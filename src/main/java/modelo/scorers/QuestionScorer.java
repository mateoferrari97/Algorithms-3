package modelo.scorers;


import modelo.Player;
import modelo.Points;

public interface QuestionScorer {
    void score(Player player, Points points);

    void reward(Points points);

    void punish(Points points);
}
