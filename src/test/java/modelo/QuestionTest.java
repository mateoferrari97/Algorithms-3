package modelo;

import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QuestionTest {

    @Test
    public void testCreateATrueFalseResponseQuestionCanCheckTheCorrectAnswer(){
        String text = "vamos a aprobar algoritmos 3?";
        CorrectState correctOption = new CorrectState();
        IncorrectState incorrectOption = new IncorrectState();
        Question question = new BooleanQuestion(text, correctOption, incorrectOption);

        Assert.assertEquals(question.getCorrectOption(),correctOption);
    }

    @Test
    public void testCreateATrueFalseResponseQuestionCanCheckAListOfAnswers(){
        //Given
        String text = "vamos a aprobar algoritmos 3?";
        CorrectState correctOption = new CorrectState();
        IncorrectState incorrectOption = new IncorrectState();
        Question question = new BooleanQuestion(text, correctOption, incorrectOption);
        Player playerOne = new Player();
        Player playerTwo = new Player();
        Integer expectedPlayerOnePoints = 1;
        Integer expectedPlayerTwoPoints = 0;
        Integer[] answers = {0,1};
        Player[] players = {playerOne,playerTwo};

        //When
        // lugar para mejora, se que answers no es tan necesario si mando una lista de players pero es para respetar el test y por ahora es medio hardcodeado la respuesta de los players
        question.compareAnswersFromPlayers(answers, players);

        //Then
        Assert.assertEquals(playerOne.getPoints(),expectedPlayerOnePoints);
        Assert.assertEquals(playerTwo.getPoints(),expectedPlayerTwoPoints);
    }
}
