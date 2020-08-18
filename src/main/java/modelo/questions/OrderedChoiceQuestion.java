package modelo.questions;


import modelo.game.Player;
import modelo.game.Points;
import modelo.options.Option;
import modelo.scorers.QuestionScorer;

import java.util.ArrayList;
import java.util.List;

import static constantes.Constantes.ORDERED_CHOICE_QUESTION_TYPE;

public class OrderedChoiceQuestion extends Question {
    private Option nextOption;

    public OrderedChoiceQuestion(String text, List<Option> options, QuestionScorer scorer) {
        super();
        this.options = options;
        this.nextOption = options.get(1);
        this.text = text;
        this.scorer = scorer;
        this.points = new Points();
        this.type = ORDERED_CHOICE_QUESTION_TYPE;
        this.multiplicator = new ArrayList<>();
        this.scorer.getMultiplicator(multiplicator);
    }

    public void selectOptions(List<Option> playerAnswers, Points points) {
        int i = 2;
        for (Option aOption : playerAnswers) {
            aOption.calculatePoints(this.scorer, this.points);
            this.nextOption.changeState();
            if (i < this.options.size()) {
                this.nextOption = this.options.get(i);
                i++;
            }
        }
        for(int j = 1; j < options.size() - 1; j++){
            options.get(j).changeState();
        }
        this.nextOption = options.get(1);
    }

    @Override
    public List<Option> getCorrectOptions() {
        return this.options;
    }

}
