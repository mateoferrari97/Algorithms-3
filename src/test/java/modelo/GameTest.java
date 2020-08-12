package modelo;

import exceptions.InvalidSizeException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class GameTest {

        @Test
        public void testGameCompletesACycle() throws InvalidSizeException {
            // Given
            List<Option> booleanOptions = Arrays.asList(
                    new Option("si", new CorrectOptionScorer()),
                    new Option("no", new IncorrectOptionScorer()));
            QuestionScorer booleanScorer = new PenaltyScorer();
            Question booleanQuestion = new BooleanQuestion("vamos a aprobar algoritmos 3?", booleanOptions, booleanScorer);

            List<Option> multipleChoiceOptions = Arrays.asList(
                    new Option("2 + 2", new CorrectOptionScorer()),
                    new Option("2 * 2", new CorrectOptionScorer()),
                    new Option("1 + 3", new CorrectOptionScorer()),
                    new Option("2^2", new CorrectOptionScorer()),
                    new Option("1 - 3", new IncorrectOptionScorer()));

            QuestionScorer multipleChoiceScorer = new MultipleChoiceScorer();
            Question multipleChoiceQuestion = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", multipleChoiceOptions, multipleChoiceScorer);

            List<Question> questions = Arrays.asList(booleanQuestion,multipleChoiceQuestion);

            Game game = new Game(questions);

            // When


            // Then
            Assert.assertEquals(booleanQuestion, questions.get(0));
        }
}
