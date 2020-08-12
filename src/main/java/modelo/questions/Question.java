package modelo.questions;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import exceptions.InvalidJsonRecognizerClassException;
import exceptions.InvalidSizeException;
import modelo.*;
import modelo.options.Option;
import modelo.scorers.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Question {
    protected String text;
    protected QuestionScorer scorer;
    protected List<Option> options;
    protected Points points;
    protected Multiplicator multiplicator;

    public void multiplicate(Integer factor){
        this.multiplicator.multiplicate(this.points, factor);
    }

    public abstract void score(Player player, List<Option> playerAnswers);

    public String getText() {
        return this.text;
    }

    public String[] getAnswerOptions() {
        int i = 0;
        String[] answersOptions = new String[this.options.size()];
        for(Option aOption : options){
            answersOptions[i] = aOption.getText();
            i++;
        }
        return answersOptions;
    }

    public List<Option> getOptions(){
        return this.options;
    }

    public void score(Player player, List<Option> playerOptionsLetters, List<Option> playerOptionsNumbers){}

    public abstract Question question(String text, List<Option> options, QuestionScorer scorer) throws InvalidSizeException;

    public Question unmarshal(JsonObject json) throws InvalidJsonRecognizerClassException, InvalidSizeException {
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

            Question question = question(text, options, questionScorer);
            return question;
        } catch (Exception e) {
            throw e;
        }
    }

    private QuestionScorer selectScorer(String scorerString) throws InvalidJsonRecognizerClassException {
        switch (scorerString) {
            case "PenaltyScorer":
                return new PenaltyScorer();
            case "MultipleChoiceWithPartialScorer":
                return new MultipleChoiceWithPartialScorer();
            case "OrderedScorer":
                return new OrderedScorer();
            case "MultipleChoiceScorer":
                return new MultipleChoiceScorer();
            case "BooleanScorer":
                return new BooleanScorer();
            default:
                String error = "invalid Question Scorer class";
                throw new InvalidJsonRecognizerClassException(error);
        }
    }
}




