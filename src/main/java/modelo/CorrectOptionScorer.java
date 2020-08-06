package modelo;

public class CorrectOptionScorer implements IOptionScorer {

    public void calculatePoints(QuestionScorer scorer, Points points) {
        scorer.reward(points);
    }

    public void changeState(Option option) {
        option.changeToIncorrect();
    }

}
