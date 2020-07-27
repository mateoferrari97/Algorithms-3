package modelo;

public class Player {
    private Integer points = 0;

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void increasePoints() {
        this.points++;
    }

    public void decreasePoints() {
        if (this.points > 0) {
            this.points--;
        }
    }
}
