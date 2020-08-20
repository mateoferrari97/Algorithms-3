package modelo.game;

public class Player {
    private String  name;
    private Integer points = 0;
    private Integer consumablesAmount = 2;
    private State state;

    public Player() {}

    public Player(String name) {
        this.setName(name);
    }

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

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void changeStateToDraw() {
        this.state = new Tied();
    }

    public void changeStateToWinner() {
        this.state = new Winner();
    }

    public void changeStateToLoser() {
        this.state = new Loser();
    }

    public boolean isWinner() {
        return this.state.isWinner();
    }

    public boolean isTied() {
        return this.state.isTied();
    }
}
