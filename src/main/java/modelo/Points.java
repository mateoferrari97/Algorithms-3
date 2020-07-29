package modelo;

public class Points {
    private Integer points;

    public Points(){
        points = 0;
    }

    public void increasePoints() {
        points++;
    }

    public void givePointsToPlayer(Player player) {
        player.gainAmountOfPoints(points);
    }

    public void decreasePoints() {
        points--;
    }
}
