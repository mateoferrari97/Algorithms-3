package modelo.questions;


import exceptions.InvalidSizeException;
import modelo.game.Points;
import modelo.options.Option;
import modelo.scorers.QuestionScorer;

import java.util.ArrayList;
import java.util.List;

import static constantes.Constantes.MULTIPLE_CHOICE_QUESTION_TYPE;
import static constantes.ErrorMessage.INVALID_MULTIPLE_CHOICE_LIMIT_OF_SIZE;

public class MultipleChoiceQuestion extends Question {


    public MultipleChoiceQuestion(String text, List<Option> options, QuestionScorer scorer) throws InvalidSizeException {
        super();

        Integer optionsSize = options.size();
        if (optionsSize < 2 || optionsSize > 5) {
            throw new InvalidSizeException(INVALID_MULTIPLE_CHOICE_LIMIT_OF_SIZE + optionsSize);
        }


        this.options = options;
        this.text = text;
        this.scorer = scorer;
        this.type = MULTIPLE_CHOICE_QUESTION_TYPE;
        this.multiplicator = new ArrayList<>();
        this.scorer.getMultiplicator(multiplicator);
    }

    @Override
    public void selectOptions(List<Option> playerAnswers, Points points) {
        for (Option aOption : playerAnswers) {
            aOption.calculatePoints(scorer, points);
        }
    }
}
