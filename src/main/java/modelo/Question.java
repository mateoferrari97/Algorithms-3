package modelo;

import java.util.List;

abstract class Question {
    protected String text;
    protected QuestionScorer scorer;
    protected List<Option> options;
    protected Points points;

    public void score(Player player) {

    }
}
