package modelo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import exceptions.InvalidJsonRecognizerClassException;
import exceptions.InvalidSizeException;
import modelo.options.CorrectOptionScorer;
import modelo.options.IncorrectOptionScorer;
import modelo.options.Option;
import modelo.questions.OrderedChoiceQuestion;
import modelo.questions.Question;
import modelo.scorers.OrderedScorer;
import modelo.scorers.QuestionScorer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class OrderedChoiceTest {
    @Test
    public void testOrderedChoiceQuestionIncreasePlayerPointsWhenOptionsAreInTheCorrectOrder() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("Primero", new CorrectOptionScorer()),
                new Option("Segundo", new IncorrectOptionScorer()),
                new Option("Tercero", new IncorrectOptionScorer()),
                new Option("Cuarto", new IncorrectOptionScorer()));

        QuestionScorer scorer = new OrderedScorer();
        Question question = new OrderedChoiceQuestion("ordene correctamente las siguientes opciones", options, scorer);

        Player player = new Player();
        List<Option> playerOptions = options;
        Integer expectedPlayerPoints = 1;

        // When
        question.score(player,playerOptions);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testOrderedChoiceQuestionDoesNotIncreasePlayerPointsWhenOptionsAreInTheIncorrectOrder() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("Primero", new CorrectOptionScorer()),
                new Option("Segundo", new IncorrectOptionScorer()),
                new Option("Tercero", new IncorrectOptionScorer()),
                new Option("Cuarto", new IncorrectOptionScorer()));

        QuestionScorer scorer = new OrderedScorer();
        Question question = new OrderedChoiceQuestion("ordene correctamente las siguientes opciones", options, scorer);

        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(
                options.get(0),
                options.get(1),
                options.get(3),
                options.get(2));
        Integer expectedPlayerPoints = 0;

        // When
        question.score(player,playerOptions);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testUnmarshalOrderedChoiceQuestionAndTestHowItWorks() throws InvalidJsonRecognizerClassException, InvalidSizeException {

        String jString = "{\"text\": \"ordene correctamente las siguientes opciones\",\"scorer\": \"OrderedScorer\",\"options\": [{\"text\": \"Primero\",\"optionScorer\": true},{\"text\": \"Segundo\",\"optionScorer\": false},{\"text\": \"Tercero\",\"optionScorer\": false},{\"text\": \"Cuarto\",\"optionScorer\": false}]}";
        JsonParser parser = new JsonParser();
        JsonObject jObj = parser.parse(jString).getAsJsonObject();

        Question question = OrderedChoiceQuestion.unmarshal(jObj);

        List<Option> options = Arrays.asList(
                new Option("Primero", new CorrectOptionScorer()),
                new Option("Segundo", new IncorrectOptionScorer()),
                new Option("Tercero", new IncorrectOptionScorer()),
                new Option("Cuarto", new IncorrectOptionScorer()));

        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(
                options.get(0),
                options.get(1),
                options.get(3),
                options.get(2));
        Integer expectedPlayerPoints = 0;

        // When
        question.score(player,playerOptions);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);

    }

}
