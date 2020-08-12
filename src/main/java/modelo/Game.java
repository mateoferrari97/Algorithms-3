package modelo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private List<Round> rounds;
    private Player[] players = new Player[2];
    private List<Question> questions;

    public Game(List<Question> questions){
        rounds = new ArrayList<>();
        for(int i = 0; i <= 1; i++){
            this.players[i] = new Player();
        }
        for(Question aQuestion : questions){
            rounds.add(new Round(players, aQuestion));
        }
    }

    public void start(){
        for(Round aRound : rounds){
            aRound.start();
        }
    }
}
