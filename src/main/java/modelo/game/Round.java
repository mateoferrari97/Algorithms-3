package modelo.game;

import modelo.questions.Question;

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
            this.turns.add(new Turn(player, question));
        }
    }

    public Turn getTurn(Game game) {
        if(currentTurn == turns.size() - 1){
            game.setNextRound();
            return turns.get(currentTurn);
        }
        Turn turn = turns.get(currentTurn);
        currentTurn++;
        return turn;
    }

    public Question getQuestion() {
        return this.question;
    }
}
