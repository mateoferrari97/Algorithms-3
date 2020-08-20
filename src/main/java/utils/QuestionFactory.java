package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import exceptions.InvalidJsonRecognizerClassException;
import exceptions.InvalidSizeException;
import modelo.options.Option;
import modelo.questions.*;
import modelo.scorers.*;

import java.util.ArrayList;
import java.util.List;

import static constantes.Constantes.*;
import static constantes.ErrorMessage.INVALID_QUESTION_ERROR;
import static constantes.ErrorMessage.INVALID_QUESTION_SCORER_ERROR;

public class QuestionFactory {
    public QuestionFactory() {
    }

    public List<Question> unmarshalArrayOfQuestions(String json) throws InvalidSizeException, InvalidJsonRecognizerClassException {
        JsonParser parser = new JsonParser();
        JsonArray jObj = parser.parse(json).getAsJsonArray();

        return QuestionFactory.unmarshalArrayOfQuestions(jObj);

    }

    public static List<Question> unmarshalArrayOfQuestions(JsonArray json) throws InvalidSizeException, InvalidJsonRecognizerClassException {
        List<Question> questions = new ArrayList<Question>();
        for (JsonElement jsonOption : json) {
            Question question = QuestionFactory.unmarshal(jsonOption.getAsJsonObject());
            questions.add(question);
        }
        return questions;
    }


    public Question unmarshal(String json) throws InvalidSizeException, InvalidJsonRecognizerClassException {
        JsonParser parser = new JsonParser();
        JsonObject jObj = parser.parse(json).getAsJsonObject();

        return QuestionFactory.unmarshal(jObj);
    }

    public static Question unmarshal(JsonObject json) throws InvalidJsonRecognizerClassException, InvalidSizeException {
        String text = json.get(QUESTION_GET_TEXT).getAsString();
        String scorerType = json.get(QUESTION_GET_SCORER_TYPE).getAsString();
        String type = json.get(QUESTION_GET_TYPE).getAsString();

        List<Option> options = new ArrayList<Option>();
        JsonArray arrayOptions = json.getAsJsonArray(QUESTION_GET_OPTIONS);
        for (JsonElement jsonOption : arrayOptions) {
            Option option = OptionFactory.unmarshal(jsonOption.getAsJsonObject());
            options.add(option);
        }

        QuestionScorer questionScorer = selectScorer(scorerType);
        return selectQuestion(type, text, options, questionScorer);
    }

    static Question selectQuestion(String type, String text, List<Option> options, QuestionScorer scorer) throws InvalidJsonRecognizerClassException, InvalidSizeException {
        switch (type) {
            case BOOLEAN_QUESTION_TYPE:
                return new BooleanQuestion(text, options, scorer);
            case GROUP_CHOCIE_QUESTION_TYPE:
                return new GroupChoiceQuestion(text, options, scorer);
            case ORDERED_CHOICE_QUESTION_TYPE:
                return new OrderedChoiceQuestion(text, options, scorer);
            case MULTIPLE_CHOICE_QUESTION_TYPE:
                return new MultipleChoiceQuestion(text, options, scorer);
            default:
                throw new InvalidJsonRecognizerClassException(INVALID_QUESTION_ERROR);
        }


    }

    static QuestionScorer selectScorer(String scorerType) throws InvalidJsonRecognizerClassException {
        switch (scorerType) {
            case PENALTY_SCORER_TYPE:
                return new PenaltyScorer();
            case MULTIPLE_CHOICE_PARTIAL_SCORER_TYPE:
                return new MultipleChoiceWithPartialScorer();
            case ORDERED_SCORER_TYPE:
                return new OrderedScorer();
            case MULTIPLE_CHOICE_SCORER_TYPE:
                return new MultipleChoiceScorer();
            case BOOLEAN_SCORER_TYPE:
                return new BooleanScorer();
            default:
                throw new InvalidJsonRecognizerClassException(INVALID_QUESTION_SCORER_ERROR);
        }
    }
}
