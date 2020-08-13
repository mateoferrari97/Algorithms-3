package modelo;
import java.util.List;

public class BooleanQuestion extends Question {

    public BooleanQuestion(String text, List<Option> options, QuestionScorer scorer, Consumable consumable) {
        super();
        this.options = options;
        this.text = text;
        this.scorer = scorer;
        this.points = new Points();
        this.consumable = consumable;
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
