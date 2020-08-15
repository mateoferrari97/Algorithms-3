package modelo.questions;

import exceptions.InvalidJsonRecognizerClassException;
import modelo.Multiplicator;
import modelo.Player;
import modelo.Points;
import modelo.options.Option;
import modelo.scorers.*;

import java.util.LinkedList;
import java.util.List;

public abstract class Question {
    protected String text;
    protected QuestionScorer scorer;
    protected List<Option> options;
    protected Points points;
    protected Multiplicator multiplicator;

    public void multiplicate(Integer factor){
        this.multiplicator.multiplicate(this.points, factor);
    }

    public abstract void score(Player player, List<Option> playerAnswers);

    public String getText() {
        return this.text;
    }

    public String[] getAnswerOptions() {
        int i = 0;
        String[] answersOptions = new String[this.options.size()];
        for(Option aOption : options){
            answersOptions[i] = aOption.getText();
            i++;
        }
        return answersOptions;
    }

    public List<Option> getOptions(){
        return this.options;
    }

    public void score(Player player, List<Option> playerOptionsLetters, List<Option> playerOptionsNumbers){}

    public List<Option> getCorrectOptions() {
        List<Option> options = new LinkedList<>();
        for (Option option: this.options) {
            if (option.isCorrect()) {
                options.add(option);
            }
        }

        return options;
    }

     static QuestionScorer selectScorer(String scorerString) throws InvalidJsonRecognizerClassException {
        switch (scorerString) {
            case "PenaltyScorer":
                return new PenaltyScorer();
            case "MultipleChoiceWithPartialScorer":
                return new MultipleChoiceWithPartialScorer();
            case "OrderedScorer":
                return new OrderedScorer();
            case "MultipleChoiceScorer":
                return new MultipleChoiceScorer();
            case "BooleanScorer":
                return new BooleanScorer();
            default:
                String error = "invalid Question Scorer class";
                throw new InvalidJsonRecognizerClassException(error);
        }
    }
}




