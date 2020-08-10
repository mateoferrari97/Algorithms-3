package modelo;

public class Player {
    private String  name;
    private Integer points = 0;

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void gainAmountOfPoints(Integer points) {
        this.points += points;
        if (this.points < 0) {
            this.points = 0;
        }
    }

    public void setText(String name){
        this.name = name;
    }

    public String getText() {
        return this.name;
    }
}
