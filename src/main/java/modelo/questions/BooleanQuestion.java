package modelo.questions;
import modelo.*;
import modelo.options.Option;
import modelo.scorers.QuestionScorer;

import java.util.List;

public class BooleanQuestion extends Question {
    public BooleanQuestion(String text, List<Option> options, QuestionScorer scorer) {
        super();
        this.options = options;
        this.text = text;
        this.scorer = scorer;
        this.points = new Points();
    }

    public BooleanQuestion(String text, List<Option> options, QuestionScorer scorer, Multiplicator multiplicator) {
        super();
        this.options = options;
        this.text = text;
        this.scorer = scorer;
        this.points = new Points();
        this.multiplicator = multiplicator;
    }


    public void score(Player player, List<Option> playerAnswers) {
        for(Option aOption : playerAnswers){
            aOption.calculatePoints(scorer, this.points);
        }
        scorer.score(player,this.points);
    }

    @Override
    public Question question(String text, List<Option> options, QuestionScorer scorer) {
        return new BooleanQuestion(text,options,scorer);
    }
}
