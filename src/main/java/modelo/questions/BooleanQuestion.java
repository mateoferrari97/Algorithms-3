package modelo.questions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import consumables.Consumable;
import consumables.ScoreExclusivity;
import exceptions.InvalidJsonRecognizerClassException;
import exceptions.InvalidSizeException;
import modelo.*;
import modelo.options.Option;
import modelo.scorers.QuestionScorer;

import java.util.ArrayList;
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
    }

    @Override
    public void score(Player player) {
        if (!(this.isCorrect())) this.consumable.useWithIncorrectAnswer();
        this.consumable.multiplicate(this.points);
        scorer.score(player,this.points);
    }

    public static Question unmarshal(JsonObject json) throws InvalidJsonRecognizerClassException, InvalidSizeException {
        try {

            String text = json.get("text").getAsString();
            String scorerString = json.get("scorer").getAsString();

            List<Option> options = new ArrayList<Option>();
            JsonArray arrayOptions = json.getAsJsonArray("options");
            for (JsonElement jsonOption : arrayOptions) {
                Option option = Option.unmarshal(jsonOption.getAsJsonObject());
                options.add(option);
            }

            QuestionScorer questionScorer = selectScorer(scorerString);

            //Question question = question(text, options, questionScorer);
            return new BooleanQuestion(text, options, questionScorer, new ScoreExclusivity());
        } catch (Exception e) {
            throw e;
        }
    }
}
