package modelo;

import exceptions.InvalidSizeException;
import java.util.List;

public class GroupChoiceQuestion extends Question {

    public GroupChoiceQuestion(String test, List<Option> options, QuestionScorer scorer, Consumable consumable) throws InvalidSizeException {
        super();

        Integer optionsSize = options.size();
        if (optionsSize < 2 || optionsSize > 6) {
            String error = "invalid options size: want minimum 2, maximum 6. got: " + optionsSize;
            throw new InvalidSizeException(error);
        }

        this.options = options;
        this.text = text;
        this.scorer = scorer;
        this.points = new Points();
        this.consumable = consumable;
    }

    @Override
    public void selectOptions(List<Option> playerAnswers) {

        Points minPoints = new Points();
        Points maxPoints = new Points();
        Points pointsDone = new Points();

        Option CorrectOption = new Option("", new CorrectOptionScorer());
        for (Option aOption : options) { CorrectOption.calculatePoints(this.scorer, maxPoints);}

        for (Option aOption : playerAnswers) { aOption.changeState(aOption);}
        for (Option aOption : options) { aOption.calculatePoints(this.scorer, pointsDone);}

        if (pointsDone.equals(minPoints) || pointsDone.equals(maxPoints)) { this.points.gainAPoint();}

        if (!(this.isCorrect())) {
            this.consumable.useWithIncorrectAnswer();
        }

        for (Option aOption : playerAnswers) { aOption.changeState(aOption);}
    }

    @Override
    public void score(Player player) {
        this.consumable.multiplicate(this.points);
        scorer.score(player, this.points);
    }
}


