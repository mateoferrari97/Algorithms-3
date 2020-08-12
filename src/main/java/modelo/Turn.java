package modelo;

import java.util.ArrayList;
import java.util.List;

public class Turn {
    private Player player;
    private Question question;
    private List<Option> answers = new ArrayList<>();

    public Turn(Player aPlayer) {
        this.player = aPlayer;
    }

    public void finish() {

    }
}
