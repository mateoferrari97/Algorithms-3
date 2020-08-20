package modelo.game;

public class Points {
    private Integer multiplication;
    private Integer points;
    private Integer factor;

    public Points(){
        this.points = 0;
        this.factor = 1;
        this.multiplication = 1;
    }

    public void increasePoints() {
        this.points++;
    }

    public void givePointsToPlayer(Player player) {
        player.gainAmountOfPoints(this.points * this.factor * this.multiplication);
        this.points = 0;
        this.factor = 1;
        this.multiplication = 1;
    }

    public void decreasePoints() {
        this.points--;
        if (this.points < 0) this.points = 0;
    }

    public void changeScoreToZero() {
        this.factor = 0;
    }

    public void gainAPoint() { this.points = 1; }

    public Integer getPoints() {return (this.points * this.factor * this.multiplication);}
  
    public void multiplicate(Integer multiplicator){
        this.multiplication = multiplicator;
    }

    public boolean equals(Points otherPoints) {return (this.points == otherPoints.getPoints());}

    public Integer getPointsWithoutMultiplicate() {
        return this.points * this.factor;
    }
}
