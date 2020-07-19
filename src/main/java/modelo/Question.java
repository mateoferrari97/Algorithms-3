package modelo;

public class Question {
    private String text;
    private Boolean answer;

    public Question(String text,Boolean answer){
        this.text = text;
        this.answer = answer;
    }

    public boolean getAnswer(){
        return answer;
    }

    public Integer[] compareAnswersFromPlayers(Boolean[] answers) {
        int i = 0;
        Integer[] points = new Integer[2];
        for (Boolean aBoolean : answers) {
            if(aBoolean == answer) {
                points[i] = 1;
            }
            else{
                points[i] = 0;
            }
            i++;
        }
        return points;
    }

    public void givePointsToPlayers(Integer[] playerPoints, Player[] players) {
        int i = 0;
        for (Integer aInteger : playerPoints) {
            players[i].gainAmountOfPoints(playerPoints[i]);
            i++;
        }
    }
}
