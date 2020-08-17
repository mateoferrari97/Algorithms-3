package modelo.questions;

import modelo.consumables.*;
import modelo.game.Player;
import modelo.game.Points;
import modelo.options.Option;
import modelo.scorers.QuestionScorer;

import java.util.List;

import static constantes.Constantes.ORDERED_CHOICE_QUESTION_TYPE;

public class OrderedChoiceQuestion extends Question {
    private Option nextOption;

    public OrderedChoiceQuestion(String text, List<Option> options, QuestionScorer scorer, Consumable consumable) {
        super();
        this.options = options;
        this.nextOption = options.get(1);
        this.text = text;
        this.scorer = scorer;
        this.points = new Points();
        this.consumable = consumable;
        this.type = ORDERED_CHOICE_QUESTION_TYPE;
    }

    public void selectOptions(List<Option> playerAnswers) {
        int i = 2;
        for(Option aOption : playerAnswers){
            aOption.calculatePoints(this.scorer, this.points);
            this.nextOption.changeState(this.nextOption);
            if(i < this.options.size()) {
                this.nextOption = this.options.get(i);
                i++;
            }
        }
        if (!(this.isCorrect())) { this.consumable.useWithIncorrectAnswer();}
    }

    public void score(Player player) {

        this.consumable.multiplicate(this.points);
        this.scorer.score(player, this.points);
    }

    @Override
    public List<Option> getCorrectOptions() {
        return this.options;
    }
}
