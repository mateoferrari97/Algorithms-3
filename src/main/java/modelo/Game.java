package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private Integer currentPlayer = 0;
    private final Integer MAX_PLAYERS = 2;
    private Player[] players = new Player[this.MAX_PLAYERS];

    private List<Round> rounds = new ArrayList<>();
    private Integer currentRound = 0;

    public void init() {
        createPlayers();
        createRounds();
    }

    public Player[] getPlayers() {
        return this.players;
    }

    public Player getNextPlayer() {
        if (this.currentPlayer < MAX_PLAYERS) {
            Integer currentPlayer = this.currentPlayer;
            this.currentPlayer++;
            return this.players[currentPlayer];
        }

        this.currentPlayer = 0;
    }

    private void createPlayers() {
        for (int i = 0; i < this.MAX_PLAYERS; i++) {
            this.players[i] = new Player();
        }
    }

    private void createRounds() {
        List<Option> options = Arrays.asList(
                new Option("si", new CorrectOptionScorer()),
                new Option("no", new IncorrectOptionScorer()));
        QuestionScorer scorer = new BooleanScorer();
        Question question = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer);

        this.rounds.add(new Round(players, question));
    }

/*
    private void start() {
        this.rounds.get(this.currentRound).start();
    }

    private void next() {
        Integer nextRound = this.currentRound + 1;
        if (nextRound >= this.rounds.size()) {
            this.finish();
        }

        this.rounds.get(nextRound).next(this);
        this.currentRound++;
    }
*/

    private void finish() {
        System.out.println("Game finished");
    }
}
