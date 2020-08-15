package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import modelo.options.CorrectOptionScorer;
import modelo.options.IncorrectOptionScorer;
import modelo.options.Option;

public class OptionFactory {
    public OptionFactory() {
    }

    public Option unmarshal(String json) {
        JsonParser parser = new JsonParser();
        JsonObject jObj = parser.parse(json).getAsJsonObject();

        return OptionFactory.unmarshal(jObj);
    }

    public static Option unmarshal(JsonObject json) {
        String text = json.get("text").getAsString();
        boolean isCorrect = json.get("optionScorer").getAsBoolean();
        if (isCorrect) {
            return new Option(text, new CorrectOptionScorer());
        }
        return new Option(text, new IncorrectOptionScorer());
    }

}
