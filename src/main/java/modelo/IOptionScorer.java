package modelo;

public interface IOptionScorer {
    void calculatePoints(QuestionScorer scorer, Points points);

    void changeState(Option option);
}
