package modelo;

import exceptions.InvalidSizeException;

import modelo.game.Player;
import modelo.options.CorrectOptionScorer;
import modelo.options.IncorrectOptionScorer;
import modelo.options.Option;
import modelo.questions.MultipleChoiceQuestion;
import modelo.questions.Question;
import modelo.scorers.MultipleChoiceScorer;
import modelo.scorers.QuestionScorer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class UnitaryQuestionTest {

    @Test
    public void getOptionsReturnsTheListOfOptionsThatAreInQuestion() throws InvalidSizeException {
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        Assert.assertEquals(options, question.getOptions());
    }

    @Test
    public void getCorretOptionsReturnAListOfTheQuestionCorrectOptions() throws InvalidSizeException {
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        List<Option> correctOptions = Arrays.asList(
                options.get(0),
                options.get(1),
                options.get(2),
                options.get(3));

        Assert.assertEquals(correctOptions, question.getCorrectOptions());
    }

    @Test
    public void getAnswerOptionsReturnAnArrayOfStringWithDeTextOfEachOption() throws InvalidSizeException {
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        String[] textOptions = new String[5];
        textOptions[0] = "2 + 2";
        textOptions[1] = "2 * 2";
        textOptions[2] = "1 + 3";
        textOptions[3] = "2^2";
        textOptions[4] = "1 - 3";

        Assert.assertArrayEquals(textOptions, question.getAnswerOptions());
    }

    @Test
    public void getType() throws InvalidSizeException {
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        String expectedType = "MultipleChoiceQuestion";

        Assert.assertEquals(expectedType, question.getType());
    }
}
