package modelo.questions;

import exceptions.InvalidSizeException;
import modelo.*;
import modelo.options.Option;
import modelo.scorers.QuestionScorer;

import java.util.List;

public class MultipleChoiceQuestion extends Question {
    public MultipleChoiceQuestion(String text, List<Option> options, QuestionScorer scorer) throws InvalidSizeException {
        super();

        Integer optionsSize = options.size();
        if (optionsSize < 2 || optionsSize > 5) {
            String error = "invalid options size: want minimum 2, maximum 5. got: " + optionsSize;
            throw new InvalidSizeException(error);
        }


        this.options = options;
        this.text = text;
        this.scorer = scorer;
        this.points = new Points();
    }

    public MultipleChoiceQuestion(String text, List<Option> options, QuestionScorer scorer, Multiplicator multiplicator) throws InvalidSizeException {
        super();

        Integer optionsSize = options.size();
        if (optionsSize < 2 || optionsSize > 5) {
            String error = "invalid options size: want minimum 2, maximum 5. got: " + optionsSize;
            throw new InvalidSizeException(error);
        }


        this.options = options;
        this.text = text;
        this.scorer = scorer;
        this.points = new Points();
        this.multiplicator = multiplicator;
    }

    @Override
    public void score(Player player, List<Option> playerAnswers) {
        for(Option aOption : playerAnswers){
            aOption.calculatePoints(scorer, this.points);
        }
        scorer.score(player,this.points);
    }

    @Override
    public Question question(String text, List<Option> options, QuestionScorer scorer) throws InvalidSizeException {
        return new MultipleChoiceQuestion(text,options,scorer);
    }
}
