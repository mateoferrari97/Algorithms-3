package modelo.options;

import modelo.game.Points;
import modelo.scorers.QuestionScorer;

public class Option {
    private String text;
    private IOptionScorer scorer;

    public Option(String text, IOptionScorer scorer) {
        this.text = text;
        this.scorer = scorer;
    }

    public void calculatePoints(QuestionScorer scorer, Points points){
        this.scorer.calculatePoints(scorer, points);
    }

    public void changeState() {
        scorer.changeState(this);
    }

    public void changeToCorrect() {
        scorer = new CorrectOptionScorer();
    }

    public void changeToIncorrect() {
        scorer = new IncorrectOptionScorer();
    }

    public String getText() {
        return this.text;
    }


    public boolean isCorrect() {
        return scorer.isCorrect();
    }
}
