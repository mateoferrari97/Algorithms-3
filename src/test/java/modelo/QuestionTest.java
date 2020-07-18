package modelo;

import modelo.Question;
import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Array;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QuestionTest {

    @Test
    public void testCreateATrueFalseResponseQuestionCanCheckTheCorrectAnswer(){
        String text = "aprobaste algoritmos 3?";
        Boolean correctAnswer = false;
        Boolean failAnswer = true;
        Question question = new Question(text,false);

        Assert.assertEquals(question.answer(),correctAnswer);
        Assert.assertNotEquals(question.answer(),failAnswer);
    }

    @Test
    public void testCreateATrueFalseResponseQuestionCanCheckAListOfAnswers(){
        //Question
        String text = "aprobaste algoritmos 3?";
        Question question = new Question(text,false);
        //Players
        Player playerOne = new Player(false);
        Player playerTwo = new Player(true);
        Integer winnerPlayerPoints = 1;
        Integer loserPlayerPoints = 0;
        Boolean[] answers = {playerOne.answer(),playerTwo.answer()};
        Player[] players = {playerOne,playerTwo};

        question.playersAnswer(answers,players);

        Assert.assertEquals(playerOne.points(),winnerPlayerPoints);
        Assert.assertEquals(playerTwo.points(),loserPlayerPoints);
    }
}
