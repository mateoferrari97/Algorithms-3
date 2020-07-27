package modelo;

public class Player {
    private Integer points;

    public Player(){
        points = 0;
    }

    public Integer getPoints(){
        return points;
    }

    public void increasePoints(Integer points) {
        this.points += points;
    }

    public void decreasePoints(Integer points) {
        if (this.points - points <= 0) {
            this.points = 0;
            return;
        }

        this.points -= points;
    }
}
