package modelo.questions;


import modelo.game.Player;
import modelo.game.Points;
import modelo.options.Option;
import modelo.scorers.QuestionScorer;

import java.util.ArrayList;
import java.util.List;

import static constantes.Constantes.BOOLEAN_QUESTION_TYPE;

public class BooleanQuestion extends Question {
    public BooleanQuestion(String text, List<Option> options, QuestionScorer scorer) {
        super();
        this.options = options;
        this.text = text;
        this.scorer = scorer;
        this.points = new Points();
        this.type = BOOLEAN_QUESTION_TYPE;
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
