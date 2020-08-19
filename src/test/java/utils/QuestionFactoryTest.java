package utils;

import exceptions.InvalidJsonRecognizerClassException;
import exceptions.InvalidSizeException;
import modelo.game.Points;
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

        Points points = new Points();
        List<Option> playerOptions = Arrays.asList(new Option("no", new IncorrectOptionScorer()));
        Integer expectedPoints = -1;

        // When
        question.selectOptions(playerOptions, points);

        // Then
        Assert.assertEquals(points.getPoints(), expectedPoints);


    }

    @Test
    public void testUnmarshalBooleanQuestionAndTestHowItWorks() throws InvalidJsonRecognizerClassException, InvalidSizeException {

        String jString = "{\"text\": \"vamos a aprobar algoritmos 3?\",\"type\": \"BooleanQuestion\",\"scorer\": \"BooleanScorer\",\"options\":[{\"text\": \"si\",\"optionScorer\": true},{\"text\": \"no\",\"optionScorer\": false}]}";
        QuestionFactory QFactory = new QuestionFactory();

        Question question = QFactory.unmarshal(jString);


        List<Option> playerOptions = Arrays.asList(new Option("si", new CorrectOptionScorer()));
        Integer expectedPoints = 1;

        Points points = new Points();

        // When
        question.selectOptions(playerOptions, points);

        // Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }

    @Test
    public void testUnmarshalMultipleChoiceQuestionWithPartialAndTestHowItWorks() throws InvalidJsonRecognizerClassException, InvalidSizeException {

        String jString = "{\"text\": \"elegir las opciones que dan como resultado igual a 4\",\"type\": \"MultipleChoiceQuestion\",\"scorer\": \"MultipleChoiceWithPartialScorer\",\"options\":[{\"text\": \"2 + 2\",\"optionScorer\": true},{\"text\": \"2 * 2\",\"optionScorer\": true},{\"text\": \"1 + 3\",\"optionScorer\": true},{\"text\": \"2^2\",\"optionScorer\": true},{\"text\": \"1 - 3\",\"optionScorer\": false}]}";
        QuestionFactory QFactory = new QuestionFactory();

        Question question = QFactory.unmarshal(jString);

        Points points = new Points();
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()));

        Integer expectedPoints = 4;

        // When
        question.selectOptions(playerOptions, points);


        // Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }

    @Test
    public void testUnmarshalMultipleChoiceQuestionWithPenaltyAndTestHowItWorks() throws InvalidJsonRecognizerClassException, InvalidSizeException {

        String jString = "{\"text\": \"elegir las opciones que dan como resultado igual a 4\",\"type\": \"MultipleChoiceQuestion\",\"scorer\": \"PenaltyScorer\",\"options\":[{\"text\": \"2 + 2\",\"optionScorer\": true},{\"text\": \"2 * 2\",\"optionScorer\": true},{\"text\": \"1 + 3\",\"optionScorer\": true},{\"text\": \"2^2\",\"optionScorer\": true},{\"text\": \"1 - 3\",\"optionScorer\": false}]}";

        QuestionFactory QFactory = new QuestionFactory();
        Question question = QFactory.unmarshal(jString);

        Points points = new Points();

        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()),
                new Option("5 / 1", new IncorrectOptionScorer()));

        Integer expectedPoints = 1;

        // When
        question.selectOptions(playerOptions, points);


        // Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }

    @Test
    public void testUnmarshalMultipleChoiceQuestionAndTestHowItWorks() throws InvalidJsonRecognizerClassException, InvalidSizeException {

        String jString = "{\"text\": \"elegir las opciones que dan como resultado igual a 4\",\"type\": \"MultipleChoiceQuestion\",\"scorer\": \"MultipleChoiceScorer\",\"options\":[{\"text\": \"2 + 2\",\"optionScorer\": true},{\"text\": \"2 * 2\",\"optionScorer\": true},{\"text\": \"1 + 3\",\"optionScorer\": true},{\"text\": \"2^2\",\"optionScorer\": true},{\"text\": \"1 - 3\",\"optionScorer\": false}]}";
        QuestionFactory QFactory = new QuestionFactory();
        Question question = QFactory.unmarshal(jString);

        Points points = new Points();
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()));

        Integer expectedPoints = 1;

        // When
        question.selectOptions(playerOptions, points);


        // Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }

    @Test
    public void testUnmarshalOrderedChoiceQuestionAndTestHowItWorks() throws InvalidJsonRecognizerClassException, InvalidSizeException {

        String jString = "{\"text\": \"ordene correctamente las siguientes opciones\",\"type\": \"OrderedChoiceQuestion\",\"scorer\": \"OrderedScorer\",\"options\": [{\"text\": \"Primero\",\"optionScorer\": true},{\"text\": \"Segundo\",\"optionScorer\": false},{\"text\": \"Tercero\",\"optionScorer\": false},{\"text\": \"Cuarto\",\"optionScorer\": false}]}";
        QuestionFactory QFactory = new QuestionFactory();
        Question question = QFactory.unmarshal(jString);

        Points points = new Points();
        List<Option> playerOptions = question.getOptions();
        Integer expectedPoints = 1;

        // When
        question.selectOptions(playerOptions, points);


        // Then
        Assert.assertEquals(points.getPoints(), expectedPoints);

    }

    @Test
    public void testUnmarshalArrayOfBooleanQuestionWithPenaltyAndBooleandTestHowItWorks() throws InvalidJsonRecognizerClassException, InvalidSizeException {

        String jString = "[{\"text\": \"vamos a aprobar algoritmos 3?\",\"type\": \"BooleanQuestion\",\"scorer\": \"PenaltyScorer\",\"options\":[{\"text\": \"si\",\"optionScorer\": true},{\"text\": \"no\",\"optionScorer\": false}]},{\"text\": \"vamos a aprobar algoritmos 3?\",\"type\": \"BooleanQuestion\",\"scorer\": \"BooleanScorer\",\"options\":[{\"text\": \"si\",\"optionScorer\": true},{\"text\": \"no\",\"optionScorer\": false}]}]";
        QuestionFactory QFactory = new QuestionFactory();

        List<Question> questions = QFactory.unmarshalArrayOfQuestions(jString);

        Question question1 = questions.get(0);

        List<Option> playerOptions1 = Arrays.asList(new Option("no", new IncorrectOptionScorer()));
        Integer expectedPoints1 = -1;

        Points points1 = new Points();

        // When
        question1.selectOptions(playerOptions1, points1);

        // Then
        Assert.assertEquals(points1.getPoints(), expectedPoints1);

        List<Option> playerOptions2 = Arrays.asList(new Option("si", new CorrectOptionScorer()));
        Integer expectedPoints2 = 1;

        Points points2 = new Points();

        // When
        question1.selectOptions(playerOptions2, points2);

        // Then
        Assert.assertEquals(points2.getPoints(), expectedPoints2);
    }
}