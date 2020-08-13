package modelo.questions;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import exceptions.InvalidJsonRecognizerClassException;
import exceptions.InvalidSizeException;
import modelo.Player;
import modelo.Points;
import modelo.options.Option;
import modelo.scorers.QuestionScorer;

import java.util.ArrayList;
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
            return new OrderedChoiceQuestion(text, options, questionScorer);
        } catch (Exception e) {
            throw e;
        }
    }

}
