package modelo;

public class Player {
    private Integer points;
    private Boolean answer;

    public Player(Boolean answer){
        points = 0;
        this.answer = answer;
    }

    public Boolean getAnswer(){
        return answer;
    }

    public Integer getPoints(){
        return points;
    }

    public void gainAmountOfPoints(Integer points) {
        this.points += points;
    }
}
