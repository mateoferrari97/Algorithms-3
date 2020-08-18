package modelo.questions;


import exceptions.InvalidSizeException;
import modelo.game.Player;
import modelo.game.Points;
import modelo.options.CorrectOptionScorer;
import modelo.options.Option;
import modelo.scorers.QuestionScorer;

import java.util.ArrayList;
import java.util.List;

import static constantes.Constantes.GROUP_CHOCIE_QUESTION_TYPE;
import static constantes.ErrorMessage.INVALID_GROUP_CHOICE_OPTION_LIMIT_OF_SIZE;

public class GroupChoiceQuestion extends Question {

    public GroupChoiceQuestion(String text, List<Option> options, QuestionScorer scorer) throws InvalidSizeException {
        super();

        Integer optionsSize = options.size();
        if (optionsSize < 2 || optionsSize > 6) {
            throw new InvalidSizeException(INVALID_GROUP_CHOICE_OPTION_LIMIT_OF_SIZE + optionsSize);
        }

        this.options = options;
        this.text = text;
        this.scorer = scorer;
        this.points = new Points();
        this.type = GROUP_CHOCIE_QUESTION_TYPE;
        this.multiplicator = new ArrayList<>();
        this.scorer.getMultiplicator(multiplicator);
    }

    @Override
    public void selectOptions(List<Option> playerAnswers, Points points) {

        Points minPoints = new Points();
        Points maxPoints = new Points();
        Points pointsDone = new Points();

        Option CorrectOption = new Option("", new CorrectOptionScorer());
        for (Option aOption : options) {
            CorrectOption.calculatePoints(this.scorer, maxPoints);
        }

        for (Option aOption : playerAnswers) {
            aOption.changeState();
        }
        for (Option aOption : options) {
            aOption.calculatePoints(this.scorer, pointsDone);
        }

        if (pointsDone.equals(minPoints) || pointsDone.equals(maxPoints)) {
            this.points.gainAPoint();
        }

        for (Option aOption : playerAnswers) {
            aOption.changeState();
        }

    }
}


