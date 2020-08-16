package modelo;

import consumables.Multiplicator;
import consumables.ScoreExclusivity;
import exceptions.InvalidSizeException;
import exceptions.NoMoreConsumablesException;
import modelo.options.CorrectOptionScorer;
import modelo.options.IncorrectOptionScorer;
import modelo.options.Option;
import modelo.questions.MultipleChoiceQuestion;
import modelo.questions.Question;
import modelo.scorers.MultipleChoiceWithPartialScorer;
import modelo.scorers.QuestionScorer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MultipleChoicePartialTest {
    @Test
    public void testMultipleChoiceQuestionWithPartialScoreIncreasePlayerPointsForeachOptionThatIsCorrect() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceWithPartialScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, new Multiplicator());

        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()));
        Integer expectedPlayerPoints = 4;

        // When
        question.selectOptions(playerOptions);
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testMultipleChoiceQuestionWithPartialScoreIncreasePlayerPointsFor3CorrectOptionAndOneIncorrect() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceWithPartialScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, new Multiplicator());

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

    @Test
    public void testMultipleChoicePartialDoublePointsWhenScoreExclusivityActivatedAndOnePlayerAnswerIncorrectly() throws InvalidSizeException, NoMoreConsumablesException {
        // Given
        ScoreExclusivity scoreExclusivity = new ScoreExclusivity();
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));
        QuestionScorer scorer = new MultipleChoiceWithPartialScorer();

        Question question1 = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, scoreExclusivity);
        Question question2 = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, scoreExclusivity);

        List<Option> player1Options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        List<Option> player2Options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()));

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
    public void testMultipleChoicePartialQuadruplePointsWhenScoreExclusivityActivatedAndOnePlayerAnswerIncorrectly() throws InvalidSizeException, NoMoreConsumablesException {
        // Given
        ScoreExclusivity scoreExclusivity = new ScoreExclusivity();
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));
        QuestionScorer scorer = new MultipleChoiceWithPartialScorer();

        Question question1 = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, scoreExclusivity);
        Question question2 = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, scoreExclusivity);

        List<Option> player1Options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        List<Option> player2Options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()));

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
    public void testMultipleChoicePartialDontModifyPointsWhenScoreExclusivityActivatedNoIncorrectAnswers() throws InvalidSizeException, NoMoreConsumablesException {
        // Given
        ScoreExclusivity scoreExclusivity = new ScoreExclusivity();
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));
        QuestionScorer scorer = new MultipleChoiceWithPartialScorer();

        Question question1 = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, scoreExclusivity);
        Question question2 = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, scoreExclusivity);

        List<Option> player1Options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()));

        List<Option> player2Options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()));

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
