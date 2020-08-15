package modelo;

import exceptions.InvalidSizeException;
import modelo.options.*;
import modelo.questions.*;
import modelo.scorers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private final Integer MAX_PLAYERS = 2;
    private Player[] players = new Player[this.MAX_PLAYERS];
    private Integer currentPlayer = 0;

    private List<Round> rounds = new ArrayList<>();
    private Integer currentRound = 0;

    public void init() throws InvalidSizeException {
        createPlayers();
        createRounds();
    }

    public Player[] getPlayers() {
        return this.players;
    }

    public Player getNextPlayer() {
        if(currentPlayer == players.length){
            currentPlayer = 0;
            return null;
        }
        Player player = players[currentPlayer];
        currentPlayer++;
        return player;
    }

    public Round getNextRound() {
        if(currentRound == rounds.size()){
            currentRound = 0;
            return null;
        }
        Round round = rounds.get(currentRound);
        return round;
    }

    private void createPlayers() {
        for (int i = 0; i < this.MAX_PLAYERS; i++) {
            this.players[i] = new Player();
        }
    }

    private void createRounds() throws InvalidSizeException {
        List<Option> options = Arrays.asList(
                new Option("si", new CorrectOptionScorer()),
                new Option("no", new IncorrectOptionScorer()));
        QuestionScorer scorer = new BooleanScorer();
        Question question = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer);

        this.rounds.add(new Round(players, question));

        List<Option> newoptions = Arrays.asList(
                new Option("True", new CorrectOptionScorer()),
                new Option("False", new IncorrectOptionScorer()));
        QuestionScorer newscorer = new BooleanScorer();
        Question newquestion = new BooleanQuestion("El cafe es lo mejor", newoptions, newscorer);

        this.rounds.add(new Round(players, newquestion));

        List<Option> multipleChoice = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        QuestionScorer multipleChoiceScorer = new MultipleChoiceScorer();
        Question multipleChoiceQuestion = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", multipleChoice, multipleChoiceScorer);

        this.rounds.add(new Round(players, multipleChoiceQuestion));
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


    public void setNextRound() {
        currentRound++;
    }
}
