package modelo;


import exceptions.InvalidSizeException;
import exceptions.NoMoreConsumablesException;
import modelo.game.Player;
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
        question.selectOptions(playerOptions, this.points);
        question.score(player);

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
        question.selectOptions(playerOptions, this.points);
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

   @Test
    public void testGroupChoiceDoublePointsWhenScoreExclusivityActivatedAndOnePlayerAnswerIncorrectly() throws InvalidSizeException, NoMoreConsumablesException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("Primero", new CorrectOptionScorer()),
                new Option("Segundo", new IncorrectOptionScorer()),
                new Option("Tercero", new IncorrectOptionScorer()),
                new Option("Cuarto", new IncorrectOptionScorer()));

        List<Option> options2 = Arrays.asList(
                new Option("Primero", new CorrectOptionScorer()),
                new Option("Segundo", new IncorrectOptionScorer()),
                new Option("Tercero", new IncorrectOptionScorer()),
                new Option("Cuarto", new IncorrectOptionScorer()));

        QuestionScorer scorer = new OrderedScorer();
        Question question1= new OrderedChoiceQuestion("ordene correctamente las siguientes opciones", options, scorer);
        Question question2 = new OrderedChoiceQuestion("ordene correctamente las siguientes opciones", options2, scorer);

        List<Option> player1Options = Arrays.asList(
                options.get(0),
                options.get(1),
                options.get(3),
                options.get(2));

        List<Option> player2Options = Arrays.asList(
                options2.get(0),
                options2.get(1),
                options2.get(2),
                options2.get(3));

        Player player1 = new Player();
        Player player2 = new Player();
        Integer expectedPlayer1Points = 0;
        Integer expectedPlayer2Points = 2;

        // When
        question1.selectOptions(player1Options, this.points);
        question2.selectOptions(player2Options, this.points);
        question1.score(player1);
        question2.score(player2);

        // Then
        Assert.assertEquals(player1.getPoints(), expectedPlayer1Points);
        Assert.assertEquals(player2.getPoints(), expectedPlayer2Points);
    }

    @Test
    public void testGroupChoiceQuadruplePointsWhenScoreExclusivityActivatedAndOnePlayerAnswerIncorrectly() throws InvalidSizeException, NoMoreConsumablesException {
        // Given

        List<Option> options = Arrays.asList(
                new Option("Primero", new CorrectOptionScorer()),
                new Option("Segundo", new IncorrectOptionScorer()),
                new Option("Tercero", new IncorrectOptionScorer()),
                new Option("Cuarto", new IncorrectOptionScorer()));

        List<Option> options2 = Arrays.asList(
                new Option("Primero", new CorrectOptionScorer()),
                new Option("Segundo", new IncorrectOptionScorer()),
                new Option("Tercero", new IncorrectOptionScorer()),
                new Option("Cuarto", new IncorrectOptionScorer()));

        QuestionScorer scorer = new OrderedScorer();
        Question question1= new OrderedChoiceQuestion("ordene correctamente las siguientes opciones", options, scorer);
        Question question2 = new OrderedChoiceQuestion("ordene correctamente las siguientes opciones", options2, scorer);

        List<Option> player1Options = Arrays.asList(
                options.get(0),
                options.get(1),
                options.get(3),
                options.get(2));

        List<Option> player2Options = Arrays.asList(
                options2.get(0),
                options2.get(1),
                options2.get(2),
                options2.get(3));

        Player player1 = new Player();
        Player player2 = new Player();
        Integer expectedPlayer1Points = 0;
        Integer expectedPlayer2Points = 4;

        // When
        question1.selectOptions(player1Options, this.points);
        question2.selectOptions(player2Options, this.points);
        question1.score(player1);
        question2.score(player2);

        // Then
        Assert.assertEquals(player1.getPoints(), expectedPlayer1Points);
        Assert.assertEquals(player2.getPoints(), expectedPlayer2Points);
    }

    @Test
    public void testGroupChoiceDontModifyPointsWhenScoreExclusivityActivatedNoIncorrectAnswers() throws InvalidSizeException, NoMoreConsumablesException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("Primero", new CorrectOptionScorer()),
                new Option("Segundo", new IncorrectOptionScorer()),
                new Option("Tercero", new IncorrectOptionScorer()),
                new Option("Cuarto", new IncorrectOptionScorer()));

        List<Option> options2 = Arrays.asList(
                new Option("Primero", new CorrectOptionScorer()),
                new Option("Segundo", new IncorrectOptionScorer()),
                new Option("Tercero", new IncorrectOptionScorer()),
                new Option("Cuarto", new IncorrectOptionScorer()));

        QuestionScorer scorer = new OrderedScorer();
        Question question1= new OrderedChoiceQuestion("ordene correctamente las siguientes opciones", options, scorer);
        Question question2 = new OrderedChoiceQuestion("ordene correctamente las siguientes opciones", options2, scorer);

        List<Option> player1Options = Arrays.asList(
                options.get(0),
                options.get(1),
                options.get(2),
                options.get(3));

        List<Option> player2Options = Arrays.asList(
                options2.get(0),
                options2.get(1),
                options2.get(2),
                options2.get(3));

        Player player1 = new Player();
        Player player2 = new Player();
        Integer expectedPlayer1Points = 0;
        Integer expectedPlayer2Points = 0;

        // When
        question1.selectOptions(player1Options, this.points);
        question2.selectOptions(player2Options, this.points);
        question1.score(player1);
        question2.score(player2);

        // Then
        Assert.assertEquals(player1.getPoints(), expectedPlayer1Points);
        Assert.assertEquals(player2.getPoints(), expectedPlayer2Points);
    }
    @Test
    public void testGetCorrectOptionsFromOrderedChoiceQuestion() throws InvalidSizeException {
        // Given
        List<Option> opt = Arrays.asList(
                new Option("Primero", new CorrectOptionScorer()),
                new Option("Segundo", new IncorrectOptionScorer()));

        QuestionScorer scorer = new OrderedScorer();
        Question question = new OrderedChoiceQuestion("ordene correctamente las siguientes opciones", opt, scorer);

        List<Option> options = question.getCorrectOptions();
        Assert.assertEquals(opt,options);
    }
}
