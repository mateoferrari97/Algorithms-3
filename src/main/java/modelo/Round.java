package modelo;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private Integer currentTurn = 0;
    private List<Turn> turns = new ArrayList<>();

    private List<Player> players = new ArrayList<>();
    private Question question;

    public Round(Player[] players, Question question) {
        this.question = question;

        for(Player player : players){
            this.players.add(player);
            this.turns.add(new Turn(player));
        }
    }
/*
    public void start() {
        this.turns.get(currentTurn).start();
    }

    public void next(Game game) {
        Integer nextTurn = this.currentTurn + 1;
        if (nextTurn >= this.turns.size()) {
            game.next();
        } else {
            this.turns.get(nextTurn).next();
            this.currentTurn++;
        }
    }*/
}
