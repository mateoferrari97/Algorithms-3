package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import consumables.Multiplicator;
import exceptions.InvalidJsonRecognizerClassException;
import exceptions.InvalidSizeException;
import modelo.options.Option;
import modelo.questions.*;
import modelo.scorers.*;

import java.util.ArrayList;
import java.util.List;

public class QuestionFactory {
    public QuestionFactory() {
    }

    public Question unmarshal(String json) throws InvalidSizeException, InvalidJsonRecognizerClassException {
        JsonParser parser = new JsonParser();
        JsonObject jObj = parser.parse(json).getAsJsonObject();

        return QuestionFactory.unmarshal(jObj);
    }

    public static Question unmarshal(JsonObject json) throws InvalidJsonRecognizerClassException, InvalidSizeException {
        try {
            String text = json.get("text").getAsString();
            String scorerString = json.get("scorer").getAsString();
            String type = json.get("type").getAsString();

            List<Option> options = new ArrayList<Option>();
            JsonArray arrayOptions = json.getAsJsonArray("options");
            for (JsonElement jsonOption : arrayOptions) {
                Option option = OptionFactory.unmarshal(jsonOption.getAsJsonObject());
                options.add(option);
            }

            QuestionScorer questionScorer = selectScorer(scorerString);

            //Question question = question(text, options, questionScorer);
            return selectQuestion(type,text,options,questionScorer);
        } catch (Exception e) {
            throw e;
        }
    }

    static Question selectQuestion(String type, String text, List<Option> options, QuestionScorer scorer) throws InvalidJsonRecognizerClassException,InvalidSizeException  {
        switch (type) {
            case "BooleanQuestion":
                return new BooleanQuestion(text,options,scorer,new Multiplicator());
            case "GroupChoiceQuestion":
                return new GroupChoiceQuestion(text,options,scorer,new Multiplicator());
            case "OrderedChoiceQuestion":
                return new OrderedChoiceQuestion(text,options,scorer,new Multiplicator());
            case "MultipleChoiceQuestion":
                return new MultipleChoiceQuestion(text,options,scorer,new Multiplicator());
            default:
                String error = "invalid Question class";
                throw new InvalidJsonRecognizerClassException(error);
        }




    }

    static QuestionScorer selectScorer(String scorerString) throws InvalidJsonRecognizerClassException {
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
