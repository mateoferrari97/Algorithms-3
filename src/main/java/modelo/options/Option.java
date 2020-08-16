package modelo.options;

import com.google.gson.JsonObject;
import modelo.Points;
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

    public void changeState(Option option) {
        scorer.changeState(option);
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
