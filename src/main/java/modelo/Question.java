package modelo;

import java.util.List;

abstract class Question {
    protected String text;
    protected QuestionScorer scorer;
    protected List<Option> options;
    protected Points points;
    protected Multiplicator multiplicator;

    public void score(Player player) {}

    public void multiplicate(Integer factor){
        this.multiplicator.multiplicate(this.points, factor);
    }
}

