package modelo;

import java.util.ArrayList;

abstract class Question {
    protected String text;
    protected Option[] answers;
    protected ScoreBehavior score;

    abstract void compareAnswersFromPlayers(Integer[] answers, Player[] players);

    public ArrayList<String> getCorrectOption(){
        ArrayList<String> correctAnswers = new ArrayList();
        for(Option aOption : answers){
            String answer = aOption.getCorrectAnswer();
            if(answer != null){
                correctAnswers.add(answer);
            }
        }
        return correctAnswers;
    }

    public abstract void compareAnswersFromPlayers(Integer[] playerOneAnswers, Integer[] playerTwoAnswers, Player[] players);
}
