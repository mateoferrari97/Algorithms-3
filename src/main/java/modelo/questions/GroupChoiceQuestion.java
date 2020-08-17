package modelo.questions;

import modelo.consumables.*;
import exceptions.InvalidSizeException;
import modelo.game.Player;
import modelo.game.Points;
import modelo.options.CorrectOptionScorer;
import modelo.options.Option;
import modelo.scorers.QuestionScorer;

import java.util.List;

import static constantes.Constantes.GROUP_CHOCIE_QUESTION_TYPE;
import static constantes.ErrorMessage.INVALID_GROUP_CHOICE_OPTION_LIMIT_OF_SIZE;

public class GroupChoiceQuestion extends Question {

    public GroupChoiceQuestion(String text, List<Option> options, QuestionScorer scorer, Consumable consumable) throws InvalidSizeException {
        super();

        Integer optionsSize = options.size();
        if (optionsSize < 2 || optionsSize > 6) {
            throw new InvalidSizeException(INVALID_GROUP_CHOICE_OPTION_LIMIT_OF_SIZE + optionsSize);
        }

        this.options = options;
        this.text = text;
        this.scorer = scorer;
        this.points = new Points();
        this.consumable = consumable;
        this.type = GROUP_CHOCIE_QUESTION_TYPE;
    }

    @Override
    public void selectOptions(List<Option> playerAnswers) {

        Points minPoints = new Points();
        Points maxPoints = new Points();
        Points pointsDone = new Points();

        Option CorrectOption = new Option("", new CorrectOptionScorer());
        for (Option aOption : options) {
            CorrectOption.calculatePoints(this.scorer, maxPoints);
        }

        for (Option aOption : playerAnswers) {
            aOption.changeState(aOption);
        }
        for (Option aOption : options) {
            aOption.calculatePoints(this.scorer, pointsDone);
        }

        if (pointsDone.equals(minPoints) || pointsDone.equals(maxPoints)) {
            this.points.gainAPoint();
        }

        for (Option aOption : playerAnswers) {
            aOption.changeState(aOption);
        }

        if (!(this.isCorrect())) this.consumable.useWithIncorrectAnswer();
    }


    @Override
    public void score(Player player) {
        this.consumable.multiplicate(this.points);
        scorer.score(player, this.points);
    }

    @Override
    public List<Option> getCorrectOptions() {
        return null;
    }
}


