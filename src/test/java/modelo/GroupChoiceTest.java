package modelo;

import exceptions.InvalidSizeException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class GroupChoiceTest {
    @Test
    public void testGroupChoiceQuestionIncreasePlayerPointsWhenCorrectGroupSeparation() throws InvalidSizeException {
        // Given
        Player player = new Player();
        Integer expectedPlayerPoints = 1;

        List<Option> options = Arrays.asList(
                new Option("A", new CorrectOptionScorer()),
                new Option("B", new CorrectOptionScorer()),
                new Option("C", new CorrectOptionScorer()),
                new Option("1", new IncorrectOptionScorer()),
                new Option("2", new IncorrectOptionScorer()));


        QuestionScorer scorer = new BooleanScorer();
        Question question = new GroupChoiceQuestion("Separar en numeros y letras", options, scorer, new ScoreExclusivity());

        List<Option> playerOptions = Arrays.asList(
                options.get(3),
                options.get(4));

        // When
        question.selectOptions(playerOptions);
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testGroupChoiceQuestionDontIncreasePlayerPointsWhenInorrectGroupSeparation() throws InvalidSizeException {

        // Given
        Player player = new Player();
        Integer expectedPlayerPoints = 0;

        List<Option> options = Arrays.asList(
                new Option("A", new CorrectOptionScorer()),
                new Option("B", new CorrectOptionScorer()),
                new Option("C", new CorrectOptionScorer()),
                new Option("1", new IncorrectOptionScorer()),
                new Option("2", new IncorrectOptionScorer()));

        QuestionScorer scorer = new BooleanScorer();
        Question question = new GroupChoiceQuestion("Separar en numeros y letras", options, scorer, new ScoreExclusivity());


        List<Option> playerOptions = Arrays.asList(
                options.get(0),
                options.get(1),
                options.get(3));

        // When
        question.selectOptions(playerOptions);
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testGroupChoiceDoublePointsWhenScoreExclusivityActivatedAndOnePlayerAnswerIncorrectly() throws InvalidSizeException {
        // Given
        ScoreExclusivity scoreExclusivity = new ScoreExclusivity();


        Player player1 = new Player();
        Player player2 = new Player();
        Integer expectedPlayer1Points = 0;
        Integer expectedPlayer2Points = 2;

        List<Option> options = Arrays.asList(
                new Option("A", new CorrectOptionScorer()),
                new Option("B", new CorrectOptionScorer()),
                new Option("C", new CorrectOptionScorer()),
                new Option("1", new IncorrectOptionScorer()),
                new Option("2", new IncorrectOptionScorer()));

        QuestionScorer scorer = new BooleanScorer();
        Question question1 = new GroupChoiceQuestion("Separar en numeros y letras", options, scorer, scoreExclusivity);
        Question question2 = new GroupChoiceQuestion("Separar en numeros y letras", options, scorer, scoreExclusivity);

        List<Option> player1Options = Arrays.asList(
                options.get(0));

        List<Option> player2Options = Arrays.asList(
                options.get(3),
                options.get(4));

        // When
        scoreExclusivity.activate();
        question1.selectOptions(player1Options);
        question2.selectOptions(player2Options);
        question1.score(player1);
        question2.score(player2);

        // Then
        Assert.assertEquals(player1.getPoints(), expectedPlayer1Points);
        Assert.assertEquals(player2.getPoints(), expectedPlayer2Points);
    }

    @Test
    public void testGroupChoiceQuadruplePointsWhenScoreExclusivityActivatedAndOnePlayerAnswerIncorrectly() throws InvalidSizeException {
        // Given
        ScoreExclusivity scoreExclusivity = new ScoreExclusivity();

        List<Option> options = Arrays.asList(
                new Option("A", new CorrectOptionScorer()),
                new Option("B", new CorrectOptionScorer()),
                new Option("C", new CorrectOptionScorer()),
                new Option("1", new IncorrectOptionScorer()),
                new Option("2", new IncorrectOptionScorer()));

        QuestionScorer scorer = new BooleanScorer();
        Question question1 = new GroupChoiceQuestion("Separar en numeros y letras", options, scorer, scoreExclusivity);
        Question question2 = new GroupChoiceQuestion("Separar en numeros y letras", options, scorer, scoreExclusivity);

        List<Option> player1Options = Arrays.asList(
                options.get(0));

        List<Option> player2Options = Arrays.asList(
                options.get(3),
                options.get(4));

        Player player1 = new Player();
        Player player2 = new Player();
        Integer expectedPlayer1Points = 0;
        Integer expectedPlayer2Points = 4;

        // When
        scoreExclusivity.activate();
        scoreExclusivity.activate();
        question1.selectOptions(player1Options);
        question2.selectOptions(player2Options);
        question1.score(player1);
        question2.score(player2);

        // Then
        Assert.assertEquals(player1.getPoints(), expectedPlayer1Points);
        Assert.assertEquals(player2.getPoints(), expectedPlayer2Points);
    }

    @Test
    public void testGroupChoiceDontModifyPointsWhenScoreExclusivityActivatedNoIncorrectAnswers() throws InvalidSizeException {
        // Given
        ScoreExclusivity scoreExclusivity = new ScoreExclusivity();

        List<Option> options = Arrays.asList(
                new Option("A", new CorrectOptionScorer()),
                new Option("B", new CorrectOptionScorer()),
                new Option("C", new CorrectOptionScorer()),
                new Option("1", new IncorrectOptionScorer()),
                new Option("2", new IncorrectOptionScorer()));

        QuestionScorer scorer = new BooleanScorer();
        Question question1 = new GroupChoiceQuestion("Separar en numeros y letras", options, scorer, scoreExclusivity);
        Question question2 = new GroupChoiceQuestion("Separar en numeros y letras", options, scorer, scoreExclusivity);

        List<Option> player1Options = Arrays.asList(
                options.get(3),
                options.get(4));

        List<Option> player2Options = Arrays.asList(
                options.get(3),
                options.get(4));

        Player player1 = new Player();
        Player player2 = new Player();
        Integer expectedPlayer1Points = 0;
        Integer expectedPlayer2Points = 0;

        // When
        scoreExclusivity.activate();
        question1.selectOptions(player1Options);
        question2.selectOptions(player2Options);
        question1.score(player1);
        question2.score(player2);

        // Then
        Assert.assertEquals(player1.getPoints(), expectedPlayer1Points);
        Assert.assertEquals(player2.getPoints(), expectedPlayer2Points);
    }
}
