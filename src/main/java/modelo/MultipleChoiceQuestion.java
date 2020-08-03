package modelo;

import exceptions.InvalidSizeException;

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

    public void score(Player player) {
        for(Option aOption : options){
            aOption.calculatePoints(scorer, this.points);
        }
        scorer.score(player,this.points);
    }
}
