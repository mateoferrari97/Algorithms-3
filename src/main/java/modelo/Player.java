package modelo;

public class Player {
    private Integer points;


    public Player(){
        points = 0;
    }

    public Integer getPoints(){
        return points;
    }

    public void gainPoint() {
        points++;
    }
}
