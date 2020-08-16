package modelo.questions;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import consumables.Consumable;
import consumables.Multiplicator;
import consumables.ScoreExclusivity;
import exceptions.InvalidJsonRecognizerClassException;
import exceptions.InvalidSizeException;
import modelo.*;
import modelo.options.Option;
import modelo.scorers.QuestionScorer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MultipleChoiceQuestion extends Question {


    public MultipleChoiceQuestion(String text, List<Option> options, QuestionScorer scorer, Consumable consumable) throws InvalidSizeException {
        super();

        Integer optionsSize = options.size();
        if (optionsSize < 2 || optionsSize > 5) {
            String error = "invalid options size: want minimum 2, maximum 5. got: " + optionsSize;
            throw new InvalidSizeException(error);
        }


        this.options = options;
        this.text = text;
        this.scorer = scorer;
        this.points = new Points();
        this.consumable = consumable;
        this.type = "multiple choice";
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
            return new MultipleChoiceQuestion(text, options, questionScorer, new Multiplicator());
        } catch (Exception e) {
            throw e;
        }
    }
}
