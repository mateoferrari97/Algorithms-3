package modelo;

public class Player {
    private Integer points;
    private Boolean answer;

    public Player(Boolean answer){
        points = 0;
        this.answer = answer;
    }

    public Boolean answer(){
        return answer;
    }

    public Integer points(){
        return points;
    }

    public void gainPoint() {
        points++;
    }
}
