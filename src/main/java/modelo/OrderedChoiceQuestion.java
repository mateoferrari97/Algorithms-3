package modelo;

import java.util.List;

public class OrderedChoiceQuestion extends Question {
    public OrderedChoiceQuestion(String text, List<Option> options, QuestionScorer scorer) {
        super();
        this.options = options;
        this.text = text;
        this.scorer = scorer;
        this.points = new Points();
    }

    public void score(Player player, List<Integer> playerAnswers) {
        for(Integer aInteger : playerAnswers){
            this.options.get(aInteger).calculatePoints(this.scorer, this.points);
            if(aInteger + 1 < options.size()) { // solo pregunta para ver el final del vector
                this.options.get(aInteger + 1).changeState(this.options.get(aInteger + 1));
            }
        }
        this.scorer.score(player, this.points);
    }
}
