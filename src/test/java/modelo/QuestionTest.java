package modelo;

import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QuestionTest {

    @Test
    public void testCreateATrueFalseResponseQuestionCanCheckTheCorrectAnswer(){
        String text = "vamos a aprobar algoritmos 3?";
        int correctOptions = 0;
        int incorrectOptions = 1;
        String[] optionsNames = {"True","False"};
        Question    question    =   new BooleanQuestion(text,correctOptions,incorrectOptions, optionsNames);

        Assert.assertEquals("True",question.getCorrectOption());
    }

    @Test
    public void testCreateATrueFalseResponseQuestionCanCheckAListOfAnswers(){
        //Given
        String text = "vamos a aprobar algoritmos 3?";
        int correctOptions = 0;
        int incorrectOptions = 1;
        String[] optionsNames = {"True","False"};
        Question    question    =   new BooleanQuestion(text,correctOptions,incorrectOptions, optionsNames);
        Player playerOne = new Player();
        Player playerTwo = new Player();
        Integer expectedPlayerOnePoints = 1;
        Integer expectedPlayerTwoPoints = 0;
        Integer[] answers = {0,1};
        Player[] players = {playerOne,playerTwo};

        //When
        question.compareAnswersFromPlayers(answers, players);

        //Then
        Assert.assertEquals(expectedPlayerOnePoints,playerOne.getPoints());
        Assert.assertEquals(expectedPlayerTwoPoints,playerTwo.getPoints());
    }

    @Test
    public void testCreateAClassicMultipleChoiceResponseQuestionCanAssignPointsToThePlayerThatChooseAllTheCorrectsOptions(){
        String text = "vamos a aprobar algoritmos 3?";
        Integer[] correctOptions = {0,3};
        Integer[] incorrectOptions = {1,2};
        String[] optionsNames = {"si","no","obvio que no", "obvio que si"};
        Question    question    =   new MultipleChoiceClassic(text,correctOptions,incorrectOptions, optionsNames);
        Player  playerOne   =   new Player();
        Player  playerTwo   =   new Player();
        Integer expectedPlayerOnePoints = 1;
        Integer expectedPlayerTwoPoints = 0;
        Integer[] playerOneAnswers = {0,3};
        Integer[] playerTwoAnswers = {0,2};
        Player[] players = {playerOne,playerTwo};


        question.compareAnswersFromPlayers(playerOneAnswers,playerTwoAnswers,players);

        Assert.assertEquals(expectedPlayerOnePoints,playerOne.getPoints());
        Assert.assertEquals(expectedPlayerTwoPoints,playerTwo.getPoints());
    }
}
