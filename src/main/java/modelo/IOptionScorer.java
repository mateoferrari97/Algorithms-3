package modelo;

public interface IOptionScorer {
    void score(Player player);

    void calculatePoints(QuestionScorer scorer, Points points);
}
