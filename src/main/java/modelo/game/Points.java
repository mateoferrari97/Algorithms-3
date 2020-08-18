package modelo.game;

public class Points {
    private Integer points;
    private Integer factor;

    public Points(){
        this.points = 0;
        this.factor = 1;
    }

    public void increasePoints() {
        this.points++;
    }

    public void givePointsToPlayer(Player player) {
        player.gainAmountOfPoints(this.points * this.factor);
        this.points = 0;
        this.factor = 1;
    }

    public void dontMultiplicate() {
        this.factor = 1;
    }

    public void decreasePoints() {
        this.points--;
    }

    public void changeScoreToZero() {
        this.factor = 0;
    }

    public void gainAPoint() { this.points = 1; }

    public Integer getPoints() {return (this.points * this.factor);}
  
    public void multiplicate(Integer multiplicator){
        this.factor = multiplicator;
    }

    public boolean equals(Points otherPoints) {return (this.points == otherPoints.getPoints());}

}
