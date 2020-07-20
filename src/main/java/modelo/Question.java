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

    public void compareAnswersFromPlayers(Boolean[] answers, Player[] players) {
        int i = 0;
        for (Boolean aBoolean: answers) {
            if (aBoolean == answer) {
                players[i].gainPoint();
            }
            i++;
        }
    }
}
