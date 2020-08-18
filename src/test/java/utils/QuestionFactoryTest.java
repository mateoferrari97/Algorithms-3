package utils;

import exceptions.InvalidJsonRecognizerClassException;
import exceptions.InvalidSizeException;
import modelo.game.Player;
import modelo.options.CorrectOptionScorer;
import modelo.options.IncorrectOptionScorer;
import modelo.options.Option;
import modelo.questions.Question;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class QuestionFactoryTest {

    @Test
    public void testUnmarshalBooleanQuestionWithPenaltyAndTestHowItWorks() throws InvalidJsonRecognizerClassException, InvalidSizeException {

        String jString = "{\"text\": \"vamos a aprobar algoritmos 3?\",\"type\": \"BooleanQuestion\",\"scorer\": \"PenaltyScorer\",\"options\":[{\"text\": \"si\",\"optionScorer\": true},{\"text\": \"no\",\"optionScorer\": false}]}";
        QuestionFactory QFactory = new QuestionFactory();

        Question question = QFactory.unmarshal(jString);

        Player player = new Player();
        player.setPoints(5);
        List<Option> playerOptions = Arrays.asList(new Option("si", new CorrectOptionScorer()));
        Integer expectedPlayerPoints = 6;

        // When
        question.selectOptions(playerOptions, this.points);
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);


    }

    @Test
    public void testUnmarshalBooleanQuestionAndTestHowItWorks() throws InvalidJsonRecognizerClassException, InvalidSizeException {

        String jString = "{\"text\": \"vamos a aprobar algoritmos 3?\",\"type\": \"BooleanQuestion\",\"scorer\": \"BooleanScorer\",\"options\":[{\"text\": \"si\",\"optionScorer\": true},{\"text\": \"no\",\"optionScorer\": false}]}";
        QuestionFactory QFactory = new QuestionFactory();

        Question question = QFactory.unmarshal(jString);


        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(new Option("si", new CorrectOptionScorer()));
        Integer expectedPlayerPoints = 1;

        // When
        question.selectOptions(playerOptions, this.points);
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testUnmarshalMultipleChoiceQuestionWithPartialAndTestHowItWorks() throws InvalidJsonRecognizerClassException, InvalidSizeException {

        String jString = "{\"text\": \"elegir las opciones que dan como resultado igual a 4\",\"type\": \"MultipleChoiceQuestion\",\"scorer\": \"MultipleChoiceWithPartialScorer\",\"options\":[{\"text\": \"2 + 2\",\"optionScorer\": true},{\"text\": \"2 * 2\",\"optionScorer\": true},{\"text\": \"1 + 3\",\"optionScorer\": true},{\"text\": \"2^2\",\"optionScorer\": true},{\"text\": \"1 - 3\",\"optionScorer\": false}]}";
        QuestionFactory QFactory = new QuestionFactory();

        Question question = QFactory.unmarshal(jString);

        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));
        Integer expectedPlayerPoints = 0;

        // When
        question.selectOptions(playerOptions, this.points);
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testUnmarshalMultipleChoiceQuestionWithPenaltyAndTestHowItWorks() throws InvalidJsonRecognizerClassException, InvalidSizeException {

        String jString = "{\"text\": \"elegir las opciones que dan como resultado igual a 4\",\"type\": \"MultipleChoiceQuestion\",\"scorer\": \"PenaltyScorer\",\"options\":[{\"text\": \"2 + 2\",\"optionScorer\": true},{\"text\": \"2 * 2\",\"optionScorer\": true},{\"text\": \"1 + 3\",\"optionScorer\": true},{\"text\": \"2^2\",\"optionScorer\": true},{\"text\": \"1 - 3\",\"optionScorer\": false}]}";

        QuestionFactory QFactory = new QuestionFactory();
        Question question = QFactory.unmarshal(jString);

        Player player = new Player();
        player.setPoints(7);
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()),
                new Option("5 / 1", new IncorrectOptionScorer()));
        Integer expectedPlayerPoints = 8;

        // When
        question.selectOptions(playerOptions, this.points);
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);

    }

    @Test
    public void testUnmarshalMultipleChoiceQuestionAndTestHowItWorks() throws InvalidJsonRecognizerClassException, InvalidSizeException {

        String jString = "{\"text\": \"elegir las opciones que dan como resultado igual a 4\",\"type\": \"MultipleChoiceQuestion\",\"scorer\": \"MultipleChoiceScorer\",\"options\":[{\"text\": \"2 + 2\",\"optionScorer\": true},{\"text\": \"2 * 2\",\"optionScorer\": true},{\"text\": \"1 + 3\",\"optionScorer\": true},{\"text\": \"2^2\",\"optionScorer\": true},{\"text\": \"1 - 3\",\"optionScorer\": false}]}";
        QuestionFactory QFactory = new QuestionFactory();
        Question question = QFactory.unmarshal(jString);


        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()));
        Integer expectedPlayerPoints = 1;

        // When
        question.selectOptions(playerOptions, this.points);
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);

    }

    @Test
    public void testUnmarshalOrderedChoiceQuestionAndTestHowItWorks() throws InvalidJsonRecognizerClassException, InvalidSizeException {

        String jString = "{\"text\": \"ordene correctamente las siguientes opciones\",\"type\": \"OrderedChoiceQuestion\",\"scorer\": \"OrderedScorer\",\"options\": [{\"text\": \"Primero\",\"optionScorer\": true},{\"text\": \"Segundo\",\"optionScorer\": false},{\"text\": \"Tercero\",\"optionScorer\": false},{\"text\": \"Cuarto\",\"optionScorer\": false}]}";
        QuestionFactory QFactory = new QuestionFactory();
        Question question = QFactory.unmarshal(jString);


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
        question.selectOptions(playerOptions, this.points);
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);

    }

    @Test
    public void testUnmarshalArrayOfBooleanQuestionWithPenaltyAndBooleandTestHowItWorks() throws InvalidJsonRecognizerClassException, InvalidSizeException {

        String jString = "[{\"text\": \"vamos a aprobar algoritmos 3?\",\"type\": \"BooleanQuestion\",\"scorer\": \"PenaltyScorer\",\"options\":[{\"text\": \"si\",\"optionScorer\": true},{\"text\": \"no\",\"optionScorer\": false}]},{\"text\": \"vamos a aprobar algoritmos 3?\",\"type\": \"BooleanQuestion\",\"scorer\": \"BooleanScorer\",\"options\":[{\"text\": \"si\",\"optionScorer\": true},{\"text\": \"no\",\"optionScorer\": false}]}]";
        QuestionFactory QFactory = new QuestionFactory();

        List<Question> questions = QFactory.unmarshalArrayOfQuestions(jString);

        Question question1 = questions.get(0);
        Player player1 = new Player();
        player1.setPoints(5);
        List<Option> playerOptions1 = Arrays.asList(new Option("si", new CorrectOptionScorer()));
        Integer expectedPlayer1Points = 6;

        // When
        question1.selectOptions(playerOptions1, this.points);
        question1.score(player1);

        // Then
        Assert.assertEquals(player1.getPoints(), expectedPlayer1Points);

        Question question2 = questions.get(1);
        Player player2 = new Player();
        List<Option> playerOptions2 = Arrays.asList(new Option("si", new CorrectOptionScorer()));
        Integer expectedPlayer2Points = 1;

        // When
        question2.selectOptions(playerOptions2, this.points);
        question2.score(player2);

        // Then
        Assert.assertEquals(player2.getPoints(), expectedPlayer2Points);
    }
}