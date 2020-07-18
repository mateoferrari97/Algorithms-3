package modelo;

import java.lang.reflect.Array;

public class Question {
    private String textQuestion;
    private Boolean answer;
    private Player player;

    public Question(String textQuestion,Boolean answer){
        this.textQuestion = textQuestion;
        this.answer = answer;
    }

    public boolean answer(){
        return answer;
    }

    /*public void playersAnswer(Boolean[] answers, Player playerOne, Player playerTwo) {
        player = playerOne;
        for (Boolean aBoolean : answers) {
            if (aBoolean == answer) {
                player.gainPoint();
            }
            player = playerTwo;
        }
    }*/

    public void playersAnswer(Boolean[] answers, Player[] players) {
        int i = 0;
        for (Boolean aBoolean : answers) {
            if (aBoolean == answer) {
                players[i].gainPoint();
            }
            i++;
        }
    }

}
