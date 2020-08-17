package modelo.questions;

import consumables.Consumable;
import exceptions.InvalidSizeException;
import modelo.game.Player;
import modelo.game.Points;
import modelo.options.Option;
import modelo.scorers.QuestionScorer;

import java.util.List;

import static constantes.ErrorMessage.INVALID_MULTIPLE_CHOICE_LIMIT_OF_SIZE;

public class MultipleChoiceQuestion extends Question {


    public MultipleChoiceQuestion(String text, List<Option> options, QuestionScorer scorer, Consumable consumable) throws InvalidSizeException {
        super();

        Integer optionsSize = options.size();
        if (optionsSize < 2 || optionsSize > 5) {
            throw new InvalidSizeException(INVALID_MULTIPLE_CHOICE_LIMIT_OF_SIZE + optionsSize);
        }


        this.options = options;
        this.text = text;
        this.scorer = scorer;
        this.points = new Points();
        this.consumable = consumable;
        this.type = "MultipleChoice";
    }

    @Override
    public void selectOptions(List<Option> playerAnswers) {
        for(Option aOption : playerAnswers){
            aOption.calculatePoints(scorer, this.points);
        }
        if (!(this.isCorrect())) this.consumable.useWithIncorrectAnswer();
    }

    @Override
    public void score(Player player) {
        this.consumable.multiplicate(this.points);
        scorer.score(player,this.points);
    }

}
