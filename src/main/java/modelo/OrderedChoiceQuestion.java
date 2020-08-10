package modelo;

import java.util.List;

public class OrderedChoiceQuestion extends Question {
    private Option nextOption;

    public OrderedChoiceQuestion(String text, List<Option> options, QuestionScorer scorer) {
        super();
        this.options = options;
        this.nextOption = options.get(1);
        this.text = text;
        this.scorer = scorer;
        this.points = new Points();
    }

    public void score(Player player, List<Option> playerAnswers) {
        int i = 2;
        for(Option aOption : playerAnswers){
            aOption.calculatePoints(this.scorer, this.points);
            this.nextOption.changeState(this.nextOption);
            if(i < this.options.size()) {
                this.nextOption = this.options.get(i);
                i++;
            }

        }
        this.scorer.score(player, this.points);
    }
}
