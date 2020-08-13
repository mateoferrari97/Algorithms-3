package modelo.questions;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import exceptions.InvalidJsonRecognizerClassException;
import exceptions.InvalidSizeException;
import consumables.Consumable;
import modelo.Player;
import modelo.Points;
import consumables.ScoreExclusivity;
import modelo.options.CorrectOptionScorer;
import modelo.options.Option;
import modelo.scorers.QuestionScorer;

import java.util.ArrayList;
import java.util.List;

public class GroupChoiceQuestion extends Question {

    public GroupChoiceQuestion(String text, List<Option> options, QuestionScorer scorer, Consumable consumable) throws InvalidSizeException {
        super();

        Integer optionsSize = options.size();
        if (optionsSize < 2 || optionsSize > 6) {
            String error = "invalid options size: want minimum 2, maximum 6. got: " + optionsSize;
            throw new InvalidSizeException(error);
        }

        this.options = options;
        this.text = text;
        this.scorer = scorer;
        this.points = new Points();
        this.consumable = consumable;
    }

    @Override
    public void selectOptions(List<Option> playerAnswers) {

        Points minPoints = new Points();
        Points maxPoints = new Points();
        Points pointsDone = new Points();

        Option CorrectOption = new Option("", new CorrectOptionScorer());
        for (Option aOption : options) { CorrectOption.calculatePoints(this.scorer, maxPoints);}

        for (Option aOption : playerAnswers) { aOption.changeState(aOption);}
        for (Option aOption : options) { aOption.calculatePoints(this.scorer, pointsDone);}

        if (pointsDone.equals(minPoints) || pointsDone.equals(maxPoints)) { this.points.gainAPoint();}

        for (Option aOption : playerAnswers) { aOption.changeState(aOption);}
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
            return new GroupChoiceQuestion(text, options, questionScorer, new ScoreExclusivity());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void score(Player player) {
        if (!(this.isCorrect())) this.consumable.useWithIncorrectAnswer();
        this.consumable.multiplicate(this.points);
        scorer.score(player, this.points);
    }
}


