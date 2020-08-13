package modelo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import consumables.ScoreExclusivity;
import exceptions.InvalidJsonRecognizerClassException;
import exceptions.InvalidSizeException;
import modelo.options.CorrectOptionScorer;
import modelo.options.IncorrectOptionScorer;
import modelo.options.Option;
import modelo.questions.MultipleChoiceQuestion;
import modelo.questions.OrderedChoiceQuestion;
import modelo.questions.Question;
import modelo.scorers.MultipleChoiceScorer;
import modelo.scorers.QuestionScorer;
import exceptions.NoMoreConsumablesException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

public class MultipleChoiceTest {

    @Test
    public void testMultipleChoiceQuestionIncreasePlayerPointsWhenAllOptionsAreCorrect() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, new ScoreExclusivity());

        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()));
        Integer expectedPlayerPoints = 1;

        // When
        question.selectOptions(playerOptions);
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testMultipleChoiceQuestionDontIncreasePlayerPointsForIncorectOption() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, new ScoreExclusivity());

        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));
        Integer expectedPlayerPoints = 0;

        // When
        question.selectOptions(playerOptions);
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testMultipleChoiceQuestionReceivesAOneOptionListAndThrowsException() throws InvalidSizeException {
        //Arrange
        List<Option> options = Arrays.asList(new Option("Malasia", new CorrectOptionScorer()));
        QuestionScorer scorer = new MultipleChoiceScorer();

        //Act and Assert
        thrown.expect(InvalidSizeException.class);
        Question question = new MultipleChoiceQuestion("Elegir los paises pertenecientes al continente asiatico", options, scorer, new ScoreExclusivity());

    }

    @Test
    public void testMultipleChoiceQuestionReceivesASixOptionListAndThrowsException() throws InvalidSizeException {
        //Arrange
        List<Option> options = Arrays.asList(
                new Option("Malasia", new CorrectOptionScorer()),
                new Option("China", new CorrectOptionScorer()),
                new Option("Francia", new IncorrectOptionScorer()),
                new Option("Laos", new CorrectOptionScorer()),
                new Option("Vietnam", new CorrectOptionScorer()),
                new Option("Japon", new CorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceScorer();

        //Act and Assert
        thrown.expect(InvalidSizeException.class);
        Question question = new MultipleChoiceQuestion("Elegir los paises pertenecientes al continente asiatico", options, scorer, new ScoreExclusivity());

    }


    @Test
    public void testUnmarshalMultipleChoiceQuestionAndTestHowItWorks() throws InvalidJsonRecognizerClassException, InvalidSizeException {

        String jString = "{\"text\": \"elegir las opciones que dan como resultado igual a 4\",\"scorer\": \"MultipleChoiceScorer\",\"options\":[{\"text\": \"2 + 2\",\"optionScorer\": true},{\"text\": \"2 * 2\",\"optionScorer\": true},{\"text\": \"1 + 3\",\"optionScorer\": true},{\"text\": \"2^2\",\"optionScorer\": true},{\"text\": \"1 - 3\",\"optionScorer\": false}]}";
        JsonParser parser = new JsonParser();
        JsonObject jObj = parser.parse(jString).getAsJsonObject();

        Question question = OrderedChoiceQuestion.unmarshal(jObj);

        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()));
        Integer expectedPlayerPoints = 1;

        // When
        question.selectOptions(playerOptions);
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);

    }

    @Test
    public void testMultipleChoiceDoublePointsWhenScoreExclusivityActivatedAndOnePlayerAnswerIncorrectly() throws InvalidSizeException, NoMoreConsumablesException {
        // Given
        ScoreExclusivity scoreExclusivity = new ScoreExclusivity();
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceScorer();
        Question question1 = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, scoreExclusivity);
        Question question2 = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, scoreExclusivity);

        List<Option> player1Options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        List<Option> player2Options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()));

        Player player1 = new Player();
        Player player2 = new Player();
        Integer expectedPlayer1Points = 0;
        Integer expectedPlayer2Points = 2;


        // When
        player1.activateConsumable(scoreExclusivity);
        question1.selectOptions(player1Options);
        question2.selectOptions(player2Options);
        question1.score(player1);
        question2.score(player2);

        // Then
        Assert.assertEquals(player1.getPoints(), expectedPlayer1Points);
        Assert.assertEquals(player2.getPoints(), expectedPlayer2Points);
    }

    @Test
    public void testMultipleChoiceQuadruplePointsWhenScoreExclusivityActivatedAndOnePlayerAnswerIncorrectly() throws InvalidSizeException, NoMoreConsumablesException {
        // Given
        ScoreExclusivity scoreExclusivity = new ScoreExclusivity();
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceScorer();
        Question question1 = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, scoreExclusivity);
        Question question2 = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, scoreExclusivity);

        List<Option> player1Options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        List<Option> player2Options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()));

        Player player1 = new Player();
        Player player2 = new Player();
        Integer expectedPlayer1Points = 0;
        Integer expectedPlayer2Points = 4;

        // When
        player1.activateConsumable(scoreExclusivity);
        player2.activateConsumable(scoreExclusivity);
        question1.selectOptions(player1Options);
        question2.selectOptions(player2Options);
        question1.score(player1);
        question2.score(player2);

        // Then
        Assert.assertEquals(player1.getPoints(), expectedPlayer1Points);
        Assert.assertEquals(player2.getPoints(), expectedPlayer2Points);
    }

    @Test
    public void testMultipleChoiceDontModifyPointsWhenScoreExclusivityActivatedNoIncorrectAnswers() throws InvalidSizeException, NoMoreConsumablesException {
        // Given
        ScoreExclusivity scoreExclusivity = new ScoreExclusivity();
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceScorer();
        Question question1 = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, scoreExclusivity);
        Question question2 = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, scoreExclusivity);

        List<Option> player1Options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()));

        List<Option> player2Options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()));

        Player player1 = new Player();
        Player player2 = new Player();
        Integer expectedPlayer1Points = 0;
        Integer expectedPlayer2Points = 0;

        // When
        player1.activateConsumable(scoreExclusivity);
        question1.selectOptions(player1Options);
        question2.selectOptions(player2Options);
        question1.score(player1);
        question2.score(player2);

        // Then
        Assert.assertEquals(player1.getPoints(), expectedPlayer1Points);
        Assert.assertEquals(player2.getPoints(), expectedPlayer2Points);
    }
}
