package modelo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import consumables.Multiplicator;
import consumables.ScoreExclusivity;
import exceptions.InvalidJsonRecognizerClassException;
import exceptions.InvalidSizeException;
import modelo.options.CorrectOptionScorer;
import modelo.options.IncorrectOptionScorer;
import modelo.options.Option;
import modelo.questions.BooleanQuestion;
import modelo.questions.Question;
import modelo.scorers.BooleanScorer;
import modelo.scorers.QuestionScorer;
import exceptions.NoMoreConsumablesException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class BooleanQuestionTest {

    @Test
    public void testBooleanQuestionIncreasePlayerPointsWhenOptionIsCorrect() {
        // Given
        List<Option> options = Arrays.asList(
                new Option("si", new CorrectOptionScorer()),
                new Option("no", new IncorrectOptionScorer()));
        QuestionScorer scorer = new BooleanScorer();
        Question question = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer, new Multiplicator());

        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(new Option("si", new CorrectOptionScorer()));
        Integer expectedPlayerPoints = 1;

        // When
        question.selectOptions(playerOptions);
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testBooleanQuestionDontIncreasePlayerPointsWhenOptionIsIncorrect() {
        // Given
        List<Option> options = Arrays.asList(
                new Option("si", new CorrectOptionScorer()),
                new Option("no", new IncorrectOptionScorer()));
        QuestionScorer scorer = new BooleanScorer();
        Question question = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer, new Multiplicator());

        Player player = new Player();
        player.setPoints(5);
        List<Option> playerOptions = Arrays.asList(new Option("no", new IncorrectOptionScorer()));
        Integer expectedPlayerPoints = 5;

        // When
        question.selectOptions(playerOptions);
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }


    @Test
    public void testUnmarshalBooleanQuestionAndTestHowItWorks() throws InvalidJsonRecognizerClassException, InvalidSizeException {

        String jString = "{\"text\": \"vamos a aprobar algoritmos 3?\",\"scorer\": \"BooleanScorer\",\"options\":[{\"text\": \"si\",\"optionScorer\": true},{\"text\": \"no\",\"optionScorer\": false}]}";

        JsonParser parser = new JsonParser();
        JsonObject jObj = parser.parse(jString).getAsJsonObject();

        Question question = BooleanQuestion.unmarshal(jObj);

        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(new Option("si", new CorrectOptionScorer()));
        Integer expectedPlayerPoints = 1;

        // When
        question.selectOptions(playerOptions);
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }



    @Test
    public void testBooleanQuestionDoublePointsWhenScoreExclusivityActivatedAndOnePlayerAnswerIncorrectly() throws NoMoreConsumablesException {
        // Given
        ScoreExclusivity scoreExclusivity = new ScoreExclusivity();
        List<Option> options = Arrays.asList(
                new Option("si", new CorrectOptionScorer()),
                new Option("no", new IncorrectOptionScorer()));
        QuestionScorer scorer = new BooleanScorer();

        Question question1 = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer, scoreExclusivity);
        Question question2 = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer, scoreExclusivity);

        Player player1 = new Player();
        Player player2 = new Player();

        List<Option> player1Options = Arrays.asList(new Option("no", new IncorrectOptionScorer()));
        List<Option> player2Options = Arrays.asList(new Option("si", new CorrectOptionScorer()));

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
    public void testBooleanQuestionQuadruplePointsWhenScoreExclusivityTwoTimesActivatedAndOnePlayerAnswerIncorrectly() throws NoMoreConsumablesException {
        // Given
        ScoreExclusivity scoreExclusivity = new ScoreExclusivity();
        List<Option> options = Arrays.asList(
                new Option("si", new CorrectOptionScorer()),
                new Option("no", new IncorrectOptionScorer()));
        QuestionScorer scorer = new BooleanScorer();

        Question question1 = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer, scoreExclusivity);
        Question question2 = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer, scoreExclusivity);

        Player player1 = new Player();
        Player player2 = new Player();

        List<Option> player1Options = Arrays.asList(new Option("no", new IncorrectOptionScorer()));
        List<Option> player2Options = Arrays.asList(new Option("si", new CorrectOptionScorer()));

        Integer expectedPlayer1Points = 0;
        Integer expectedPlayer2Points = 4;


        // When
        player1.activateConsumable(scoreExclusivity);
        question1.selectOptions(player1Options);
        player2.activateConsumable(scoreExclusivity);
        question2.selectOptions(player2Options);
        question1.score(player1);
        question2.score(player2);

        // Then
        Assert.assertEquals(player1.getPoints(), expectedPlayer1Points);
        Assert.assertEquals(player2.getPoints(), expectedPlayer2Points);
    }

    @Test
    public void testBooleanQuestionDontModifyPointsWhenScoreExclusivityActivatedNoIncorrectAnswers() throws NoMoreConsumablesException {
        // Given
        ScoreExclusivity scoreExclusivity = new ScoreExclusivity();
        List<Option> options = Arrays.asList(
                new Option("si", new CorrectOptionScorer()),
                new Option("no", new IncorrectOptionScorer()));
        QuestionScorer scorer = new BooleanScorer();

        Question question1 = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer, scoreExclusivity);
        Question question2 = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer, scoreExclusivity);

        Player player1 = new Player();
        Player player2 = new Player();

        List<Option> player1Options = Arrays.asList(new Option("si", new CorrectOptionScorer()));
        List<Option> player2Options = Arrays.asList(new Option("si", new CorrectOptionScorer()));

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
