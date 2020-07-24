package modelo;

public class Player {
    private Integer points;


    public Player(){
        points = 0;
    }

    public Integer getPoints(){
        return points;
    }


    public CorrectOption answersCorrectly() {
        CorrectOption answer = new CorrectOption();
        return answer;
    }

    public IncorrectOption answersIncorrectly() {
        IncorrectOption answer = new IncorrectOption();
        return answer;
    }

    public void gainAmountOfPoints(Integer score) {
        points += score;
    }
}
