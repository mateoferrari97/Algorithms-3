package modelo;

import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QuestionTest {

    @Test
    public void testCreateATrueFalseResponseQuestionCanCheckTheCorrectAnswer(){
        String text = "vamos a aprobar algoritmos 3?";
        Boolean correctAnswer = true;
        Boolean failAnswer = false;
        Question question = new Question(text,true);

        Assert.assertEquals(question.getAnswer(),correctAnswer);
        Assert.assertNotEquals(question.getAnswer(),failAnswer);
    }

    @Test
    public void testCreateATrueFalseResponseQuestionCanCheckAListOfAnswers(){
        //Given
        String text = "vamos a aprobar algoritmos 3?";
        Question question = new Question(text,true);
        Player playerOne = new Player();
        Player playerTwo = new Player();
        Integer expectedPlayerOnePoints = 1;
        Integer expectedPlayerTwoPoints = 0;
        Boolean[] answers = {true,false};
        Player[] players = {playerOne,playerTwo};

        //When
        question.compareAnswersFromPlayers(answers, players);

        //Then
        Assert.assertEquals(playerOne.getPoints(),expectedPlayerOnePoints);
        Assert.assertEquals(playerTwo.getPoints(),expectedPlayerTwoPoints);
    }
}
