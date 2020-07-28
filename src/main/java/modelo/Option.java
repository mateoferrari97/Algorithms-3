package modelo;

import javax.swing.*;

public class Option {
    private String  text;
    private State state;

    public  Option(State state, String text){
        this.state = state;
        this.text = text;
    }

    public void setState(modelo.State answer) {
        state = answer;
    }

    public String getCorrectAnswer() {
        return state.getCorrectAnswer(text);
    }

    public void changeScore(ScoreBehavior score) {
        state.changeScore(score);
    }
}
